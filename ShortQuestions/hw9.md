# HW9 Java-Spring-Basic

## 2. Compare Spring and Springboot? What are the benefits of Springboot?

### **Spring vs. Spring Boot**

**Spring**:
- A comprehensive framework for Java development.
- Requires significant configuration.
- Provides flexibility to build any Java application, but the setup can be complex.

**Spring Boot**:
- Built on top of Spring to simplify application development.
- Offers auto-configuration and embedded servers (Tomcat, Jetty).
- No need for extensive XML configurations.

### **Benefits of Spring Boot**:
1. **Auto-Configuration**: Automatically configures application based on project dependencies.
2. **Embedded Servers**: No need for external servers, making deployment easier.
3. **Microservice Ready**: Best suited for microservice architecture.
4. **Reduced Boilerplate Code**: Minimizes the need for manual configuration.
5. **Production-Ready Features**: Includes health checks, monitoring, and metrics out of the box.

## 3. What is IOC and What is DI?

### **Inversion of Control (IoC)**:
- A design principle where the control of object creation, configuration, and management is given to a container or framework (e.g., Spring), rather than being managed manually by the developer.
- It decouples the application’s components, making them more modular and testable.

### **Dependency Injection (DI)**:
- A specific implementation of IoC.
- It refers to injecting dependencies (objects) into a class, rather than creating them within the class itself.
- DI reduces tight coupling between objects and increases flexibility.

### **Relation**:
- DI is a technique to achieve IoC.

## 4. What is `@CompnonentScan`?

### **@ComponentScan**:
- An annotation in Spring used to automatically discover and register beans within the application context.
- It scans the specified package and its sub-packages for classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc.
- Helps in configuring Spring beans without explicitly defining them in XML or Java configuration.

**Usage**:
```java
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {
}
```

- In the example above, Spring will scan the `com.example` package for components.

## 5. What is `@SpringbootApplication` ?

### **@SpringBootApplication**:
- A convenience annotation in Spring Boot that combines three crucial annotations:
    1. **@Configuration**: Indicates that the class can be used by Spring as a source of bean definitions.
    2. **@EnableAutoConfiguration**: Enables Spring Boot’s auto-configuration mechanism, which automatically configures beans based on classpath settings.
    3. **@ComponentScan**: Scans the package for Spring components like `@Controller`, `@Service`, and `@Repository`.

### **Purpose**:
- It simplifies the configuration by combining the most commonly used annotations in a Spring Boot application.

