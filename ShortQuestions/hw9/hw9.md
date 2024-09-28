### 2. Compare Spring and Springboot? What are the benfits of Srpingboot?

| Feature                       | Spring Framework                                            | Spring Boot                                                                      |
| ----------------------------- | ----------------------------------------------------------- | -------------------------------------------------------------------------------- |
| **Definition**                | A comprehensive framework for Java application development. | A framework built on top of Spring, designed to simplify Spring app development. |
| **Configuration**             | Requires manual configuration (XML, annotations).           | Provides auto-configuration based on project dependencies.                       |
| **Embedded Server**           | Requires external server setup (e.g., Tomcat).              | Comes with embedded servers (Tomcat, Jetty, etc.).                               |
| **Boilerplate Code**          | More boilerplate code and manual setup required.            | Reduces boilerplate code with sensible defaults.                                 |
| **Flexibility**               | High flexibility and control over configuration.            | Reduces flexibility for the sake of simplicity and speed.                        |
| **Dependency Management**     | Manual management of dependencies.                          | Simplified using Spring Boot Starters.                                           |
| **Production-Ready Features** | Requires custom setup for monitoring, metrics, etc.         | Built-in features for monitoring, health checks, and metrics.                    |
| **Microservices Support**     | Can be used, but requires more manual setup.                | Designed with microservices in mind for easy scaling.                            |

**What are the benfits of Srpingboot?**

1. **Rapid Development**: Spring Boot simplifies application development by reducing configuration time, which allows developers to focus on business logic.
2. **Auto-Configuration**: Automatically configures many aspects of the application based on the dependencies, reducing manual configuration.
3. **Embedded Servers**: Built-in support for embedded servers (Tomcat, Jetty, Undertow), enabling easy testing and deployment.
4. **Production-ready Features**: Built-in features like monitoring, health checks, and metrics.
5. **Spring Boot Starters**: Pre-configured dependency starters for commonly used technologies (e.g., `spring-boot-starter-web`, `spring-boot-starter-data-jpa`), simplifying dependency management.
6. **Externalized Configuration**: Supports externalized configuration through `application.properties` or `application.yml`, making it easy to manage different environments.
7. **Microservices Friendly**: Ideal for developing microservices, with lightweight configuration and easy integration with cloud platforms.

## 3. What is IOC and What is DI?

- **Inversion of Control (IoC)** is a design principle in which the control flow of a program is inverted compared to traditional programming. Instead of the developer manually controlling the flow of execution, the framework or container takes control of the program's flow and manages the execution of objects and their dependencies.

- **Dependency Injection (DI)** is a design pattern used to implement IoC, where the dependencies (objects) required by a class are provided by the framework, rather than the class creating the dependencies itself.

### 4. What is `@CompnonentScan` ?

`@ComponentScan` is an annotation in Spring that tells the Spring framework to scan for components, configurations, and services in specified packages so that they can be registered as beans in the Spring application context.

Spring uses the `@ComponentScan` annotation to automatically detect and register beans (i.e., components) from classes annotated with stereotype annotations like:

- `@Component`
- `@Service`
- `@Repository`
- `@Controller`

### 5. What is `@SpringbootApplication` ?

`@SpringBootApplication` is a convenient annotation that combines the functionality of several Spring annotations and serves as the main entry point for most Spring Boot applications. It simplifies configuration, enables auto-configuration, and provides component scanning automatically, allowing for rapid and hassle-free development of Spring Boot applications.

### 6. How many ways to define a bean? Provide code examples.

### 7. What is default bean name for `@Component` and `@Bean` ? Also compare `@Component` and `@Bean`

1. Using `@Component` Annotation

```
import org.springframework.stereotype.Component;

@Component
public class MyBean {
    public void doSomething() {
        System.out.println("Doing something in MyBean");
    }
}
```

2. Using `@Service`, `@Repository`, `@Controller` Annotations