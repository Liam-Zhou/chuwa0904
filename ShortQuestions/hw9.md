
# Spring Basic Homework

## 1. List all of the annotations you learned from class and homework to annotations.md (your own cheatsheet).
- `@Component`
- `@Service`
- `@Repository`
- `@Controller`
- `@ControllerAdvice`
- `@Bean`
- `@Autowired`
- `@Qualifier`
- `@Primary`
- `@Resource`
- `@ExceptionHandler`
- `@Configuration`
- `@EnableAutoConfiguration`
- `@SpringBootApplication`
- `@Valid`
- `@OneToMany`
- `@Override`

## 2. Compare Spring and Spring Boot? What are the benefits of Spring Boot?
- **Spring** is a framework for building Java applications with dependency injection and aspect-oriented programming. It requires a lot of configuration.
- **Spring Boot** simplifies the setup and development process by providing default configurations, embedded servers, and production-ready features. It is
-  the extensions of **Spring**.
- **Benefits of Spring Boot**:
  - Reduces boilerplate code with auto-configuration.
  - Comes with an embedded web server (Tomcat, Jetty, etc.)
  - Provides production-ready features like monitoring and health checks.
  - Simplifies dependency management with `spring-boot-starter` packages.

## 3. What is IoC and What is DI?
- **IoC (Inversion of Control)** is a principle where the control of objects or portions of a program is transferred to a container or framework.
- **DI (Dependency Injection)** is a design pattern used to implement IoC, where the dependencies of an object are injected by a container instead of being created by the object itself.

## 4. What is @ComponentScan?
- `@ComponentScan` is an annotation used to specify the base packages to scan for Spring-managed components, allowing Spring to detect and register beans.

## 5. What is @SpringBootApplication?
- `@SpringBootApplication` is a convenience annotation that combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` to configure a Spring Boot application.

## 6. How many ways to define a bean? Provide code examples.
There are three main ways to define a bean:
1. Using `@Component`:
   ```java
   @Component
   public class MyComponent {
       // Bean logic
   }
   ```
2. Using `@Bean`:
   ```java
   @ComponentScan
   @Configuration
   public class AppConfig {
       @Bean
       public MyBean myBean() {
           return new MyBean();
       }
   }
   ```
3. Using XML Configuration (older approach):
   ```xml
   <bean id="myBean" class="com.example.MyBean"/>
   ```

## 7. What is the default bean name for @Component and @Bean? Also compare @Component and @Bean.
- The default bean name for `@Component` is the class name with the first letter in lowercase.
- For `@Bean`, the default name is the method name.

**Comparison:**
- `@Component` is used to create beans at the class level, while `@Bean` is used to create beans at the method level.

## 8. Compare @Component, @Service, @Repository, @Controller.
- `@Component`: Generic stereotype for any Spring-managed component.
- `@Service`: Used to denote a service class (business logic).
- `@Repository`: Used for DAO (Data Access Object) components.
- `@Controller`: Used to handle web requests in Spring MVC.

## 9. Explain @Autowired, @Qualifier, @Resource, and @Primary.
- `@Autowired`: Injects a bean by type.
- `@Qualifier`: Used alongside `@Autowired` to specify which bean to inject when multiple beans exist.
- `@Resource`: Similar to `@Autowired` but injects by name first.
- `@Primary`: Marks a bean as the primary candidate for injection when multiple beans are available.

## 10. How many annotations can we use to inject a bean?
Three main annotations can be used: `@Autowired`, `@Resource`, and `@Inject`.

## 11. Explain and compare different types of dependency injection, their pros and cons, and use cases.
- **Constructor Injection**: Dependencies are passed via the constructor. It's suitable for immutable objects and ensures all dependencies are injected.
- **Setter Injection**: Dependencies are injected via setter methods. It allows optional dependencies but makes the object mutable.
- **Field Injection**: Dependencies are injected directly into fields. It is less flexible and harder to test.

## 12. If we have multiple beans for one type, how to set one as primary? How does Spring IoC pick one bean to inject if there's no primary? Demo with code examples.
- Use `@Primary` to mark one bean as primary.
- If no primary bean is specified, Spring will try to match by name or throw a `NoUniqueBeanDefinitionException`.

Example:
```java
@Component
public class MyBeanA {}

@Component
@Primary
public class MyBeanB {}
```

## 13. Compare BeanFactory and ApplicationContext in the Spring framework.
- **BeanFactory**: Basic container for managing beans. Lazily loads beans.
- **ApplicationContext**: An advanced container with additional features like event handling, internationalization, and eager loading of beans.

## 14. Explain bean scope in Spring IoC. List bean scopes with explanations and code examples if possible.
- **Singleton**: One instance per Spring container.
- **Prototype**: A new instance is created each time the bean is requested.
- **Request**: One instance per HTTP request (web apps).
- **Session**: One instance per HTTP session (web apps).

Example:
```java
@Component
@Scope("prototype")
public class MyPrototypeBean {}
```

## 15. Write a Spring application that registers and autowires beans.
```java
@Component
public class DependencyB {
    private final DependencyA dependencyA;

    @Autowired
    public DependencyB(DependencyA dependencyA) {
        this.dependencyA = dependencyA;
    }
}
```

## 16. Explain builder pattern with code examples.
The Builder pattern is used to create complex objects step by step.
```java
public class User {
    private String name;
    private int age;

    public static class Builder {
        private String name;
        private int age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.age = this.age;
            return user;
        }
    }
}
```

To use the builder:
```java
User user = new User.Builder().setName("John").setAge(30).build();
```
