package com.chuwa.training;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.yourpackage") // Adjust to your base package
public class AppConfig {
    // Define beans and additional configurations here, if needed.
}
