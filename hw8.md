1. List all of the annotations you learned from class and homework to annotaitons.md
See the file.
2. Type out the code for the Comment feature of the class project.
```java
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment() {
    }

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", post=" + post +
                '}';
    }
}
```
3. In postman, call all of the APIs in PostController and CommentController.
4. What is JPA? and what is Hibernate?
JPA (Java Persistence API):
JPA is a specification in Java that defines how to map Java objects to relational database tables. It provides a standard way to interact with relational databases by using Java objects, but JPA itself is just a specification, meaning it doesn't provide an implementation.
JPA allows developers to focus on the object-oriented domain model instead of the underlying database interaction.
JPA annotations like @Entity, @Table, @Id, etc., are used to define how Java classes and fields correspond to database tables and columns.
Hibernate:
Hibernate is a popular ORM (Object-Relational Mapping) framework and one of the most commonly used JPA implementations. It follows the JPA specification but also adds additional features not defined in JPA (e.g., caching, custom SQL queries, batch processing).
Hibernate simplifies the interaction between Java objects and relational databases by automatically managing data persistence, retrieval, and updates.
Hibernate can be used independently of JPA, but in most modern applications, it's used as the JPA provider.
5. What is Hiraki? what is the benefits of connection pool?
HikariCP:
HikariCP is a fast and lightweight connection pooling library for Java. It's designed to provide high performance and low-latency access to databases in Java applications.
It manages the database connections and helps in reusing connections to improve application performance.
Benefits of Connection Pooling:
Improved Performance:

Opening and closing a database connection for each database request is expensive in terms of time and resources. Connection pools allow reusing existing database connections, reducing overhead.
Resource Management:

Connection pooling helps control and limit the number of open connections to the database, preventing excessive resource usage (e.g., too many open connections can lead to resource exhaustion).
Thread Safety:

Multiple threads in a web application can reuse connections from the pool safely without creating conflicts.
Latency Reduction:

By keeping connections open and reusing them, the time spent on establishing new connections is minimized, leading to lower latency in applications.
6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
@OneToMany:
One entity is related to many other entities. For example, one User can have multiple Orders.
Mapped on the parent entity side.
@ManyToOne:
Many entities are related to one entity. For example, many Orders belong to one User.
Mapped on the child entity side.
@ManyToMany:
Many entities are related to many other entities. For example, a Student can enroll in many Courses, and a Course can have many Students.
Usually involves a join table to represent the relationship.
7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?
CascadeType:
Cascade is an operation applied from the parent entity to the child entities. It defines how certain operations (like persist, remove, etc.) should propagate from a parent entity to its related child entities.
CascadeType.ALL: Applies all cascade operations (like PERSIST, REMOVE, MERGE, etc.) to related entities. For example, if you save or delete a Post, all associated Comment objects will also be saved or deleted.

Orphan removal means that if a child entity (e.g., Comment) is removed from the parent's (e.g., Post) collection, it is also automatically removed from the database.
Use Case: This is useful when child entities are dependent on their parent entity for existence. For example, if a Comment is removed from a Post, it should also be deleted from the database.

8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which situation you choose which one?
In JPA, fetch type defines when related entities are loaded from the database. JPA provides two fetching strategies: FetchType.LAZY and FetchType.EAGER.

FetchType.LAZY (Lazy Loading):
Definition: With lazy loading, the associated entity is not loaded from the database until it is explicitly accessed in your code.
Behavior: When you fetch a parent entity, the related entities (like a list of comments for a post) will not be loaded until you try to access them. This reduces the initial load time and resource usage if you don’t need the related entities immediately.
FetchType.EAGER (Eager Loading):
Definition: With eager loading, the associated entity is always loaded from the database immediately when the parent entity is fetched.
Behavior: When you fetch a parent entity, all associated entities (e.g., comments of a post) are fetched as part of the same query.

9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
JPA Naming Convention:
JPA repository interfaces follow a naming convention for method names that automatically map to SQL queries. Spring Data JPA automatically provides implementations for these methods based on their names.

Structure:
findBy<PropertyName>: Finds entities by a property of the entity.
findBy<PropertyName>And<PropertyName>: Combines conditions using logical AND.
findBy<PropertyName>Or<PropertyName>: Combines conditions using logical OR.
OrderBy<PropertyName>: Sorts the result by the given property.


13. What is JPQL?
JPQL (Java Persistence Query Language) is the query language used in JPA to retrieve data from a relational database. Unlike SQL, which operates directly on tables and columns, JPQL works with entity objects and their attributes, providing a more object-oriented approach to querying.

JPQL allows you to write queries using the entity model rather than the database schema, meaning you query based on Java class names and fields.
JPQL queries resemble SQL but are designed to operate on the JPA entity objects and use their properties and relationships.
Example JPQL Query:

```java

@Query("SELECT u FROM User u WHERE u.username = :username")
User findByUsername(@Param("username") String username);
In this example, User is a JPA entity, and the query operates on the username field of the User entity.
```
14. What is @NamedQuery and @NamedQueries?
@NamedQuery and @NamedQueries are JPA annotations used to define predefined, static queries that can be reused throughout the application. These queries are defined at the entity class level and can be executed by their name later.

@NamedQuery: Defines a single query.
@NamedQueries: Allows the definition of multiple named queries.
15. What is @Query? In which Interface we write the sql or JPQL?
@Query is an annotation provided by Spring Data JPA that allows you to define custom queries using JPQL (or native SQL) directly in the repository interface.

