package framework;

import protocol.http.HttpClient;
import protocol.http.HttpProtocol;
import provider.api.HelloService;
import register.RomoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static <T> T getProxy(final Class interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//                HttpClient httpClient = new HttpClient();
//                Protocol protocol = new HttpProtocol();
                Protocol protocol = ProtocolFactory.getProtocol();

                Invocation invocation = new Invocation(
                        interfaceClass.getName(),
                        method.getName(),
                        method.getParameterTypes(),
                        args);

                URL url = RomoteMapRegister.random(interfaceClass.getName());


//                String result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                String result = protocol.send(url, invocation);
                return result;
            }
        });


    }

}
