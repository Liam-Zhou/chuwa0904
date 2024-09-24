1. **List all of the annotations you learned from class and homework**  
   [See annotations.md](./annotations.md)

2. **Type out the code for the Comment feature of the class project.**
    ```
    package com.chuwa.redbook.entity;

    import com.fasterxml.jackson.annotation.JsonProperty;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import javax.persistence.*;
    import java.time.LocalDateTime;

    /**
    * @author b1go
    * @date 6/23/22 10:46 PM
    */
    @Entity
    @Table(name = "comments")
    public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @JsonProperty("name")
        private String name;
        private String email;
        private String body;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;

        @CreationTimestamp
        private LocalDateTime createDateTime;

        @UpdateTimestamp
        private LocalDateTime updateDateTime;

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }

        public Comment() {
        }

        public Comment(long id, String name, String email, String body) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.body = body;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public LocalDateTime getCreateDateTime() {
            return createDateTime;
        }

        public void setCreateDateTime(LocalDateTime createDateTime) {
            this.createDateTime = createDateTime;
        }

        public LocalDateTime getUpdateDateTime() {
            return updateDateTime;
        }

        public void setUpdateDateTime(LocalDateTime updateDateTime) {
            this.updateDateTime = updateDateTime;
        }

        @Override
        public String toString() {
            return "Comment{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", body='" + body + '\'' +
                    '}';
        }
    }
    ```

3. **In postman, call all of the APIs in PostController and CommentController.**
    - POST http://localhost:8080/api/v1/posts/  
    - GET http://localhost:8080/api/v1/posts/  
    - GET http://localhost:8080/api/v1/posts/1
    - UPDATE http://localhost:8080/api/v1/posts/1
    - DELETE http://localhost:8080/api/v1/posts/1
    - POST http://localhost:8080/api/v1/posts/2/comments
    - GET http://localhost:8080/api/v1/posts/2/comments
    - UPDATE http://localhost:8080/api/v1/posts/2/comments/1
    - GET http://localhost:8080/api/v1/posts/2/comments/1
    - DELETE http://localhost:8080/api/v1/posts/2/comments/1
4. **What is JPA? What is Hibernate?**
- JPA (Java Persistence API) is a specification that defines how Java objects (entities) can be mapped to relational database tables. It standardizes the way data is persisted and retrieved in Java applications. JPA provides an abstraction layer for performing operations like CRUD (Create, Read, Update, Delete) on the database. However, JPA itself does not provide an implementation; it only defines the interfaces and rules.
- Hibernate is one of the most popular implementations of the JPA specification. It provides the actual functionality to persist data to a database by translating the JPA annotations and interfaces into SQL queries. Hibernate automates the tasks of database interaction and handles complexities like managing relationships, caching, and lazy/eager loading.


5. **What is Hiraki? What are the benefits of connection pool?**
HikariCP is a popular JDBC connection pool implementation, known for its speed and performance efficiency in managing database connections in Java applications. A connection pool is a cache of database connections that are maintained so that they can be reused when future requests to the database are made, rather than opening a new connection every time. It can improve the proformance, has faster Connection Acquisition, Reduced Latency, Avoid Connection Leaks, Scalability, Connection Timeouts and Pool Configuration.


6. **What are @OneToMany, @ManyToOne, @ManyToMany annotations?**  
   Write some examples.

- @OneToMany: Entity A (Parent) has one or more instances of Entity B (Child).  
e.g.   
    ```
    @Entity
    public class Post {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Comment> comments = new ArrayList<>();
    }
    ```
- @ManyToOne: Entity B (Child) has one instance of Entity A (Parent).  
e.g.
    ```
    @Entity
    public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String content;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;
    }
    ```  
- @ManyToMany: Entity A has multiple instances of Entity B, and Entity B has multiple instances of Entity A.  
e.g.  
    ```
    @Entity
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        private String username;

        @ManyToMany
        @JoinTable(
            name = "user_role", 
            joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();
    }

    @Entity
    public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        private String name;

        @ManyToMany(mappedBy = "roles")
        private Set<User> users = new HashSet<>();
    }
    ```




7. **What is `cascade = CascadeType.ALL, orphanRemoval = true`?**  
   What are the other CascadeTypes and their features? In which situations would you choose each one?

- cascade = CascadeType.ALL:

    - This means that all cascade operations (persist, merge, remove, refresh, detach) will be applied to the associated entity automatically whenever they are applied to the owning entity.
    -  For example, if you delete a parent entity, all the associated child entities will also be deleted.
