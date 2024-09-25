# HW8 Spring-Data

## 2. Compare Spring and Springboot? What are the benfits of Srpingboot?

### Comparison: Spring vs Spring Boot

| Aspect                        | **Spring**                                                             | **Spring Boot**                                                                         |
| ----------------------------- | ---------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| **Setup & Configuration**     | Requires extensive configuration (XML or Java annotations)             | Provides auto-configuration and reduces boilerplate code.                               |
| **Boilerplate Code**          | More boilerplate code for setup and configuration.                     | Minimal boilerplate due to embedded servers and auto-config.                            |
| **Application Server**        | Requires external server setup (Tomcat, Jetty, etc.).                  | Comes with embedded servers (Tomcat, Jetty, Undertow).                                  |
| **Deployment**                | Usually packaged as a WAR and deployed to external servers.            | Can be packaged as a standalone JAR and run independently.                              |
| **Development Speed**         | Slower, requires manual setup and configuration.                       | Faster, with built-in tools and simplified configuration.                               |
| **Auto-Configuration**        | Manual configuration of each component is necessary.                   | Auto-configures components based on the classpath and settings.                         |
| **Complexity**                | More complex due to manual configuration requirements.                 | Easier to start and maintain, especially for new applications.                          |
| **Testing**                   | Requires manual setup for testing environments.                        | Testing support is built-in with simplified configurations.                             |
| **Microservices**             | Can be used for microservices but requires manual setup.               | Optimized for microservices with lightweight configuration and tools like Spring Cloud. |
| **Production-Ready Features** | No built-in production monitoring tools.                               | Provides Actuator for health checks, metrics, and other production-ready tools.         |
| **Learning Curve**            | Steeper learning curve due to manual setup and various configurations. | Easier learning curve with sensible defaults and pre-configured setups.                 |

### Summary:

- **Spring** is a more comprehensive and flexible framework, but it requires more setup and configuration. It is ideal for large and complex enterprise applications where fine-grained control is required.
- **Spring Boot** simplifies the setup process, speeds up development, and is designed for building standalone and microservices-based applications with minimal configuration. It is well-suited for small to medium-sized applications or rapid development environments.

### Benefits of Spring Boot

Spring Boot provides several advantages over traditional Spring development, especially for modern application architectures like microservices.

1. Reduced Boilerplate Code: Spring Boot significantly reduces the amount of configuration code and XML files needed, allowing developers to focus more on the business logic.

2. Faster Development: With auto-configuration, starter dependencies, and embedded servers, Spring Boot enables faster application setup and development.

3. Standalone Applications: Spring Boot applications can be run directly as standalone JARs with embedded servers (like Tomcat), eliminating the need to set up external servers.

4. Microservices Support: Spring Boot is ideal for microservices architecture because of its lightweight nature, fast setup, and support for embedded servers. It’s also commonly used with Spring Cloud for distributed systems.

5. Easy Monitoring and Management: With the Spring Boot Actuator, you can easily monitor your application, check health status, expose metrics, and interact with your app through HTTP endpoints.

6. Easier Testing: Spring Boot includes embedded testing tools and makes it easier to mock environments for integration and unit tests.

7. Community and Ecosystem: Spring Boot is built on top of the Spring Framework, so it benefits from the rich ecosystem of tools, libraries, and a large community that Spring provides.

8. Conventions Over Configurations: Spring Boot follows a "convention over configuration" approach, making it easier for developers to get started without worrying about low-level configurations.

## 3. What is IOC and What is DI?

Inversion of Control (IoC) and Dependency Injection (DI) are two core concepts in software development, particularly in the context of frameworks like Spring. They help manage dependencies between objects, making the system more modular, testable, and easier to maintain.

Inversion of Control (IoC) is a design principle where the control of object creation, configuration, and lifecycle is transferred (or "inverted") from the application code to a framework or container. Instead of the application being responsible for managing these aspects, the framework takes over.

Dependency Injection (DI) is a design pattern and a specific way of implementing IoC. In DI, the framework or container is responsible for injecting the dependencies (or collaborators) an object needs, rather than the object itself creating those dependencies. It is one of the ways to achieve IoC.

## 4. What is @CompnonentScan?

@ComponentScan is an annotation in the Spring Framework used to specify the base packages that Spring should scan for components (such as classes annotated with @Component, @Service, @Repository, @Controller, etc.). It allows Spring to automatically detect and register beans into the application context.

