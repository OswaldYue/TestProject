package com.mgw.config;

import com.mgw.fallback.ProducerFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FallbackConfig {

    @Bean
    public ProducerFallback producerFallback() {

        System.out.println("-----------------ProducerFallback---------0-----------------------");
        return new ProducerFallback();

    }

}
