package protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;
import provider.LocalRegister;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest request, HttpServletResponse response) {

        //处理请求
        try {
            InputStream inputStream  = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);

            Invocation invocation = (Invocation) ois.readObject();

            Class implClass = LocalRegister.get(invocation.getInterfaceName());

            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());

            String result = (String)method.invoke(implClass.newInstance(), invocation.getParams());

            IOUtils.write(result,response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