-  orphanRemoval = true:

    - This specifies that if a child entity is removed from the relationship (i.e., the parent no longer references it), the child should also be deleted from the database. It is often used in a @OneToMany or @ManyToOne relationship where child entities shouldn't exist without the parent.
    - For example, if a Post entity has a list of Comments, and a comment is removed from that list, the comment will also be deleted from the database when orphanRemoval = true.

CascadeType.PERSIST:  
When the parent entity is persisted (saved), the child entities are also automatically persisted. This is useful when you want to save both the parent and its associated child entities together. For example, when creating a new post with comments, you might want the comments to be saved automatically when the post is saved.

CascadeType.MERGE:
When the parent entity is merged (updated), the child entities are also automatically merged. This is useful when you want to update both the parent and its child entities at the same time. For example, when updating a post, you might also want the associated comments to be updated.  

CascadeType.REMOVE:  
When the parent entity is removed (deleted), the child entities are also automatically removed. This ensures that the child entities are not left behind in the database when the parent entity is deleted. For example, when deleting a post, you want all its associated comments to be deleted as well.

CascadeType.REFRESH:  
When the parent entity is refreshed (reloaded from the database), the child entities are also refreshed. This is useful when you want to make sure that both the parent and child entities are updated with the latest data from the database.

CascadeType.DETACH:  
When the parent entity is detached (no longer managed by the persistence context), the child entities are also detached. This ensures that both the parent and its child entities are no longer managed by the entity manager, meaning any changes made will not be synchronized with the database.

CascadeType.ALL:  
This includes all the cascade operations: PERSIST, MERGE, REMOVE, REFRESH, and DETACH. It ensures that any action taken on the parent entity is automatically applied to its associated child entities.



8. **What is `fetch = FetchType.LAZY`, `fetch = FetchType.EAGER`?**  
   What is the difference? In which situations would you choose each one?

- fetch = FetchType.LAZY:  
This is lazy loading. It means that the related entities are not loaded from the database until they are explicitly accessed in the code.
In other words, the related data is fetched on-demand. The default fetch type for @OneToMany and @ManyToMany is lazy.

- fetch = FetchType.EAGER:
This is eager loading. It means that all related entities are fetched immediately when the parent entity is loaded, even if the related entities are not immediately needed.
The related data is fetched at the same time as the parent entity. The default fetch type for @ManyToOne and @OneToOne is eager.

- Use FetchType.LAZY (Lazy Loading):  
When the related data is not always needed and may not be accessed in every use case.
For performance optimization, especially if fetching related entities would result in a lot of data being loaded unnecessarily.
Suitable for collections like @OneToMany or @ManyToMany where the number of related entities might be large, and you don’t want to load them all unless needed.

- Use FetchType.EAGER (Eager Loading):  
When the related data is always required when the parent entity is fetched.
For small or critical relationships where the performance overhead is minimal, and you need the related data at the same time as the parent.
Suitable for relationships like @ManyToOne or @OneToOne where the related entity is usually small or always needed.

9. **What are the rules of JPA naming conventions?**  
   Should we implement the methods ourselves? Could you list some examples?

- Prefix: Start with `findBy`, `getBy`, `queryBy`, `countBy`, `deleteBy`, etc.
   - Example: `findByLastname(String lastname)`

- Property Name: Follow the prefix with the entity’s field name.
   - Example: `findByTitle(String title)`

- Combining Conditions: Use `And`, `Or` to combine conditions.
   - Example: `findByFirstnameAndLastname(String firstname, String lastname)`

- Comparison Operators: Include `GreaterThan`, `LessThan`, `Like`, `IsNull`, etc.
   - Example: `findByAgeGreaterThan(int age)`

- Ordering Results: Use `OrderBy` followed by field name and direction (`Asc`, `Desc`).
   - Example: `findByLastnameOrderByAgeAsc(String lastname)`

- Limiting Results: Use `First` or `Top` for limiting results.
   - Example: `findTop3ByOrderByAgeDesc()`

- Should We Implement Methods Ourselves?
    - No, JPA automatically generates methods based on naming conventions. Only implement methods manually if you need complex queries or optimizations.


10. **Try to use JPA advanced methods in your class project.**  
    In the repository layer, use the naming convention to implement the methods provided by JPA.
- The findByPostId(long postId)
    - findBy: indicates that the method is a query to find entities
    - PostId: correspond to a field in the Comment entity that should be mapped to the PostId
    - 
        ```
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;
        ```