**Usage**:
```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

## 6. How many ways to define a bean? Provide code examples

### **Ways to Define a Bean in Spring**:

1. **Using `@Component` Annotation**:
   - Spring automatically detects classes annotated with `@Component` and registers them as beans.

   ```java
   @Component
   public class MyComponent {
     // Bean logic
   }
   ```

2. **Using `@Bean` Annotation in a Configuration Class:**:
   - Define beans within a `@Configuration` class using the `@Bean` annotation.

   ```java
   @Configuration
   public class AppConfig {
     @Bean
     public MyBean myBean() {
        return new MyBean();
     }
   }
   ```

3. **Using XML Configuration:**:
   - Beans can be defined using traditional XML configuration.

   ```java
   <bean id="myBean" class="com.example.MyBean"/>
   ```   

4. **Using `@Service`, `@Repository`, `@Controller` Annotations:**:
   - Spring can also scan and register beans using specialized annotations like `@Service`, `@Repository`, and `@Controller`.

   ```java
   @Service
   public class MyService {
      // Service logic
   }
   ```   

### **Summary**: 

- **`@Component`**, **`@Bean`**, **XML**, and specialized annotations like `@Service`, `@Repository`, and `@Controller` are the common ways to define a bean in Spring.

## 7. What is default bean name for `@Component` and `@Bean` ? Also compare `@Component` and `@Bean` .

### **Default Bean Name for `@Component` and `@Bean`**:

- **@Component**:
    - The default bean name is the class name with the first letter in lowercase.

      Example:
      ```java
      @Component
      public class MyService {
      }
      ```
      The default bean name is `myService`.

- **@Bean**:
    - The default bean name is the method name.

      Example:
      ```java
      @Configuration
      public class AppConfig {
          @Bean
          public MyService myService() {
              return new MyService();
          }
      }
      ```
      The default bean name is `myService`.

---

### **Comparison of `@Component` vs `@Bean`**:

| **Aspect**          | **@Component**                               | **@Bean**                                      |
|---------------------|----------------------------------------------|------------------------------------------------|
| **Usage**           | Used on a class level to auto-detect and register beans. | Used within a `@Configuration` class to manually define beans. |
| **Bean Definition**  | Automatically scans packages for components. | Explicitly defines a bean with more flexibility. |
| **Scope**           | Only detects component classes like `@Service`, `@Repository`, `@Controller`. | Can define beans for any method, offering more control over bean creation. |
| **Flexibility**      | Less control over bean instantiation.        | Offers more control with detailed instantiation logic. |


## 8. Compare `@component` and `@service` , `@repository` , `@controller` ?

### **Comparison of `@Component`, `@Service`, `@Repository`, and `@Controller`**:

| **Annotation**    | **Purpose**                                                 | **Use Case**                                     |
|-------------------|-------------------------------------------------------------|--------------------------------------------------|
| **@Component**    | General-purpose stereotype for Spring-managed components.   | Used for any Spring bean that doesn't fall under a specific layer (service, repository, controller). |
| **@Service**      | Specialization of `@Component`, indicating a service layer. | Typically used to define business logic or service beans. |
| **@Repository**   | Specialization of `@Component`, indicating a data access layer. | Used for DAO (Data Access Object) classes to handle database operations. It also provides exception translation for database errors. |
| **@Controller**   | Specialization of `@Component`, indicating a web controller. | Used in Spring MVC for handling web requests and returning views or responses. |

### **Key Differences**:
- **@Component**: The most generic annotation, suitable for any Spring-managed bean.
- **@Service**: Indicates business logic or service-related functionality.
- **@Repository**: Indicates DAO layer components, with added database-related features like exception translation.
- **@Controller**: Specifically used in MVC for handling HTTP requests, typically returning views or REST responses.

Each of these annotations is a specialization of `@Component`, making it easier to categorize and manage Spring components based on their role in the application.

## 9. Explain `@Autowired` , `@Qualifier` , `@Resource` and `@Primary` 

### **@Autowired**:
- **Purpose**: Automatically injects dependencies by type.
- **Usage**: Applied to fields, constructors, or setter methods.
- **Behavior**: Spring resolves the bean by matching the type of the dependency. If there are multiple beans of the same type, ambiguity occurs unless resolved using `@Qualifier` or `@Primary`.

```java
@Autowired
private MyService myService;
```

### **@Qualifier**:
- **Purpose**: Used to resolve ambiguity when multiple beans of the same type exist.
- **Usage**:  Applied with `@Autowired` to specify which bean to inject by name.
- **Behavior**: Ensures that the correct bean is injected by specifying the bean name.

```java
@Autowired
@Qualifier("myServiceImpl")
private MyService myService;
```

### **@Resource**:
- **Purpose**: SR-250 annotation used for dependency injection, similar to `@Autowired`.
- **Usage**:  Resolves dependencies by name (by default) or by type (if no match by name).
- **Behavior**: First tries to inject by bean name, then falls back to type if no match.

```java
@Resource(name = "myService")
private MyService myService;
```

### **@Primary**:
- **Purpose**: Specifies the default bean to be injected when there are multiple candidates.
- **Usage**:  Applied on a bean definition to give it precedence over other beans of the same type.
- **Behavior**: Marks one bean as the primary option when multiple beans of the same type are available, making it the default choice.

```java
@Primary
@Bean
public MyService myServiceImpl() {
   return new MyServiceImpl();
}
```

### **Comparison**:
- **@Autowired**:  Injects dependencies by type automatically.
- **@Qualifier**:  Resolves type ambiguity by specifying the bean name.
- **@Resource**:   Injects dependencies by name (preferred) or by type.
- **@Primary**:  Sets the default bean when multiple beans of the same type exist.

## 10. How many annotations we can use to inject a bean?

### **Annotations to Inject a Bean in Spring**:

1. **@Autowired**:
   - Automatically injects dependencies by type.
   - Can be applied to constructors, fields, or methods.

   ```java
   @Autowired
   private MyService myService;
   ```

2. **@Qualifier**:
   - Used alongside `@Autowired` to resolve ambiguity when multiple beans of the same type exist.
   - Specifies the bean by name.

   ```java
   @Autowired
   @Qualifier("myServiceImpl")
   private MyService myService;
   ```

3. **@Resource**:
   - JSR-250 annotation that injects beans by name (default) or type.
   - Similar to `@Autowired`, but with a focus on name-based injection.

   ```java
   @Resource(name = "myService")
   private MyService myService;
   ```

4. **@Inject**:
   - JSR-330 annotation for dependency injection, similar to `@Autowired`.
   - Part of the Java CDI (Contexts and Dependency Injection) standard.

   ```java
   @Inject
   private MyService myService;
   ```
   
## 11. Explain and compare different types of dependency injection, their pros and cons, and use cases.

### **Types of Dependency Injection**:

1. **Constructor Injection**:
    - **Definition**: Dependencies are provided through the constructor of the class.
    - **Example**:
      ```java
      public class MyService {
          private final MyRepository repository;
 
          @Autowired
          public MyService(MyRepository repository) {
              this.repository = repository;
          }
      }
      ```

    - **Pros**:
        - Makes dependencies explicit and required.
        - Promotes immutability as dependencies are `final` and cannot be changed after construction.
        - Easier for testing (mocking dependencies is straightforward).

    - **Cons**:
        - Can result in complex constructors if a class has many dependencies.
        - Less flexible when new dependencies need to be injected.

    - **Use Case**: Preferred when dependencies are mandatory for object creation, especially for complex applications.

---

2. **Setter Injection**:
    - **Definition**: Dependencies are provided through setter methods.
    - **Example**:
      ```java
      public class MyService {
          private MyRepository repository;
 
          @Autowired
          public void setRepository(MyRepository repository) {
              this.repository = repository;
          }
      }
      ```

    - **Pros**:
        - More flexible, allowing optional dependencies.
        - Supports changes to dependencies after object creation.

    - **Cons**:
        - Dependencies are not enforced as required, which can lead to runtime errors if not properly initialized.
        - Can lead to less readable code if used extensively.

    - **Use Case**: Useful for optional dependencies or configurations that need to be set after object construction.

---

3. **Field Injection**:
    - **Definition**: Dependencies are injected directly into the class's fields.
    - **Example**:
      ```java
      public class MyService {
          @Autowired
          private MyRepository repository;
      }
      ```

    - **Pros**:
        - Simplifies code by reducing boilerplate (no need for constructors or setters).
        - The most concise form of injection.

    - **Cons**:
        - Harder to test (mocking dependencies can be difficult without reflection).
        - Violates immutability as dependencies can be changed.
        - Hides dependencies, making them less explicit.

    - **Use Case**: Common in smaller applications or when simplicity is a priority, but not recommended for large, test-driven development.

---

### **Comparison Table**:

| **Type**              | **Pros**                                                      | **Cons**                                                    | **Best Use Case**                                           |
|-----------------------|---------------------------------------------------------------|-------------------------------------------------------------|-------------------------------------------------------------|
| **Constructor Injection** | - Dependencies are required and explicit. <br> - Supports immutability.  | - Complex constructors if many dependencies. <br> - Less flexible for optional dependencies. | Best for mandatory dependencies and large applications. |
| **Setter Injection**   | - Flexible for optional dependencies. <br> - Supports changes post-creation. | - Dependencies are not enforced as required.<br> - More verbose. | Best for optional dependencies and configurations.         |
| **Field Injection**    | - Simplest and most concise. <br> - Reduces boilerplate.      | - Harder to test. <br> - Hidden dependencies.<br> - No immutability. | Simple or small applications with minimal testing needs.    |

---

### **Conclusion**:
- **Constructor Injection** is best when dependencies are mandatory and immutable.
- **Setter Injection** is ideal for optional or changeable dependencies.
- **Field Injection** offers simplicity but is harder to test and lacks explicitness.

## 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primary, demo with code examples.

### **Setting a Primary Bean**:
- If there are multiple beans of the same type, you can mark one bean as the primary choice using the `@Primary` annotation. Spring will use this bean when there’s ambiguity.

**Example**:
```java
@Configuration
public class AppConfig {

