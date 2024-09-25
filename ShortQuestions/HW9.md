# HW9

Yuhang Li

## 1. List all of the annotations you learned from class and homework to annotaitons.md (you rown cheatsheet).

Please refer to the annotations.md

## 2.Compare Spring and Spring boot? What are the benfits of Spring boot?

**Spring Framework** and **Spring Boot** are both part of the Spring ecosystem but serve different purposes. Here's a comparison of the two:

### 1. **Spring Framework**
   - **Purpose**: A comprehensive framework for building enterprise-level Java applications. It provides tools for various features like dependency injection, aspect-oriented programming, transaction management, and data access.
   - **Configuration**: Requires extensive configuration through XML or Java annotations. You need to manually define beans, set up the dispatcher servlet, and configure security, database connections, etc.
   - **Flexibility**: Offers a high level of flexibility and control over how applications are configured, but this comes with increased complexity.
   - **Modular**: The framework is modular, allowing developers to choose which modules they want (e.g., Spring Security, Spring Data).

### 2. **Spring Boot**
   - **Purpose**: An extension of Spring that aims to simplify the setup and development of Spring applications by reducing boilerplate code and configurations.
   - **Configuration**: It provides **auto-configuration** features that automatically configure Spring components based on the dependencies on the classpath. This greatly reduces manual configuration.
   - **Embedded Server**: Includes an embedded Tomcat (or Jetty) server, meaning you don't need to deploy the application to an external server. You can just run your Spring Boot application as a standalone Java application.
   - **Opinionated Defaults**: Spring Boot provides sensible defaults, reducing the amount of decision-making required during setup, which makes it easier to start new projects.
   - **Actuator**: Includes **Spring Boot Actuator**, which provides production-ready features like health checks, metrics, and monitoring out of the box.

### **Benefits of Spring Boot**
1. **Quick Setup**: Spring Boot drastically simplifies project setup with **auto-configuration**, meaning developers can start projects faster with minimal initial setup.
2. **Reduced Boilerplate Code**: It reduces the need for boilerplate code and XML configuration, allowing developers to focus on business logic rather than configuration details.
3. **Embedded Servers**: You can package the application as a **self-contained JAR** or WAR file with an embedded web server (like Tomcat), simplifying the deployment process.
4. **Microservices Ready**: Spring Boot is particularly well-suited for building **microservices** due to its lightweight nature and embedded server support.
5. **Actuator and Monitoring**: Out-of-the-box support for **monitoring, health checks, and metrics** via the Actuator module.
6. **Starter Dependencies**: Provides **Spring Boot Starters**, which are sets of pre-configured dependencies, so you don’t have to manually define dependencies in your `pom.xml` or `build.gradle`.
7. **Convention over Configuration**: Spring Boot follows the principle of **convention over configuration**, making it easier to get things done without the hassle of manually configuring every part of the application.

In summary, Spring Boot makes Spring development easier by automating much of the configuration and setup process, especially for small or microservice-based applications, while Spring Framework offers more flexibility and control when needed.

## 3. What is IOC and What is DI?

**Inversion of Control (IoC)** and **Dependency Injection (DI)** are core principles in the Spring Framework that help decouple components in a system, making them more modular and easier to manage.

### 1. **Inversion of Control (IoC)**
   - **Definition**: IoC is a design principle where the control of creating and managing objects is transferred from the programmer to a framework or container. Instead of manually instantiating objects and managing their dependencies, a framework (like Spring) takes over this responsibility.
   - **Explanation**: Traditionally, in a tightly-coupled system, objects directly instantiate other objects they depend on. In an IoC system, objects are created by an external entity (the IoC container), and dependencies are provided rather than created within the object itself.
   - **Example**: In a Spring application, the IoC container (typically the `ApplicationContext`) is responsible for creating and managing the beans (objects) and wiring them together.
   
   - **Benefit**: IoC enables better separation of concerns and makes the system more flexible and testable, as objects don't manage their own dependencies or lifecycle.

### 2. **Dependency Injection (DI)**
   - **Definition**: DI is a specific implementation of the IoC principle. It refers to the process where the IoC container injects the dependencies (objects) that a class needs, rather than the class creating them itself.
   - **Types of DI**:
     - **Constructor Injection**: Dependencies are provided through a class constructor.
     - **Setter Injection**: Dependencies are provided via setter methods.
     - **Field Injection**: Dependencies are injected directly into fields (commonly used but discouraged as it is harder to test and lacks clarity compared to constructor injection).

   - **Example**:
     ```java
     @Component
     public class UserService {
         private final UserRepository userRepository;
         
         // Constructor Injection
         public UserService(UserRepository userRepository) {
             this.userRepository = userRepository;
         }
     }
     ```

   - **Benefit**: DI reduces coupling between components, making the code easier to test and maintain. It also promotes reusability by allowing components to be easily swapped.

### Relationship Between IoC and DI:
   - **IoC** is the overarching principle that gives control over object creation and management to a container or framework.
   - **DI** is one way of achieving IoC by injecting dependencies into objects, rather than the objects creating dependencies themselves.

In summary, **IoC** hands over control of object creation to a container, while **DI** allows dependencies to be injected into objects, promoting loose coupling and flexibility in application development.

## 4. What is @CompnonentScan ?

`@ComponentScan` is an annotation in the Spring Framework used to specify the packages that the Spring container should scan for components, configurations, and beans to be registered in the application context.

### Purpose of `@ComponentScan`:
When Spring starts, it automatically scans for classes annotated with Spring stereotypes like `@Component`, `@Service`, `@Repository`, and `@Controller` to detect and register beans in the application context. The `@ComponentScan` annotation tells Spring where to look for these components.

### Key Details:
- **Default Behavior**: If no specific package is specified, `@ComponentScan` scans the package of the class that declares this annotation and all of its sub-packages.
- **Custom Package Scanning**: You can define custom base packages to scan by specifying them explicitly using the `basePackages` or `basePackageClasses` attributes.

### Example Usage:

#### 1. **Basic Usage** (Scanning the current package and sub-packages):
```java
@Configuration
@ComponentScan
public class AppConfig {
    // Spring will scan the current package and all its sub-packages for Spring components
}
```

#### 2. **Specifying Packages to Scan**:
```java
@Configuration
@ComponentScan(basePackages = {"com.example.service", "com.example.repository"})
public class AppConfig {
    // Spring will scan these specific packages for components
}
```

#### 3. **Using Classes to Specify Packages**:
```java
@Configuration
@ComponentScan(basePackageClasses = {UserService.class, UserRepository.class})
public class AppConfig {
    // Spring will scan the packages containing these classes
}
```

