## **Core Spring Bean Management and IoC Annotations**
- **@Component**: Marks a class as a Spring-managed bean.
- **@Service**: Specialization of `@Component` for service layer beans.
- **@Repository**: Marks the class as a Data Access Object (DAO) that interacts with the database.
- **@Controller**: Specialization of `@Component` for web controllers.
- **@Bean**: Defines a method that returns a Spring bean, used in configuration classes.
- **@Configuration**: Marks a class as a source of bean definitions.
- **@ComponentScan**: Specifies base packages to scan for Spring components.

---

## **Dependency Injection Annotations**
- **@Autowired**: Automatically injects beans by type.
- **@Qualifier**: Resolves ambiguity when multiple beans of the same type exist by specifying the bean by name.
- **@Primary**: Marks one bean as the default when multiple beans of the same type exist.
- **@Resource**: Injects a bean by name (preferred) or type.
- **@Inject**: JSR-330 annotation for dependency injection, similar to `@Autowired`.

---

## **Scope and Lifecycle Annotations**
- **@Scope**: Defines the scope of a bean (e.g., `singleton`, `prototype`, `request`, `session`).
- **@PostConstruct**: Executes a method after bean initialization.
- **@PreDestroy**: Executes a method before a bean is destroyed.

---

## **Spring Boot Annotations**
- **@SpringBootApplication**: Marks the main class of a Spring Boot application and combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

---

## **Exception Handling Annotations**
- **@ExceptionHandler**: Handles specific exceptions in Spring MVC controllers.
- **@ControllerAdvice**: Allows global exception handling across multiple controllers.

---

## **Validation and Request Annotations**
- **@Valid**: Triggers validation for incoming request data.
- **@RequestBody**: Binds the HTTP request body to a method parameter.

---

## **Spring MVC Annotations**
- **@RestController**: Combines `@Controller` and `@ResponseBody` to simplify REST API development.
- **@RequestMapping**: Maps HTTP requests to handler methods (can be customized with HTTP methods like `@GetMapping`, `@PostMapping`, etc.).
- **@GetMapping**: Maps HTTP GET requests for retrieving resources.
- **@PostMapping**: Maps HTTP POST requests to handler methods for creating resources.
- **@PutMapping**: Maps HTTP PUT requests for updating resources.
- **@DeleteMapping**: Maps HTTP DELETE requests for deleting resources.

---

## **JPA Annotations**
- **@Entity**: Declares a class as a JPA entity (mapped to a database table).
- **@Table**: Specifies the table name and details of the table that the entity maps to.
- **@Id**: Identifies the primary key in the entity.
- **@GeneratedValue**: Defines the generation strategy for primary key values (e.g., `GenerationType.IDENTITY` for auto-increment).
- **@Column**: Maps a specific field to a table column and defines column constraints like `nullable`.
- **@CreationTimestamp**: Automatically assigns the timestamp when the entity is created.
- **@UpdateTimestamp**: Automatically assigns the timestamp when the entity is updated.

---

## **JPA Relationship Annotations**
- **@ManyToOne**: Establishes a many-to-one relationship between entities.
- **@JoinColumn**: Specifies the foreign key column for the relationship.
- **@ManyToMany**: Establishes a many-to-many relationship between entities.
- **@JoinTable**: Defines the join table for a many-to-many relationship.

---

## **Query Annotations**
- **@Query**: Allows defining custom queries using JPQL.
- **@NamedQuery**: Declares a named JPQL query at the entity level.

---

## **Transaction Annotations**
- **@Transactional**: Ensures that the method or class runs within a database transaction.

---

## **Summary of Use Cases**
- **Spring Core & IoC**: `@Component`, `@Service`, `@Repository`, `@Controller`, `@Bean`, `@Configuration`.
- **Dependency Injection**: `@Autowired`, `@Qualifier`, `@Primary`, `@Resource`, `@Inject`.
- **Spring Boot**: `@SpringBootApplication`.
- **Exception Handling**: `@ExceptionHandler`, `@ControllerAdvice`.
- **Web MVC**: `@RestController`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`.
- **JPA**: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@ManyToOne`, `@JoinColumn`.
