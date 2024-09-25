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

