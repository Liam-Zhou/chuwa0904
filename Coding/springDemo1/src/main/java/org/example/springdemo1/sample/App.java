package org.example.springdemo1.sample;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Spring Boot introduces the @SpringBootApplication annotation. This single annotation is equivalent to using
// @Configuration, @EnableAutoConfiguration, and @ComponentScan.
// As a result, when we run this Spring Boot application, it will automatically scan the components in the current
// package and its sub-packages. Thus it will register them in Spring’s Application Context, and allow us to inject
// beans using @Autowired.
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // @Autowired It allows Spring to resolve and inject collaborating beans into our bean.
        // The Spring IoC container is responsible for managing the objects of an application. It uses dependency injection
        // to achieve inversion of control.

        // The interfaces BeanFactory and ApplicationContext represent the Spring IoC container.
        // BeanFactory is the root interface for accessing the Spring container. It provides basic functionalities for managing beans.
        // On the other hand, the ApplicationContext is a sub-interface of the BeanFactory. Therefore, it offers all the
        // functionalities of BeanFactory.
        // Furthermore, it provides more enterprise-specific functionalities. The important features of ApplicationContext
        // are resolving messages, supporting internationalization, publishing events, and application-layer specific contexts.
        // This is why we use it as the default Spring container.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // In Spring, a bean is an object that the Spring container instantiates, assembles, and manages.
        FooService fooService = context.getBean(FooService.class);
//        fooService.doStuff();
    }
}
