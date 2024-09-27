# HW8 -- Spring Data

## 1. List all of the annotations you learned from class and homework to annotations.md

[annotations.md](annotations.md)

## 2. Type out the code for the Comment feature of the class project.

[red_book](../Coding/hw8/springboot-redbook)

## 3. In postman, call all of the APIs in PostController and CommentController.

## 4. What is JPA? and what is Hibernate?

**JPA**:

- Definition: A specification for managing relational data using object-relational mapping (ORM) in Java.
- Purpose: Defines how Java objects map to database tables.
- Key Feature: Provides annotations like `@Entity`, `@Table`, and `@Id` to map classes to tables.
- Note: JPA is not an implementation but a set of guidelines.

**Hibernate**:
- Definition: An ORM framework that **implements** JPA.
- Purpose: Provides the actual database interaction for JPA interfaces.
- Key Feature: Offers additional features like caching and HQL (Hibernate Query Language).
- Note: Hibernate handles the conversion of Java objects into SQL and vice versa.

In summary, JPA is a specification that defines how to map Java objects to database tables, while Hibernate is an implementation of that specification, providing the actual functionality for working with databases in Java.

## 5. What is Hikari? what is the benefits of connection pool?

1. **HiKari**
   - Definition: A fast and lightweight JDBC connection pool for managing database connections in Java.
   - Purpose: Optimizes database connection management in applications.

2. **Benefits of Connection Pool**
   - **Improved Performance**: Reuses connections, reducing the overhead of opening/closing them.
   - **Resource Management**: Limits the number of active connections to prevent overload.
   - **Scalability**: Efficiently handles a growing number of requests.
   - **Prevents Leaks**: Ensures connections are properly closed and returned after use.

## 6. What is the `@OneToMany`, `@ManyToOne`, `@ManyToMany` ? write some examples.

1. `@OneToMany`:
   - Definition: One entity has a relationship with many entities.
   - Example:
   ```java
   @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
   private List<Comment> comments;
   ```

2. `@ManyToOne`:
   - Definition: Many entities are related to one entity.
   - Example:
   ```java
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "post_id")
   private Post post;
   ```
3. `@ManyToMany:`:
    - Definition:  Many entities are related to many other entities..
    - Example:
   ```java
   @ManyToMany
   @JoinTable(name = "user_roles",
   joinColumns = @JoinColumn(name = "user_id"),
   inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;
   ```

## 7. What is the `cascade = CascadeType.ALL, orphanRemoval = true` ? and what are the other CascadeType and their features? In which situation we choose which one?

### 1. **`cascade = CascadeType.ALL`**
- **Definition**: Applies all cascade operations (persist, merge, remove, refresh, detach) from parent to child entities.
- **Use Case**: When you want the child entities to follow the lifecycle of the parent (e.g., delete a parent, all its children are deleted).

### 2. **`orphanRemoval = true`**
- **Definition**: Automatically removes child entities that are no longer referenced by the parent.
- **Use Case**: When removing a child from a collection should delete it from the database (e.g., when a parent no longer "owns" a child entity).

### 3. **Other `CascadeType` options**
- **`PERSIST`**: Saves child entities when the parent is saved.
- **`MERGE`**: Updates child entities when the parent is updated.
- **`REMOVE`**: Deletes child entities when the parent is deleted.
- **`REFRESH`**: Reloads child entities when the parent is refreshed from the database.
- **`DETACH`**: Detaches child entities when the parent is detached from the persistence context.

### 4. **Choosing the right `CascadeType`**
- **Use `ALL`**: When you want all operations (insert, update, delete) to cascade from parent to child.
- **Use `PERSIST`**: When you only want child entities to be persisted when the parent is.
- **Use `REMOVE`**: When deleting the parent should remove all associated children.
- **Use `orphanRemoval = true`**: When you want to automatically delete child entities that are removed from the parent's collection.

## 8. What is the `fetch = FetchType.LAZY`, `fetch = FetchType.EAGER` ? what is the difference? In which situation you choose which one?

### 1. **`fetch = FetchType.LAZY`**
- **Definition**: Data is **loaded on demand**. The associated entities are only fetched from the database when they are accessed for the first time.
- **Use Case**: Used when you don't always need the related entities, improving performance by loading them only when necessary.

### 2. **`fetch = FetchType.EAGER`**
- **Definition**: Data is **loaded immediately**. The associated entities are fetched together with the parent entity.
- **Use Case**: Use when you always need the related entities to avoid additional database queries.

