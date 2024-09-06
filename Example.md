# Introduction to Spring Framework, Spring Boot, and Spring MVC

## 1. Overview

Spring is one of the most popular Java frameworks used to build enterprise-level applications. It provides a comprehensive infrastructure to develop robust and maintainable Java applications. Spring MVC and Spring Boot are two important projects built on top of Spring that simplify web development and configuration.

---

## 2. Spring Framework

Spring Framework is the core of the Spring ecosystem. It offers a powerful, comprehensive platform for Java development. The key features of Spring include:

- **Inversion of Control (IoC)**: Spring’s IoC container manages the dependencies between objects (beans), promoting loose coupling.
- **Aspect-Oriented Programming (AOP)**: This allows for modularization of concerns like transaction management, logging, and security.
- **Transaction Management**: Spring provides a consistent transaction management interface that can scale across environments.
- **Data Access**: Spring simplifies JDBC and ORM (Object Relational Mapping) frameworks like Hibernate.
- **Integration with various frameworks**: Spring integrates easily with other popular frameworks and libraries.

### Example of a Spring Bean:
```java
@Component
public class HelloWorld {
    public String sayHello() {
        return "Hello, World!";
    }
}