   @Bean
   @Primary
   public MyService myPrimaryService() {
      return new MyServiceImpl1();
   }

   @Bean
   public MyService mySecondaryService() {
      return new MyServiceImpl2();
   }
}
```

In this example, `myPrimaryService` is marked as the primary bean, so Spring will inject it whenever `MyService` is required.

### When No `@Primary` is Defined:

If there is no `@Primary` annotation and multiple beans of the same type are defined, Spring will throw an `org.springframework.beans.factory.NoUniqueBeanDefinitionException`.

To resolve this, you can use `@Qualifier` to specify which bean to inject.

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService1() {
        return new MyServiceImpl1();
    }

    @Bean
    public MyService myService2() {
        return new MyServiceImpl2();
    }
}

@Component
public class MyComponent {

    private MyService myService;

    @Autowired
    public MyComponent(@Qualifier("myService1") MyService myService) {
        this.myService = myService;
    }
}
```

In this example, we use `@Qualifier("myService1")` to explicitly tell Spring which bean to inject when there are multiple beans of type `MyService`.

### Summary:
- **@Primary**: Marks one bean as the default choice when multiple beans of the same type exist.
- **@Qualifier**: Used to specify the exact bean to inject when there is ambiguity, in case no primary bean is defined.
- If neither `@Primary` nor `@Qualifier` is used, Spring will throw an exception due to ambiguity.