### Common Annotations Detected by `@ComponentScan`:
- **@Component**: Generic stereotype for any Spring-managed component.
- **@Service**: A specialization of `@Component`, used to indicate service layer components.
- **@Repository**: A specialization of `@Component`, used to indicate data access objects (DAOs).
- **@Controller**: A specialization of `@Component`, used in Spring MVC for controllers.

### Benefits of `@ComponentScan`:
- **Automatic Bean Detection**: Simplifies bean configuration by automatically detecting and registering beans, avoiding the need for manual `@Bean` declarations.
- **Modularity**: Enables modularization by scanning different packages, which promotes better separation of concerns and cleaner project structure.
  

In summary, `@ComponentScan` tells Spring which packages to scan for annotated components, allowing for automatic registration of beans in the application context, making the configuration process more streamlined and less error-prone.

## 5. What is @SpringbootApplication ?

`@SpringBootApplication` is a key annotation in Spring Boot that serves as a convenient shorthand for enabling several configurations and features needed to set up a Spring Boot application. It is essentially a combination of three commonly used annotations, making it easier to configure and start the application with minimal setup.

### Composition of `@SpringBootApplication`:
The `@SpringBootApplication` annotation combines the following three annotations:

1. **`@Configuration`**: Marks the class as a source of bean definitions for the Spring application context. This means the class is used to define application-level beans and settings.

2. **`@EnableAutoConfiguration`**: Enables Spring Boot’s auto-configuration mechanism. It automatically configures your Spring application based on the dependencies you have in your project, making setup much simpler. For example, if you have `spring-boot-starter-web` in your project, it automatically configures a web server like Tomcat and sets up Spring MVC.

3. **`@ComponentScan`**: This tells Spring to scan the package where the annotated class is located (and its sub-packages) for Spring components, such as beans annotated with `@Component`, `@Service`, `@Repository`, and `@Controller`. This helps to automatically discover and register these components in the Spring context.

### Usage Example:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApp.class, args);
    }
}
```

In this example:
- `@SpringBootApplication` automatically enables Spring’s configuration, component scanning, and auto-configuration, making the application ready to run with minimal manual configuration.
- The `main()` method uses `SpringApplication.run()` to launch the Spring Boot application.

### Benefits of `@SpringBootApplication`:

1. **Simplified Configuration**: Instead of needing to use `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` separately, `@SpringBootApplication` combines them into one convenient annotation.
   
2. **Auto-Configuration**: With `@EnableAutoConfiguration`, Spring Boot automatically configures beans and components based on what’s in the classpath, simplifying the need for manual configuration.

3. **Component Scanning**: By default, it scans the current package and all sub-packages for Spring components, making it easier to manage large applications.

4. **Customizability**: While `@SpringBootApplication` enables auto-configuration, developers can override or exclude certain auto-configurations if needed using annotations like `@EnableAutoConfiguration(exclude = ...)`.

### Customization Example (Excluding Auto-Configuration):
```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MySpringBootApp {
    // Excludes a specific auto-configuration
}
```

In summary, `@SpringBootApplication` is a meta-annotation that simplifies the setup of Spring Boot applications by combining configuration, component scanning, and auto-configuration into a single annotation, significantly reducing boilerplate code.

## 6. How many ways to define a bean? Provide code examples.

In Spring, a **bean** is an object that is managed by the Spring IoC (Inversion of Control) container. There are several ways to define and configure beans, each suitable for different scenarios. Below are the common ways to define a bean with code examples.

### 1. **Using `@Component` Annotation**
   The `@Component` annotation is used to mark a class as a Spring bean. It works alongside `@ComponentScan` or `@SpringBootApplication` to automatically detect and register the bean.

   **Example**:
   ```java
   @Component
   public class MyBean {
       public void doSomething() {
           System.out.println("Bean is doing something");
       }
   }
   ```
   This bean will be automatically picked up by Spring if the package is included in the `@ComponentScan`.

### 2. **Using Stereotype Annotations (`@Service`, `@Repository`, `@Controller`)**
   These annotations are specializations of `@Component` and serve specific roles in Spring applications.
   - `@Service`: Used for service layer components.
   - `@Repository`: Used for data access components.
   - `@Controller`: Used in Spring MVC for handling web requests.

   **Example (using `@Service`)**:
   ```java
   @Service
   public class MyService {
       public String process() {
           return "Processing...";
       }
   }
   ```

### 3. **Using `@Bean` Annotation in a Configuration Class**
   You can define a bean by manually specifying it in a Java configuration class using the `@Bean` annotation. This approach is useful when you want full control over the bean creation logic.

   **Example**:
   ```java
   @Configuration
   public class AppConfig {
       
       @Bean
       public MyBean myBean() {
           return new MyBean(); // Manually creating and returning the bean
       }
   }
   ```

   - The method annotated with `@Bean` returns an instance of the bean, and the method name is used as the bean name by default.

### 4. **Using XML Configuration**
   Spring allows beans to be defined in an XML configuration file, which was a more common approach before Spring Boot popularized Java-based configuration.

   **Example (`applicationContext.xml`)**:
   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans 
                              http://www.springframework.org/schema/beans/spring-beans.xsd">
       
       <bean id="myBean" class="com.example.MyBean"/>
   </beans>
   ```

   - The `id` attribute specifies the bean name, and the `class` attribute specifies the fully qualified class name of the bean.

### 5. **Using `@Configuration` with `@Component` and `@Bean` Together**
   You can mix both `@Component` scanning and `@Bean` method definitions for different levels of control.

   **Example**:
   ```java
   @Configuration
   @ComponentScan("com.example")
   public class AppConfig {
       
       @Bean
       public MyBean myBean() {
           return new MyBean();  // Defining a bean using @Bean
       }
   }
   ```

   In this example, Spring will scan for `@Component`-annotated classes in the `com.example` package and also register beans defined by `@Bean` methods.

### 6. **Using Factory Methods**
   A bean can be created using factory methods. This can be achieved with either `@Bean` or XML configuration.

   **Example** (using `@Bean` with a factory method):
   ```java
   @Configuration
   public class AppConfig {
       
       @Bean
       public MyBean myBean() {
           return MyBeanFactory.createBean(); // Using a factory method to create the bean
       }
   }
   ```

   **Example** (using XML with a factory method):
   ```xml
   <bean id="myBean" class="com.example.MyBeanFactory" factory-method="createBean"/>
   ```

