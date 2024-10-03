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