### 3. **Difference**
- **LAZY**: Fetches the associated entities only when needed, reducing initial load time and memory usage.
- **EAGER**: Fetches the associated entities immediately, which may increase load time but ensures all needed data is available right away.

### 4. **Choosing Between `LAZY` and `EAGER`**
- **Choose LAZY**: When associated entities are not always needed and performance is a concern (e.g., large datasets or rarely accessed relationships).
- **Choose EAGER**: When the associated entities are always needed with the parent entity to avoid multiple queries.

## 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?

### 1. **JPA Naming Convention**
- **Definition**: JPA provides a naming convention for repository method names. The method names are parsed by JPA to generate the necessary SQL queries.
- **Rule**: The method name must start with a keyword (e.g., `find`, `get`, `count`), followed by the entity field names (in camel case) that you want to query by.

### 2. **Shall we implement the method by ourselves?**
- **Answer**: No, we don't need to implement common CRUD methods ourselves. If we follow JPA naming conventions, Spring Data JPA will automatically generate the query for us. However, for complex queries, we may use `@Query` or implement custom methods.

### 3. **Examples of JPA Method Naming**
- **`findByLastname(String lastname)`**: Finds all entities where the `lastname` field matches the provided value.
- **`findByFirstnameAndLastname(String firstname, String lastname)`**: Finds all entities where both `firstname` and `lastname` match.
- **`findByAgeGreaterThan(int age)`**: Finds all entities where the `age` field is greater than the provided value.
- **`countByStatus(String status)`**: Counts all entities where the `status` field matches the provided value.

### 4. **Custom Queries (if needed)**
- For more complex queries, we can use the `@Query` annotation.
    - Example:
      ```java
      @Query("SELECT p FROM Post p WHERE p.title = :title")
      List<Post> findByTitle(@Param("title") String title);
      ```

## 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.

[comment repository](../Coding/hw8/springboot-redbook/src/main/java/com/chuwa/redbook/dao/CommentRepository.java)

## 11. (Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcTemplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.

## 12. type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with https://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.

[hw05_01_slides_JPQL](../Coding/hw8/hw05_01_slides_JPQL)

## 13. What is JPQL?

### **JPQL**

- **Definition**: JPQL (Java Persistence Query Language) is a query language used in JPA to interact with databases via **entity objects**.

- **Key Feature**: It uses **entity class names** and **field names**, instead of database tables and columns.

- **Purpose**: Allows writing object-oriented queries for database operations.

### **Example**:
```java
@Query("SELECT p FROM Post p WHERE p.title = :title")
List<Post> findPostByTitle(@Param("title") String title);
```

- This JPQL query retrieves `Post` entities where the `title` field matches the given parameter. It operates on the `Post` entity, not directly on the database table.

## 14. What is `@NamedQuery` and `@NamedQueries`?

### **@NamedQuery**:
- **Definition**: Defines a **single JPQL query** at the entity level that can be reused throughout the application.
- **Purpose**: Allows pre-defining queries to avoid writing JPQL repeatedly.

### **@NamedQueries**:
- **Definition**: A container annotation that allows defining **multiple `@NamedQuery`** annotations for an entity.
- **Purpose**: Enables organizing and grouping multiple named queries on a single entity.

### **Example**:
```java
@Entity
@NamedQueries({
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title"),
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
})
public class Post {
    // entity fields and methods
}
```

`@NamedQuery` defines a single query, while `@NamedQueries` allows multiple queries on the same entity.

## 15. What is `@Query`? In which Interface we write the sql or JPQL?

### **@Query**:
- **Definition**: `@Query` is used to define custom SQL or JPQL queries directly in the repository interface.
- **Purpose**: Allows writing more complex queries that cannot be handled by JPA naming conventions.

### **Where to Write**:
- **Interface**: The SQL or JPQL queries are written in the **Repository interface**, typically extending `JpaRepository` or `CrudRepository`.

### **Example**:
```java
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.title = :title")
    List<Post> findPostByTitle(@Param("title") String title);
}
```

In this example, the `@Query` annotation is used to define a JPQL query in the `PostRepository` interface.

### 1. **HQL (Hibernate Query Language)**
- **Definition**: HQL is an **object-oriented query language** that works on **Hibernate entity objects** rather than directly on database tables, similar to JPQL.
- **Purpose**: Allows querying using entity names and their properties instead of table names and column names.

#### **Example**:
```java
String hql = "FROM Post p WHERE p.title = :title";
Query query = session.createQuery(hql);
query.setParameter("title", "My First Post");
List<Post> results = query.list();
```

This HQL query fetches `Post` entities based on the `title` field.

