# Annotations 

Yuhang Li

Here’s a summary of key annotations in JPA, Hibernate, and Spring that we learned recently, organized by their purpose. This list will help us compare and understand the annotations better:

### **Entity Relationship Annotations**
1. **`@OneToMany`**:
   - Defines a one-to-many relationship between entities.
   - Example:
     ```java
     @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
     private List<Comment> comments;
     ```

2. **`@ManyToOne`**:
   - Defines a many-to-one relationship.
   - Example:
     ```java
     @ManyToOne
     @JoinColumn(name = "post_id")
     private Post post;
     ```

3. **`@ManyToMany`**:
   - Defines a many-to-many relationship.
   - Example:
     ```java
     @ManyToMany
     @JoinTable(
       name = "post_tags",
       joinColumns = @JoinColumn(name = "post_id"),
       inverseJoinColumns = @JoinColumn(name = "tag_id")
     )
     private Set<Tag> tags;
     ```

### **Cascade and Orphan Removal**
4. **`cascade = CascadeType.ALL`**:
   - Propagates all operations (e.g., persist, merge, remove) from the parent entity to the child entities.
   - Example:
     ```java
     @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
     private List<Comment> comments;
     ```

5. **`orphanRemoval = true`**:
   - Automatically deletes child entities if they are no longer referenced by the parent.
   - Example:
     ```java
     @OneToMany(mappedBy = "post", orphanRemoval = true)
     private List<Comment> comments;
     ```

6. **Other `CascadeType` Options**:
   - **`PERSIST`**: Only cascades the persist operation.
   - **`MERGE`**: Only cascades the merge operation.
   - **`REMOVE`**: Only cascades the remove operation.
   - **`REFRESH`**: Cascades the refresh operation.
   - **`DETACH`**: Cascades the detach operation.

### **Fetching Strategies**
7. **`fetch = FetchType.LAZY`**:
   - Loads the associated entity only when it is accessed, not when the parent is loaded.
   - Example:
     ```java
     @OneToMany(fetch = FetchType.LAZY)
     private List<Comment> comments;
     ```

8. **`fetch = FetchType.EAGER`**:
   - Immediately loads the associated entity when the parent is loaded.
   - Example:
     ```java
     @ManyToOne(fetch = FetchType.EAGER)
     private Post post;
     ```

### **Querying and JPQL**
9. **`@Query`**:
   - Used to define custom JPQL or native SQL queries in repository interfaces.
   - Example:
     ```java
     @Query("SELECT p FROM Post p WHERE p.title = :title")
     List<Post> findPostByTitle(@Param("title") String title);
     ```

10. **`@NamedQuery`**:
    - Predefines a JPQL query that can be reused multiple times.
    - Example:
      ```java
      @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
      ```

11. **`@NamedQueries`**:
    - A container for multiple `@NamedQuery` annotations.
    - Example:
      ```java
      @NamedQueries({
        @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
        @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id")
      })
      ```

### **Transaction Management**
12. **`@Transactional`**:
    - Defines transactional behavior for methods or classes.
    - Example:
      ```java
      @Transactional
      public void createPost(Post post) {
          postRepository.save(post);
      }
      ```

### **EntityManager and SessionFactory**
13. **`EntityManager`**:
    - Manages entity life cycles in JPA.
    - Example:
      ```java
      @PersistenceContext
      private EntityManager entityManager;
      ```

14. **`SessionFactory`** and **`Session`** (Hibernate):
    - **SessionFactory** creates sessions for database interactions, and **Session** is used to manage entity states within a transaction.
    - Example:
      ```java
      SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
      Session session = sessionFactory.openSession();
      ```

By comparing these annotations, you'll be able to recognize their usage patterns and apply them effectively in your project. Would you like more details on any of these?

### Core Spring Boot Annotations

1. **@SpringBootApplication**:
   - Marks the main class of a Spring Boot application.
   - Combines three annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
   - This enables automatic configuration, scanning for components, and registering them as Spring beans.