### 7. **Using `@Scope` for Bean Scope**
   Spring beans by default are singleton-scoped, but you can change the scope using the `@Scope` annotation, typically used with `@Component` or `@Bean`.

   **Example**:
   ```java
   @Component
   @Scope("prototype")
   public class MyPrototypeBean {
       // This bean will have prototype scope, meaning a new instance is created each time
   }
   ```

### Summary of Ways to Define Beans:
1. **Using `@Component`** (or its specialized annotations: `@Service`, `@Repository`, `@Controller`)
2. **Using `@Bean`** in a `@Configuration` class
3. **Using XML Configuration**
4. **Using Factory Methods**
5. **Using `@Scope`** to modify bean scope

Each method provides flexibility for different use cases, allowing you to define beans in a way that suits your application design.

## 7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean.

### Default Bean Names for `@Component` and `@Bean`

1. **`@Component` Default Bean Name**:
   - The default name for a bean defined with `@Component` is the **class name** with the **first letter lowercased**.
   - **Example**:
     ```java
     @Component
     public class MyService {
         // Default bean name will be "myService"
     }
     ```
   - If you need a custom name, you can explicitly specify it:
     ```java
     @Component("customService")
     public class MyService {
         // Bean name will be "customService"
     }
     ```

2. **`@Bean` Default Bean Name**:
   - The default bean name for a method annotated with `@Bean` is the **method name**.
   - **Example**:
     ```java
     @Configuration
     public class AppConfig {
     
         @Bean
         public MyService myService() {
             return new MyService();
         }
     }
     ```
   - Here, the default bean name is `myService`, which is the method name.
   - You can also explicitly specify a bean name:
     ```java
     @Bean(name = "customService")
     public MyService myService() {
         return new MyService();
     }
     ```

### Comparison Between `@Component` and `@Bean`

| Aspect                | `@Component`                                                 | `@Bean`                                                      |
| --------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Usage**             | Marks a class as a Spring-managed bean automatically discovered via classpath scanning (with `@ComponentScan`). | Used within a `@Configuration` class to manually define a bean by returning an instance from a method. |
| **Bean Creation**     | Spring automatically manages bean creation when the class is annotated. | The developer controls how the bean is created within the method body. |
| **Default Bean Name** | The class name with the first letter in lowercase.           | The method name by default.                                  |
| **Flexibility**       | Automatic and minimal boilerplate. Primarily for simple, stateless components like services, repositories, controllers. | Allows for more control over the creation logic, useful for complex configuration or when beans depend on external parameters. |
| **Dependencies**      | Automatically injected through constructor or setter injection (if `@Autowired` or similar annotations are used). | Manually injected and created inside the method body. Can use method parameters to inject dependencies. |
| **When to Use**       | Use for simple, stateless, or commonly used components that are automatically discovered and registered by Spring. | Use when more control is needed over bean instantiation, especially for third-party library classes or complex creation logic. |
| **Example**           | `@Component` annotated classes are automatically registered as beans during component scanning. | Beans are explicitly defined in a method within a class annotated with `@Configuration`. |
| **Scope Management**  | Can use `@Scope` to specify bean scope like singleton, prototype, etc. | Can use `@Scope` with methods, but typically managed manually through method creation logic. |

### Example Comparisons:

#### `@Component` Example:
```java
@Component
public class MyService {
    // Spring will create and manage an instance of MyService automatically.
}
```

#### `@Bean` Example:
```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyService(); // You control the bean creation process.
    }
}
```

### Key Differences:
- **Automatic vs Manual Bean Definition**: `@Component` is part of Spring’s automatic scanning process, making it easier and quicker for common components. `@Bean` is used for explicit, manual bean creation in `@Configuration` classes, which provides more control over how the bean is instantiated.
- **Third-Party Libraries**: `@Bean` is typically used when you need to create beans from third-party classes or when you need more customization during the bean creation process (e.g., setting specific properties).
- **Granularity**: `@Bean` provides fine-grained control over how the bean is initialized, whereas `@Component` is more straightforward and designed for regular application components.

In summary:
- **Use `@Component`** for typical Spring-managed components that are easily discovered and auto-configured.
- **Use `@Bean`** for fine-tuned, manual configuration of beans, especially when working with third-party libraries or when specific initialization logic is needed.

## 8. Compare @component and @service , @repository , @controller?

`@Component`, `@Service`, `@Repository`, and `@Controller` are all **stereotype annotations** in Spring, which mark classes as Spring-managed beans. They play slightly different roles in Spring’s architecture, even though they all inherit from `@Component` and thus function similarly when it comes to bean registration. Here’s a comparison of these annotations and their specific purposes:

### 1. **`@Component`**
   - **Purpose**: A generic stereotype for any Spring-managed component.
   - **Usage**: Use it to denote a class as a Spring bean when the class doesn't fit into a more specific role like service, repository, or controller.
   - **Typical Use Case**: Utility classes, general-purpose components, or beans that don’t logically belong to a particular layer.
   - **Example**:
     ```java
     @Component
     public class MyUtilityComponent {
         // Some generic functionality
     }
     ```

### 2. **`@Service`**
   - **Purpose**: A specialization of `@Component` to indicate a class that contains business logic.
   - **Usage**: Primarily used for the **service layer**. It's used to represent service classes that handle business logic or other core application logic.
   - **Additional Functionality**: While it's largely a marker annotation, in future versions of Spring or when working with tools like Spring AOP (Aspect-Oriented Programming), `@Service` might carry additional functionality (like applying transactional boundaries or other cross-cutting concerns).
   - **Typical Use Case**: Service classes that coordinate between the repository and controller layers, handling the business logic.
   - **Example**:
     ```java
     @Service
     public class MyService {
         public void process() {
             // Business logic
         }
     }
     ```

### 3. **`@Repository`**
   - **Purpose**: A specialization of `@Component` to indicate a **data access** component (DAO).
   - **Usage**: Used in the **repository (data access) layer**, typically for classes that directly interact with the database (e.g., via JPA, JDBC, etc.).
   - **Additional Functionality**: Spring translates exceptions related to database access (e.g., `SQLException`) into Spring’s unified exception hierarchy (`DataAccessException`) when `@Repository` is applied.
   - **Typical Use Case**: Repositories that perform CRUD operations on a data source, often using JPA, JDBC, or another persistence framework.
   - **Example**:
     ```java
     @Repository
     public class MyRepository {
         public List<User> findAllUsers() {
             // Data access logic
         }
     }
     ```

