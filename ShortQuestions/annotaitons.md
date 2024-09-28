## **1. Spring Framework Annotations**

### `@Component`

- **Description:** Marks a Java class as a Spring component. It serves as a generic stereotype for any Spring-managed component.
- **Usage:** `@Component`

### `@Service`

- **Description:** Specialization of `@Component` indicating that the class holds business logic.
- **Usage:** `@Service`

### `@Repository`

- **Description:** Specialization of `@Component` for persistence layer components. It also provides exception translation.
- **Usage:** `@Repository`

### `@Controller`

- **Description:** Indicates that the class serves as a web controller in Spring MVC.
- **Usage:** `@Controller`

### `@RestController`

- **Description:** Combines `@Controller` and `@ResponseBody`. Used for RESTful web services.
- **Usage:** `@RestController`

### `@Autowired`

- **Description:** Enables automatic dependency injection by type.
- **Usage:** `@Autowired`

### `@Qualifier`

- **Description:** Specifies which bean should be injected when multiple candidates are present.
- **Usage:** `@Qualifier("beanName")`

### `@Value`

- **Description:** Injects values into fields from property files.
- **Usage:** `@Value("${property.name}")`

### `@Configuration`

- **Description:** Indicates that the class has @Bean definitions and can be processed by the Spring container.
- **Usage:** `@Configuration`

### `@Bean`

- **Description:** Indicates that a method produces a bean to be managed by the Spring container.
- **Usage:** `@Bean`

## **2. Spring Data JPA Annotations**

### `@Entity`

- **Description:** Specifies that the class is an entity and is mapped to a database table.
- **Usage:** `@Entity`

### `@Table`

- **Description:** Specifies the primary table for the annotated entity.
- **Usage:** `@Table(name = "table_name")`

### `@Id`

- **Description:** Specifies the primary key of an entity.
- **Usage:** `@Id`

### `@GeneratedValue`

- **Description:** Provides the specification of generation strategies for the values of primary keys.
- **Usage:** `@GeneratedValue(strategy = GenerationType.AUTO)`

### `@Column`

- **Description:** Specifies the mapped column for a persistent property or field.
- **Usage:** `@Column(name = "column_name")`

### `@OneToOne`

- **Description:** Defines a one-to-one relationship between two entities.
- **Usage:** `@OneToOne`

### `@OneToMany`

- **Description:** Defines a one-to-many relationship between two entities.
- **Usage:** `@OneToMany`

### `@ManyToOne`

- **Description:** Defines a many-to-one relationship between two entities.
- **Usage:** `@ManyToOne`

### `@ManyToMany`

- **Description:** Defines a many-to-many relationship between two entities.
- **Usage:** `@ManyToMany`

### `@JoinColumn`

- **Description:** Specifies a column for joining an entity association or element collection.
- **Usage:** `@JoinColumn(name = "column_name")`

### `@JoinTable`

- **Description:** Specifies the join table for many-to-many relationships.
- **Usage:** `@JoinTable(name = "join_table_name")`

### `@RepositoryRestResource`

- **Description:** Customizes the REST endpoint for a repository.
- **Usage:** `@RepositoryRestResource(path = "customPath")`

## **3. Spring MVC Annotations**

### `@RequestMapping`

- **Description:** Maps HTTP requests to handler methods of MVC and REST controllers.
- **Usage:** `@RequestMapping("/path")`

### `@GetMapping`

- **Description:** Shortcut for `@RequestMapping` with method `GET`.
- **Usage:** `@GetMapping("/path")`

### `@PostMapping`

- **Description:** Shortcut for `@RequestMapping` with method `POST`.
- **Usage:** `@PostMapping("/path")`

### `@PutMapping`

- **Description:** Shortcut for `@RequestMapping` with method `PUT`.
- **Usage:** `@PutMapping("/path")`

### `@DeleteMapping`

- **Description:** Shortcut for `@RequestMapping` with method `DELETE`.
- **Usage:** `@DeleteMapping("/path")`

### `@PathVariable`

- **Description:** Indicates that a method parameter should be bound to a URI template variable.
- **Usage:** `@PathVariable("id") Long id`

### `@RequestParam`

- **Description:** Binds a web request parameter to a method parameter.
- **Usage:** `@RequestParam("name") String name`

### `@RequestBody`

- **Description:** Indicates that a method parameter should be bound to the body of the web request.
- **Usage:** `@RequestBody Object obj`

### `@ResponseBody`

- **Description:** Indicates that the return value of a method should be bound to the web response body.
- **Usage:** `@ResponseBody`

## **4. Other Useful Annotations**

### `@Transactional`

- **Description:** Indicates that the method or class should be wrapped in a database transaction.
- **Usage:** `@Transactional`

### `@EnableJpaRepositories`

- **Description:** Enables JPA repositories. Activates the Spring Data JPA repositories.
- **Usage:** `@EnableJpaRepositories(basePackages = "com.example.repositories")`

### `@EnableAutoConfiguration`

- **Description:** Enables Spring Boot’s auto-configuration mechanism.
- **Usage:** `@EnableAutoConfiguration`

### `@SpringBootApplication`

- **Description:** Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. It's a convenience annotation for Spring Boot applications.
- **Usage:** `@SpringBootApplication`

### `@Slf4j`

- **Description:** Lombok annotation to automatically generate a logger field.
- **Usage:** `@Slf4j`

### `@Data`

- **Description:** Lombok annotation to generate getters, setters, `toString`, `equals`, and `hashCode` methods.
- **Usage:** `@Data`

### `@Builder`

- **Description:** Lombok annotation to implement the builder pattern.
- **Usage:** `@Builder`
