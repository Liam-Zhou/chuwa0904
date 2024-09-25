#### 1. List all of the annotations you learned from class and homework to annotaitons.md (your own cheatsheet).
#### 2. Compare Spring and Springboot? What are the benfits of Srpingboot?
**Spring** is a comprehensive framework that provides a wide range of features such as dependency injection (via Spring Core), aspect-oriented programming (via Spring AOP), data access (via Spring Data, Spring ORM), transaction management, and more. It provides great flexibility but requires significant configuration to set up applications, especially for larger projects.
**Spring Boot**, on the other hand, is a layer built on top of the Spring Framework. It simplifies the development of Spring applications by reducing the boilerplate code and configuration required to set up a Spring project. Spring Boot provides embedded servers, starter templates, and many auto-configuration options, allowing developers to focus more on business logic than on the configuration setup.

#### 3. What is IOC and What is DI?
**Inversion of Control (IoC)**: A design principle where the control of object creation and dependency management is transferred from the application code to a framework (like Spring). The framework manages the lifecycle of objects, reducing the need for manual instantiation.
**Dependency Injection (DI)**: A specific technique to implement IoC, where dependencies (objects or services) are injected into a class by the framework, rather than the class creating them itself. DI can be achieved through constructor injection, setter injection, or field injection.

#### 4. What is @CompnonentScan ?
@ComponentScan is an annotation in Spring used to automatically detect and register beans (components, services, repositories, controllers, etc.) in the application context by scanning specified packages. It tells Spring where to look for classes annotated with stereotype annotations like @Component, @Service, @Repository, and @Controller, and automatically registers them as beans.

#### 5. What is @SpringbootApplication ?
@SpringBootApplication is a key annotation in Spring Boot that serves as a convenience annotation, combining several other annotations to simplify the configuration of a Spring Boot application. It is typically placed on the main application class and marks it as the starting point of a Spring Boot application.

#### 6. How many ways to define a bean? Provide code examples.
Using @Component (and related annotations like @Service, @Repository, @Controller):
This is the most straightforward way of defining a Spring bean, where the class is automatically detected and registered as a bean during component scanning.
```
@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Doing something in MyComponent");
    }
}
```
Using @Bean in a @Configuration class:
This approach is more explicit and allows for more control over bean instantiation.
```
@Configuration
public class AppConfig {

    @Bean
    public MyComponent myComponent() {
        return new MyComponent();
    }
    
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```
Using XML configuration (older method):
Although not as common in newer Spring applications, beans can also be defined using XML configuration.
```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="myComponent" class="com.example.MyComponent"/>
    <bean id="myService" class="com.example.MyService"/>

</beans>
```

#### 7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean
@Component Default Bean Name:
When you define a bean using @Component, the default bean name is the class name with the first letter lowercased.
@Bean Default Bean Name:
When using @Bean in a @Configuration class, the default bean name is the name of the method that defines the bean.
Aspect	                                                     @Component	                                                             @Bean
Purpose	                      Automatically registers a class as a Spring bean via component scanning.	           Explicitly defines a bean method inside a @Configuration class.
Default Bean Name	          Class name with the first letter lowercased (e.g., MyService becomes myService).	   Method name defining the bean (e.g., method myService() becomes myService).
Usage	                      Applied at the class level (@Component, @Service, @Repository, @Controller).	       Applied to methods inside @Configuration classes.
Control Over Instantiation	  Limited control over instantiation, with no custom logic for object creation.	       Full control over bean creation, including complex logic, parameterized configuration, and dependency injection.
When to Use	                  For lightweight and automatic bean detection when no specific configuration is needed.	For manually configuring beans, creating instances of third-party classes, or applying custom initialization logic.

#### 8. Compare @component and @service , @repository , @controller ?
@Component: It is the most generic annotation and can be applied to any class to indicate that it is a Spring-managed bean.
@Service: It is a specialization of @Component that indicates a class contains business logic or service layer functionality.
@Repository: It indicates that the class is a Data Access Object (DAO). It's responsible for interacting with the database and handling persistence logic.
@Controller: It marks a class as a Spring MVC controller, meaning it's responsible for handling HTTP requests in a web application.

#### 9. Explain @Autowired , @Qualifier , @Resource and @Primary ?
@Autowired: Automatically injects a Spring-managed bean into another bean.
@Qualifier: Helps Spring resolve ambiguity when multiple beans of the same type exist by specifying which bean to inject.
@Resource: Another way of performing dependency injection. Unlike @Autowired, which performs by-type injection, @Resource performs by-name injection by default.
@Primary: Marks a bean as the primary candidate for injection when there are multiple beans of the same type, making it the default unless explicitly overridden by @Qualifier or @Resource.

#### 10. How many annotaitons we can use to inject a bean?
The most common annotations used for injecting beans in Spring are @Autowired, @Qualifier, @Resource, and @Inject.
Additionally, @Primary is used for resolving conflicts when multiple beans of the same type exist.
@Value can also be used for injecting external configuration values