2. **@ComponentScan**:
   - Used to specify the base packages to scan for Spring components like `@Controller`, `@Service`, and `@Repository` annotated classes.
   - Automatically included by `@SpringBootApplication`.
3. **@Configuration**:
   - Marks a class as a source of bean definitions.
   - Allows the class to define beans using methods annotated with `@Bean`.
4. **@Bean**:
   - Used inside a `@Configuration` class to define a Spring-managed bean.
   - The method annotated with `@Bean` returns an object that will be registered in the Spring application context.
5. **@EnableAutoConfiguration**:
   - Enables Spring Boot's auto-configuration feature, which automatically configures beans based on the classpath and other settings.
   - Typically included in `@SpringBootApplication`.

### Web-Related Annotations

1. **@RestController**:
   - A convenience annotation that combines `@Controller` and `@ResponseBody`.
   - Used to create RESTful web services by returning JSON or XML directly from controller methods.
2. **@RequestMapping**:
   - Maps HTTP requests to specific handler methods in a controller.
   - Can be used at the class and method levels to specify paths, HTTP methods (`GET`, `POST`, etc.), and other request details.
3. **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping**:
   - Specialized annotations for HTTP method-specific request mapping (e.g., `@GetMapping` for `GET` requests).
   - A more readable alternative to using `@RequestMapping` with a method attribute.
4. **@PathVariable**:
   - Used to bind URI template variables to method parameters in controller methods.
5. **@RequestParam**:
   - Used to extract query parameters or form data from an HTTP request and bind them to method parameters.
6. **@RequestBody**:
   - Used in a controller method to bind the body of an HTTP request to a method parameter (typically a POJO).
   - Primarily used in RESTful APIs to handle JSON or XML request bodies.
7. **@ResponseBody**:
   - Indicates that the return value of a method should be used as the response body in a web request.
   - Typically used in REST APIs.
8. **@CrossOrigin**:
   - Enables Cross-Origin Resource Sharing (CORS) for controller methods or globally across the application.

### Data and Transaction Management Annotations

1. **@Transactional**:
   - Marks a method or class to be wrapped in a transaction.
   - Ensures that methods execute within a database transaction, with automatic commit or rollback based on success or failure.
2. **@EnableJpaRepositories**:
   - Enables JPA repositories, allowing Spring Data JPA to create repository implementations automatically.
3. **@EntityScan**:
   - Specifies the base packages to scan for JPA entity classes.
   - Often used alongside `@EnableJpaRepositories`.
4. **@EnableTransactionManagement**:
   - Enables transaction management in the Spring Boot application.
   - Allows `@Transactional` to work in the application.

### Dependency Injection Annotations

1. **@Autowired**:
   - Used to inject Spring-managed beans into a class, either via constructor, field, or setter injection.
   - It allows Spring to resolve and inject collaborating beans automatically.
2. **@Qualifier**:
   - Used alongside `@Autowired` to resolve ambiguity when multiple beans of the same type are available.
   - Specifies which bean should be injected.
3. **@Value**:
   - Used to inject values from application properties or environment variables into fields or method parameters.
4. **@Component, @Service, @Repository, @Controller**:
   - Stereotype annotations that define a class as a Spring-managed component.
   - `@Component` is a generic Spring bean, while `@Service` (business logic), `@Repository` (data access), and `@Controller` (web layer) are more specific types.

### Security and Validation Annotations

1. **@PreAuthorize**:
   - Used to define security restrictions on methods, allowing access based on user roles, permissions, or conditions.
2. **@Valid**:
   - Triggers validation on a method parameter, often used with form data or `@RequestBody` objects.
   - Works with the Java Bean Validation API (e.g., Hibernate Validator).
3. **@EnableWebSecurity**:
   - Enables Spring Security's web security configuration.
   - Usually used with a `WebSecurityConfigurerAdapter` class to define custom security settings.





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



# How many annotaitons we can use to inject a bean?

There are **three primary annotations** used to inject a bean in Spring:

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