### 4. **`@Controller`**
   - **Purpose**: A specialization of `@Component` to indicate a **web controller** in Spring MVC.
   - **Usage**: Used in the **presentation layer**, specifically for classes that handle HTTP requests, typically as part of a web application or API.
   - **Additional Functionality**: It enables Spring to map HTTP requests to handler methods (when used with annotations like `@RequestMapping`). It integrates with view technologies (e.g., JSP, Thymeleaf) or RESTful APIs (with `@RestController`).
   - **Typical Use Case**: Web controllers that handle user input and return views or REST responses.
   - **Example**:
     ```java
     @Controller
     public class MyController {
         
         @RequestMapping("/greet")
         public String greet() {
             return "greeting"; // Returns a view name for rendering
         }
     }
     ```

   - **Note**: `@RestController` is a variant of `@Controller` that combines `@Controller` and `@ResponseBody`, used to build RESTful APIs.

---

### Summary of Differences

| Annotation    | Purpose                                   | Layer in Application | Additional Features                                   |
| ------------- | ----------------------------------------- | -------------------- | ----------------------------------------------------- |
| `@Component`  | Generic stereotype for Spring beans       | Any                  | Basic Spring bean management functionality.           |
| `@Service`    | Business logic or service layer           | Service Layer        | Potential AOP features (e.g., transactionality).      |
| `@Repository` | Data access layer (DAO or Repository)     | Repository Layer     | Exception translation for persistence-related errors. |
| `@Controller` | Web controller for handling HTTP requests | Presentation Layer   | HTTP request mapping, view rendering.                 |

---

### When to Use Each

- **`@Component`**: When the class doesn’t fit into a clear layer like service, repository, or controller but still needs to be a Spring-managed bean.
- **`@Service`**: For classes that contain business logic and act as intermediaries between the repository layer and the controller layer.
- **`@Repository`**: For data access classes interacting with the database, where Spring can translate persistence exceptions.
- **`@Controller`**: For handling web requests in a Spring MVC web application or REST API.

---

### Practical Examples

#### `@Component`:
```java
@Component
public class MyHelper {
    public String assist() {
        return "Helping with utility work.";
    }
}
```

#### `@Service`:
```java
@Service
public class PaymentService {
    public void processPayment() {
        // Business logic for processing payment
    }
}
```

#### `@Repository`:
```java
@Repository
public class UserRepository {
    public User findUserById(Long id) {
        // Code to find and return user from database
    }
}
```

#### `@Controller`:
```java
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String homePage() {
        return "home"; // returns the home view
    }
}
```

Each annotation serves to clearly define the role of a class in the application architecture, promoting separation of concerns and better organization in a Spring-based application.

## 9. Explain @Autowired , @Qualifier , @Resource and @Primary?

In Spring, `@Autowired`, `@Qualifier`, `@Resource`, and `@Primary` are annotations used for **dependency injection** (DI), which allows Spring to manage object dependencies automatically. They help in configuring how Spring should inject dependencies into your beans. Here’s a detailed explanation of each annotation:

---

### 1. **`@Autowired`**

- **Purpose**: `@Autowired` is used to **inject dependencies automatically** by type.
- **Usage**: Spring uses this annotation to resolve and inject a bean of the required type into the class where it is applied.
- **Injection Types**: It can be applied to constructors, setters, or directly on fields.
- **Default Behavior**: By default, Spring will try to inject a bean that matches the type of the field or constructor parameter.
  
#### Example:
```java
@Component
public class MyService {

    @Autowired  // Field Injection (not recommended for testing or maintainability)
    private MyRepository myRepository;

    // Constructor Injection (recommended)
    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    // Setter Injection (if the dependency is optional)
    @Autowired
    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
}
```

- If there is more than one bean of the same type, Spring will throw an error unless `@Qualifier` or `@Primary` is used to clarify which bean should be injected.

---

### 2. **`@Qualifier`**

- **Purpose**: `@Qualifier` is used to **disambiguate** when there are **multiple beans** of the same type and you need to specify which one should be injected.
- **Usage**: It works alongside `@Autowired` to specify which bean to inject by its name or a custom qualifier.

#### Example:
```java
@Component
public class MyService {

    @Autowired
    @Qualifier("specificRepository")  // Specify which bean to inject
    private MyRepository myRepository;
}
```

- You can assign a specific name to a bean by using the `@Component("beanName")` or `@Bean(name = "beanName")` annotations and then refer to that name using `@Qualifier`.

#### Example of Multiple Beans:
```java
@Component("primaryRepository")
public class PrimaryRepository implements MyRepository {
    // ...
}

@Component("secondaryRepository")
public class SecondaryRepository implements MyRepository {
    // ...
}

@Component
public class MyService {

    @Autowired
    @Qualifier("secondaryRepository")  // Specify which bean to inject
    private MyRepository myRepository;
}
```

---

### 3. **`@Resource`**

- **Purpose**: `@Resource` is part of the **Javax EE** specification (JSR-250) and is used for **dependency injection** by **name** or **type**.
- **Usage**: It works similarly to `@Autowired`, but it first looks for a bean **by name**, then by **type**.
- **Default Behavior**: It tries to match the field or method name with the bean name. If no name match is found, it looks for a bean by type.

#### Example:
```java
@Component
public class MyService {

    @Resource(name = "specificRepository")  // Inject by bean name
    private MyRepository myRepository;
}
```

- If you omit the `name` attribute, `@Resource` will first try to match by the field name and, if no match is found, will fall back to type-based matching.

#### Example Without Name:
```java
@Component
public class MyService {

    @Resource  // Spring tries to inject a bean by the field name first
    private MyRepository myRepository;
}
```

- **Key Difference from `@Autowired`**: While `@Autowired` primarily works by type (and can use `@Qualifier` for clarification), `@Resource` prioritizes the bean name, then checks the type.

---

### 4. **`@Primary`**

- **Purpose**: `@Primary` is used to **resolve conflicts** when there are **multiple beans** of the same type, by marking one bean as the **default** choice.
- **Usage**: You can apply `@Primary` to a bean definition to make it the default when there are multiple beans of the same type, without needing to specify a `@Qualifier`.

#### Example:
```java
@Component
@Primary  // This bean will be injected by default if no @Qualifier is provided
public class PrimaryRepository implements MyRepository {
    // ...
}

@Component
public class SecondaryRepository implements MyRepository {
    // ...
}

@Component
public class MyService {

    @Autowired
    private MyRepository myRepository;  // Injects the PrimaryRepository by default
}
```

- When both `@Primary` and `@Qualifier` are used, `@Qualifier` takes precedence. `@Primary` is a way to set a "default" bean in the absence of a qualifier.

---

### Comparison of `@Autowired`, `@Qualifier`, `@Resource`, and `@Primary`

