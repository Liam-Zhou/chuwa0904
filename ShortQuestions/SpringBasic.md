# Spring Basic

## 1. Annotations
Check annotations.md

## 2. Compare Spring and Springboot? What are the benefits of Springboot?
### Spring vs Spring Boot
1. **Configuration**:
    - Spring requires manual setup for components like databases and security.
    - Spring Boot auto-configures most settings based on dependencies.

2. **Project Setup**:
    - Spring involves more manual configuration and dependencies.
    - Spring Boot simplifies setup with embedded servers and less boilerplate.

3. **Deployment**:
    - Spring needs an external server for deployment.
    - Spring Boot includes an embedded server, allowing standalone apps.

4. **Dependency Management**:
    - Spring uses manual dependency management.
    - Spring Boot uses "starter" dependencies to bundle common libraries.

5. **Application Entry Point**:
    - Spring doesn’t provide a default entry point.
    - Spring Boot has a built-in `main()` method for easy startup.

6. **Microservices**:
    - Spring can be used for microservices, but isn’t optimized for them.
    - Spring Boot is designed with features that simplify microservice development.

### Benefits of Spring Boot
- Auto-configuration simplifies setup.
- Embedded servers for easy deployment.
- Starter dependencies reduce manual work.
- Faster project development with less boilerplate.
- Optimized for building microservices.


## 3. What is IOC and What is DI?
### IOC (Inversion of Control)
IOC is a design principle where the control of object creation and management is shifted from the application code to a framework or container (like Spring). Instead of the code controlling the flow and creating objects, the framework takes control, managing dependencies and object lifecycle.

### DI (Dependency Injection)
DI is a pattern used to implement IOC, where an object's dependencies are injected by the framework rather than being created internally. This allows for better modularity, testability, and decoupling of components.

### IOC vs. DI
- IOC: The concept of shifting control from the application to the framework.
- DI: The specific method used to provide the objects' dependencies, enabling IOC.


## 4. What is @CompnonentScan ?
It is a Spring annotation used to specify the packages that the framework should scan to find components (like `@Component`, `@Service`, `@Repository`, `@Controller`) and register them as Beans in the Spring container.
- It tells Spring where to look for classes annotated with Spring stereotypes (e.g., `@Component`, `@Service`).
- By default, it scans the current package and its sub-packages.
- Can be customized to scan specific packages by providing the base package names.

```java
@Configuration
@ComponentScan(basePackages = "com.example.service")
public class AppConfig {
    // Configuration class
}
```
This will scan the `com.example.service` package and register any classes annotated with Spring stereotypes as Beans.


## 5. What is @SpringbootApplication ?
@SpringBootApplication is an annotation in Spring Boot that combines three annotations, making it easier to set up a Spring Boot application.
### Key Components:
- @Configuration: which declares a class as the source for bean definitions.

- @EnableAutoConfiguration: allows the application to add beans using classpath definitions.

- @ComponentScan: directs Spring to search for components in the path specified.


## 6. How many ways to define a bean? Provide code examples.
In Spring, a Bean is an object that is instantiated, managed, and assembled by the Spring IoC container.
It tells Spring to new this bean and manages the beans.

### Class Level
- **@Component**: Marks a class as a Spring component, allowing it to be detected during classpath scanning.
  ```java
  @Component
  public class MyComponent {
      // Logic here
  }
  ```
- **@Service**: A specialization of `@Component`, used for service layer components.
  ```java
  @Service
  public class UserService {
      // Service logic here
  }
  ```

- **@Repository**: Indicates that the class is a DAO (Data Access Object) for data access logic.
  ```java
  @Repository
  public class UserRepository {
      // Database logic here
  }
  ```

- **@Controller**: Used in Spring MVC to define a controller that handles web requests.
  ```java
  @Controller
  public class UserController {
      // Request handling logic here
  }
  ```

- **@ControllerAdvice**: A specialization for handling exceptions globally across controllers.
  ```java
  @ControllerAdvice
  public class GlobalExceptionHandler {
      // Exception handling logic here
  }
  ```

