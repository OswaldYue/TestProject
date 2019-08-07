package com.mgw.proxy.proxyCoustom.utils;


import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义代理类
 *
 * 增加动态执行代理的方法
 *
 * */
public class ProxyUtil1 {

    /**
     * 生成java文件
     * 编译
     * 加载类
     * 使用构造方法创建一个代理对象
     *
     * */
    public static Object newProxyInstance(Class targetInterface, ProxyInvocationHandler h) throws Exception{

        //Class targetInterface = target.getClass().getInterfaces()[0];

        String handlerName = ProxyInvocationHandler.class.getSimpleName();

        Method methods[]  = targetInterface.getDeclaredMethods();

        String line = "\n";
        String tab = "\t";
        String targetInterfaceFullName = targetInterface.getName();

        String content = "";
        String packageContent = "package com.mgw.proxy.proxyCoustom.utils;" + line;

        String clazzFirstLineContent = "public class $Proxy implements "+targetInterfaceFullName+" {" + line;
        String filedContent = tab + "private "+ ProxyInvocationHandler.class.getName() +" h;"+line;
//        String filedContent = tab + "private "+targetInterfaceFullName+" target;"+line;
        String constructorContent = tab + "public $Proxy"+ "(" + h.getClass().getName()+ " h) {" + line
                + tab + tab + "this.h = h;" + line
                + tab + "}" + line;

        String methodContent = "";

        for (Method method : methods) {

            String returnTypeFullName = method.getReturnType().getName();
            String methodName = method.getName();
            Class[] args = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            int flag = 0;
            for (Class arg : args) {
                String temp = arg.getName();
                argsContent += temp + " p" + flag + ",";
                paramsContent += "p" + flag + ", ";
                flag++;
            }
            if (argsContent.length() > 0) {
                argsContent=argsContent.substring(0,argsContent.lastIndexOf(","));
                paramsContent=paramsContent.substring(0,paramsContent.lastIndexOf(","));
            }

            methodContent += tab + "public " + returnTypeFullName + " " + methodName + "(" + argsContent + ") {" + line
                    + tab + tab + "try {" + line
                    + tab + tab + tab + "java.lang.reflect.Method method = Class.forName(\"" + targetInterface.getName() + "\").getDeclaredMethod(\"" + methodName + "\");" + line
                    + tab + tab + tab +"h.invoke(method);" + line
                    + tab + tab + "}catch(java.lang.Exception e) {" + line
                    + tab + tab + "}" + line;
            methodContent += tab + "}" + line;

        }
        content = packageContent  + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";
//        content = packageContent  + clazzFirstLineContent + filedContent  + methodContent + "}";

        File pathFile = new File("D:\\com\\mgw\\proxy\\proxyCoustom\\utils");
        File file = new File(pathFile,"\\$Proxy.java");
        pathFile.mkdirs();

        if (file.exists()) {
            file.createNewFile();
        }
        //生成代理的java文件
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(content.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();

        //编译
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = systemJavaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(null, fileManager, null, null, null, javaFileObjects);
        task.call();
        fileManager.close();

        //加载类
        URL[] urls = new URL[] {new URL("file:D:\\\\")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class<?> clazz = urlClassLoader.loadClass("com.mgw.proxy.proxyCoustom.utils.$Proxy");

//        return clazz.newInstance();
        //使用构造方法创建一个对象
//        Constructor<?> constructor = clazz.getConstructor(targetInterface);
//        Object proxy = constructor.newInstance(target);

        //善后删除临时创建的java源文件以及class文件
//        deleteFileOrDirectory(pathFile);
//        if (file.exists()) {
//            file.delete();
//        }
//        if (pathnFile.exists()) {
//            pathnFile.delete();
//        }

//        return proxy;

    }

    public static void deleteFileOrDirectory(File file) {
        if (null != file) {

            if (!file.exists()) {
                return;
            }

            int i;
            // file 是文件
            if (file.isFile()) {
                boolean result = file.delete();
                // 限制循环次数，避免死循环
                for(i = 0; !result && i++ < 10; result = file.delete()) {
                    // 垃圾回收
                    System.gc();
                }

                return;
            }

            // file 是目录
            File[] files = file.listFiles();
            if (null != files) {
                for(i = 0; i < files.length; ++i) {
                    deleteFileOrDirectory(files[i]);
                }
            }

            file.delete();
        }

    }
}