| Annotation       | Injection Type                  | Usage                      | Priority Order        | Typical Use Case                                             |
| ---------------- | ------------------------------- | -------------------------- | --------------------- | ------------------------------------------------------------ |
| **`@Autowired`** | By type                         | Field, constructor, setter | Type-based injection  | Automatic injection of dependencies based on the type of the field or constructor parameter. |
| **`@Qualifier`** | By name                         | With `@Autowired`          | Resolves conflicts    | Specifies which exact bean to inject when multiple beans of the same type exist. |
| **`@Resource`**  | By name (default), then by type | Field, setter method       | Name first, then type | Java EE annotation, first tries to match by bean name, then by type. |
| **`@Primary`**   | By type                         | Bean declaration           | Default choice        | Marks a bean as the default when multiple beans of the same type exist, used when no `@Qualifier` is provided. |

---

### When to Use Each:

- **`@Autowired`**: Use for most dependency injections, especially when you have a single bean of a type or use `@Qualifier` to resolve conflicts.
- **`@Qualifier`**: Use when multiple beans of the same type exist and you need to specify which one to inject.
- **`@Resource`**: Use when you prefer injection by bean name (especially when using legacy Java EE-based code).
- **`@Primary`**: Use to mark a bean as the default when there are multiple beans of the same type and you don’t want to specify `@Qualifier` everywhere.

## 10. How many annotaitons we can use to inject a bean?

There are **four primary annotations** used to inject a bean in Spring:

### 1. **`@Autowired`**
   - Injects beans **by type**.
   - Can be applied to fields, constructor parameters, and setter methods.
   - If there are multiple beans of the same type, you can combine it with `@Qualifier` to specify the exact bean to inject.

### 2. **`@Qualifier`** (used alongside `@Autowired`)
   - Helps to inject a specific bean when there are multiple beans of the same type.
   - Injects beans **by name**.
   - Must be used with `@Autowired` to resolve ambiguity.

### 3. **`@Resource`**
   - Injects beans **by name** first, then **by type** if the name match fails.
   - Comes from Java EE (JSR-250).
   - Used primarily in older or Java EE-based environments but supported in Spring.

### 4. **`@Inject`** (from JSR-330)
   - Part of the **Java CDI** (Contexts and Dependency Injection) specification.
   - Similar to `@Autowired`, it injects beans **by type**.
   - Does not support `@Qualifier` directly; `@Named` is used for name-based injection.

### Summary:

- **`@Autowired`**: Type-based injection.
- **`@Qualifier`**: Works with `@Autowired` for name-based injection.
- **`@Resource`**: Name-first injection, falls back to type-based.
- **`@Inject`**: Similar to `@Autowired` but part of Java CDI.

So, there are **4 annotations** that can be used to inject beans in Spring: `@Autowired`, `@Qualifier`, `@Resource`, and `@Inject`.

## 11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.

In Spring (and other DI frameworks), there are **three main types of Dependency Injection** (DI) techniques:

### 1. **Constructor Injection**
### 2. **Setter Injection**
### 3. **Field Injection**

Each method has its own pros, cons, and use cases, depending on how and when you want to inject dependencies into your classes.

---

### 1. **Constructor Injection**

**Definition**: Dependencies are provided through the class constructor. All required dependencies are passed as arguments to the constructor when the object is created.

#### Example:
```java
@Component
public class MyService {
    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
}
```

#### **Pros**:
- **Immutability**: Since dependencies are provided at the time of object creation, the injected dependencies can be made `final`, making the class easier to test and ensuring that dependencies can’t be changed after initialization.
- **Completeness**: Ensures that the object is always fully initialized with all required dependencies.
- **Testability**: Easier to write unit tests for classes using constructor injection because dependencies are clear and can be provided through the constructor.
- **Thread Safety**: Supports immutable design patterns, which is beneficial in multi-threaded environments.
- **IDE Support**: Most IDEs will alert you if a required dependency is missing when constructing an object.

#### **Cons**:
- **Large Constructors**: If the class has many dependencies, the constructor can become large and cumbersome, violating the Single Responsibility Principle (SRP).
- **Optional Dependencies**: Difficult to handle optional dependencies. You’ll need overloaded constructors or setter methods to inject optional dependencies.

#### **Use Cases**:
- When all dependencies are **mandatory** for the class to function properly.
- When you want to implement **immutable objects**.
- Preferred in **framework-based applications** like Spring, which manage object creation via DI.

---

### 2. **Setter Injection**

**Definition**: Dependencies are provided through public setter methods. The dependency is injected after the object is constructed.

#### Example:
```java
@Component
public class MyService {
    private MyRepository myRepository;

    @Autowired
    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
}
```

#### **Pros**:
- **Flexibility**: You can inject dependencies after object creation, and setter methods can be used to inject optional dependencies.
- **Readability**: Can improve readability when the object has many dependencies, as the constructor doesn’t need to be cluttered.
- **Partial Initialization**: The object can be constructed without all dependencies being immediately available, allowing you to inject dependencies later.
- **Bean Lifecycle**: Can be helpful in cases where you need to initialize or modify beans post-construction, like during bean lifecycle callbacks (e.g., `@PostConstruct`).

#### **Cons**:
- **Potential for Incomplete Object**: There’s a risk of having an object in an invalid state if a required dependency is not set, as there’s no guarantee all dependencies will be injected before use.
- **Mutability**: Setter injection allows dependencies to be changed after object creation, which can lead to side effects and unexpected behavior in a multi-threaded environment.
- **Hidden Dependencies**: Dependencies are not as explicit as with constructor injection, making it harder to see what the object depends on just by looking at its constructor.

#### **Use Cases**:
- When you have **optional dependencies**.
- When you want to support **dynamic reconfiguration** of a bean.
- Useful when working with **frameworks** where beans might be partially initialized and later completed.
- Preferred when **dependency cycles** (circular dependencies) are an issue, as Spring can partially initialize the beans and inject the dependencies later.

---

### 3. **Field Injection**

**Definition**: Dependencies are injected directly into the fields of a class, bypassing constructors or setter methods. This is done using the `@Autowired` annotation directly on fields.

#### Example:
```java
@Component
public class MyService {

    @Autowired
    private MyRepository myRepository;
}
```

#### **Pros**:
- **Simplicity**: Easy and quick to set up. Field injection involves less boilerplate code compared to constructor or setter injection.
- **No Setter Methods Required**: No need to write explicit constructor or setter methods, making the code appear cleaner.

