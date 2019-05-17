package com.mgw.config;

import com.mgw.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    @Bean
    public TokenFilter tokenFilter() {

        System.out.println("--------------------------0-------------------------------");
        return new TokenFilter();

    }

}
