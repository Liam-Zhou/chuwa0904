1. **List all of the annotations you learned from class and homework to annotations.md (your own cheatsheet)**:
   - `@Component`: Marks a class as a Spring-managed bean.
   - `@Service`: Specialization of `@Component` for service layer beans.
   - `@Repository`: Specialization of `@Component` for the persistence layer.
   - `@Controller`: Specialization of `@Component` for MVC controllers.
   - `@Autowired`: Enables automatic dependency injection.
   - `@Qualifier`: Specifies which bean to inject when multiple beans of the same type exist.
   - `@Primary`: Marks a bean as the primary candidate for injection.
   - `@Bean`: Used to define beans within a configuration class.
   - `@Configuration`: Indicates that a class declares one or more `@Bean` methods.
   - `@SpringBootApplication`: Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
   - `@ComponentScan`: Configures which packages to scan for beans.
   - `@RequestMapping`: Maps HTTP requests to handler methods in controllers.
   - `@Scope`: Defines the scope of a bean (`singleton`, `prototype`, etc.).
   - `@PostConstruct`: Used for a method that will run after the bean has been constructed.
   - `@PreDestroy`: Used for a method that will run before a bean is destroyed.
   - `@Resource`: Similar to `@Autowired` but with more fine-grained control over dependency injection.

2. **Compare Spring and Spring Boot? What are the benefits of Spring Boot?**
   - **Spring**: A comprehensive framework that requires manual setup and configuration.
   - **Spring Boot**: A layer on top of Spring that simplifies configuration by providing:
     - Auto-configuration.
     - Embedded servers like Tomcat or Jetty.
     - Reduced boilerplate with sensible defaults.
     - Spring Boot starters that bundle dependencies for common use cases.
     - Easy integration with Spring modules (e.g., JPA, Security).
     - Simplifies microservices architecture.

3. **What is IoC and what is DI?**
   - **IoC (Inversion of Control)**: The principle where the control of object creation and dependency management is transferred from the program to the Spring container.
   - **DI (Dependency Injection)**: A design pattern where the dependencies of an object are provided by an external source, usually the Spring container.

4. **What is `@ComponentScan`?**
   - `@ComponentScan`: Tells Spring which packages to scan for annotated components (`@Component`, `@Service`, `@Controller`, `@Repository`). It helps Spring automatically detect and register beans.

5. **What is `@SpringBootApplication`?**
   - `@SpringBootApplication`: A convenience annotation that combines:
     - `@Configuration`: Marks the class as a source of bean definitions.
     - `@EnableAutoConfiguration`: Enables Spring Boot’s auto-configuration mechanism.
     - `@ComponentScan`: Scans for `@Component`-annotated classes in the package.

6. **How many ways to define a bean? Provide code examples.**
   - **Using `@Component`**:
     ```java
     @Component
     public class MyBean {
     }
     ```
   - **Using `@Bean` in `@Configuration`**:
     ```java
     @Configuration
     public class MyConfig {
         @Bean
         public MyBean myBean() {
             return new MyBean();
         }
     }
     ```

7. **What is the default bean name for `@Component` and `@Bean`? Also, compare `@Component` and `@Bean`.**
   - **Default bean name**: The class name with the first letter in lowercase. For example, `MyBean` becomes `myBean`.
   - **`@Component`**: Automatically detected through component scanning.
   - **`@Bean`**: Manually declared in a configuration class.
   
8. **Compare `@Component` and `@Service`, `@Repository`, `@Controller`:**
   - **`@Component`**: Generic annotation for Spring-managed beans.
   - **`@Service`**: Specialization of `@Component` for service layer beans.
   - **`@Repository`**: Specialization of `@Component` for data access objects (DAOs).
   - **`@Controller`**: Specialization of `@Component` for handling web requests in an MVC setup.

9. **Explain `@Autowired`, `@Qualifier`, `@Resource`, and `@Primary`:**
   - **`@Autowired`**: Automatically injects a dependency.
   - **`@Qualifier`**: Specifies the bean to inject when multiple candidates exist.
   - **`@Resource`**: Similar to `@Autowired`, but allows specifying the bean by name or type.
   - **`@Primary`**: Marks a bean as the primary candidate for injection when multiple beans of the same type exist.

10. **How many annotations can we use to inject a bean?**
    - `@Autowired`, `@Resource`, and `@Inject` (from JSR-330).

11. **Explain and compare different types of dependency injection, their pros and cons, and use cases:**
    - **Constructor Injection**: 
      - Pros: Immutable, makes testing easier.
      - Cons: Can lead to constructor bloat if too many dependencies.
    - **Setter Injection**:
      - Pros: Flexible, can inject dependencies after object creation.
      - Cons: Dependencies can be changed, making the object mutable.
    - **Field Injection**:
      - Pros: Simplest to use.
      - Cons: Hard to test and violates the principle of immutability.

12. **If we have multiple beans for one type, how to set one as primary? Demo with code examples.**
    - Use `@Primary` or `@Qualifier` to specify which bean to use.
    ```java
    @Bean
    @Primary
    public MyBean primaryBean() {
        return new MyBean();
    }
    
    @Bean
    public MyBean secondaryBean() {
        return new MyBean();
    }
    ```

13. **Compare `BeanFactory` and `ApplicationContext` in Spring framework:**
    - **BeanFactory**: The basic container that provides basic functionality for managing beans.
    - **ApplicationContext**: Extends `BeanFactory`, provides more enterprise features such as event propagation, declarative transaction management, and AOP.

14. **Explain bean scope in Spring IoC? List bean scopes with explanations and code examples if possible.**
    - **Singleton** (default): One instance per Spring container.
    - **Prototype**: A new instance is created each time the bean is requested.
    - **Request**: One instance per HTTP request (Web applications).
    - **Session**: One instance per HTTP session (Web applications).

    Example:
    ```java
    @Scope("prototype")
    @Component
    public class MyPrototypeBean {
    }
    ```

15. **Write a Spring application that registers and autowires beans:**
    ```java
    @Configuration
    public class AppConfig {
        @Bean
        public Service service() {
            return new Service();
        }
    }
    
    @Component
    public class Client {
        @Autowired
        private Service service;
    }
    ```

16. **Explain builder pattern with code examples:**
    - The Builder pattern allows step-by-step object creation.
    ```java
    public class User {
        private String name;
        private String email;
    
        private User(Builder builder) {
            this.name = builder.name;
            this.email = builder.email;
        }
    
        public static class Builder {
            private String name;
            private String email;
    
            public Builder setName(String name) {
                this.name = name;
                return this;
            }
    
            public Builder setEmail(String email) {
                this.email = email;
                return this;
            }
    
            public User build() {
                return new User(this);
            }
        }
    }
    
    // Usage:
    User user = new User.Builder().setName("John").setEmail("john@example.com").build();
    ```

