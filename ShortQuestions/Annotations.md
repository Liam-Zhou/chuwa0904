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





## Testing

### 1. JUnit

**Annotations**: JUnit provides annotations to identify test methods, set up resources before tests, and clean up afterward.

- `@Test`: Marks a method as a test case.
- `@BeforeEach`: Runs before each test to set up conditions.
- `@AfterEach`: Runs after each test for cleanup.
- `@BeforeAll` and `@AfterAll`: Run once before and after all tests, respectively.

### 2. Mockito

Common Mockito Annotations:

1. **@Mock**: Creates a mock instance of a class. You can inject this mock into the class you want to test.
2. **@InjectMocks**: Automatically injects mock dependencies into the class you are testing.
3. **@Spy**: Creates a partial mock, where you can stub certain methods while allowing others to use the real implementation.
4. **@Captor**: Used to capture method arguments for further assertions.

### 3. PowerMockito

Common PowerMockito Annotations and Methods:

- **@RunWith(PowerMockRunner.class)**: This annotation is used to tell JUnit to run the test class with PowerMock’s runner. It integrates PowerMock with the JUnit lifecycle.
- **@PrepareForTest**: This annotation specifies the classes whose static or private methods will be mocked.
- **PowerMockito.mockStatic(Class.class)**: This method is used to mock static methods of the specified class.
- **PowerMockito.whenNew(Class.class)**: This method is used to mock the constructor of a class.
- **PowerMockito.spy(Class.class)**: Creates a partial mock (spy) of a class, allowing real methods to be called except for those that are mocked.
- **PowerMockito.doReturn().when()**: Defines the behavior of a private method using PowerMockito’s syntax.
