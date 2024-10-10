# List of Annotations

1. **@Entity**  
   Marks a class as a JPA entity.

2. **@Table**  
   Specifies the table name in the database for the entity.

3. **@Id**  
   Indicates the primary key of the entity.

4. **@GeneratedValue**  
   Defines the primary key generation strategy.

5. **@Column**  
   Maps a field to a specific column in the database.

6. **@OneToOne**  
   Defines a one-to-one relationship between entities.

7. **@OneToMany**  
   Defines a one-to-many relationship between entities.

8. **@ManyToOne**  
   Defines a many-to-one relationship between entities.

9. **@ManyToMany**  
   Defines a many-to-many relationship between entities.

10. **@JoinColumn**  
    Specifies the foreign key column for a relationship.

11. **@Transactional**  
    Manages transaction boundaries in service methods.

12. **@Query**  
    Used to define custom queries in Spring Data JPA.

13. **@Param**  
    Binds method parameters to query parameters.

14. **@Fetch**  
    Specifies how the associated entities should be fetched (e.g., `LAZY`, `EAGER`).

15. **@Transient**  
    Marks a field that should not be persisted to the database.

16. **@Component**  
    Marks a class as a Spring component for automatic scanning and registration.

17. **@Service**  
    Indicates that a class is a service in the service layer of the application.

18. **@Repository**  
    Indicates that a class is a Data Access Object (DAO) that interacts with the database.

19. **@Controller**  
    Marks a class as a Spring MVC controller to handle web requests.

20. **@RestController**  
    Combines `@Controller` and `@ResponseBody` to simplify RESTful web services.

21. **@RequestMapping**  
    Specifies the URL mapping for a controller method.

22. **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping**  
    Shorthand for `@RequestMapping` for specific HTTP methods.

23. **@Autowired**  
    Automatically injects the dependent beans into a class.

24. **@Qualifier**  
    Specifies which bean to inject when multiple candidates are present.

25. **@Primary**  
    Indicates that a bean should be given preference when multiple candidates are present.

26. **@Valid**  
    Used to trigger validation on a method parameter or field.

27. **@ExceptionHandler**  
    Specifies a method to handle specific exceptions in a controller.

28. **@ControllerAdvice**  
    Provides global exception handling across multiple controllers.

29. **@SpringBootApplication**  
    A convenience annotation that combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

30. **@PostConstruct**  
    Marks a method to be executed after the bean's properties have been initialized.

31. **@PreAuthorize**  
    Specifies permission expressions that must be satisfied before a method is invoked for method-level security.

32. **@Secured**  
    Specifies one or more roles that must be present to allow method invocation.

33. **@RolesAllowed**  
    Specifies the roles allowed to access a method, commonly related to Java EE security.

34. **@PostAuthorize**  
    Specifies permission expressions that must be satisfied after a method has been invoked for method-level security.

35. **@EnableGlobalMethodSecurity**  
    Enables global method security, allowing the use of `@PreAuthorize` and other security annotations.

36. **@Aspect**  
    Indicates that a class is an aspect in Aspect-Oriented Programming (AOP).

37. **@Before**  
    Specifies advice that runs before a join point.

38. **@After**  
    Specifies advice that runs after a join point, regardless of its outcome.

39. **@AfterReturning**  
    Specifies advice that runs after a join point completes successfully.

40. **@AfterThrowing**  
    Specifies advice that runs if a join point throws an exception.

41. **@Pointcut**  
    Defines a pointcut, which is an expression that matches join points.

42. **@Around**  
    Specifies advice that wraps around a join point, allowing control over when to proceed with the join point execution.

43. **@Api**  
    Used to annotate controller classes for generating corresponding API documentation.

44. **@ApiOperation**  
    Describes a single operation method, including its description, HTTP method, return value, etc.

45. **@ApiParam**  
    Describes information about method parameters, such as name, type, and whether it is required.

46. **@ApiResponse**  
    Describes the response information of a method, including status code and description.

47. **@ApiResponses**  
    Describes a collection of multiple responses.

48. **@ApiModel**  
    Describes model objects, typically used for complex objects in request or response bodies.

49. **@ApiModelProperty**  
    Describes information about model properties, including name, description, and whether it is required.

50. **@RunWith**  
    Specifies a runner to use for the test class (e.g., JUnit).

51. **@Mock**  
    Creates a mock object for a given class or interface.

52. **@Spy**  
    Creates a spy object that wraps a real object, allowing for partial mocking.

53. **@InjectMocks**  
    Automatically injects mocks into the annotated class.

54. **@BeforeAll**  
    Indicates that a method should run once before all tests in the test class.

55. **@BeforeEach**  
    Indicates that a method should run before each test in the test class.

56. **@Test**  
    Marks a method as a test method.

57. **@AfterEach**  
    Indicates that a method should run after each test in the test class.

58. **@AfterAll**  
    Indicates that a method should run once after all tests in the test class.

59. **@MockBean**  
    Used to create and inject a mock bean into the Spring application context for testing.

60. **@DirtiesContext**  
    Indicates that the application context should be closed and removed after the test, ensuring a fresh context for subsequent tests.

61. **@SpringBootTest**  
    Indicates that the application context should be loaded for integration tests.

62. **@WebMvcTest**  
    Focuses on testing Spring MVC components (like controllers).

63. **@DataJpaTest**  
    Focuses on testing JPA components, configuring an in-memory database.

64. **@RestClientTest**  
    Focuses on testing REST clients and services.