## 13. Compare BeanFactory and ApplicationContext in Spring framework?

### **Comparison of `BeanFactory` and `ApplicationContext` in Spring Framework**:

| **Aspect**                | **BeanFactory**                                   | **ApplicationContext**                                    |
|---------------------------|--------------------------------------------------|-----------------------------------------------------------|
| **Definition**             | Basic container for managing Spring beans.       | Advanced container built on top of `BeanFactory`.          |
| **Bean Initialization**    | Beans are lazily loaded (created only when requested). | Beans are eagerly loaded at startup (except for `lazy-init` beans). |
| **Additional Features**    | Only provides basic features like bean instantiation and dependency injection. | Provides more enterprise features like event propagation, AOP, internationalization, etc. |
| **Event Handling**         | No support for event handling.                   | Supports event handling mechanisms such as `ApplicationEvent`. |
| **BeanPostProcessor**      | Must be manually registered.                     | Automatically detects and registers `BeanPostProcessor` and `BeanFactoryPostProcessor`. |
| **Resource Loading**       | Limited support for resource loading.            | Supports resource loading from a variety of sources (e.g., file system, classpath, etc.). |
| **Use Case**               | Suitable for lightweight, simple applications.   | Suitable for complex, enterprise-level applications requiring advanced features. |

---

### **Summary**:
- **BeanFactory**: A more basic and lightweight container, primarily used for simple dependency injection without additional features.
- **ApplicationContext**: A more feature-rich container that builds on `BeanFactory`, providing advanced functionalities for enterprise-level applications like event handling, AOP, and internationalization.

