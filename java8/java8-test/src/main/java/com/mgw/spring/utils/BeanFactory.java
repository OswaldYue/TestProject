package com.mgw.spring.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class BeanFactory {

    Map<String,Object> map = new HashMap<>() ;

    public BeanFactory(String xml) {

        parseXml(xml);

    }

    public void parseXml(String xml) {

        String path = this.getClass().getResource("/").getPath();

        File file = new File(path,xml);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            String defaultAutowireAttributeValue = rootElement.attributeValue("default-autowire");

            boolean flage = false;
            if (defaultAutowireAttributeValue != null) {
                flage = true;
            }

            List<Element> allChildElements = rootElement.elements();
            Iterator<Element> iterator = allChildElements.iterator();
            while (iterator.hasNext()) {

                Element element = iterator.next();
                String beanName = element.attributeValue("id");
                String className = element.attributeValue("class");

                Class<?> clazz = Class.forName(className);
                Object instance = null;

                /*
                * 判断是否有依赖 (判断是否有property,或者判断类是否有属性) 有则注入
                * */
                Field[] declaredFields = clazz.getDeclaredFields();
                Map<String,Field> fieldMap = new HashMap<>();
                for (Field declaredField : declaredFields) {
                    fieldMap.put(declaredField.getName(),declaredField);

                }

                List<Element> propertyElements = element.elements("property");
                if (propertyElements != null && !propertyElements.isEmpty()) {

                    instance = clazz.newInstance();

                    Iterator<Element> propertyIterator = propertyElements.iterator();
                    while (propertyIterator.hasNext()) {

                        Element propertyElement = propertyIterator.next();
                        String name = propertyElement.attributeValue("name");
                        String ref = propertyElement.attributeValue("ref");

                        if (fieldMap.containsKey(name)) {
                            Field field = fieldMap.get(name);
                            field.setAccessible(true);
                            field.set(instance, map.get(ref));
                        } else {
                            throw new RuntimeException("没有此属性" + name);
                        }


                    }
                }
                int count = 0;
                Object injectObject = null;
                if (flage) {
                    if (instance == null) {

                        instance = clazz.newInstance();

                        if (defaultAutowireAttributeValue.equals("byType")) {
                            //byType
                            Field[] fields = clazz.getDeclaredFields();
                            for (Field field : fields) {
                                //属性的类型
                                Class<?> type = field.getType();
                                //遍历整个map中的所有对象判断类型,相同的注入
                                for (String key : map.keySet()) {
                                    injectObject = map.get(key);
                                    Class<?> temp = injectObject.getClass().getInterfaces()[0];
                                    if (temp.getName().equals(type.getName())) {

                                        //可能找到多个,多个时抛异常
                                        count++;
                                    }
                                }
                                if (count > 1) {
                                    //找到多个
                                    throw new RuntimeException("需要一个" + type.getName() + ",但是找到多个");
                                } else if (count == 0) {
                                    //没找到
                                    throw new RuntimeException("没有此属性" + field.getName());
                                } else {

                                    field.setAccessible(true);
                                    field.set(instance, injectObject);
                                }
                            }

                        } else {
                            //byName

                        }
                    }
                }

                map.put(beanName,instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }

    public Object getBean(String beanName) {

        if (map.containsKey(beanName)) {
            return map.get(beanName);
        }else {
            return null;
        }
    }


}