#### 11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.
1. Constructor Injection
Description: Dependencies are provided through a class constructor.
How It Works: When creating an instance of the class, the required dependencies are passed as parameters to the constructor.
Pros:
Immutability: Dependencies can be marked as final, making the object immutable after construction.
Required Dependencies: Clearly indicates which dependencies are required for the class to function properly, promoting better design.
Easier Testing: Makes it easier to pass mock or stub implementations during testing.
Cons:
Constructor Overloading: If a class has many dependencies, the constructor can become unwieldy.
Circular Dependencies: Can lead to circular dependency issues, which may require refactoring.

2. Setter Injection
Description: Dependencies are provided through setter methods after the object is constructed.
How It Works: The Spring container calls the setter methods to inject dependencies.
Pros:
Optional Dependencies: Allows for optional dependencies, as setter methods can be left uncalled.
Flexible Configuration: Can change the dependencies after the object is constructed.
Cons:
Not Immutable: Does not allow for immutable objects since dependencies can be changed post-construction.
Less Clear: May not clearly indicate required dependencies compared to constructor injection.

3. Field Injection
Description: Dependencies are injected directly into the class fields, bypassing the constructor or setter methods.
How It Works: The Spring container injects dependencies directly into the fields marked with @Autowired.
Pros:
Concise Code: Reduces boilerplate code since you don’t need explicit constructors or setter methods.
Less Verbose: Easier to read and write for classes with many dependencies.
Cons:
Hidden Dependencies: Makes it harder to identify dependencies just by looking at the constructor or method signatures.
Difficult Testing: More challenging to test, as you cannot easily provide mock dependencies without using reflection or frameworks like Mockito.
Lacks Immutability: Dependencies can change at any time after instantiation

#### 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.
In Spring, when you have multiple beans of the same type and want to designate one as the primary bean for dependency injection, you can use the @Primary annotation. If no primary bean is designated, Spring will throw an exception when trying to autowire the bean, as it won’t know which one to inject.

#### 13. BeanFactory is best suited for lightweight applications where simplicity and resource conservation are priorities. It offers only the basic features of a Spring container.
ApplicationContext is recommended for most applications due to its additional capabilities, such as event handling, internationalization, and support for AOP. It provides a richer feature set and is typically the preferred choice for building modern Spring applications.

#### 14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
1. Singleton Scope
Description: This is the default scope. A single instance of the bean is created and shared across the entire Spring container.
Lifecycle: The bean is instantiated once per Spring IoC container and exists for the duration of the application context.
2. Prototype Scope
Description: A new instance of the bean is created each time it is requested from the Spring container.
Lifecycle: The bean is instantiated every time it is requested, meaning each client gets a different instance.
3. Request Scope
Description: A new instance of the bean is created for each HTTP request. This scope is applicable only in web applications.
Lifecycle: The bean exists for the duration of an HTTP request, and a new instance is created for every request.
4. Session Scope
Description: A new instance of the bean is created for each HTTP session. This is also applicable only in web applications.
Lifecycle: The bean exists for the duration of an HTTP session, and a new instance is created for each session.
5. Global Session Scope
Description: A new instance of the bean is created for each global HTTP session. This scope is mainly used in portlet-based web applications.
Lifecycle: The bean exists for the duration of a global session and is shared across multiple portlets in the same session.
6. Application Scope
Description: A single instance of the bean is created for the lifecycle of a ServletContext. This scope is applicable only in web applications.
Lifecycle: The bean exists for the duration of the application context and is shared across multiple HTTP requests and sessions.

#### 15. Write a Spring application that registers and autowires beans,
Demo different types of dependency injection
Demo bean scopes.
Demo dependency injection by type and by name, when there's ambiguity in bean definition.
Demo bean registration by both @Component and @Bean
#### 16. Explain builder pattern with code examples.
The Builder Pattern is a design pattern used to create complex objects step by step. It is particularly useful when an object needs to be created with many optional parameters or when the construction process involves multiple steps. The Builder Pattern allows for more readable and maintainable code, separating the construction of a complex object from its representation.
**Step 1: Define the Product**
```
public class Pizza {
    private String dough;
    private String sauce;
    private String topping;

    // Private constructor to restrict direct instantiation
    private Pizza(PizzaBuilder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.topping = builder.topping;
    }

    @Override
    public String toString() {
        return "Pizza [dough=" + dough + ", sauce=" + sauce + ", topping=" + topping + "]";
    }

    // Static Builder Class
    public static class PizzaBuilder {
        private String dough;
        private String sauce;
        private String topping;

        public PizzaBuilder setDough(String dough) {
            this.dough = dough;
            return this;
        }

        public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder setTopping(String topping) {
            this.topping = topping;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}
```
**Step 2: Using the Builder**
```
public class BuilderPatternExample {
    public static void main(String[] args) {
        // Creating a Pizza object using the Builder pattern
        Pizza pizza = new Pizza.PizzaBuilder()
                .setDough("Thin Crust")
                .setSauce("Tomato Sauce")
                .setTopping("Mozzarella Cheese")
                .build();

        System.out.println(pizza); // Output: Pizza [dough=Thin Crust, sauce=Tomato Sauce, topping=Mozzarella Cheese]
    }
}
```