- **@ComponentScan**: Instructs Spring to scan specified packages for components to register as Beans.
  ```java
  @ComponentScan(basePackages = "com.example")
  public class AppConfig {
      // Configuration logic here
  }
  ```

### Method Level

- **@Bean**: Used with `@Configuration` to define a Bean via a method. This is often used to instantiate external objects.
  ```java
  @Configuration
  public class AppConfig {
      
      @Bean
      public MyService myService() {
          return new MyService(); // or use a builder, e.g., new MyService.Builder().build();
      }
  }
  ```

### XML
- **XML Configuration**: You can define Beans using an XML file.

  ```xml
  <bean id="dataNucleusChuwaNoComponent" class="com.chuwa.springbasic.components.impl.DataNucleusChuwaNoComponent"></bean>
  ```


## 7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean.
### Default Bean Name
- **@Component**: The default bean name is the uncapitalized simple name of the class. For example, a class named `UserService` would be registered with the bean name `userService`.
- **@Bean**: The default bean name is the name of the method that defines the bean. For instance, a method named `userService()` would register the bean with the name `userService`.

### Comparison of @Component and @Bean
- **Usage Context**:
    - `@Component` is used at the class level to mark a class for automatic detection and registration as a Spring component.
    - `@Bean` is used at the method level within a configuration class to explicitly define a bean.

- **Suitability**:
    - `@Component` is suitable for simple components that can be automatically instantiated by Spring.
    - `@Bean` is appropriate for more complex object creation, such as when working with external libraries or custom instantiation logic.

- **Configuration Requirement**:
    - `@Component` can be applied to any class and does not require a specific context.
    - `@Bean` must be declared in a class annotated with `@Configuration`.

- **Instantiation Control**:
    - Both annotations result in Spring managing the object as a bean, but `@Component` relies on auto-detection.
    - `@Bean` provides more control over the instantiation process, allowing for specific configurations or parameters.

## 8. Compare @component and @service, @repository, @controller?
- **@Component**:
  - Is a generic stereotype for any Spring-managed component. 
  - Suitable for any class that you want to be managed by the Spring container. 
  - Does not imply any specific role or behavior.

- **@Service**:
    - A specialization of `@Component` , is used for classes that implement business logic.
    - Helps with clarity and organization, making it clear that the class is part of the service layer.

- **@Repository**:
    - Another specialization of `@Component`, is for classes that handle data access and database operations.
    - Can also include additional functionality such as exception translation for database-related errors.

- **@Controller**:
    - A specialization of `@Component` used in Spring MVC for handling web requests.
    - Indicates that a class is responsible for processing HTTP requests and returning responses.
    - Often used to define the mapping between URLs and methods that handle those requests.


## 9. Explain @Autowired, @Qualifier, @Resource and @Primary?
### @Autowired
- Automatically injects Spring beans by type.
- Can be used on constructors, methods, or fields.

### @Qualifier
- Works with `@Autowired` to specify which bean to inject when multiple candidates exist.
- Requires the value to match the bean name.

### @Resource
- Injects dependencies by name, falling back to type if not found.
- Can be used on fields and setter methods.

### @Primary
- Marks a bean as the preferred candidate when multiple beans are available for injection.
- Used to avoid ambiguity in dependency resolution.


## 10. How many annotations we can use to inject a bean?
The primary annotations for injecting a bean are `@Autowired`, `@Qualifier`, `@Resource`, and `@Inject`.
1. **@Autowired**: Automatically injects a bean by type. It can be applied to constructors, methods, or fields.

2. **@Qualifier**: Used in conjunction with `@Autowired` to specify which bean to inject when multiple beans of the same type exist.

3. **@Resource**: Injects a bean by name and falls back to type if the specified name is not found. It can be used on fields and setter methods.

4. **@Inject**: Part of the Java Dependency Injection (JSR-330) specification, it functions similarly to `@Autowired`. However, it does not support optional dependencies by default.

5. **@Primary**: While not an injection annotation itself, it marks a bean as the preferred candidate when multiple beans are available for injection.


