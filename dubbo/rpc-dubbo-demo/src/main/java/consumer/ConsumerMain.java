package consumer;

import framework.Invocation;
import framework.ProxyFactory;
import protocol.http.HttpClient;
import provider.api.HelloService;

public class ConsumerMain {

    public static void main(String[] args) {

//        HttpClient httpClient = new HttpClient();
//
//        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello",
//                new Class[]{String.class}, new Object[]{"mgw"});
//
//        String result = httpClient.send("localhost", 8080, invocation);
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("OswaldYue");

        System.out.println(result);

    }

}