You can use @Query for both JPQL and native SQL queries, depending on the nativeQuery flag.
@Query is typically used in Repository interfaces to define custom JPQL or SQL queries.
16. What is HQL and Criteria Queries?
HQL (Hibernate Query Language):
HQL is a query language similar to JPQL but specific to Hibernate.
Like JPQL, HQL operates on entities and their attributes rather than database tables and columns. It is used in Hibernate to perform queries on the database using the object model.
HQL is case-insensitive for class and property names but case-sensitive for query keywords.
17. What is EnityManager?
EntityManager is the core interface of JPA that manages the persistence context and interacts with the underlying database. It is responsible for the lifecycle of entities and provides methods for:

Persisting entities.
Merging entities (updating existing entities).
Removing entities.
Querying the database.
Managing transactions.

18. What is SessionFactory and Session?
SessionFactory:
In Hibernate, SessionFactory is a heavyweight object responsible for creating and managing Session objects.
It is thread-safe and usually created once per application. Typically, SessionFactory is initialized when the application starts and is used to create Session objects whenever the application interacts with the database.
SessionFactory is an immutable object and is used to manage connections, configuration details, and sessions in Hibernate.
Key Responsibilities:

Creating instances of Session.
Providing access to the second-level cache.
Reading and writing Hibernate configuration.


Session:
Session is a lightweight object representing a single unit of work with the database. It is used to interact with the database by executing CRUD operations on persistent objects.
It is not thread-safe, meaning each thread or request should use its own Session object.
Session is used to:
Persist, retrieve, update, and delete entities.
Manage the first-level cache (a session-scoped cache).
Manage transactions and batch operations.

19. What is Transaction? how to manage your transaction?
Transaction:
A transaction is a sequence of operations that are treated as a single logical unit of work, and either all of them are executed successfully (commit) or none of them (rollback) if any operation fails.
Transactions ensure ACID properties (Atomicity, Consistency, Isolation, and Durability) in database operations.
In Hibernate, transactions are typically managed using the Session object, which interacts with the database within a transaction boundary.

How to manage transactions:
Programmatic Transaction Management:

This involves manually beginning and committing transactions using Transaction API.
It’s useful when you need fine-grained control over transaction management.
```java
Session session = sessionFactory.openSession();
Transaction transaction = null;

try {
    transaction = session.beginTransaction();
    // Perform database operations (CRUD)
    User user = new User("John", "Doe");
    session.save(user);
    transaction.commit(); // Commit transaction
} catch (Exception e) {
    if (transaction != null) {
        transaction.rollback(); // Rollback transaction if an error occurs
    }
    e.printStackTrace();
} finally {
    session.close();
}
```
2. Declarative Transaction Management (Spring):

When using Spring Framework, transactions are typically managed declaratively using annotations like @Transactional, allowing you to handle transactions without explicitly starting, committing, or rolling back them.
This approach is more convenient and abstracts away much of the boilerplate code.
Example with @Transactional:
```java
@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user); // Transaction is automatically managed
    }
}
```
20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
Hibernate Caching refers to the mechanism that stores objects in memory to reduce the number of database queries, improve performance, and speed up data access. Caching is critical in improving the efficiency of database-heavy applications by reducing the need to repeatedly fetch data from the database.

Hibernate supports two levels of caching:

First-Level Cache (Session Cache)
Second-Level Cache (SessionFactory Cache)
Hibernate Caching Mechanism:
First-Level Cache (Default):

Scope: Session-scoped, meaning it exists for the lifecycle of the session.
Behavior: Data is cached in the Session object. If the same entity is requested multiple times within the same session, Hibernate fetches the data from the cache, rather than querying the database.
Automatic: The first-level cache is enabled by default, and Hibernate manages it automatically.
Second-Level Cache:

Scope: SessionFactory-scoped, meaning it is shared across multiple sessions and persists until the SessionFactory is closed.
Behavior: Caches entities, collections, and queries. It is not enabled by default and must be configured explicitly.
Types of Caching Strategies:
Read-Only: Suitable for entities that do not change.
Read-Write: Suitable for entities that can be modified.
Non-Strict Read-Write: Suitable for entities where caching is not synchronized with database updates.
Transactional: Used when database-level transactions are involved
21. What is the difference between first-level cache and second-level cache?
| Feature                | First-Level Cache                   | Second-Level Cache                |
|------------------------|--------------------------------------|-----------------------------------|
| **Scope**              | Per `Session` (session-scoped)       | Per `SessionFactory` (shared across sessions) |
| **Default**            | Enabled by default                   | Disabled by default (needs configuration) |
| **Lifetime**           | Exists only for the duration of the session | Exists until the `SessionFactory` is closed |
| **Visibility**         | Cache is **not shared** between sessions | Cache is **shared** across multiple sessions |
| **Configuration**      | No explicit configuration required   | Requires configuration for the cache provider (e.g., EhCache, Redis) |
| **Granularity**        | Caches individual entities           | Can cache entities, collections, and queries |
| **Use Cases**          | Ensures that multiple requests for the same entity within a session avoid repeated database queries | Reduces database load across multiple sessions by caching frequently accessed entities |
| **Cache Provider**     | Managed internally by Hibernate      | Requires an external cache provider (e.g., EhCache, Redis, Infinispan) |
| **Performance Impact** | Improves performance only within a single session | Significantly improves performance across the application by reducing the number of database queries |
