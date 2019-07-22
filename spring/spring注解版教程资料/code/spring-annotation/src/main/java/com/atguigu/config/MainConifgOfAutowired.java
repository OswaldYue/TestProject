package com.atguigu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.dao.BookDao;


/**
 * �Զ�װ��;
 * 		Spring��������ע�루DI������ɶ�IOC�������и��������������ϵ��ֵ��
 * 
 * 1����@Autowired���Զ�ע�룺
 * 		1����Ĭ�����Ȱ�������ȥ�������Ҷ�Ӧ�����:applicationContext.getBean(BookDao.class);�ҵ��͸�ֵ
 * 		2��������ҵ������ͬ���͵�������ٽ����Ե�������Ϊ�����idȥ�����в���
 * 							applicationContext.getBean("bookDao")
 * 		3����@Qualifier("bookDao")��ʹ��@Qualifierָ����Ҫװ��������id��������ʹ��������
 * 		4�����Զ�װ��Ĭ��һ��Ҫ�����Ը�ֵ�ã�û�оͻᱨ��
 * 			����ʹ��@Autowired(required=false);
 * 		5����@Primary����Spring�����Զ�װ���ʱ��Ĭ��ʹ����ѡ��bean��
 * 				Ҳ���Լ���ʹ��@Qualifierָ����Ҫװ���bean������
 * 		BookService{
 * 			@Autowired
 * 			BookDao  bookDao;
 * 		}
 * 
 * 2����Spring��֧��ʹ��@Resource(JSR250)��@Inject(JSR330)[java�淶��ע��]
 * 		@Resource:
 * 			���Ժ�@Autowiredһ��ʵ���Զ�װ�书�ܣ�Ĭ���ǰ���������ƽ���װ��ģ�
 * 			û����֧��@Primary����û��֧��@Autowired��reqiured=false��;
 * 		@Inject:
 * 			��Ҫ����javax.inject�İ�����Autowired�Ĺ���һ����û��required=false�Ĺ��ܣ�
 *  @Autowired:Spring����ģ� @Resource��@Inject����java�淶
 * 	
 * AutowiredAnnotationBeanPostProcessor:��������Զ�װ�书�ܣ�		
 * 
 * 3���� @Autowired:�����������������������ԣ����Ǵ������л�ȡ���������ֵ
 * 		1����[��ע�ڷ���λ��]��@Bean+���������������������л�ȡ;Ĭ�ϲ�д@AutowiredЧ����һ���ģ������Զ�װ��
 * 		2����[���ڹ�������]��������ֻ��һ���вι�����������вι�������@Autowired����ʡ�ԣ�����λ�õ�������ǿ����Զ��������л�ȡ
 * 		3�������ڲ���λ�ã�
 * 
 * 4�����Զ��������Ҫʹ��Spring�����ײ��һЩ�����ApplicationContext��BeanFactory��xxx����
 * 		�Զ������ʵ��xxxAware���ڴ��������ʱ�򣬻���ýӿڹ涨�ķ���ע����������Aware��
 * 		��Spring�ײ�һЩ���ע�뵽�Զ����Bean�У�
 * 		xxxAware������ʹ��xxxProcessor��
 * 			ApplicationContextAware==��ApplicationContextAwareProcessor��
 * 	
 * 		
 * @author lfy
 *
 */
@Configuration
@ComponentScan({"com.atguigu.service","com.atguigu.dao",
	"com.atguigu.controller","com.atguigu.bean"})
public class MainConifgOfAutowired {
	
	@Primary
	@Bean("bookDao2")
	public BookDao bookDao(){
		BookDao bookDao = new BookDao();
		bookDao.setLable("2");
		return bookDao;
	}
	
	/**
	 * @Bean��ע�ķ������������ʱ�򣬷���������ֵ�������л�ȡ
	 * @param car
	 * @return
	 */
	@Bean
	public Color color(Car car){
		Color color = new Color();
		color.setCar(car);
		return color;
	}
	

}
