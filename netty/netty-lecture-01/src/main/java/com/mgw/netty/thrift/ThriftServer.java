package com.mgw.netty.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;


/**
 * 案例:thrift的rpc调用
 *
 * 此案例的主要目的在于，告诫自己不要一想到传输就用http，实际在分布式项目中，内网之间的服务调用，
 * 实际rpc的效率要远远高于http
 *
 * */
public class ThriftServer {

    public static void main(String[] args) throws Exception{


        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8899);

        THsHaServer.Args arg = new THsHaServer.Args(serverSocket).
                minWorkerThreads(2).maxWorkerThreads(4);

        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);

        System.out.println("Thrift Server Startred");

        server.serve();
    }

}