When you use @ComponentScan, Spring will look for Spring-managed components in the specified packages and their sub-packages and register them as beans in the IoC (Inversion of Control) container.

## 5. What is @SpringbootApplication?

`@SpringBootApplication` is a key annotation in Spring Boot that is used to enable a variety of configuration and bootstrap functionalities in Spring Boot applications. It is essentially a convenience annotation that combines several other annotations that are commonly used together when building a Spring Boot application.

## 6. How many ways to define a bean? Provide code examples.

1. Using `@Component`

```
@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Component Bean Method Executed");
    }
}
```

2. Using `@Bean` in a `@Configuration` Class

```
@Configuration
public class AppConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    @Bean
    public AnotherBean anotherBean() {
        return new AnotherBean(myBean()); // You can inject dependencies between beans
    }
}
```

3. Using XML Configuration

```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="myBean" class="com.example.MyBean" />
    <bean id="anotherBean" class="com.example.AnotherBean">
        <constructor-arg ref="myBean" />
    </bean>

</beans>
```

4. Using `@Import` Annotation

```
@Configuration
public class AppConfig1 {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}

@Configuration
@Import(AppConfig1.class)
public class AppConfig2 {
    @Bean
    public AnotherBean anotherBean(MyBean myBean) {
        return new AnotherBean(myBean);
    }
}
```

5. Using @Autowired or Constructor Injection for Dependency Injection

```
@Component
public class MyComponent {
    private final MyService myService;

    @Autowired  // Constructor injection
    public MyComponent(MyService myService) {
        this.myService = myService;
    }

    public void doSomething() {
        myService.processData();
    }
}

@Service
public class MyService {
    public String processData() {
        return "Processing data...";
    }
}
```

6. Using Factory Beans

```
public class MyFactoryBean implements FactoryBean<MyBean> {

    @Override
    public MyBean getObject() throws Exception {
        return new MyBean(); // Custom creation logic
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }
}

@Configuration
public class AppConfig {
    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }
}
```

7. Using `@Value` for Property Injection

```
@Component
public class MyComponent {

    @Value("${my.property}")
    private String myProperty;

    public void printProperty() {
        System.out.println("Property Value: " + myProperty);
    }
}
```

## 7. What is default bean name for for @Component and @Bean? Also compare @Component and @Bean.

When you use the `@Component` annotation (or one of its specialized versions like `@Service`, `@Repository`, `@Controller`), Spring automatically generates a bean name by default. This name is derived from the **class name**, with the first letter lowercased.

When you define a bean using the `@Bean` annotation in a `@Configuration` class, the default bean name is the **name of the method that returns** the bean.

### Comparison of `@Component` vs `@Bean`

| Aspect                     | `@Component`                                                                                               | `@Bean`                                                                                                 |
| -------------------------- | ---------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| **Purpose**                | Marks a class as a Spring-managed component/bean.                                                          | Defines a bean method within a `@Configuration` class.                                                  |
| **How It's Used**          | Applied directly on a class to indicate it is a bean.                                                      | Applied on a method that returns a bean, usually in a `@Configuration` class.                           |
| **Bean Creation Control**  | Spring automatically detects the class and registers it as a bean.                                         | Full control over bean instantiation, including constructor arguments, configuration, and dependencies. |
| **Scope**                  | Typically used for simple, self-contained components.                                                      | Allows more complex bean definitions with custom creation logic.                                        |
| **Dependency Injection**   | Dependencies are injected using `@Autowired` or constructor injection.                                     | Dependencies can be passed explicitly when calling the method.                                          |
| **Use of Method**          | Not associated with methods. Bean is created via class instantiation.                                      | The method is called by Spring, and the return value is registered as a bean.                           |
| **Stereotype Annotations** | Specialized forms of `@Component` exist: `@Service`, `@Repository`, `@Controller`.                         | No stereotypes exist. It is only used within a `@Configuration` class.                                  |
| **Bean Name**              | The default name is the class name with the first letter in lowercase (e.g., `myService` for `MyService`). | The default name is the method name (e.g., `myService`).                                                |
| **When to Use**            | Use when you want Spring to automatically detect and manage beans via classpath scanning.                  | Use when you need more fine-grained control over the creation process or need complex setup logic.      |

## 8. Compare @component and @service, @repository, @controller?

In Spring, @Component, @Service, @Repository, and @Controller are all stereotype annotations used to define Spring-managed beans. They serve similar purposes but are intended for different layers of an application, providing better readability and understanding of the architecture.

