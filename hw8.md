#### 1. List all of the annotations you learned from class and homework to annotaitons.md
#### 2. Type out the code for the Comment feature of the class project.
#### 3. In postman, call all of the APIs in PostController and CommentController.
#### 4. What is JPA? and what is Hibernate?
**JPA** stands for Java Persistence API, which is a specification provided by Java for object-relational mapping (ORM). It defines a standard way for Java applications to interact with databases, allowing developers to work with Java objects instead of writing SQL directly to perform database operations. JPA is part of the Java EE (Enterprise Edition) and Java SE (Standard Edition) platforms.
**Hibernate** is one of the most popular implementations of JPA. It's a powerful ORM (Object-Relational Mapping) framework for Java applications, and it helps you map Java objects to relational database tables without writing much SQL. It provides a more extensive set of features beyond the JPA specification

#### 5. What is Hiraki? what is the benefits of connection pool?
**HikariCP** is a high-performance JDBC connection pool library for Java. A connection pool manages a pool of database connections that can be reused, minimizing the overhead of repeatedly opening and closing database connections in an application. HikariCP is known for its simplicity, reliability, and performance, often outperforming other popular connection pool libraries.
**Benifits of Connection Pooling**
It reuses existing connections instead of creating new ones for each request.
It maintains a pool of active connections to the database, which can be checked out and returned by different parts of the application.
It minimizes the overhead of creating and closing database connections, resulting in better performance and resource management.

#### 6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
**@OneToMany**: One entity is related to many others. For example, one Post can have many Comments.
```
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Getters and setters
}

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Getters and setters
}
```
**@ManyToOne**: Many entities are related to one entity. For example, many Comments belong to one Post.
```
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Getters and setters
}

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Getters and setters
}
```
**@ManyToMany**: Many entities are related to many others. For example, many Students can enroll in many Courses, and many Courses can have many Students.
```
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    // Getters and setters
}

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    // Getters and setters
}
```
#### 7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?
cascade = CascadeType.ALL: Applies all cascading operations (persist, merge, remove, etc.) from parent to child entities.
orphanRemoval = true: Deletes child entities automatically if they are removed from the parent entity's collection or no longer referenced.
Other CascadeTypes allow more granular control over specific operations (persist, merge, remove, etc.) and can be chosen based on how tightly coupled your entities are and the lifecycle management you need.

#### 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which situation you choose which one?
**FetchType.LAZY:**
Default for collections (@OneToMany, @ManyToMany).
Loads associated entities on demand (when accessed).
Best for large or rarely used relationships to optimize performance.
Avoids fetching unnecessary data, but can cause LazyInitializationException if used incorrectly.
**FetchType.EAGER:**
Default for single-valued associations (@ManyToOne, @OneToOne).
Loads associated entities immediately when the parent is loaded.
Best for small, frequently accessed relationships where convenience and simplicity are important.
Can lead to performance overhead if the related entities are large or unnecessary.
Choosing between LAZY and EAGER depends on your use case, performance considerations, and how frequently the related entities are accessed.

#### 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
JPA (Java Persistence API) follows certain naming conventions for entity classes, attributes, and methods to ensure smooth mapping between Java objects and database tables. While JPA provides default conventions, you can override these with annotations like @Table, @Column, etc., but following naming conventions simplifies the development process.
**Default Convention Example:** 
```
@Entity
public class UserAccount {
    @Id
    private Long id;
    private String username;
    private String emailAddress;
}
```
#### 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.
#### 11. (Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcT emplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.
#### 12. type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with https://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.
#### 13. What is JPQL?
JPQL (Java Persistence Query Language) is a query language used in JPA (Java Persistence API) to perform queries over entities in a relational database. It is similar to SQL but operates on Java objects (entities) rather than directly on database tables, making it more object-oriented.

#### 14. What is @NamedQuery and @NamedQueries?
@NamedQuery is used to define a single JPQL query with a name that can be called later in the code.
@NamedQueries allows defining multiple named queries on the same entity.
Named queries improve code reusability, maintainability, and can potentially enhance performance by allowing JPA providers to cache the query structure.

