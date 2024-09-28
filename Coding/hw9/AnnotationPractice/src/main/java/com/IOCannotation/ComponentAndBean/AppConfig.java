package com.IOCannotation.ComponentAndBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Car carBean() {
        return new Car("Honda");
    }
}

