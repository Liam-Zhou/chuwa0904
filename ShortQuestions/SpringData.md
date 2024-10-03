# Spring Data

## 1. Annotations
Check annotations.md

## 2. Type out the code for the Comment feature of the class project.
[Comment](https://github.com/CTYue/springboot-redbook/blob/04_comment/src/main/java/com/chuwa/redbook/entity/Comment.java)

## 3. Call all of APIs in the PostController and CommentController.
[PostController](https://github.com/CTYue/springboot-redbook/blob/04_comment/src/main/java/com/chuwa/redbook/controller/PostController.java)
[CommentController](https://github.com/CTYue/springboot-redbook/blob/04_comment/src/main/java/com/chuwa/redbook/controller/CommentController.java)

## 4. What is JPA? And what is Hibernate?
- JPA (Java Persistence API): A specification for object-relational mapping in Java, allowing developers to manage relational data as Java objects.
- Hibernate: An implementation of JPA that simplifies database interactions and offers features like caching and transaction management.
- In summary, JPA is an ORM specification, Hibernate is a JPA implementation.

## 5. What is Hiraki? What is the benefits of connection pool?
Hikari is a high-performance JDBC connection pool library for Java, known for its speed and low overhead.
### Benefits of connection pool
A connection pool is a cache of database connections that can be reused for future database requests. Instead of creating a new connection each time, an application borrows a connection from the pool, uses it, and then returns it for reuse. This approach enhances performance and resource management.
- Performance: Reuses existing connections, reducing overhead.
- Resource Management: Limits active connections, preventing database overload.
- Faster Response Times: Improves application responsiveness with ready-to-use connections.
- Concurrency: Supports multiple simultaneous database accesses, enhancing scalability.

## 6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
- @OneToMany: Defines a one-to-many relationship, where one entity can be related to multiple instances of another entity.
```java
@Entity
public class User {
    @Id
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
```

- @ManyToOne: Defines a many-to-one relationship, where multiple instances of one entity can relate to a single instance of another entity.
```java
@Entity
public class Post {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

- @ManyToMany: Defines a many-to-many relationship, where multiple instances of one entity can relate to multiple instances of another entity.
```java
@Entity
public class Student {
    @Id
    private Long id;

    @ManyToMany
    @JoinTable(name = "student_course", 
               joinColumns = @JoinColumn(name = "student_id"), 
               inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
```


## 7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?
- CascadeType.ALL allows all operations (persist, merge, remove, refresh, detach) to cascade from the parent entity to child entities.
- orphanRemoval = true automatically removes child entities that are no longer referenced by the parent entity. 

### Other CascadeType Options
1. CascadeType.PERSIST: Cascades persist operations.
2. CascadeType.MERGE: Cascades merge operations.
3. CascadeType.REMOVE: Cascades remove operations.
4. CascadeType.REFRESH: Cascades refresh operations.
5. CascadeType.DETACH: Cascades detach operations.

### When to Use
- CascadeType.ALL: For full lifecycle management of child entities.
- CascadeType.PERSIST, CascadeType.MERGE: For saving or updating only.
- CascadeType.REMOVE: For deleting child entities when the parent is deleted.


## 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which situation you choose which one?
- FetchType.LAZY:
    - Related entities are loaded only when accessed, improving performance by avoiding unnecessary loading.
    - Use it for large datasets or when related entities are accessed conditionally.

- FetchType.EAGER:
    - Related entities are loaded immediately with the parent entity, which can increase memory consumption and loading time.
    - Use it when you always need the related entities together with the parent.

## 9. What is the rule of JPA naming convention? Shall we implement the method by yourselves? Could you list some examples?
JPA naming convention specifies how to name query methods in Spring Data JPA so that they can be automatically translated into SQL queries.
- Methods must start with specific keywords like findBy, countBy, or deleteBy.
- The following part must be the names of entity attributes, using camel case.
- Logical operators (like And, Or) and comparison operators (like LessThan, GreaterThan) can be used in method names.

We do not need to implement the query methods ourselves; JPA handles the translation based on the naming convention.

### Examples:
- `findByLastName(String lastName)`
- `countByAgeGreaterThan(int age)`
- `deleteByEmail(String email)`
- `findByFirstNameAndLastName(String firstName, String lastName)`

## 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.
```java
// Find comments by post ID
List<Comment> findByPostId(long postId);

// Find comments containing a specific text
List<Comment> findByTextContaining(String text);

// Count comments by post ID
long countByPostId(long postId);
```

## 11. Optional
Check out a new [branch](https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcTemplate) created from the 02_post_RUD branch. In this branch, the DAO layer has been replaced using JdbcTemplate.


## 12. Type the code
[Code](https://github.com/CTYue/springboot-redbook/tree/02_post_RUD)


## 13. What is JPQL?
JPQL stands for Java Persistence Query Language. 
It is an object-oriented query language defined by JPA that operates on the entity objects rather than directly on database tables. 
JPQL queries are similar to SQL but focus on the entities and their relationships. It allows developers to write queries using the entity model, making it more intuitive for Java applications.


## 14. What is @NamedQuery and @NamedQueries?
- @NamedQuery is a JPA annotation used to define a static, pre-defined query at the entity level. It allows developers to create named queries that can be reused, improving code organization and readability.

- @NamedQueries is a container annotation that holds multiple @NamedQuery annotations. It allows the definition of several named queries for a single entity in one place.

```java
@Entity
@NamedQueries({
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
})
public class User {
    // entity fields and methods
}
```



## 15. What is @Query? In which Interface we write the sql or JPQL?
- @Query is a Spring Data JPA annotation used to define custom queries directly in the repository interface. It allows developers to write SQL or JPQL queries for specific data retrieval without relying on the method naming conventions.

- @Query can be applied to repository methods in interfaces that extend JpaRepository or CrudRepository. This provides flexibility to perform complex queries beyond simple CRUD operations.

```java
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
    
    @Query(value = "SELECT * FROM users WHERE age > ?1", nativeQuery = true)
    List<User> findUsersOlderThan(int age);
}
```

## 16. What is HQL and Criteria Queries?
- HQL (Hibernate Query Language) is an object-oriented query language for querying Hibernate entities. It uses entity names and properties instead of database tables and columns.
```java
String hql = "FROM User WHERE age > :age";
```

- Criteria Queries allow for building queries using Java code with a fluent API. This approach provides type safety and flexibility for dynamic queries.
```java
CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
criteriaQuery.select(root).where(criteriaBuilder.greaterThan(root.get("age"), 18));
```

## 17. What is EntityManager?
EntityManager is an interface in JPA (Java Persistence API) used to interact with the persistence context. It manages the lifecycle of entity instances, allowing for CRUD operations and query execution.

### Key Functions:
- Persist: Save a new entity to the database.
- Merge: Update an existing entity or merge detached entities.
- Remove: Delete an entity from the database.
- Find: Retrieve an entity by its primary key.
- Query: Execute JPQL or native SQL queries.
```java
EntityManager entityManager = entityManagerFactory.createEntityManager();
entityManager.getTransaction().begin();
entityManager.persist(new User("John Doe"));
entityManager.getTransaction().commit();
```


## 18. What is SessionFactory and Session?
- SessionFactory:
  - Is a thread-safe factory that creates Session instances in Hibernate. 
  - It configures Hibernate settings, create Session objects, and manages caching and database connections. 

- Session 
  - Is a short-lived, single-threaded object used to perform CRUD operations and interact with the database. 
  - Executes operations like save, update, delete, and query.

```java
SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
Session session = sessionFactory.openSession();
session.beginTransaction();
session.save(new User("John Doe"));
session.getTransaction().commit();
session.close();
```

## 19. What is Transaction? How to manage your transaction?
Transaction is a sequence of operations performed as a single unit of work, ensuring data integrity and consistency according to ACID properties (Atomicity, Consistency, Isolation, Durability).

### Managing Transactions:
- Use @Transactional to automatically handle transactions.
- Use TransactionManager to manually control transactions (start, commit, rollback).

```java
// Use @Transactional
@Transactional
public void performDatabaseOperation() {
    // CRUD operations
}

// Use TransactionManager
TransactionManager transactionManager = ...;
TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
try {
    // CRUD operations
    transactionManager.commit(status);
} catch (Exception e) {
    transactionManager.rollback(status);
}
```

## 20. What is Hibernate Caching? Explain Hibernate Caching Mechanism in Detail.
Hibernate caching is a mechanism to store frequently accessed data in memory, improving performance by reducing database queries.

### Hibernate Caching Mechanism
1. First-level cache:
    - Tied to the Session object and enabled by default.
    - Stores entities loaded during the session, preventing redundant database hits.
    - Cleared when the session is closed.

2. Second-level cache:
    - Associated with the SessionFactory and shared across sessions.
    - Stores entities and collections, configurable with cache providers (e.g., Ehcache).
    - Must be explicitly enabled.

### Caching Strategies
- Read-only: Cached data does not change, suitable for static data.
- Read-write: Cached data can be updated, with versioning for concurrency.
- Transactional: Similar to read-write but for transactional requirements.

### Benefits
- Improves performance by reducing database access.
- Decreases database load, allowing for better scalability.
- Speeds up application response times.



## 21. What is difference between First-Level Cache and Second-Level Cache
First-level cache:
- Tied to a specific Session.
- Cleared when the session closes.
- Automatically enabled, stores entities loaded during the session.
- Not shared between sessions.

Second-level cache:
- Associated with SessionFactory.
- Shared across multiple sessions.
- Must be explicitly enabled.
- Caches entities, collections, and query results for better performance.


## 22. How do you understanding @Transactional?
@Transactional in Spring ensures data integrity during multiple database operations. @Transactional guarantees that either all operations succeed or none do, maintaining consistent data in our application.
1. Without @Transactional:
The order might be saved in the database, leading to inconsistencies since the payment process would fail, leaving the system in an invalid state.
2. With @Transactional:
If the payment fails, the order will not be saved, and all changes will be rolled back automatically, ensuring data integrity.