package com.example.springdemo.config;

import com.example.springdemo.beans.FirstBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public FirstBean firstBean(){
        return new FirstBean();
    }

    @Bean
    public FirstBean firstBean1(){
        return new FirstBean("Alikahn", 29);
    }

    @Bean
    public FirstBean secondBean(){
        return new FirstBean("Madiyar", 19);
    }
}
