package com.mgw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication {


    /*
    *
        仓库中的配置文件会被转换成web接口，访问可以参照以下的规则：
        /{application}/{profile}[/{label}]
        /{application}-{profile}.yml
        /{label}/{application}-{profile}.yml
        /{application}-{profile}.properties
        /{label}/{application}-{profile}.properties
        mgw-config-dev.properties为例子，它的application是mgw-config，profile是dev。client会根据填写的参数来选择读取对应的配置。
    */

    public static void main(String[] args) {

        SpringApplication.run(ConfigServerApplication.class,args);

    }

}