#### **Cons**:
- **Testing Difficulties**: Makes unit testing more difficult because fields are private and final, requiring reflection or other techniques to inject mocks or stubs during testing.
- **Immutability Issues**: Since fields can be modified after the object is constructed, this can lead to issues with immutability, making the class harder to reason about.
- **Hidden Dependencies**: Since dependencies aren’t explicitly injected via constructor or setter, it’s not immediately clear what dependencies the class relies on, which can lead to poor maintainability.
- **Framework Dependency**: Heavily reliant on the Spring framework to inject dependencies, making it less flexible and harder to use outside of Spring or during testing.

#### **Use Cases**:
- Used when **simplicity** is the priority, and you are not concerned with testability.
- Can be a quick way to inject dependencies for **small applications** or **prototyping**.
- Generally not recommended for production code but sometimes used in legacy systems.

---

### Comparison of Dependency Injection Types

| Type                      | Pros                                                         | Cons                                                         | Best Use Case                                                |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Constructor Injection** | - Clear and explicit dependencies<br>- Ensures immutability<br>- Easier to test<br>- No partial initialization issues | - Can result in long constructors for classes with many dependencies<br>- Harder to handle optional dependencies | When all dependencies are mandatory and immutability is preferred |
| **Setter Injection**      | - Flexible<br>- Supports optional dependencies<br>- No need for overloaded constructors | - Risk of partially initialized objects<br>- Less secure (dependencies can be changed)<br>- Lower testability | When optional dependencies are involved, or when the object can be partially initialized |
| **Field Injection**       | - Simple and concise<br>- No need for setter methods<br>- Minimal boilerplate | - Hard to test (especially in unit testing)<br>- Dependencies are hidden<br>- Relies heavily on Spring | Quick prototypes or legacy systems; not recommended for large production systems |

---

### When to Use Each Type of Dependency Injection:

- **Constructor Injection**: Use this when all dependencies are mandatory, and you want your class to be immutable. It’s best for applications with a well-defined set of required dependencies. It’s also preferred for better testability.
  
- **Setter Injection**: Use when you have optional dependencies or when the bean requires a partial initialization, and dependencies can be injected at a later stage. It’s ideal when working with configurations or dynamic values.

- **Field Injection**: Use only for small-scale projects, rapid prototyping, or when you need to inject dependencies quickly. It’s not recommended for long-term use in production due to difficulties in testing and maintaining code quality.

---

### Key Takeaways:
- **Constructor Injection** is generally recommended because it promotes immutability, better testability, and ensures all required dependencies are injected upfront.
- **Setter Injection** is useful for optional or dynamically configured dependencies, but you need to ensure that all mandatory dependencies are injected.
- **Field Injection** is simple but has downsides in terms of testing and flexibility. Use it cautiously, and avoid it in complex or production-grade applications.

## 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.

When you have multiple beans of the same type in a Spring application, you can designate one of them as the **primary** bean. Spring will then use this primary bean when injecting dependencies unless explicitly specified otherwise. Here’s how you can do this along with an example of how Spring handles bean selection when no primary bean is designated.

### Setting a Primary Bean

You can designate a bean as primary by using the `@Primary` annotation. This can be applied to either the bean method in a `@Configuration` class or to the bean class itself when using annotations like `@Component`, `@Service`, `@Repository`, or `@Controller`.

#### Example 1: Using `@Primary` with @Component
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
@Primary  // This bean will be the primary choice for injection
public class PrimaryRepository implements MyRepository {
    @Override
    public void performOperation() {
        System.out.println("PrimaryRepository Operation");
    }
}

@Component
public class SecondaryRepository implements MyRepository {
    @Override
    public void performOperation() {
        System.out.println("SecondaryRepository Operation");
    }
}
```

#### Example 2: Using `@Primary` with @Bean in Configuration
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    
    @Bean
    @Primary  // This bean will be the primary choice for injection
    public MyRepository primaryRepository() {
        return new PrimaryRepository();
    }

    @Bean
    public MyRepository secondaryRepository() {
        return new SecondaryRepository();
    }
}
```

### Injecting the Primary Bean

Now, you can inject `MyRepository` into another component, and Spring will inject the primary bean unless specified otherwise.

#### Example: Injecting `MyRepository`
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;  // Spring will inject the PrimaryRepository
    }

    public void execute() {
        myRepository.performOperation();  // Outputs: "PrimaryRepository Operation"
    }
}
```

### Handling Multiple Beans without a Primary

If you have multiple beans of the same type and **none of them are marked as primary**, Spring will throw an exception when trying to autowire that dependency, indicating that there are multiple candidates for the injection point.

#### Example without a Primary Bean
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;  // This will throw an exception if no primary is set
    }
}
```

### Error Example
If you try to run the above code without any `@Primary` annotation, you will encounter an error like this:

```
Error creating bean with name 'myService': Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'MyRepository' available: expected single matching bean but found 2: primaryRepository,secondaryRepository
```

### Summary
1. **Setting a Primary Bean**: Use the `@Primary` annotation to designate one of the beans as the primary choice for dependency injection.
2. **Injecting Beans**: When you inject `MyRepository`, Spring will use the primary bean if it exists. If there are multiple candidates and none are marked as primary, an exception will be thrown.
3. **Best Practice**: Always set a primary bean when you have multiple implementations of a type that are used in the same context to avoid ambiguity. If you require a specific bean, use `@Qualifier` to specify which one to inject.

## 13. Compare BeanFactory and ApplicationContext in Spring framework?

In the Spring Framework, both `BeanFactory` and `ApplicationContext` are interfaces that provide the functionality for managing and configuring beans, but they have different features and use cases. Here's a detailed comparison:

### 1. **Overview**

- **BeanFactory**: 
  - The simplest container that provides basic support for dependency injection.
  - Lazy initialization of beans by default, meaning that beans are created only when they are requested.

- **ApplicationContext**: 
  - A more advanced and feature-rich container that builds on the `BeanFactory`.
  - Eager initialization by default, meaning all singleton beans are created at startup.
  - Provides additional features like internationalization, event propagation, and support for AOP (Aspect-Oriented Programming).

### 2. **Key Features Comparison**

| Feature                     | **BeanFactory**                            | **ApplicationContext**                                  |
| --------------------------- | ------------------------------------------ | ------------------------------------------------------- |
| **Bean Initialization**     | Lazy initialization of beans               | Eager initialization of singleton beans                 |
| **Configuration Support**   | Limited to XML or Java-based configuration | Supports XML, Java annotations, and JavaConfig          |
| **Event Propagation**       | No support for application events          | Supports event propagation through `ApplicationEvent`   |
| **Internationalization**    | No support for message sources             | Supports internationalization through `MessageSource`   |
| **AOP Support**             | No support for AOP                         | Provides AOP features through proxy-based approach      |
| **Bean Post-Processors**    | Basic support for post-processing          | Advanced support for post-processing and bean lifecycle |
| **Environment Abstraction** | None                                       | Supports environment abstraction (profiles, properties) |
| **Resource Loading**        | Basic resource loading                     | Enhanced resource loading with various resource types   |