## 14. Explain bean scope in Spring IOC? List bean scopes with explanations and code examples if possible.

### **Bean Scope in Spring IoC**:

**Bean scope** defines the lifecycle and visibility of a bean in the Spring container. There are several scopes available, and each one dictates how and when a bean is created and shared.

---

### **Types of Bean Scopes**:

1. **Singleton** (Default):
   - **Definition**: Only one instance of the bean is created per Spring IoC container. All requests for that bean return the same instance.
   - **Usage**: Suitable for stateless beans.

   **Example**:
   ```java
   @Bean
   @Scope("singleton")
   public MyService myService() {
     return new MyServiceImpl();
   }
   ```
   **Explanation**: The `MyService` bean will be created once and shared across the application.

2. **Prototype**:
   - **Description**: A new instance of the bean is created each time it is requested.
   - **Use Case**: Suitable for stateful or temporary objects that need to be recreated every time they are used. 
   - **Example**:
   ```java
   @Bean
   @Scope("prototype")
   public MyService myService() {
       return new MyServiceImpl();
   }
   ```
   **Explanation**: A new `MyService` instance is created each time it's requested.

3. **Request** (Web Application Only):
   - **Description**: A new bean instance is created for each HTTP request and exists only during that request.
   - **Use Case**: Useful for handling request-specific data in web applications.
   - **Example**:
   ```java
   @Bean
   @Scope("request")
   public MyService myService() {
      return new MyServiceImpl();
   }
   ```
   **Explanation**: A new `MyService` bean is created for each HTTP request and destroyed at the end of the request.

4. **Session** (Web Application Only):
   - **Description**: A new bean instance is created for each HTTP session and exists throughout the session lifecycle.
   - **Use Case**: Useful for managing user session data in web applications.
   - **Example**:
   ```java
   @Bean
   @Scope("session")
   public MyService myService() {
      return new MyServiceImpl(); 
   }
   ```
   **Explanation**: The `MyService` bean is created once per HTTP session and shared throughout the session.

5. **Global Session** (Web Application Only, Portlet Context):
   - **Description**: A new bean instance is created for each global HTTP session, typically in a portlet-based application.
   - **Use Case**: Used in portlet-based applications where a global session is shared across multiple portlets.
   - **Example**:
   ```java
   @Bean
   @Scope("globalSession")
   public MyService myService() {
      return new MyServiceImpl();
   }
   ```
   **Explanation**: The `MyService` bean is created once per global session, used across multiple portlets.

### **Summary**:
- **Singleton**: One instance per Spring container, shared across the application.
- **Prototype**: A new instance for every bean request.
- **Request**: One instance per HTTP request (web-specific).
- **Session**: One instance per HTTP session (web-specific).
- **GlobalSession**: One instance per global session (portlet-based applications).

## 15. Write a Spring application that registers and autowires beans,
   - Demo different types of dependency injection
   - Demo bean scopes.
   - Demo dependency injection by type and by name, when there's ambiguity in bean definition.
   - Demo bean registration by both @Component and @Bean

[DemoApplication](../Coding/hw9/DemoApplication.java)

## 16. Explain builder pattern with code examples.

### **Builder Pattern Overview**:

The **Builder Pattern** is a creational design pattern that simplifies the construction of complex objects with many optional parameters. Instead of using multiple constructors (telescoping constructors), the builder provides a flexible and readable way to build objects step by step.

---

### **Code Example**:

```java
public class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phoneNumber;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phoneNumber = builder.phoneNumber;
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private int age;
        private String phoneNumber;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

### **Usage**:

```java
User user = new User.UserBuilder("John", "Doe")
                    .age(30)
                    .phoneNumber("123-456-7890")
                    .build();
```

### **Benefits**:

1. Readable and flexible object creation.
2. Avoids complex constructors with many parameters.
3. Ensures immutability by using a private constructor.










