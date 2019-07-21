package provider.impl;

import provider.api.HelloService;

public class HelloServiceImpl implements HelloService {

    public String sayHello(String username) {
        return  "hello ,"+username;
    }

}
