package com.chris.hw9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean
    public ServiceBean serviceBean1() {
        return new ServiceBean("Bean 1");
    }

    @Bean
    public ServiceBean serviceBean2() {
        return new ServiceBean("Bean 2");
    }

    @Bean
    @Primary
    public ServiceBean primaryServiceBean() {
        return new ServiceBean("Primary Bean");
    }
}
