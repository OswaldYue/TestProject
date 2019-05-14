package com.mgw.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-cloud-producer",fallback = HelloRemote.HelloRemoteHystrix.class)
public interface HelloRemote {

    @RequestMapping("/hello")
    String hello(@RequestParam(name = "name") String name);


    @Service
    class HelloRemoteHystrix implements HelloRemote {

        @Override
        public String hello(String name) {
            return "hystrix,"+ name +",message get failed!";
        }
    }

}
