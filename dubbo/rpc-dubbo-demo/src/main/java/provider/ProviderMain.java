package provider;

import framework.Protocol;
import framework.ProtocolFactory;
import framework.URL;
import protocol.http.HttpProtocol;
import protocol.http.HttpServer;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.RomoteMapRegister;

public class ProviderMain {

    public static void main(String[] args) {

        URL url = new URL("localhost", 8080);

        //1.本地注册
        //{接口:实现类}
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        //2.注册中心注册
        //{}
        RomoteMapRegister.regist(HelloService.class.getName(),url);

        //启动tomcat
//        HttpServer httpServer = new HttpServer();
//        httpServer.start("localhost",8080);
//        Protocol protocol = new HttpProtocol();
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