#### 15. What is @Query? In which Interface we write the sql or JPQL?
The @Query annotation in Spring Data JPA is used to define custom queries directly on repository methods. This allows you to specify either JPQL (Java Persistence Query Language) or native SQL queries, providing flexibility in how you retrieve data from the database.
Use @Query in any repository interface extending JpaRepository or CrudRepository to execute custom queries easily

#### 16. What is HQL and Criteria Queries?
**HQL** is an object-oriented query language that is similar to SQL but operates on Hibernate entities rather than directly on database tables. It allows you to perform database operations using the Java object model.
**Criteria** Queries provide a programmatic way to construct queries using Java objects, making it easier to create dynamic queries that can change based on conditions.

#### 17. What is EnityManager?
The EntityManager is a key component of JPA that facilitates the interaction between your application and the database.
It manages the lifecycle of entity instances, handles CRUD operations, and provides the ability to create and execute queries.
By using EntityManager, you can perform efficient and straightforward database operations in a Java application.

#### 18. What is SessionFactory and Session?
**SessionFactory:**
A heavyweight, thread-safe factory for creating Session instances.
Configured once during application startup and shared across the application.
Responsible for managing configuration, caching, and resource management.
**Session:**
A lightweight, non-thread-safe object representing a single unit of work with the database.
Maintains a persistence context and handles CRUD operations.
Typically short-lived and associated with a single transaction.

#### 19. What is Transaction? how to manage your transaction?
In the context of Java Persistence API (JPA) and Hibernate, a Transaction represents a single unit of work with the database. It ensures that a series of operations are executed as a single atomic action, meaning that either all operations are successfully completed, or none of them are applied (if an error occurs). This behavior is essential for maintaining data integrity and consistency in a database.
Managing transactions involves beginning a transaction, performing the necessary operations (like CRUD operations), and either committing the transaction if everything is successful or rolling it back if an error occurs.

**Manage Transactions**
Obtain a Session: Get a Session instance from SessionFactory.
Begin Transaction: Start a transaction using the beginTransaction() method.
Perform Operations: Execute the necessary database operations (e.g., save, update, delete).
Commit/Rollback:
    If operations succeed, commit the transaction to save changes.
    If an error occurs, roll back the transaction to revert changes.
Close Session: Finally, close the session to release resources.

#### 20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
**First-Level Cache (Session Cache):**
Scope: The first-level cache is associated with a single Session instance and is enabled by default.
Lifetime: It lasts as long as the session is open. When the session is closed, the cache is cleared.
Functionality: When you retrieve an entity, Hibernate checks the first-level cache before making a database call. If the entity is already in the cache, it is returned directly, avoiding the database query.
Use Case: Suitable for managing entities within a single session.
**Second-Level Cache:**
Scope: The second-level cache is shared across multiple Session instances and is optional.
Lifetime: It can persist across sessions, meaning data can be cached even after the session is closed.
Configuration: It must be explicitly configured and can use various caching providers (e.g., EHCache, Infinispan, Hazelcast).
Functionality: When a session requests an entity, Hibernate checks the second-level cache before querying the database. If the entity is found in the cache, it is returned without hitting the database.
Use Case: Suitable for data that is frequently accessed across multiple sessions and does not change often.

#### 21. What is the difference between first-level cache and second-level cache?
Both first-level and second-level caches are essential for optimizing database interactions in Hibernate. The first-level cache provides a quick access mechanism for entities within a session, while the second-level cache allows for sharing of frequently accessed data across multiple sessions, significantly improving application performance and resource utilization. Understanding their differences and appropriate use cases helps in effectively leveraging Hibernate's caching capabilities.

#### 22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)
The @Transactional annotation in Spring is a powerful feature that simplifies transaction management in a Java application. It is used to demarcate transaction boundaries declaratively, meaning that you can manage transactions without having to write boilerplate code for beginning, committing, or rolling back transactions manually.