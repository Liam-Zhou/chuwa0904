
# Homework

## 1. List all of the annotations you learned from class and homework to `annotations.md`
- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@Column`
- `@OneToMany`
- `@ManyToOne`
- `@ManyToMany`
- `@JoinColumn`
- `@JoinTable`
- `@NamedQuery`
- `@NamedQueries`
- `@Query`
- `@PersistenceContext`
- `@Transactional`
- `@EnableTransactionManagement`

## 2. Type out the code for the Comment feature of the class project
```java
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Getters and Setters
}
```

## 3. In Postman, call all of the APIs in `PostController` and `CommentController`
- **PostController**
  - `GET /posts` - Retrieve all posts
  - `GET /posts/{id}` - Retrieve a post by ID
  - `POST /posts` - Create a new post
  - `PUT /posts/{id}` - Update an existing post
  - `DELETE /posts/{id}` - Delete a post
- **CommentController**
  - `GET /comments` - Retrieve all comments
  - `GET /comments/{id}` - Retrieve a comment by ID
  - `POST /comments` - Create a new comment
  - `PUT /comments/{id}` - Update an existing comment
  - `DELETE /comments/{id}` - Delete a comment

## 4. What is JPA? and what is Hibernate?
- **JPA (Java Persistence API)**: A specification for ORM (Object-Relational Mapping) that allows managing relational data in Java applications.
- **Hibernate**: The most popular implementation of JPA that provides additional features on top of JPA for managing database interactions.

## 5. What is Hikari? What are the benefits of a connection pool?
- **Hikari**: A high-performance JDBC connection pool.
- **Benefits**:
  - Efficient resource management
  - Faster database connection acquisition and release
  - Improved application performance

## 6. What are `@OneToMany`, `@ManyToOne`, `@ManyToMany`? Write some examples.
- `@OneToMany`: A parent entity has multiple child entities.
  ```java
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;
  ```
- `@ManyToOne`: Multiple child entities belong to one parent entity.
  ```java
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;
  ```
- `@ManyToMany`: Entities have a many-to-many relationship.
  ```java
  @ManyToMany
  @JoinTable(
    name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private Set<Course> courses;
  ```

## 7. What is `cascade = CascadeType.ALL`, `orphanRemoval = true`? What are the other CascadeTypes and their features? In which situation do we choose which one?
- `CascadeType.ALL`: All operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH) are cascaded to related entities.
- `orphanRemoval = true`: Removes child entities if they are no longer associated with the parent.
- **Other CascadeTypes**:
  - `PERSIST`: Cascades the persist operation
  - `MERGE`: Cascades the merge operation
  - `REMOVE`: Cascades the remove operation
  - `REFRESH`: Cascades the refresh operation
  - `DETACH`: Cascades the detach operation

## 8. What is `fetch = FetchType.LAZY`, `fetch = FetchType.EAGER`? What is the difference? In which situation do you choose which one?
- `FetchType.LAZY`: Data is loaded only when it is accessed (default for `@ManyToOne` and `@OneToMany`).
- `FetchType.EAGER`: Data is loaded immediately (default for `@OneToOne`).
- Use `LAZY` for performance efficiency when you don’t need the data immediately. Use `EAGER` when you need the associated data upfront.

## 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
- **Naming Convention**: JPA methods should follow patterns like `findBy`, `getBy`, `countBy`.
- Examples:
  - `findByLastName(String lastName)`
  - `findByAgeGreaterThan(int age)`
- You don’t have to implement them; JPA will provide the methods based on the naming convention.

## 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.
Example:
```java
List<Comment> findByPostId(Long postId);
```

## 11. (Optional) Check out a new branch from branch `02_post_RUD` and replace the DAO layer using JdbcTemplate.
```bash
git checkout -b hw02_01_jdbcTemplate
```

## 12. Type the code, you need to check out a new branch from branch `02_post_RUD`, name the new branch with `hw05_01_slides_JPQL`.
```bash
git checkout -b hw05_01_slides_JPQL
```

## 13. What is JPQL?
- **JPQL (Java Persistence Query Language)**: A query language used in JPA that allows querying against entity objects.

## 14. What is `@NamedQuery` and `@NamedQueries`?
- `@NamedQuery`: Defines a static query with a specified name.
- `@NamedQueries`: Allows multiple named queries within a single entity.

## 15. What is `@Query`? In which Interface do we write the SQL or JPQL?
- `@Query`: Used to write custom SQL/JPQL queries in the repository interface.

## 16. What is HQL and Criteria Queries?
- **HQL (Hibernate Query Language)**: Similar to SQL, but uses Java entities instead of tables.
- **Criteria Queries**: A type-safe way to build dynamic queries using Hibernate.

## 17. What is `EntityManager`?
- **EntityManager**: Manages the persistence context, providing methods for CRUD operations and query execution.

## 18. What is `SessionFactory` and `Session`?
- **SessionFactory**: Creates sessions and maintains a connection to the database.
- **Session**: Represents a single interaction with the database.

## 19. What is a Transaction? How to manage your transaction?
- **Transaction**: A unit of work that is either fully completed or fully rolled back.
- Manage transactions using `@Transactional` or through `EntityManager`.

## 20. What is Hibernate Caching? Explain the Hibernate caching mechanism in detail.
- Hibernate offers two levels of caching:
  - **First-Level Cache**: Associated with the session (default).
  - **Second-Level Cache**: Shared across sessions and must be explicitly enabled.

## 21. What is the difference between first-level cache and second-level cache?
- **First-Level Cache**: Session-specific and not shared.
- **Second-Level Cache**: Shared across sessions and enabled manually.

## 22. How do you understand `@Transactional`?
- `@Transactional`: Ensures that a method is executed within a transaction, handling commit and rollback automatically.