## 11. Explain and compare different types of dependency injection, their pros and cons, and use cases.
1. **Constructor Injection**
    - **Description**: Dependencies are provided through the class constructor.
    - **Pros**: Ensures dependencies are provided at instantiation, promotes immutability, and improves readability.
    - **Cons**: Can result in long parameter lists and complex constructors.
    - **Use Cases**: Ideal for mandatory dependencies.

   ```java
   @Service
   public class UserService {
       private final UserRepository userRepository;

       @Autowired
       public UserService(UserRepository userRepository) {
           this.userRepository = userRepository;
       }
   }
   ```

2. **Setter Injection**
    - **Description**: Dependencies are set via setter methods after object creation.
    - **Pros**: Allows for optional dependencies and simplifies constructor complexity.
    - **Cons**: Risk of incomplete objects and mutable state.
    - **Use Cases**: Suitable for optional dependencies and circular dependencies.

   ```java
   @Service
   public class UserService {
       private UserRepository userRepository;

       @Autowired
       public void setUserRepository(UserRepository userRepository) {
           this.userRepository = userRepository;
       }
   }
   ```

3. **Field Injection**
    - **Description**: Dependencies are injected directly into fields.
    - **Pros**: Reduces boilerplate code and is straightforward.
    - **Cons**: Makes testing difficult and hides dependencies.
    - **Use Cases**: Generally discouraged but can be used in simple applications.

   ```java
   @Service
   public class UserService {
       @Autowired
       private UserRepository userRepository;
   }
   ```
   

## 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primary, demo with code examples.
### Setting a Primary Bean
Use the `@Primary` annotation to designate a primary bean among multiple beans of the same type.
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public UserService primaryUserService() {
        return new UserService("Primary User Service");
    }

    @Bean
    public UserService secondaryUserService() {
        return new UserService("Secondary User Service");
    }
}
```

### Spring Bean Resolution
If no bean is marked as `@Primary`, Spring will inject by type. If multiple beans exist, it throws a `NoUniqueBeanDefinitionException`.
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserController {
    
    @Autowired
    private UserService userService; // Causes an error if multiple UserService beans exist

    public void execute() {
        userService.doSomething();
    }
}
```


## 13. Compare BeanFactory and ApplicationContext in Spring framework?
**BeanFactory**
- Basic container for dependency injection.
- Lazy initialization: beans are created when requested.
- Limited features (no AOP, event propagation).
- Suitable for lightweight applications.

**ApplicationContext**
- Advanced container that extends BeanFactory.
- Eager initialization: all singleton beans are created at startup.
- Supports AOP, event propagation, and internationalization.
- Preferred for most applications due to extensive features.

## 14. Explain bean scope in Spring IOC? List bean scopes with explanations and code examples if possible.
### Bean Scope in Spring IOC

#### Basic (General)
- **Singleton**: Only one instance exists in the IOC Container; this is the default scope.
    - **Example**:
      ```java
      @Component
      public class SingletonBean {
          // Only one instance exists
      }
      ```

- **Prototype**: A new instance is created each time the bean is requested, such as when using `context.getBean()` or `@Autowired`.
    - **Example**:
      ```java
      @Component
      @Scope("prototype")
      public class PrototypeBean {
          // A new instance is created on each request
      }
      ```

#### Web (Applicable Only to Web Applications)
- **Request**: A new instance is created for each HTTP request; valid only in a web-aware Spring ApplicationContext.
    - **Example**:
      ```java
      @Component
      @Scope("request")
      public class RequestScopedBean {
          // A new instance for each HTTP request
      }
      ```

- **Session**: A new instance is created for each HTTP session; valid only in a web-aware Spring ApplicationContext.
    - **Example**:
      ```java
      @Component
      @Scope("session")
      public class SessionScopedBean {
          // A new instance for each HTTP session
      }
      ```

## 15. Write a Spring application that registers and autowires beans.


## 16. Explain builder pattern with code examples.
### Builder Pattern
The Builder Pattern is a design pattern used to construct complex objects step by step. It provides a flexible solution for creating different representations of an object using the same construction process.
### Components
1. **Product**: The complex object being built.
2. **Builder**: Defines methods for creating parts of the product.
3. **Concrete Builder**: Implements the builder interface and constructs the product.
4. **Director**: Controls the building process.

