package framework;

import protocol.http.HttpProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ProtocolFactory {

    public static Protocol getProtocol() {

        //使用java的spi机制实现多协议的切换
        ServiceLoader<Protocol> serviceLoader =  ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();

        //使用系统参数实现多协议的切换  -DprotocolName=HTTP
//        String protocolName = System.getProperty("protocolName");
//
//        if (protocolName == null || protocolName.equals("")) {
//
//            protocolName = ProtocolEnum.HTTP.name();
//        }
//        ProtocolEnum protocolEnum = ProtocolEnum.getEnumByName(protocolName);
//        switch (protocolEnum) {
//            case DUBBO:
//                return null;
//            default:
//                return new HttpProtocol();
//        }
    }

}
