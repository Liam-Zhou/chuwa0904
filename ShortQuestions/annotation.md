***Spring JPA Annotations***
- @Entity: mark a class as a "database entity"
- @Table: used alongside @Entity to get more control of the database table
- @Id: used to specify the field which acts as a unique identifier (primary key) for the class.
- @GeneratedValue: instructs JPA to manage primary key generation
- @Column: provides control over the database column definition,allows control over column settings such as name and length
- @Transient:  indicates that the field is not to be persisted in the database and should be ignored by JPA
-  @OneToMany: Establishes a one-to-many relationship between two entities. This is used when one entity has a collection of another entity.
-  @ManyToOne: Establishes a many-to-one relationship, where multiple entities relate to a single entity. Often used as a foreign key.
-  @ManyToMany: Defines a many-to-many relationship between two entities and usually requires a join table.
-  @JoinColumn: Specifies the foreign key column used in relationships, such as @OneToMany or @ManyToOne.
-  @Lob: Used for mapping large objects, such as large strings or binary data, to database columns.
-  @Enumerated: Maps an enum type in Java to a corresponding column in the database

***Spring MVC annotation***

- @RestController: Combines @Controller and @ResponseBody to simplify the creation of RESTful web services, It handles HTTP requests and automatically serializes the response into JSON or XML.
- @RequestMapping: defines the base URL for endpoints in a controller.
````angular2html
@RequestMapping("/api/v1")
````
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping: Specialized versions of @RequestMapping that handle specific HTTP methods (GET, POST, PUT, DELETE)
```angular2html
 @PostMapping("/posts")
```
- @RequestBody:  Binds the body of an HTTP request to a method parameter, allowing Spring to automatically convert JSON or XML data from the request into a Java object.
```angular2html
public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){}
```
- @PathVariable: Extracts values from the URI and binds them to method parameters
```angular2html
@GetMapping("/posts/{id}")
public PostDto getPostById(@PathVariable Long id) { ... }

```
- @RequestParam: Binds HTTP request parameters to method arguments.
```angular2html
@GetMapping("/posts")
public List<PostDto> getPosts(@RequestParam String category) { ... }

```
- @ResponseStatus: Sets the HTTP status code for a response when a specific exception is thrown or when returning a specific result
```angular2html
@ResponseStatus(HttpStatus.CREATED)

```
***Spring Core annotation***
- @Autowired: Automatically injects dependencies into Spring-managed beans
-  @Component: A generic stereotype for any Spring-managed component. It is used to define a bean and make it available for dependency injection.
-  @Service: A specialization of @Component, indicating that the class provides business services.
-  @Repository: A specialization of @Component, typically used in the persistence layer to indicate that the class interacts with the database.
-  @Scope: Defines the scope of a Spring bean, such as singleton, prototype, request, session, or global session.

***Spring Security annotation***
- @EnableWebSecurity: It activates Spring Security and allows you to configure security settings like authentication, authorization
- @PreAuthorize("hasAuthority('User')"):This annotation is used for method-level security, allowing you to restrict access to a method based on the  roles or authorities
- @EnableGlobalMethodSecurity: Enables method-level security annotations like @PreAuthorize, @Secured, and @RolesAllowed. You need to add this annotation to enable the use of these method-level annotations.
- @PostAuthorize: it is used to authorize access after the method has been invoked. It is often used when authorization depends on the result of the method.

***Spring AOP***
- @Aspect:Marks a class as an Aspect
```angular2html
@Aspect
@Component
public class LoggingAspect {
    // Aspect logic goes here
}

```
- @Pointcut: Defines where (which methods) the AOP logic should be applied.
```angular2html
@Pointcut("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
    public void methodLogPointcut() {}
```
- @Before: This advice runs before the target method is executed.
```angular2html
@Before("methodLogPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before method: " + joinPoint.getSignature());
    }
```
- @AfterReturning: Advice that runs after the target method returns successfully
```angular2html
@AfterReturning(pointcut = "methodLogPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("After method: " + joinPoint.getSignature() + ", Result: " + result);
    }
```
- @Around: Around advice that runs both before and after the target method
```angular2html
@Around("methodLogPointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Before method: " + proceedingJoinPoint.getSignature());
        Object result = proceedingJoinPoint.proceed();  // Executes the target method
        log.info("After method: " + proceedingJoinPoint.getSignature() + ", Result: " + result);
        return result;
    }
```
- @annotation: Used in the pointcut expression to match methods annotated with @MethodLog. This tells the system to apply the AOP logic only to methods with that annotation.
```angular2html
@Pointcut("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
    public void methodLogPointcut() {}
```

***Mockito annotation***
- @Mock: Mock dependencies like postRepository, commentRepository.
- @InjectMocks: Inject the mocks into the class under test (e.g., CommentServiceImpl).
- @Captor: Capture and verify the arguments passed to mocked methods.
- @Spy: Spy on a real object while still being able to mock specific methods (optional).
- @BeforeEach: Set up the mocks before each test (with MockitoAnnotations.openMocks(this)).
- @Test: Define individual test methods.