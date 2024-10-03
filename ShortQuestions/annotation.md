### Core Spring Boot Annotations
* @SpringBootApplication: A combination of three annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan. It is the entry point for a Spring Boot application, typically used on the main class.
* @EnableAutoConfiguration: Enables Spring Boot's auto-configuration feature, which automatically configures your Spring application based on the dependencies in your classpath.
* @Configuration: Indicates that a class contains Spring bean definitions. It is typically used in conjunction with @Bean methods to define beans.
* @ComponentScan: This annotation is used to tell Spring Boot to scan the specified packages for Spring components, configurations, and services.
* @Component: Indicates that a class is a Spring-managed component and can be autowired.
* @Bean: Used within @Configuration-annotated classes to define beans that are managed by the Spring container.
* @Service: A specialized @Component used to denote a service layer class.
* @Repository: A specialized @Component that marks a class as a data repository, allowing Spring to handle database exceptions.
* @Controller: A specialized @Component used to define web controllers (MVC).
* @RestController: A combination of @Controller and @ResponseBody. It marks a class as a Spring MVC controller and assumes that every method returns a response body, typically in JSON format.
* @Autowired: Marks a dependency to be injected automatically by Spring.

### Web Annotations
* @RequestMapping: Maps HTTP requests to handler methods in Spring controllers. It can be applied at both the class and method level
* @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping: Specialized versions of @RequestMapping for handling HTTP GET, POST, PUT, DELETE, and PATCH requests respectively.
* @RequestBody: Used to map HTTP request body to a method parameter.
* @PathVariable: Binds URL template variables to method parameters.

### Database and Data Access Annotations
* @Entity: Mark a class as JPA entity. And Spring Data JPA uses this annotation to map the class to the corresponding table in the database.
* @Table: Specifites the anem of the table int the database to which this entity will be mapped. It is used when the entity calss doesn't match the table name (e.g.@Table(name = "{table_name}"))
* @Id: Marks a field as the primary key of the entity. It is required in every JPA entity to identify the primary key field.
* @GeneratedValue: It defines the strategy for how the primary key should be generated.
* @Column: Customizes the column mapping for a specific field in an entity like specify column details like name, length, or whether the column is nullable.
* @ManyToOne, @OneToMany, @OneToOne, @ManyToMany: Specifies how entities relate to each other in the database
* @JoinColumn: Specifies the column that joins two tables in a foreign key relationship.Used in combination with annotations like @ManyToOne or @OneToOne to specify the foreign key.
* @Repository: Marks a class as a repository. It indicates that the class provides mechanisms for data access, often used in data access objects (DAO).
* @Query: Used to define custom JPQL or SQL queries for specific methods in a repository. Allows custom database queries when standard method names in JpaRepository aren't sufficient. 
* @Modifying: Marks a query method that modifies data in the database (like an UPDATE or DELETE query).Used in combination with @Query to specify that the query performs an update or delete operation.
* @Transactional: It ensures that a sequence of operations is handled as a single transaction. Used for manages transactions at the method or class level.
* @PrePersist, @PostPersist, @PreUpdate, @PostUpdate: They handel tasks like auditing, logging, or validation before or after a particular lifecycle event. They are used to trigger events during the lifecycle of an entity
* @Version: Marks a field as a version field
### Spring Security

* @EnableWebSecurity：Enables Spring Security's web security support.
* @Secured: Used at the method level to specify which roles are allowed to execute a method.
* @PreAuthorize: This annotation is more powerful than @Secured and allows you to use SpEL (Spring Expression Language) to define more complex access control expressions.