## 9. Explain @Autowired, @Qualifier, @Resource and @Primary?

In Spring, dependency injection (DI) is a core feature, and several annotations are available to assist with this. Each of the following annotations—@Autowired, @Qualifier, @Resource, and @Primary—helps Spring resolve and inject dependencies in different scenarios.

1. @Autowired  
   `@Autowired` is the most commonly used annotation in Spring for automatic dependency injection. It tells Spring to automatically resolve and inject the required dependency into a class.

2. @Qualifier  
   `@Qualifier` is used to resolve ambiguity when there are multiple beans of the same type. It allows you to specify which exact bean to inject by name.

3. @Resource  
   `@Resource` is part of the JSR-250 specification and works similarly to `@Autowired`, but it resolves the dependency by name by default (and then by type if no matching name is found). It can be thought of as a hybrid between `@Autowired` and `@Qualifier`.

4. @Primary  
   `@Primary` is used to resolve conflicts when multiple beans of the same type are present, by marking one of them as the default. If no @Qualifier is specified, Spring will inject the bean marked with `@Primary`.

### Comparison of `@Autowired`, `@Qualifier`, `@Resource`, and `@Primary`

| Annotation       | Description                                                                                                                       | Injection Type  | Use Case                                                                                                     |
| ---------------- | --------------------------------------------------------------------------------------------------------------------------------- | --------------- | ------------------------------------------------------------------------------------------------------------ |
| **`@Autowired`** | Automatically injects dependencies by **type**. Throws an exception if multiple beans of the same type exist without a qualifier. | By type         | Used for automatic injection of dependencies, typically with only one bean of the type available.            |
| **`@Qualifier`** | Used alongside `@Autowired` to specify the **exact bean** to inject when multiple beans of the same type are present.             | By name         | Resolves ambiguity by specifying the name of the bean to inject when multiple beans of the same type exist.  |
| **`@Resource`**  | Injects dependencies by **name** first, then by type if no matching name is found.                                                | By name or type | Often used when JSR-250 standard is followed or when injecting beans by name first is important.             |
| **`@Primary`**   | Marks a bean as the **default** when multiple beans of the same type are available and no qualifier is specified.                 | By type         | Defines the default bean to inject when multiple candidates are available and no specific qualifier is used. |

## 10. How many annotaitons we can use to inject a bean?

| Annotation       | Description                                                       | Primary Use Case                                  |
| ---------------- | ----------------------------------------------------------------- | ------------------------------------------------- |
| **`@Autowired`** | Injects a dependency by type.                                     | When you want automatic dependency injection.     |
| **`@Qualifier`** | Specifies which exact bean to inject when multiple exist.         | When multiple beans of the same type are present. |
| **`@Resource`**  | Injects a bean by name, or by type if no name is specified.       | When you want to inject by name first.            |
| **`@Inject`**    | Part of JSR-330 standard, similar to `@Autowired`.                | When you want to use standard CDI.                |
| **`@Primary`**   | Marks a bean as the default candidate for injection.              | When there are multiple beans of the same type.   |
| **`@Value`**     | Injects literal values (e.g., properties, environment variables). | When you want to inject configuration values.     |

## Descriptions

- **`@Autowired`**: The most commonly used annotation for dependency injection in Spring. It automatically resolves dependencies by type.
- **`@Qualifier`**: Used with `@Autowired` to specify which bean to inject when multiple beans of the same type exist.
- **`@Resource`**: Injects dependencies by name first, and then by type if no matching name is found.
- **`@Inject`**: A JSR-330 standard annotation, similar to `@Autowired`, commonly used in Java CDI-based frameworks.
- **`@Primary`**: Designates a bean as the default one when there are multiple candidates of the same type.
- **`@Value`**: Used to inject literal values, such as properties from configuration files or environment variables.

## 11. Explain and compare different types of denpendency injection, their pros and cons, and use cases.

### Explain:

1. Constructor Injection: In Constructor Injection, dependencies are provided through a class constructor. The dependencies are passed as parameters, and they become part of the class’s state as soon as the object is created
2. Setter (or Method) Injection: In Setter Injection, dependencies are provided through setter methods after the object is created. Spring calls the setter methods to inject the required beans.
3. Field Injection: In Field Injection, dependencies are injected directly into fields. Spring uses reflection to set the field’s value during the bean creation process. This is done by using the @Autowired annotation on the field.