### 3. **Performance**
- **BeanFactory**: Generally has lower memory overhead and better performance for applications that only require simple bean management and lazy loading.
- **ApplicationContext**: Has a higher overhead due to its richer feature set, but the eager initialization may improve performance in scenarios where beans are frequently accessed.

### 4. **Use Cases**
- **BeanFactory**:
  - Suitable for lightweight applications where only basic dependency injection is required.
  - Useful in scenarios with limited resources or when implementing a custom application container.
  
- **ApplicationContext**:
  - Recommended for most applications due to its rich feature set.
  - Ideal for enterprise-level applications that require integration with AOP, event handling, internationalization, and other features.

### 5. **Common Implementations**
- **BeanFactory Implementations**:
  - `XmlBeanFactory` (deprecated since Spring 3.1): Used to load beans from an XML configuration file.

- **ApplicationContext Implementations**:
  - `ClassPathXmlApplicationContext`: Loads the context definition from an XML file located in the classpath.
  - `FileSystemXmlApplicationContext`: Loads the context definition from an XML file located in the file system.
  - `AnnotationConfigApplicationContext`: Used for Java-based configuration with annotations.
  - `WebApplicationContext`: A specialized version of `ApplicationContext` for web applications, providing additional features for handling web-related concerns.

### Summary
- **BeanFactory** is suitable for simple, resource-constrained applications with basic dependency injection needs, while **ApplicationContext** is the preferred choice for most applications due to its comprehensive features, including event handling, internationalization, and AOP support. Generally, developers tend to use `ApplicationContext` for most scenarios because it offers a more robust set of functionalities.

## 14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.

In Spring, the scope of a bean defines the lifecycle and visibility of that bean in the application context. The scope can determine how many instances of a bean are created and when they are destroyed. Spring provides several built-in scopes, each serving different needs in application design.

### 1. **Singleton Scope**
- **Description**: A singleton bean is created only once per Spring IoC container. All requests for that bean will return the same instance.
- **Default Scope**: This is the default scope in Spring.
  
#### Example:
```java
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("SingletonBean Instance Created");
    }

    public void display() {
        System.out.println("Singleton Bean Method Called");
    }
}
```

### 2. **Prototype Scope**
- **Description**: A prototype bean results in a new instance being created every time it is requested from the container.
  
#### Example:
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")  // Define this bean as prototype
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("PrototypeBean Instance Created");
    }

    public void display() {
        System.out.println("Prototype Bean Method Called");
    }
}
```

### 3. **Request Scope**
- **Description**: A request-scoped bean is created for a single HTTP request and is destroyed at the end of the request. This scope is only valid in a web application context.
  
#### Example:
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope("request")  // Define this bean as request-scoped
public class RequestScopedBean {
    public RequestScopedBean() {
        System.out.println("RequestScopedBean Instance Created");
    }

    public void display() {
        System.out.println("Request Scoped Bean Method Called");
    }
}
```

### 4. **Session Scope**
- **Description**: A session-scoped bean is created for a single HTTP session and is destroyed at the end of the session. This is also only valid in a web application context.
  
#### Example:
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")  // Define this bean as session-scoped
public class SessionScopedBean {
    public SessionScopedBean() {
        System.out.println("SessionScopedBean Instance Created");
    }

    public void display() {
        System.out.println("Session Scoped Bean Method Called");
    }
}
```

### 5. **Global Session Scope**
- **Description**: A global session-scoped bean is created for a global HTTP session and is primarily used in portlet applications. Like the session scope, this is applicable only in web applications.
  
#### Example:
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "globalSession", proxyMode = ScopedProxyMode.TARGET_CLASS)  // Global session scope
public class GlobalSessionScopedBean {
    public GlobalSessionScopedBean() {
        System.out.println("GlobalSessionScopedBean Instance Created");
    }

    public void display() {
        System.out.println("Global Session Scoped Bean Method Called");
    }
}
```

### 6. **Application Scope**
- **Description**: This scope is similar to singleton, but it is specific to the lifecycle of a `WebApplicationContext`. It is shared across all users and all requests in a single application.
  
#### Example:
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("application")  // Define this bean as application-scoped
public class ApplicationScopedBean {
    public ApplicationScopedBean() {
        System.out.println("ApplicationScopedBean Instance Created");
    }

    public void display() {
        System.out.println("Application Scoped Bean Method Called");
    }
}
```

### Summary of Bean Scopes
| Scope Name         | Description                                              | Web Context | Default |
| ------------------ | -------------------------------------------------------- | ----------- | ------- |
| **Singleton**      | One instance per container; shared across all requests.  | No          | Yes     |
| **Prototype**      | New instance for each request; not shared.               | No          | No      |
| **Request**        | One instance per HTTP request; destroyed at request end. | Yes         | No      |
| **Session**        | One instance per HTTP session; destroyed at session end. | Yes         | No      |
| **Global Session** | One instance for a global HTTP session (in portlets).    | Yes         | No      |
| **Application**    | One instance per application; shared across sessions.    | Yes         | No      |

### Choosing the Right Scope
- **Use Singleton** for shared services or components that maintain shared state.
- **Use Prototype** for components that should not maintain any shared state and require fresh instances.
- **Use Request and Session** scopes in web applications to manage user sessions and request-specific data.
- **Use Application scope** for beans that need to be shared across multiple sessions in a web application.

### Note
When using `request`, `session`, or `globalSession` scopes, the application must be running in a web context (i.e., deployed on a servlet container like Tomcat) for these scopes to work properly.

## 15. Write a Spring application that registers and autowires beans,

Here’s a complete Spring application demonstrating various concepts such as dependency injection, bean scopes, resolving ambiguity, and registering beans using both `@Component` and `@Bean`. 

### Project Structure
```
spring-demo
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           ├── DemoApplication.java
│       │           ├── config
│       │           │   └── AppConfig.java
│       │           ├── beans
│       │           │   ├── SingletonBean.java
│       │           │   ├── PrototypeBean.java
│       │           │   ├── RequestScopedBean.java
│       │           │   ├── SessionScopedBean.java
│       │           │   └── MyService.java
│       │           └── AmbiguousBeans.java
│       └── resources
│           └── application.properties
└── pom.xml
```

### 1. **Setting Up the Project with Maven**

#### `pom.xml`
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>spring-demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <spring.version>5.3.13</spring.version>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

### 2. **Application Configuration**

#### `AppConfig.java`
```java
package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.example.beans.PrototypeBean;
import com.example.beans.SingletonBean;

