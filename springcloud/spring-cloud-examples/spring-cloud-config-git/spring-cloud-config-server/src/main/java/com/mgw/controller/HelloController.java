package com.mgw.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//会报${mgw.hello}找不到的异常 初步估计是因为整个配置服务没有完全启动起来就去获取${mgw.hello}值  所以获取不到
//    @Value("${mgw.hello}")
//    private String hello;
//
//    @RequestMapping("/hello")
//    public String hello() {
//        return this.hello;
//    }
}