### Comparison Table of Different Dependency Injection Types:

| Type                      | How Dependencies Are Injected               | Pros                                                                                                               | Cons                                                                                           | Use Cases                                                                                               |
| ------------------------- | ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| **Constructor Injection** | Dependencies passed via constructor.        | - Promotes immutability<br>- Easy to mock for testing<br>- Explicit dependencies<br>- Avoids circular dependencies | - Constructor can become unwieldy with many parameters<br>- All dependencies must be provided. | - When all dependencies are required at object creation<br>- When immutability and testability are key. |
| **Setter Injection**      | Dependencies passed via setter methods.     | - Ideal for optional dependencies<br>- More flexible and readable in some cases                                    | - Object can be in an inconsistent state<br>- Dependencies can be changed after creation.      | - When optional dependencies are present<br>- When dependencies are injected after object creation.     |
| **Field Injection**       | Dependencies injected directly into fields. | - Simplest to implement<br>- Less boilerplate code                                                                 | - Reduced testability<br>- Breaks encapsulation<br>- Hidden dependencies<br>- Not immutable    | - Simple applications with minimal testing needs.<br>- When concise code is preferred over structure.   |

## 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.

1. Mark one of the beans as @Primary: This will make the marked bean the default choice if no other specific bean is specified.
2. Use @Qualifier: If no @Primary is set and multiple beans are available, you can explicitly specify which bean to inject using @Qualifier.

## 13. Compare BeanFactory and ApplicationContext in Spring framework?

| Aspect                             | **BeanFactory**                                                                                           | **ApplicationContext**                                                                                     |
| ---------------------------------- | --------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| **Definition**                     | Basic container in Spring that provides the core functionality of managing beans.                         | More advanced container built on top of `BeanFactory` with additional enterprise-level features.           |
| **Eager/Lazy Initialization**      | Initializes beans lazily (only when requested).                                                           | Initializes beans eagerly on startup, unless specified otherwise.                                          |
| **Event Handling**                 | No built-in support for event propagation.                                                                | Provides event handling capabilities with the ability to publish and listen for events.                    |
| **Internationalization (i18n)**    | Does not provide built-in support for internationalization.                                               | Provides built-in support for internationalization (i18n).                                                 |
| **Web Applications**               | Typically not used for web applications.                                                                  | Often used for web applications with features like integration with Spring MVC.                            |
| **Bean Post Processing**           | Requires manual configuration for BeanPostProcessor.                                                      | Automatically registers `BeanPostProcessor` and `BeanFactoryPostProcessor` for lifecycle events.           |
| **Built-in Enterprise Features**   | No support for enterprise-level features such as AOP, declarative transactions, etc.                      | Supports AOP, declarative transactions, and other enterprise-level features.                               |
| **Type of Usage**                  | Lightweight and typically used in scenarios where resource constraints exist (e.g., mobile applications). | Preferred for most Spring applications due to its rich feature set and built-in capabilities.              |
| **XML Configuration**              | Supports XML configuration but requires more manual setup.                                                | Supports XML configuration with more advanced features and easier setup.                                   |
| **Annotation-Based Configuration** | Basic support for annotations.                                                                            | Full support for annotations like `@Component`, `@Autowired`, and `@Configuration`.                        |
| **Event Handling**                 | No event handling mechanism available.                                                                    | Offers a robust event handling mechanism for publishing and consuming events.                              |
| **Built-in Beans**                 | Does not provide built-in beans such as `MessageSource`, `ApplicationEventPublisher`.                     | Provides various built-in beans such as `MessageSource`, `ApplicationEventPublisher`, etc.                 |
| **Common Implementations**         | `XmlBeanFactory` (deprecated as of Spring 3.1).                                                           | `ClassPathXmlApplicationContext`, `FileSystemXmlApplicationContext`, `AnnotationConfigApplicationContext`. |

## Summary:

- **`BeanFactory`**: A basic Spring container that is lightweight and used in scenarios where resources are limited, and the advanced features of `ApplicationContext` are not required. It is useful when lazy initialization is needed or when you want minimal memory footprint.
- **`ApplicationContext`**: A more feature-rich container, widely used in most Spring applications, especially web-based and enterprise applications. It provides many advanced features, such as event propagation, AOP, transaction management, internationalization, and more. It is the preferred choice for most applications.

### Recommendation:

In modern Spring applications, `ApplicationContext` is typically used due to its richer feature set and support for most enterprise and web application needs. `BeanFactory` is mainly used in limited resource environments where performance and memory usage are critical.

