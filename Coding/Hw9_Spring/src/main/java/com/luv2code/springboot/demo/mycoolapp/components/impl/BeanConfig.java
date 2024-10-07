package com.luv2code.springboot.demo.mycoolapp.components.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public NewClassForBeanCreation getNewClassForBean() {
        return new NewClassForBeanCreation();
    }
}