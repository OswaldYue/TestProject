package com.mgw.netty.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {

    public static void main(String[] args) {

        TTransport transport = new TFastFramedTransport(new TSocket("localhost",8899),600);

        TCompactProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            Person person = client.getPersonByUsername("张三");

            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("-----------------------------");

            Person person2 = new Person();

            person2.setUsername("李四");
            person2.setAge(30);
            person2.setMarried(true);

            client.savePerson(person2);

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }finally {
            transport.close();
        }

    }

}