## 14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.

In Spring, bean scope defines the lifecycle and visibility of a bean. It controls how and when the beans are created and how they are shared within the Spring IoC (Inversion of Control) container. Spring provides several bean scopes, each designed for different use cases.

Example:

1. Singleton (default): A single instance of the bean is created and shared across the entire Spring container. This means that the container will create the bean only once and will return the same instance every time it is requested.

   The bean is instantiated when the Spring container is initialized and exists for the entire lifecycle of the application. Use singleton scope when the bean represents a stateless service that can be shared across the application.

```
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")  // Optional, as singleton is the default scope
public class SingletonBean {
    public SingletonBean() {
        System.out.println("Singleton Bean Created");
    }
}
```

2. Prototype: A new instance of the bean is created every time it is requested. Unlike the singleton scope, the Spring container doesn’t manage the complete lifecycle of a prototype bean, meaning it only creates new instances but doesn’t track or destroy them.

   A new instance is created each time the bean is requested, but Spring does not manage the bean’s destruction. Use prototype scope when you need a separate instance of the bean for each use, such as for stateful components or beans with mutable state.

```
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("Prototype Bean Created");
    }
}
```

3. Request (Web Application Scope): A new instance of the bean is created for each HTTP request. The bean is created and used during the lifecycle of a single HTTP request and is discarded once the request is completed.

   The bean exists only during the lifecycle of a single HTTP request. Use request scope for beans that should be created and used within the scope of a single HTTP request, such as request-scoped data or context-specific objects.

```
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope  // Alternatively, @Scope("request")
public class RequestScopedBean {
    public RequestScopedBean() {
        System.out.println("Request Scoped Bean Created");
    }
}
```

4. Session (Web Application Scope): A new instance of the bean is created for each HTTP session. The bean is available throughout the lifecycle of an HTTP session and is shared across requests made within the same session.

   The bean exists during the entire HTTP session, and a new instance is created for each session. Use session scope when you need to maintain state or store session-related data across multiple HTTP requests in a single session.

```
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope  // Alternatively, @Scope("session")
public class SessionScopedBean {
    public SessionScopedBean() {
        System.out.println("Session Scoped Bean Created");
    }
}
```

5. Application (Web Application Scope): A single instance of the bean is created for the entire web application. This scope is similar to the singleton scope, but it is specific to web applications, meaning the bean is shared across all requests and sessions within the application.

   The bean exists for the entire lifecycle of the web application. Use application scope for beans that represent global resources and need to be shared across all sessions and requests in the web application.

```
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope  // Alternatively, @Scope("application")
public class ApplicationScopedBean {
    public ApplicationScopedBean() {
        System.out.println("Application Scoped Bean Created");
    }
}
```

## 15. A Spring Application sample

See the coding part

## 16. Explain builder pattern with code examples

### Builder Pattern Overview

The Builder Pattern is a creational design pattern used to construct complex objects step by step. Unlike other creational patterns, which construct objects in one go, the Builder Pattern provides a way to build an object incrementally, adding attributes one by one. This is especially useful when an object has multiple optional fields or when the creation process itself is complex.

### When to Use the Builder Pattern

When the object creation involves multiple parameters, especially optional ones.
When you want to avoid a constructor with too many arguments (the "telescoping constructor" problem).
When the construction process is complex and needs to be controlled step by step.

Builder Pattern Example:

```
public class User {
    // Required parameters
    private String firstName;
    private String lastName;

    // Optional parameters
    private int age;
    private String phone;
    private String address;

    // Private constructor to enforce the use of the Builder
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // Static nested Builder class
    public static class UserBuilder {
        // Required parameters
        private String firstName;
        private String lastName;

        // Optional parameters
        private int age;
        private String phone;
        private String address;

        // Constructor for required parameters
        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // Setter method for optional parameters, returning the builder
        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        // Build method to create the final object
        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", phone=" + phone
                + ", address=" + address + "]";
    }
}

//====== Next Page ======

public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Using the Builder to create a User object
        User user1 = new User.UserBuilder("John", "Doe")
                .setAge(30)
                .setPhone("123-456-7890")
                .setAddress("123 Main St")
                .build();

        User user2 = new User.UserBuilder("Jane", "Doe")
                .setPhone("098-765-4321")
                .build();

        System.out.println(user1);
        System.out.println(user2);
    }
}
```