### 2. **Criteria Queries**
- **Definition**: Criteria Queries are a **programmatic API** for building database queries using Java code, allowing dynamic and type-safe query construction.
- **Purpose**: Useful for building complex queries dynamically at runtime in a type-safe manner.

#### **Example**:
```java
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Post> cq = cb.createQuery(Post.class);
Root<Post> post = cq.from(Post.class);
cq.select(post).where(cb.equal(post.get("title"), "My First Post"));
Query<Post> query = session.createQuery(cq);
List<Post> results = query.getResultList();
```

This Criteria Query dynamically builds a query to fetch `Post` entities with a specific `title`.


## 17. What is `EnityManager`?

### **EntityManager**
- **Definition**: `EntityManager` is an API provided by JPA that manages the lifecycle of **entity objects** and enables interaction with the database.
- **Purpose**: It provides methods to **persist**, **find**, **merge**, and **remove** entities in the context of a persistence unit.

### **Key Responsibilities**:
1. **Persisting Entities**: Saving new entities to the database.
2. **Retrieving Entities**: Finding entities by their primary key or running JPQL queries.
3. **Updating Entities**: Merging detached entities into the current persistence context.
4. **Removing Entities**: Deleting entities from the database.

### **Example**:
```java
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
}

EntityManager entityManager = entityManagerFactory.createEntityManager();

// Persist a new post
Post newPost = new Post();
newPost.setTitle("My First Post");
entityManager.getTransaction().begin();
entityManager.persist(newPost);
entityManager.getTransaction().commit();
```

In this example, `EntityManager` is used to persist a new Post entity to the database.

## 18. What is `SessionFactory` and `Session`?

### **SessionFactory**
- **Definition**: `SessionFactory` is a **Hibernate object** that is responsible for creating and managing `Session` objects. It is a factory for `Session` instances and is typically created once per application.
- **Purpose**: Manages the configuration and lifecycle of sessions. It is heavyweight, thread-safe, and designed to be instantiated once per application lifecycle.

### **Session**
- **Definition**: `Session` is the primary interface used in Hibernate to interact with the database. It represents a single unit of work or a database transaction.
- **Purpose**: Provides methods for **CRUD operations**, **query execution**, and **transaction management**. It is not thread-safe and is typically short-lived.

### **Relationship**:
- **SessionFactory** creates and provides `Session` instances.

### **Example**:
```java
SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
Session session = sessionFactory.openSession();

session.beginTransaction();
Post post = new Post();
post.setTitle("Hibernate Post");
session.save(post);
session.getTransaction().commit();
session.close();
```

- `SessionFactory` is used to create a `Session`, which is then used for database operations.

## 19. What is `Transaction`? how to manage your transaction?

### **Transaction**
- **Definition**: A `Transaction` in Hibernate or JPA represents a single unit of work, where a series of database operations are executed. It ensures **atomicity**—meaning that either all operations within the transaction succeed, or none are applied (rolled back in case of failure).
- **Purpose**: Guarantees **consistency** in the database by grouping operations together, ensuring that all or nothing is committed.

### **Managing Transactions**
In JPA, transactions can be managed using:
1. **Programmatic Transactions**: Managed manually using the `EntityManager` or `Session` interface.
2. **Declarative Transactions**: Managed using the `@Transactional` annotation, which simplifies transaction management by delegating it to the framework.

### **Example of Programmatic Transaction (EntityManager)**:
```java
EntityManager entityManager = entityManagerFactory.createEntityManager();
entityManager.getTransaction().begin();
try {
    Post post = new Post();
    post.setTitle("Transactional Post");
    entityManager.persist(post);
    entityManager.getTransaction().commit(); // Commit transaction if successful
} catch (Exception e) {
    entityManager.getTransaction().rollback(); // Rollback transaction on failure
}
```

### **Example of Declarative Transaction (Spring) with `@Transactional`**:

```java
@Service
public class PostService {

    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }
}
```

- **Programmatic Transactions**: Manually begin and commit/rollback transactions.
- **Declarative Transactions**: Use `@Transactional` to manage transactions automatically, leaving the framework to handle commit/rollback.

## 20. What is Hibernate Caching? Explain Hibernate caching mechanism in detail.

### **Hibernate Caching**
- **Definition**: Hibernate caching is a mechanism to **improve performance** by reducing the number of database hits. It stores data temporarily so that repeated queries can be served from the cache rather than hitting the database again.
- **Purpose**: Speeds up data retrieval, reduces database load, and enhances application performance.

### **Hibernate Caching Mechanism**

