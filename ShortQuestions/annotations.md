### Spring Boot Annotations:
- **@SpringBootApplication**: Marks the main class of a Spring Boot application.

### JPA Annotations:
- **@Entity**: Declares a class as a JPA entity (mapped to a database table).
- **@Table**: Specifies the table name and details of the table that the entity maps to.
- **@Id**: Identifies the primary key in the entity.
- **@GeneratedValue(strategy = GenerationType.IDENTITY)**: Defines the generation strategy for primary key values (e.g., auto-increment).
- **@Column**: Maps a specific field to a table column and can define column details such as name, and nullable constraints.
- **@CreationTimestamp**: Automatically assigns the timestamp when the entity is created.
- **@UpdateTimestamp**: Automatically assigns the timestamp when the entity is updated.

### Repository Annotations:
- **@Repository**: Marks the class as a Data Access Object (DAO) that interacts with the database.

### Relationship Annotations:
- **@ManyToOne**: Establishes a many-to-one relationship between entities.
- **@JoinColumn**: Specifies the foreign key column for the relationship.
- **@ManyToMany**: Establishes a many-to-many relationship between entities.
- **@JoinTable**: Defines the join table for a many-to-many relationship.

### Query Annotations:
- **@Query**: Allows defining custom queries using JPQL.
- **@NamedQuery**: Declares a named JPQL query at the entity level.

### Transaction Annotations:
- **@Transactional**: Ensures that the method or class runs within a database transaction.

### Spring MVC Annotations:
- **@PostMapping**: Maps HTTP POST requests to specific handler methods for creating resources.
- **@GetMapping**: Maps HTTP GET requests for retrieving resources.
- **@PutMapping**: Maps HTTP PUT requests for updating resources.
- **@DeleteMapping**: Maps HTTP DELETE requests for deleting resources.
- **@RequestBody**: Binds the HTTP request body to a method parameter.
- **@RestController**: Combines `@Controller` and `@ResponseBody` to simplify REST API development.
