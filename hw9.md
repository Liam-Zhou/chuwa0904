2. Compare Spring and Springboot? What are the benfits of Srpingboot?
Spring Framework: A comprehensive framework that provides a wide range of tools for building enterprise-level applications. It includes various features like dependency injection (DI), aspect-oriented programming (AOP), and integration with databases, messaging systems, etc. However, it requires a lot of manual configuration (like XML or Java-based configuration) to set up projects.

Spring Boot: Built on top of the Spring Framework, Spring Boot simplifies the development of Spring applications by eliminating the need for manual configuration. It provides default configurations and starters, making it easier and faster to get Spring-based applications up and running.

Benefits of Spring Boot:

Auto-Configuration: Spring Boot provides intelligent defaults and auto-configures many components based on the dependencies included, which reduces manual configuration.
Embedded Servers: It comes with embedded servers like Tomcat, Jetty, or Undertow, allowing you to run web applications directly without needing an external server.
Starter Dependencies: Spring Boot offers "starters" (e.g., spring-boot-starter-web, spring-boot-starter-data-jpa) that bundle commonly used dependencies and configurations.
Production-Ready Features: Includes production-ready features like metrics, health checks, and monitoring with minimal configuration.
Faster Development: By removing boilerplate setup code, Spring Boot accelerates the development process.
3. What is IOC and What is DI?
IoC (Inversion of Control): A design principle in which control over the flow of a program is inverted. In traditional programming, the application controls the flow and object creation. In IoC, the framework or container (like Spring) takes control of the program flow, managing object creation and lifecycle.

DI (Dependency Injection): A specific type of IoC where objects (dependencies) are injected into other objects (consumers) by the framework, rather than the objects creating those dependencies themselves. It allows loose coupling between components.
4. What is @CompnonentScan ?
@ComponentScan is a Spring annotation used to specify the packages to search for Spring-managed components (like @Component, @Service, @Repository, and @Controller).

By default, it scans the package where the class annotated with @SpringBootApplication resides and its sub-packages.
You can customize the packages to scan by providing them explicitly.
5. What is @SpringbootApplication ?
@SpringBootApplication is a convenience annotation in Spring Boot that combines three key annotations:

@Configuration: Marks the class as a source of bean definitions.
@EnableAutoConfiguration: Enables auto-configuration in Spring Boot, automatically configuring beans based on the classpath and existing beans.
@ComponentScan: Enables component scanning, automatically scanning the package where the application resides and its sub-packages for Spring components.
6. How many ways to define a bean? Provide code examples.
    - There are two main ways to define a Spring bean:

        Using @Component (or other stereotype annotations like @Service, @Repository):

        Spring will automatically scan the annotated classes and register them as beans.

    - Using @Bean in a @Configuration class:

        You can explicitly define beans in a configuration class using the @Bean annotation.

7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean .

Default Bean Name:
For @Component: The default bean name is the class name with the first letter in lowercase. For example, if the class is MyService, the default bean name will be myService.
For @Bean: The default bean name is the method name. For example, if the method is myService(), the bean name will be myService.

8. Compare @component and @service , @repository , @controller ?
All of these annotations are Spring stereotype annotations, meaning they mark a class as a Spring-managed component (i.e., a bean), but they have different use cases based on the layer they represent in the application:

@Component:
Generic annotation indicating that a class is a Spring-managed bean. It can be used anywhere, but doesn't specify a particular role.

@Service:

Specialized form of @Component, used to indicate that a class provides business logic.
Spring treats it as a service layer class, but technically behaves like @Component.

@Repository:

Specialized form of @Component, used for Data Access Objects (DAO). It also provides additional benefits like exception translation (converting database-specific exceptions into Spring's DataAccessException).

@Controller:

Specialized form of @Component, used in Spring MVC to define a web controller. It handles HTTP requests and maps them to appropriate handler methods.

9. Explain @Autowired , @Qualifier , @Resource and @Primary ?
@Autowired:

Used for automatic dependency injection. Spring will automatically inject the appropriate bean by type.

@Qualifier:

Used to resolve ambiguity when multiple beans of the same type exist. It specifies the exact bean to inject.

@Resource:

Part of the JSR-250 specification, it performs dependency injection by bean name (similar to @Autowired + @Qualifier combined).

@Primary:

Marks a bean as the primary candidate for injection when multiple beans of the same type are present. Used to resolve ambiguity.

10. How many annotaitons we can use to inject a bean?
You can use the following annotations to inject a bean in Spring:

@Autowired: Default Spring annotation for dependency injection (by type).
@Resource: JSR-250 annotation (by name, optionally by type).
@Inject: JSR-330 annotation (similar to @Autowired, but more standard).
@Value: Injects primitive values or properties.

11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.

There are three main types of Dependency Injection in Spring:

Constructor Injection:

Dependencies are provided through the constructor.
Pros: Immutable fields, promotes testability, can enforce mandatory dependencies.
Cons: Can be verbose if there are many dependencies.
Use Case: Best for mandatory dependencies.

Setter Injection:

Dependencies are provided via setter methods.
Pros: Optional dependencies are easy to inject.
Cons: Can lead to mutable state.
Use Case: When dependencies are optional or when there are many dependencies.

Field Injection:

Dependencies are injected directly into the fields.
Pros: Less boilerplate, simple.
Cons: Difficult to test, as dependencies are hidden from constructors.
Use Case: Quick and simple use cases, though discouraged in favor of constructor injection.

12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to
inject if no primay, demo with code examples.
Setting a Primary Bean: You can use the @Primary annotation to mark a bean as the default choice.
If there are multiple beans and no @Primary or @Qualifier is specified, Spring will throw a NoUniqueBeanDefinitionException because it doesn’t know which bean to choose.
13. Compare BeanFactory and ApplicationContext in Spring framework?
BeanFactory:

The core container of Spring, responsible for managing beans and their lifecycles.
Lazy Initialization: Beans are created only when requested.
Limited Functionality: No support for advanced features like event propagation, declarative transactions, or AOP.
ApplicationContext:

A superset of BeanFactory, providing all BeanFactory features plus additional functionality like event handling, AOP, and internationalization.
Eager Initialization: All singleton beans are instantiated at startup.
Supports more enterprise-level features like environment abstraction, internationalization, and lifecycle callbacks.
14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
Spring provides different bean scopes, which determine the lifecycle and visibility of a bean.

Singleton (default):

A single instance of the bean is created and shared throughout the application.

Prototype:

A new instance is created each time the bean is requested.

Request (web):

A new bean instance is created for each HTTP request (available in Spring MVC and Spring WebFlux).

Session (web):

A new bean instance is created for each HTTP session.

Global Session (portlet environment):

A bean is scoped to the global session (used in portlet-based applications).
Application (web):

Bean is scoped to the lifecycle of the servlet context.
Use Case: Shared data across the entire application.