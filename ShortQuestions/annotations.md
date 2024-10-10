# Annotations

### 1. `@NoRepositoryBean`

- **Purpose**: Indicates that a particular interface is not a repository itself but serves as a base for other repository interfaces. It prevents Spring from trying to create an instance of this interface.

### 2. `@Deprecated`

- **Purpose**: Marks a method, class, or field as deprecated, indicating that it should no longer be used and may be removed in future versions. It typically provides a warning to developers.

### 3. `@Repository`

- **Purpose**: Indicates that a class is a Spring Data repository. It is a specialization of the `@Component` annotation, allowing for exception translation and enabling Spring to automatically detect and register the class as a bean.

### 4. `@Service`

- **Purpose**: Indicates that a class is a service in the service layer. Like `@Repository`, it is also a specialization of `@Component` and is used to indicate that the class holds business logic.

### 5. `@Autowired`

- **Purpose**: Indicates that a dependency should be automatically injected by Spring's dependency injection mechanism. It can be used on fields, constructors, or methods.

### 6. `@Entity`

- **Purpose**: Indicates that a class is a JPA entity, meaning it represents a table in the database. It is used for mapping Java objects to database records.

### 7. `@GeneratedValue`

- **Purpose**: Specifies the strategy for generating primary keys for JPA entities. Common strategies include `IDENTITY`, `SEQUENCE`, and `AUTO`.

### 8. `@Retention`

- **Purpose**: Specifies how long annotations with the annotated type are to be retained. Possible values are `SOURCE`, `CLASS`, and `RUNTIME`.

### 9. `@Target`

- **Purpose**: Indicates the types of elements an annotation can be applied to, such as methods, fields, classes, or parameters.

### 10. `@Documented`

- **Purpose**: Indicates that whenever the annotated element is documented (for example, in Javadoc), the annotation should be included in the documentation.

### 11. `@Component`

- **Purpose**: Indicates that a class is a Spring component. It is a generic stereotype for any Spring-managed component, allowing Spring to automatically detect and register the class as a bean.

### 12. `@AliasFor`

- **Purpose**: Used in annotations to indicate that one attribute is an alias for another, providing more flexible attribute mappings.

### 13. `@CreationTimestamp`

- **Purpose**: Indicates that a field should be automatically populated with the timestamp of when the entity is created. Typically used with `LocalDateTime` or `Date`.

### 14. `@UpdateTimestamp`

- **Purpose**: Similar to `@CreationTimestamp`, this annotation indicates that a field should be updated with the current timestamp whenever the entity is updated.

### 15. `@Transactional`

- **Purpose**: Indicates that a method or class should be executed within a transactional context. This ensures that all operations within the method or class are treated as a single transaction.

### 16. `@UniqueConstraint`

- **Purpose**: Used in the `@Table` annotation to specify unique constraints on the columns of a database table.

### 17. `@NamedQuery`

- **Purpose**: Defines a static, reusable query in the entity class that can be referenced by name in code. It helps improve performance by allowing queries to be parsed at startup.

### 18. `@SpringBootApplication`

- **Purpose**: A convenience annotation that combines three annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. It marks the main class of a Spring Boot application.



## hw10

### @Resource(" {name} ")

Get the bean by name.

###  @PostConstruct

Mark a method that would be executed after a bean constructed

### @GetMapping @PostMapping



## hw 11

**@PreAuthorize**

Define access control on methods  based on user roles or conditions. Simplify security constraints.

~~~java
@PreAuthorize("hasRole('ADMIN')")
public void adminMethod() {
// Only users with the 'ADMIN' role can execute this method
	System.out.println("Admin method executed.");
}

@PreAuthorize("hasAuthority('READ_PRIVILEGE')")
public void readData() {
// Only users with 'READ_PRIVILEGE' authority can execute this method
	System.out.println("Data read successfully.");
}
~~~

@PreFilter 



@PostAuthorize 



@PostFilter  



@Secured



@RolesAllowed



## hw12

Following controls the AOP running. 

**Order**: running order 

**Before advice**: Runs before something happens. For instance, a barista might check inventory before making coffee.

**After returning advice**: Runs after a task is successfully completed, like cleaning up after a customer leaves.

**After throwing advice**: Runs if something goes wrong, such as an apology or discount if a customer spills their drink.

**Around advice**: Runs before and after an event, like a cashier’s process of greeting, taking payment, and saying goodbye.

## hw13

- **`@ParameterizedTest`**: This annotation marks a method as a parameterized test.

- **`@ValueSource`** (or other parameter sources): Specifies the different inputs that will be used to invoke the test method.

- **`@CsvSource`**: For comma-separated values (multiple parameters per test case).
- **`@CsvFileSource`**: For loading CSV data from a file.
- **`@MethodSource`**: For providing complex objects or values using a method.
- **`@EnumSource`**: For testing with enum values.
- **@Mock** creates mock objects that simulates the behavior of a real object by defaulting all method calls to return default value. The behavior can be defined using **stubbing**.
- **@InjectMock** automatically inject mock objects (those annotated with @Mock or created manually with Mockito.mock()) into the class under  test.