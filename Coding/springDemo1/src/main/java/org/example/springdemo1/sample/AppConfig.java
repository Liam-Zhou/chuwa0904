package org.example.springdemo1.sample;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// The Spring framework enables automatic dependency injection. In other words, by declaring all the bean dependencies
// in a Spring configuration file, Spring container can autowire relationships between collaborating beans. This is
// called Spring bean autowiring.

// Alternatively, the <context:annotation-config> annotation is mainly used to activate the dependency injection
// annotations in Spring XML files.
@Configuration
@ComponentScan("org.example.springdemo1.sample")
public class AppConfig {

}
