package com.mgw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {

        System.out.println("------> request /hello name is "+name);

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            System.out.println("------> request /hello error ");
        }

        return "hello," + name + ",this is producer 2 first message";

    }


}