11. *(Optional)* Check out a new branch from [branch 02_post_RUD](https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcTemplate), replace the DAO layer using JdbcTemplate.

12. **Type the code.**  
    Check out a new branch from branch 02_post_RUD, name the new branch:  
    [https://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL](#)

13. **What is JPQL?**
JPQL: is an object-oriented query language defined as part of the JPA. It is used to query entities stored in a relational database.

14. **What are @NamedQuery and @NamedQueries?**
**`@NamedQuery`** and **`@NamedQueries`** are JPA annotations used to define static, pre-defined JPQL queries. These queries are assigned names and can be reused across the application.

- **`@NamedQuery`**: Defines a single named JPQL query.
- **`@NamedQueries`**: Used to define multiple `@NamedQuery` annotations.

15. **What is @Query?**  
    In which Interface do we write the SQL or JPQL?

**`@Query`** is a JPA annotation used to define custom JPQL (Java Persistence Query Language) or native SQL queries directly in repository methods. It allows more flexibility compared to JPA’s naming convention for method queries, especially when you need complex or non-standard queries.

- **JPQL**: Queries are written in an object-oriented manner using entity classes and fields.
- **Native SQL**: You can also use native SQL queries by setting `nativeQuery = true` in the `@Query` annotation.

16. **What are HQL and Criteria Queries?**
- **HQL (Hibernate Query Language)**:
  - HQL is similar to JPQL but is specific to Hibernate. It is an object-oriented query language that uses the entity class names and fields rather than table names and columns to query the database.
  - HQL supports features like joins, aggregation, and sorting, much like SQL, but focuses on entities and their relationships.

- Criteria Queries:
  - Criteria API provides a programmatic way to build queries using Java objects. Instead of writing HQL/JPQL strings, you use method chaining and class types to construct queries dynamically.
  - This is useful for building complex queries with conditions that are determined at runtime.

17. **What is EntityManager?**
EntityManager is a JPA interface used to manage the lifecycle of entities, interact with the database, and execute JPQL or native queries.  
It is responsible for CRUD operations, transaction management, and maintaining a "persistence context" (a set of managed entity instances).  
You can use methods like persist(), merge(), remove(), and find() to interact with entities.

18. **What are SessionFactory and Session?**
In Hibernate, SessionFactory is a heavyweight object responsible for creating Session objects. It is designed to be instantiated once per application and serves as a factory for Session objects.  
It is thread-safe and holds the configuration details (like database connections, caching, etc.).

19. **What is a Transaction?**  
    How do you manage your transactions?

A **transaction** is a unit of work in which a series of database operations are executed as a single operation. A transaction ensures **ACID properties** (Atomicity, Consistency, Isolation, Durability), meaning all operations either succeed or fail together, leaving the database in a consistent state.

- How to manage transactions:
    - In JPA, transactions are typically managed by the **EntityManager** or with annotations like `@Transactional`.
    - You can manually begin, commit, or rollback transactions using `EntityManager` or `Session`.

20. **What is Hibernate Caching?**  
    Explain the Hibernate caching mechanism in detail.

Hibernate caching is a mechanism that stores data in memory to reduce the number of database hits, improving performance by preventing repeated queries for the same data.  

* First-level cache 
  * Hibernate first-level is associated with a Hibernate `session`, entity instances stored in a`session` can be considered as "cached" inside session object, it is enabled by default, there's no way to disable it. `Session`  provides methods through which we can persist, merge, remove, and find entity instances inside the "cache" (session). Objects cached in session will NOT be visible to other sessions, so Hibernate first-level cache only works within a session.  
* Second-level cache
  * Second-level cache is **NOT** enabled unless configured explicitly.
  * Second-level cache works across sessions, in `sessionFactory` level.
  * Hibernate itself does **NOT** implement second-level cache, instead, it relies on third party caching providers such as Redis, Ehcache, etc.


21. **What is the difference between first-level cache and second-level cache?**
First-level cache is session-specific and temporary, while second-level cache is application-wide and persists across sessions.

22. **How do you understand @Transactional?**  
    [https://github.com/TAIsRich/tutorial-transaction](https://github.com/TAIsRich/tutorial-transaction)


The **`@Transactional`** annotation in Spring is used to manage transactions declaratively. It marks methods or classes to indicate that the operations within should be executed within a transaction, ensuring **ACID** properties (Atomicity, Consistency, Isolation, Durability).