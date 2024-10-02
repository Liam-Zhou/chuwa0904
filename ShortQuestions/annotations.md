- @Entity: Marks a class as an entity that will be mapped to a database table.
- @Table: Specifies the table in the database that corresponds to the entity.
- @Id: Specifies the primary key of an entity.
- @GeneratedValue: Defines the strategy for primary key generation (e.g., GenerationType.IDENTITY).
- @Column: Specifies the column details (e.g., name, nullable) for a field in an entity.
- @OneToMany: Defines a one-to-many relationship between entities.
- @ManyToOne: Defines a many-to-one relationship between entities.
- @ManyToMany: Defines a many-to-many relationship between entities.
- @JoinColumn: Specifies the foreign key column used in a relationship.
- @JoinTable: Specifies the table used in many-to-many relationships.
- @Query: Allows writing custom JPQL or SQL queries directly in the repository layer.
- @NamedQuery: Defines a static query with a name that can be referenced later.
- @NamedQueries: Contains multiple @NamedQuery annotations.
- @PersistenceContext: Injects an instance of EntityManager for interacting with the persistence context.
- @Transactional: Marks methods or classes where transactions should be managed.
- @EnableTransactionManagement: Enables annotation-driven transaction management in Spring.
- @Repository: Indicates that a class is a repository that will interact with the database.
- @Service: Marks a class as a service provider.
- @Autowired: Automatically injects dependencies into a Spring-managed bean.
- @RestController: Combines @Controller and @ResponseBody to handle RESTful web services.
- @RequestMapping: Maps HTTP requests to handler methods in controller classes.

### 1. **Core Spring Annotations:**
- `@Component`: Marks a class as a Spring-managed component. Automatically detected through classpath scanning.
- `@Service`: Specialization of `@Component`, indicating a service class.
- `@Repository`: Specialization of `@Component`, indicating a data access class (DAO).
- `@Controller`: Specialization of `@Component`, used in Spring MVC to define controllers that handle web requests.
- `@ControllerAdvice`: Specialization for global exception handling in Spring MVC.

### 2. **Dependency Injection Annotations:**
- `@Autowired`: Injects dependencies automatically by type.
- `@Qualifier`: Used with `@Autowired` to specify which bean to inject when there are multiple beans of the same type.
- `@Primary`: Marks a bean as the default choice when multiple beans of the same type exist.
- `@Resource`: Part of Java EE, injects dependencies by name first, then by type.
- `@Inject`: Part of JSR-330 (Java DI), similar to `@Autowired`.

### 3. **Configuration Annotations:**
- `@Configuration`: Marks a class as a source of bean definitions.
- `@Bean`: Used within `@Configuration` classes to define beans manually.
- `@ComponentScan`: Tells Spring where to scan for `@Component` classes.
- `@EnableAutoConfiguration`: Automatically configures Spring based on the classpath.

### 4. **Spring Boot Annotations:**
- `@SpringBootApplication`: Combines `@Configuration`, `@ComponentScan`, and `@EnableAutoConfiguration` for Spring Boot applications.

### 5. **Bean Scope Annotations:**
- `@Scope`: Defines the scope of a bean.
  - `singleton`: One instance per Spring IoC container (default).
  - `prototype`: A new instance is created each time the bean is requested.
  - `request`: A new instance per HTTP request (web-specific).
  - `session`: A new instance per HTTP session (web-specific).
  - `application`: A single instance per `ServletContext` (web-specific).
  - `websocket`: One instance per WebSocket session (web-specific).

### 6. **Others:**
- `@PostConstruct`: Marks a method to be executed after dependency injection is done to perform any initialization.
- `@PreDestroy`: Marks a method to be executed before the bean is removed from the container to perform cleanup.

### 7. **Additional Dependency Injection Methods:**
- **Constructor Injection**: Using constructors to inject dependencies.
- **Setter Injection**: Using setters to inject dependencies.
- **Field Injection**: Using annotations on fields to inject dependencies.

### References:
- ClassPathXmlApplicationContext
- AnnotationConfigApplicationContext
- Singleton vs Prototype scope handling


### Spring MVC
- @GetMapping: Maps HTTP GET requests onto specific handler methods.
- @PutMapping: Maps HTTP PUT requests onto specific handler methods, usually used to update resources.
- @PostMapping: Maps HTTP POST requests onto specific handler methods, typically used to create resources.
- @DeleteMapping: Maps HTTP DELETE requests onto specific handler methods, typically used to delete resources.
- @RequestMapping: Used to map web requests onto handler classes or methods. Can be used for any HTTP request type.
- @RestController: Combines @Controller and @ResponseBody. Marks a class as a Spring MVC controller that returns data, typically in JSON or XML format.
- @Controller: Marks a class as a Spring MVC controller that typically returns views (e.g., HTML or JSP pages).
- @RequestBody: Binds the body of an HTTP request to a method parameter, commonly used for JSON or XML input.
- @ResponseBody: Indicates that the return value of a method should be written directly to the HTTP response body (usually in JSON or XML format).
- @Autowired: Used for automatic dependency injection, telling Spring to inject a bean into another bean automatically.
- @PathVariable: Binds a URL template variable to a method parameter.


### Spring Security
- @RestController: Combines @Controller and @ResponseBody. Marks a class as a Spring MVC controller that returns data, typically in JSON or XML format.
- @Controller: Marks a class as a Spring MVC controller that typically returns views (e.g., HTML or JSP pages).
- @PreAuthorize: Specifies that a method can only be accessed by users who meet the given expression. Commonly used for expression-based access control.
- @PostAuthorize: Allows a method to execute but restricts the return of the method based on the given expression. Often used for fine-grained control over who can see the result of a method.
- @Secured: Used to specify a list of roles on a method, restricting access to users who hold one of the specified roles. It's a simpler alternative to expression-based access control.
- @EnableWebSecurity: Enables Spring Security’s web security support and provides the Spring MVC integration. It allows customization of web security and method security.
