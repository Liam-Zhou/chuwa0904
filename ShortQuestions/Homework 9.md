## Homework 9

### 2. Compare Spring and Spring Boot. Benefits of Spring Boot:
 - Spring: A comprehensive framework for Java enterprise applications, providing dependency injection (DI) and inversion of control (IoC). However, configuration can be complex.
 - Spring Boot: A sub-project of Spring that simplifies the setup by offering auto-configuration and embedded servers, reducing boilerplate. Benefits include:
  - Quick application development.
  - Embedded Tomcat/Jetty servers.
  - Opinionated defaults for configuration.
  - Ready-to-use production-grade setup.

### 3. IOC and DI:
 - IoC (Inversion of Control): A design principle where the control flow is inverted; the framework controls the program.
 - DI (Dependency Injection): A form of IoC where dependencies are injected into objects rather than objects creating their own dependencies.

### 4. @ComponentScan:
 - This annotation is used to specify the packages to search for Spring components such as @Component, @Service, @Repository, and @Controller.

### 5. @SpringbootApplication:
 - It is a combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan. It marks the main class of a Spring Boot application and enables auto-configuration and component scanning.

### 6. Ways to define a bean:
 - Beans can be defined using @Component, @Service, @Repository, @Controller, or @Bean within a configuration class.
 - Example:
 ```java
 @Component
 public class MyComponent {
 }

 @Bean
 public MyBean myBean() {
     return new MyBean();
 }
```

### 7. Default bean names for @Component and @Bean:
 - @Component: The default bean name is the class name with the first letter in lowercase.
 - @Bean: The default bean name is the method name.
 - @Component vs @Bean: @Component is used for class-level component scanning, while @Bean is typically used in configuration classes to manually define beans.

### 8. @Component vs @Service, @Repository, @Controller:
 - All are specializations of @Component used for specific layers in the application.
  - @Service: For business logic.
  - @Repository: For data access layers.
  - @Controller: For presentation layer in MVC.

### 9. @Autowired, @Qualifier, @Resource, @Primary:
 - @Autowired: Marks a constructor, method, or field to be autowired by Spring's dependency injection.
 - @Qualifier: Used with @Autowired to specify the bean to be injected when multiple beans of the same type are available.
 - @Resource: Works similarly to @Autowired but allows more control (e.g., inject by name).
 - @Primary: Marks a bean as the default choice for autowiring if multiple candidates are available.

### 10. Annotations to inject a bean:
 - @Autowired, @Resource, @Inject (JSR-330).

### 11. Types of Dependency Injection:
 - Constructor Injection: Dependencies are provided via the constructor.
  - Pros: Immutable dependencies, easy to test.
  - Cons: Can have large constructor signatures.
 - Setter Injection: Dependencies are set via setter methods.
  - Pros: Optional dependencies can be easily injected.
  - Cons: Allows mutable dependencies.
 - Field Injection: Dependencies are injected directly into fields.
  - Pros: Simple and concise.
  - Cons: Difficult to test; doesn't support immutability.

### 12. Setting a primary bean when multiple beans exist:
 - Mark one bean with @Primary to set it as the default.
 - Code example:
    ```java
    @Bean
    @Primary
    public MyBean primaryBean() {
        return new MyBean();
    }
    ```

### 13. BeanFactory vs ApplicationContext:
 - BeanFactory: Basic container providing dependency injection.
 - ApplicationContext: Extends BeanFactory with more features like event propagation, AOP integration, and internationalization.

### 14. Bean Scopes:

 - Singleton: A single instance per Spring container (default).
 - Prototype: A new instance for every request.
 - Request: One bean per HTTP request (web applications).
 - Session: One bean per HTTP session (web applications).
 - Example:
    ```java
    @Scope("singleton")
    public class MyBean {
    }
    ```

### 15. Write a Spring Application:
 - Register beans with @Component and @Bean, and autowire them using @Autowired.
 - Show examples of different types of dependency injection, bean scopes, and ambiguity resolution.

### 16. Builder Pattern:
 - A design pattern used to create complex objects step by step. Example:
    ```java
    public class User {
        private String name;
        private String email;

        private User(UserBuilder builder) {
            this.name = builder.name;
            this.email = builder.email;
        }

        public static class UserBuilder {
            private String name;
            private String email;

            public UserBuilder setName(String name) {
                this.name = name;
                return this;
            }

            public UserBuilder setEmail(String email) {
                this.email = email;
                return this;
            }

            public User build() {
                return new User(this);
            }
        }
    }
    ```