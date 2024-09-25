# Spring Basics

## 1. List all the annotations you learned from class and homework to `annotations.md` (your own cheatsheet).
see [annotation.md](./annotations.md)

## 2.Compare Spring and Spring Boot? What are the benefits of Spring Boot?
- Reduced Configuration: With auto-configuration and sensible defaults, it eliminates the need for manual configurations, allowing developers to focus on writing business logic.
- Embedded Server: Enables easy testing and deployment as the application can be run independently without needing an external server setup.
- Simplified Dependency Management: Starter projects bundle all necessary dependencies for common use cases, reducing the complexity of managing project dependencies.
- Production-Ready Applications: Comes with built-in support for monitoring, metrics, and health checks, making it easier to prepare applications for production environments.
- Rapid Development: Spring Boot accelerates development by providing tools like Spring Boot CLI and reducing boilerplate code.
## 3. What is (IOC) and what is (DI)?
- Inversion of Control (IoC): Inversion of Control is a design principle where the control of object creation and flow of a program is transferred from the developer to a framework or container. Rather than the application code controlling the flow and lifecycle of objects, a framework (like Spring) manages that for you. IoC decouples the execution of specific tasks from the implementation logic, making the system more flexible and modular.

- Dependency Injection (DI): Dependency Injection is a specific implementation of IoC. It refers to the process of supplying external dependencies (i.e., objects that a class needs to function) to a class. Rather than a class creating its own dependencies, they are provided by the IoC container, which injects them into the class at runtime.

## 4. What is `@ComponentScan`?
@ComponentScan is an annotation in Spring that tells the Spring framework where to look for Spring-managed components (beans), such as classes annotated with @Component, @Service, @Repository, or @Controller. It is used to configure the base packages to be scanned for these annotated components.

## 5.What is `@SpringBootApplication`?
@SpringBootApplication is a key annotation in Spring Boot that combines three commonly used Spring annotations into one. It is typically placed on the main class that starts a Spring Boot application. This annotation simplifies the setup by automatically configuring the application and making it easier to develop Spring-based applications with minimal configuration.

## 6. How many ways are there to define a bean? Provide code examples.
1. Using @Component
```
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Component Bean Method");
    }
}
```
2. Using @Service, @Repository, @Controller Annotations
```
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public void serve() {
        System.out.println("Service Bean Method");
    }
}
```
3. Using @Bean Annotation in @Configuration Class
```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyComponent myComponent() {
        return new MyComponent(); // Manually create and return the bean
    }
}
```
4. Using XML Configuration (Legacy Approach)
```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="myComponent" class="com.example.MyComponent"/>
</beans>
```
```
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyComponent myComponent = context.getBean(MyComponent.class);
        myComponent.doSomething();
    }
}
```
5.  Using @Autowired with Constructor, Setter, or Field Injection
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    private final MyService myService;

    @Autowired
    public MyComponent(MyService myService) {
        this.myService = myService;
    }

    public void doSomething() {
        myService.serve();
    }
}
```
6. Using @Lazy for Lazy Initialization
```
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MyLazyComponent {
    public MyLazyComponent() {
        System.out.println("Lazy Component Initialized");
    }

    public void doSomething() {
        System.out.println("Doing something lazily");
    }
}
```
7. Using Factory Methods
```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyComponent myComponent() {
        return MyComponentFactory.createComponent();
    }
}

