
package com.example.demo.config;

import com.example.demo.model.ServiceA;
import com.example.demo.model.ServiceB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    // Singleton scope (default)
    @Bean
    public ServiceA serviceA() {
        return new ServiceA();
    }

    // Prototype scope
    @Bean
    @Scope("prototype")
    public ServiceB serviceB() {
        return new ServiceB();
    }
}