#### 1. **First-Level Cache (Session-Level Cache)**
- **Definition**: This is the default cache provided by Hibernate at the **session level**. Each Hibernate `Session` object maintains a cache of its loaded entities.
- **Scope**: Exists only during the lifecycle of the session.
- **Features**:
  - It is automatically enabled.
  - Each session has its own cache, and the cache is not shared between sessions.

#### **Example**:
```java
Session session = sessionFactory.openSession();
Post post1 = session.get(Post.class, 1L); // First time, hits the database
Post post2 = session.get(Post.class, 1L); // Second time, fetches from cache (same session)
```

#### 2. **Second-Level Cache (SessionFactory-Level Cache)**
- **Definition**: Second-level cache is **optional** and operates at the **SessionFactory level**. It is shared among all sessions and can store data across transactions.
- **Scope**: Exists across multiple sessions and transactions.
- **Features**:
  - Needs to be explicitly configured and enabled.
  - Common second-level cache providers include **Ehcache**, **Hazelcast**, **Redis**, and **Infinispan**
#### **Example (Configuring Second-Level Cache)**:
```java
<property name="hibernate.cache.use_second_level_cache" value="true"/>
<property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>
```

#### 3. **Query Cache**
- **Definition**: Query cache caches **query result sets**. It works in conjunction with the second-level cache and stores the result of queries.
- **Features**:
  - It does not cache the actual entities, but the identifiers of the entities.
  - Must be explicitly enabled.
#### **Example**:
```java
<property name="hibernate.cache.use_query_cache" value="true"/>
```

### Summary of Hibernate Caching Mechanism:
- **First-Level Cache**: Exists per session, default, and cannot be disabled.
- **Second-Level Cache**: Shared across sessions, needs explicit configuration.
- **Query Cache**: Caches query results, must be enabled manually, and works with second-level cache.

## 21. What is the difference between first-level cache and second-level cache?

### **Differences Between First-Level Cache and Second-Level Cache**

| Feature                | First-Level Cache                          | Second-Level Cache                         |
|------------------------|--------------------------------------------|--------------------------------------------|
| **Scope**               | Per session (limited to a single session). | Across multiple sessions (shared globally).|
| **Default Behavior**    | Enabled by default, cannot be disabled.    | Optional, needs explicit configuration.    |
| **Storage Location**    | Cached within the session object.          | Cached at the SessionFactory level.        |
| **Lifecycle**           | Exists only for the duration of the session. | Exists across multiple sessions and transactions. |
| **Usage**               | Caches entities within a single session.   | Caches entities globally for all sessions. |
| **Performance Impact**  | Reduces database calls within the same session. | Improves performance across the entire application. |
| **Accessibility**       | Not accessible by other sessions.          | Accessible by all sessions.                |

### **Summary**:
- **First-Level Cache**: Bound to a single session, automatically enabled, and helps reduce database access within that session.
- **Second-Level Cache**: Shared among all sessions, improves overall performance but needs to be explicitly configured.

## 22. How do you understand `@Transactional`? (https://github.com/TAIsRich/tutorial-transaction)

### **Understanding `@Transactional`**
- **Definition**: `@Transactional` is an annotation in Spring that manages transactions automatically, ensuring that a method runs within a transaction boundary. It simplifies transaction management by eliminating the need to manually begin, commit, or rollback transactions.

### **Key Features**:
- **Atomicity**: Ensures that a series of database operations succeed or fail as a unit. If any operation fails, all changes are rolled back.
- **Propagation**: Determines how transactions interact with each other. For example, a new transaction can be started or an existing one can be joined.
- **Isolation**: Defines the level of isolation between transactions, affecting how data changes in one transaction are visible to others.
- **Rollback**: Automatically rolls back a transaction in case of runtime exceptions.

### **Example**:
```java
@Service
public class OrderService {

    @Transactional
    public void placeOrder(Order order) {
        // Multiple operations like saving order and processing payment
        orderRepository.save(order);
        paymentService.processPayment(order);
        // If any exception occurs, both operations are rolled back
    }
}
```

### **In the Example (GitHub)**:
The `@Transactional` annotation is used to handle operations related to orders and payments. It ensures that if any operation in the transaction fails, the entire transaction is rolled back, ensuring data consistency.

### **Advantages of `@Transactional`**:

1. **Automatic Rollback**: Ensures rollback if any exception occurs.
2. **Simplifies Transaction Management**: No need to manually manage `begin`, `commit`, or `rollback`.
3. **Flexible Propagation**: Supports nested transactions or reusing existing ones.