public class MyComponentFactory {
    public static MyComponent createComponent() {
        // Custom logic to create a bean
        return new MyComponent();
    }
}
```




## 7. What is the default bean name for `@Component` and `@Bean`? Also, compare `@Component` and `@Bean`.
The default bean name for `@Component`: the class name with the first letter converted to lowercase.

The default bean name for `@Bean`: he name of the method that defines the bean.

@Component is used for automatic detection and registration of beans through classpath scanning.

@Bean provides more control over bean creation, allowing manual instantiation and configuration of beans, often used when you need to customize bean creation or work with external beans.



## 8. Compare `@Component`, `@Service`, `@Repository`, and `@Controller`.
- Use @Component when the class doesn’t fit into any of the other specialized categories (general-purpose Spring components).
- Use @Service for classes that contain business logic or service-related operations.
- Use @Repository for classes that interact with the database or data sources, benefiting from Spring’s exception handling.
- Use @Controller for web controllers in Spring MVC to handle incoming HTTP requests.

## 9. Explain `@Autowired`, `@Qualifier`, `@Resource`, and `@Primary`.
- @Autowired is an annotation in Spring used for automatic dependency injection. It allows Spring to resolve and inject the required dependencies automatically.
- @Qualifier is used along with @Autowired to resolve ambiguity when multiple beans of the same type are available in the Spring context. It helps specify which bean should be injected when more than one candidate bean is present.
- @Resource is part of the Java EE specification (JSR-250) and is used for dependency injection in Spring. It is similar to @Autowired but provides more fine-grained control by allowing injection by name first, then by type.
- @Primary is an annotation used to mark a bean as the primary bean to be injected when multiple beans of the same type exist. If no other qualifiers are used (like @Qualifier), the primary bean is injected by default.


## 10. How many annotations can we use to inject a bean?
@Autowired, @Qualifier, @Resource, @Primary, @Inject and @Named

## 11. Explain and compare different types of dependency injection, their pros and cons, and use cases.
| **Aspect**                    | **Constructor Injection**                       | **Setter Injection**                            | **Field Injection**                             |
|-------------------------------|-------------------------------------------------|-------------------------------------------------|-------------------------------------------------|
| **Immutability**               | Supports immutability; dependencies can be `final`. | Does not support immutability; dependencies are mutable. | Does not support immutability; dependencies are mutable. |
| **Object Completeness**        | Ensures object is fully initialized at creation. | Object may be incomplete or invalid until setters are called. | Object may be incomplete if dependencies are not injected. |
| **Testing Ease**               | Easy to mock dependencies by passing them via the constructor. | Can inject mocks using setters, but risk of incomplete state. | Hard to replace dependencies for testing, requires reflection or frameworks like Mockito. |
| **Visibility of Dependencies** | Explicit, as all dependencies are visible in the constructor. | Dependencies are somewhat hidden, as they are injected via setters. | Least visible, as dependencies are injected directly into fields. |
| **Code Verbosity**             | More verbose, especially with many dependencies. | Less verbose than constructors, but can still require multiple setters. | Least verbose, requires no constructors or setters. |
| **Flexibility**                | Rigid, as dependencies must be provided during construction. | More flexible, allows changing dependencies after object creation. | Very inflexible; dependencies are injected and not easily changed. |
| **Use Cases**                  | When all dependencies are required and should not change. | When dependencies are optional or configurable post-construction. | Simple or small applications, or for rapid development. |


## 12. If we have multiple beans for one type, how do we set one as primary? How does Spring IOC pick one bean to inject if there is no primary? Demonstrate with code examples.
In Spring, when there are multiple beans of the same type, you can use the @Primary annotation to designate one bean as the default (primary) bean to be injected. If no @Primary bean is set, Spring will throw an exception because it doesn’t know which bean to inject. However, you can resolve this ambiguity using the @Qualifier annotation to explicitly specify which bean should be injected.

1. using `@Primary`:
```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository1() {
        return new UserRepositoryImpl1();
    }

    @Bean
    @Primary  // This bean will be the default choice for UserRepository
    public UserRepository userRepository2() {
        return new UserRepositoryImpl2();
    }
}
```

2. without `@Primary`
```
@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository1() {
        return new UserRepositoryImpl1();
    }

    @Bean
    public UserRepository userRepository2() {
        return new UserRepositoryImpl2();
    }
}
```


## 13. Compare `BeanFactory` and `ApplicationContext` in the Spring framework.
- BeanFactory is a basic, lightweight container primarily for dependency injection, with lazy initialization of beans. It is suited for simple use cases or when memory constraints are critical.
- ApplicationContext builds on top of BeanFactory and provides more advanced features, such as eager loading, event propagation, internationalization, AOP, and environment support. It is the go-to container for most Spring applications, especially in enterprise scenarios.

## 14. Explain bean scope in Spring IOC. List bean scopes with explanations and code examples if possible.
bean scope refers to the lifecycle and visibility of a bean. The scope defines how long the bean instance lives, how it's created, and how many instances of the bean exist within the Spring IoC (Inversion of Control) container.

bean scope in Spring:
| **Scope**        | **Definition**                                                                 | **Use Case**                                    |
|------------------|--------------------------------------------------------------------------------|-------------------------------------------------|
| `singleton`      | One instance per Spring IoC container (default scope).                          | Stateless beans, services, DAOs.                |
| `prototype`      | A new instance is created each time the bean is requested.                      | Stateful beans, domain objects.                 |
| `request`        | One instance per HTTP request (Web-specific).                                   | Beans tied to a single HTTP request lifecycle.  |
| `session`        | One instance per HTTP session (Web-specific).                                   | Session-specific beans, like user session data. |
| `application`    | One instance per `ServletContext` (Web-specific).                               | Global beans shared across the entire application. |
| `websocket`      | One instance per WebSocket session (Web-specific).                              | WebSocket session-specific beans.               |


code example:
```
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("Singleton bean created");
    }
}
```


## 15. Write a Spring application that:
- Registers and autowires beans.
- Demonstrates different types of dependency injection.
- Demonstrates bean scopes.
- Demonstrates dependency injection by type and by name when there's ambiguity in bean definitions.
- Demonstrates bean registration by both `@Component` and `@Bean`.


see [HW9](../Coding/hw9/Hw9-SpringApplication/) in the Coding Folder.

## 16. Explain the builder pattern with code examples.

The Builder Pattern is a creational design pattern that provides a way to construct complex objects step by step. The pattern separates the construction of an object from its representation, allowing the same construction process to create different representations. This pattern is especially useful when a class has multiple fields, many of which are optional, making the constructor overly complex.

```
// Product class: Car
public class Car {
    private String engine;
    private int wheels;
    private String color;
    private String transmission;
    private boolean airBags;

    // Private constructor to restrict direct instantiation
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
        this.transmission = builder.transmission;
        this.airBags = builder.airBags;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheels=" + wheels +
                ", color='" + color + '\'' +
                ", transmission='" + transmission + '\'' +
                ", airBags=" + airBags +
                '}';
    }

    // Static nested Builder class
    public static class CarBuilder {
        private String engine;
        private int wheels;
        private String color;
        private String transmission;
        private boolean airBags;

        // Required parameters (for example: engine, wheels)
        public CarBuilder(String engine, int wheels) {
            this.engine = engine;
            this.wheels = wheels;
        }

        // Optional parameters with chainable methods
        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        public CarBuilder setAirBags(boolean airBags) {
            this.airBags = airBags;
            return this;
        }

        // Build method to create the final Car object
        public Car build() {
            return new Car(this);
        }
    }
}
```