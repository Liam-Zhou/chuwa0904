## HW 9

### Question 2
#### Spring
* Requires extensive manual configuration.
* You need to manage server deployment (e.g., Tomcat, Jetty) manually and handle a lot of boilerplate code.
* Spring applications require you to set up configuration files for beans, data sources, view resolvers, etc., which takes time.
#### Spring boot
* Spring Boot provides auto-configuration, which automatically configures components based on the classpath and environment, making it faster to set up.
* Spring Boot provides starter POMs (like spring-boot-starter-web, spring-boot-starter-data-jpa) to simplify dependency management for common use cases.
* Spring Boot also have Spring Boot Actuator provides built-in monitoring, metrics, health checks, and other production-ready features.

### Question 3
* IoC (Inversion of Control) is a design principle where the control of object creation and the flow of a program is handed over to a framework or container, rather than being managed by the application itself. With IoC, the responsibility for creating and managing these objects (beans) is transferred to the Spring IoC container.
- Dependency Injection (DI) is a specific form of IoC where the dependencies (objects or services a class needs to function) are provided by an external entity, typically a framework or container, instead of the class creating them itself. DI is a way to implement IoC, enabling objects to become independent of how their dependencies are created.
There are three primary ways to inject dependencies in Spring:
* Constructor Injection: Dependencies are provided through a class constructor.
* Setter Injection: Dependencies are provided through setter methods.
* Field Injection： The dependency is injected directly into the field.

### Question 4
@ComponentScan is a Spring annotation used to specify the base packages to scan for Spring-managed components, such as @Component, @Service, @Repository, and @Controller annotations. When Spring's IoC container starts, it automatically scans the specified packages for classes annotated with these stereotype annotations and registers them as beans in the application context.
By default, @ComponentScan is used with the @Configuration or @SpringBootApplication annotations to tell Spring where to look for beans and components that it should manage.


### Question 5
@SpringBootApplication is a central annotation in Spring Boot applications. It is a convenience annotation that combines three key Spring annotations into one:
* **@SpringBootConfiguration**: A specialized version of @Configuration, used to mark the class as a configuration class for Spring Boot. It allows the class to define bean methods that are managed by the Spring container.
* **@EnableAutoConfiguration**: Enables Spring Boot’s auto-configuration mechanism, which automatically configures Spring components based on the classpath dependencies and properties. It reduces the need for manual configuration by automatically setting up common beans (e.g., data sources, MVC controllers, etc.).
* **@ComponentScan**: Scans the package where the application is located (and its sub-packages) for Spring-managed components such as @Controller, @Service, @Repository, and @Component. This helps Spring automatically discover and register beans.

 It simplifies the configuration of a Spring Boot application. It is typically used on the main class of a Spring Boot application.

 ### Question 6
1. You can define a bean by annotating a class with @Component (or @Service, @Repository, @Controller for readablity).
2. Using @Bean Method with @configuration and @ComponentScan
3. Using XML Configuration
4. Using @Import Annotation

### Question 7
When defining a bean using the **@Bean** annotation in a @Configuration class, the default bean name is the name of the method that defines the bean.
**@Component** cannot be used for third-party libraries or external classes because you cannot modify those classes by adding annotations.
**@Bean** is the preferred way for third-party libraries, as you can configure these beans in the @Configuration class even if you don’t control the source code.

### Question 8
@Component, @Service, @Repository, and @Controller are stereotype annotations used to indicate that a class is a Spring-managed bean. 
* @Component: Generic annotation for any Spring-managed bean.
* @Service: Marks a class as a service, typically containing business logic.
* @Repository: Marks a class as a data access object (DAO), responsible for database interaction.
* @Controller: Marks a class as a Spring MVC controller, handling HTTP requests and returning responses.

### Question 9
* **@Autowired** is used to automatically inject beans by type. Spring looks for a bean of a matching type in the IoC container and injects it into the class where **@Autowired** is applied.
* **@Qualifier**is used in conjunction with **@Autowired** when there are multiple beans of the same type, and you want to specify which bean should be injected. While **@Autowired** resolves dependencies by type, **@Qualifier** allows for fine-grained control by specifying the bean name to inject.
* **@Resource** is part of **javax.annotation** package and can be used for dependency injection in Spring. It injects beans by name, unlike **@Autowired**, which injects by type. However, if **@Resource** cannot find a bean by name, it will fallback to type-based injection.
* **@Primary** is used to mark a bean as the primary candidate when there are multiple beans of the same type. When Spring finds multiple beans of the same type, it uses the bean marked with **@Primary**as the default bean to inject (unless another bean is specified using **@Qualifier**).

### Question 10
* @Autowired
* @Qualifier
* @Resource
* @Inject
* @Primary

### Question 11
1. Constructor Injection:
   * Immutability: Since dependencies are provided when the object is created, you can declare fields as final, which enforces immutability.  Immutable objects are easier to understand and maintain because they do not have unexpected state changes.
   * Clear Dependencies: The class’s constructor clearly declares what dependencies it needs, making it easy to understand the class’s dependencies at a glance.
   - Too Many Parameters: If a class has many dependencies, the constructor can become long and difficult to manage (constructor overloading).
2. Setter Injection:
   * Optional Dependencies: Setter injection is ideal for optional dependencies. You can instantiate the object without providing the dependency and set it later.
   * Flexibility: Setter methods can be used to update dependencies at runtime, providing more flexibility than constructor injection.
   - Lack of Immutability: Dependencies can be changed after object creation, which can lead to mutable state and side effects if not handled properly.
3. Field Injection
   * Simplicity: Field injection is easy to implement as it requires minimal boilerplate code. The dependencies are directly injected into fields without the need for setters or constructors.
   - Testability: It’s harder to write unit tests because the dependencies are private, and injecting mocks during testing often requires using reflection or Spring’s testing utilities.
   - ack of Immutability: The injected fields can be mutable, and it’s not clear when or where the dependency is set.

### Question 12
We try to set primary under **@Component**:
```
@Component
@Primary
public class HibernateChuwa implements JpaChuwa {

    @Override
    public void printMessage() {
        System.out.println("Message from " + getClass().getName());
    }
}
```
If No Bean is Marked @Primary, Spring will choose according to the @Qualifier,or variable name. It Spring cannot determine through varible name, Spring will throw a NoUniqueBeanDefinitionException.

### Question 13

| Feature | Bean Factory | ApplicationContext |
| -------- | ------- |------- |
| Basic | Basic IoC container, provides fundamental DI features.| Advanced IoC container, built on BeanFactory with more features|
|Initialization|Beans are created lazily|Beans are created eagerly (at startup) by default.|
| Event Handling | No support for event handling.| Provides support for application events and listener registration.|
| Automatic Bean Discovery | No automatic scanning; beans must be defined explicitly.| Supports component scanning, automatically detects beans annotated with @Component, @Service, etc.|  

### Question 14
1. **Singleton**: In the **Singleton** scope, Spring creates only **one instance** of the bean per Spring IoC container. The same instance is returned every time the bean is requested.
2. **Prototype**: In the **Prototype** scope, a new instance of the bean is created every time the bean is requested from the Spring IoC container.