@Configuration
@ComponentScan(basePackages = "com.example.beans")  // Scan for @Component beans
public class AppConfig {

    @Bean
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }

    @Bean
    @Scope("prototype")  // Define as prototype
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }
}
```

### 3. **Beans Demonstrating Different Features**

#### `SingletonBean.java`
```java
package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("SingletonBean Instance Created");
    }

    public void display() {
        System.out.println("Singleton Bean Method Called");
    }
}
```

#### `PrototypeBean.java`
```java
package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("PrototypeBean Instance Created");
    }

    public void display() {
        System.out.println("Prototype Bean Method Called");
    }
}
```

#### `RequestScopedBean.java`
```java
package com.example.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope("request")
public class RequestScopedBean {
    public RequestScopedBean() {
        System.out.println("RequestScopedBean Instance Created");
    }

    public void display() {
        System.out.println("Request Scoped Bean Method Called");
    }
}
```

#### `SessionScopedBean.java`
```java
package com.example.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionScopedBean {
    public SessionScopedBean() {
        System.out.println("SessionScopedBean Instance Created");
    }

    public void display() {
        System.out.println("Session Scoped Bean Method Called");
    }
}
```

#### `MyService.java`
```java
package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final SingletonBean singletonBean;
    private final PrototypeBean prototypeBean;

    @Autowired
    public MyService(SingletonBean singletonBean, PrototypeBean prototypeBean) {
        this.singletonBean = singletonBean;
        this.prototypeBean = prototypeBean;
    }

    public void performService() {
        singletonBean.display();
        prototypeBean.display();
    }
}
```

### 4. **Demonstrating Ambiguity Resolution**

#### `AmbiguousBeans.java`
```java
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AmbiguousBeans {

    private final SingletonBean singletonBean;

    @Autowired
    public AmbiguousBeans(@Qualifier("singletonBean") SingletonBean singletonBean) {
        this.singletonBean = singletonBean;
    }

    public void displayBean() {
        singletonBean.display();
    }
}
```

### 5. **Main Application Class**

#### `DemoApplication.java`
```java
package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.AppConfig;
import com.example.beans.MyService;

public class DemoApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Demo Singleton Bean
        System.out.println("---- Singleton Bean Demo ----");
        SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
        singletonBean1.display();
        SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
        singletonBean2.display();  // Same instance

        // Demo Prototype Bean
        System.out.println("---- Prototype Bean Demo ----");
        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        prototypeBean1.display();
        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        prototypeBean2.display();  // Different instances

        // Demo MyService
        System.out.println("---- MyService Demo ----");
        MyService myService = context.getBean(MyService.class);
        myService.performService();

        // Demo Ambiguous Beans
        System.out.println("---- Ambiguous Beans Demo ----");
        AmbiguousBeans ambiguousBeans = context.getBean(AmbiguousBeans.class);
        ambiguousBeans.displayBean();
    }
}
```

### 6. **Running the Application**
- Ensure you have a servlet container like Tomcat configured if you are testing request and session scopes.
- Run the `DemoApplication` class as a Java application to see the output.

### Expected Output
When you run the application, you should see logs indicating the creation of singleton and prototype beans, service execution, and resolution of ambiguity in bean definitions.

### Conclusion
This Spring application demonstrates various core features:
1. **Different Types of Dependency Injection**: Constructor-based injection in `MyService`.
2. **Bean Scopes**: Singleton and prototype examples.
3. **Ambiguity Resolution**: Using `@Qualifier` to specify which bean to inject when multiple beans of the same type exist.
4. **Bean Registration**: Using both `@Component` and `@Bean` for defining beans.

This structure provides a good foundation for understanding Spring's dependency injection and bean lifecycle management.

## 16. Explain builder pattern with code examples.

The Builder Pattern is a creational design pattern that allows you to construct complex objects step by step. This pattern is particularly useful when an object needs to be created with many parameters or has a complex structure. It separates the construction of a complex object from its representation, making the same construction process create different representations.

### Key Components of the Builder Pattern:
1. **Builder Interface**: Defines the methods for creating parts of the product.
2. **Concrete Builder**: Implements the builder interface and provides methods to construct the parts of the product.
3. **Director**: (Optional) Responsible for managing the construction process, it knows the order in which to call the builder's methods.
4. **Product**: The complex object being built.

### Example of the Builder Pattern

#### 1. **Product Class**
This is the complex object we want to build.

```java
public class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private boolean isGraphicsCardEnabled;

    // Constructor is private to enforce the use of the builder
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", storage='" + storage + '\'' +
                ", isGraphicsCardEnabled=" + isGraphicsCardEnabled +
                '}';
    }

    // Static nested Builder class
    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;
        private boolean isGraphicsCardEnabled;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder enableGraphicsCard() {
            this.isGraphicsCardEnabled = true;
            return this;
        }

        public Builder disableGraphicsCard() {
            this.isGraphicsCardEnabled = false;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
```

#### 2. **Using the Builder to Create a Computer Object**
Here’s how you can use the `Computer.Builder` class to create an instance of `Computer`.

```java
public class BuilderPatternDemo {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .enableGraphicsCard()
                .build();

        System.out.println(computer);
    }
}
```

### Explanation of the Code:
1. **Computer Class**: This class is the complex object we want to build. It has a private constructor that takes a `Builder` object.
2. **Builder Class**: This is a static nested class inside `Computer`. It has methods for setting various properties of the `Computer` class and a `build()` method that constructs the `Computer` instance.
3. **Usage**: In the `BuilderPatternDemo` class, we create a `Computer` instance using the `Builder` class. We set various parameters and finally call `build()` to get the constructed `Computer` object.

### Benefits of the Builder Pattern:
- **Readability**: The code for constructing an object is more readable and maintainable.
- **Immutability**: The constructed object can be made immutable by only exposing it through a builder.
- **Flexibility**: It allows for creating different representations of an object using the same construction code.
- **No Constructor Overloading**: Instead of having multiple constructors with different parameters, you can use a single builder with descriptive method names.

### Summary
The Builder Pattern is a powerful tool in object-oriented design, especially useful for constructing complex objects with many parameters. By using the builder, you can create flexible and easily maintainable code.
