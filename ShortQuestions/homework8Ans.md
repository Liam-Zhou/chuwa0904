# Homework 8 Answers

### 4. JPA and Hibernate:
 - JPA is a specification for object-relational mapping (ORM) in Java. It defines a set of guidelines for managing relational data in applications using Java objects.
 - Hibernate is a framework that implements the JPA specification, providing tools for ORM. It helps map Java classes to database tables and offers powerful data query and transaction handling capabilities.

### 5. Hikari and Connection Pooling:
 - Hikari is a high-performance JDBC connection pool library. It offers efficient connection management to databases, ensuring that connections are reused instead of opening new ones.
 - The benefits of connection pooling include reduced resource usage and faster execution by reusing database connections.

### 6. @OneToMany, @ManyToOne, @ManyToMany:
 - @OneToMany: This annotation is used when one entity has a collection of another entity.
 - @ManyToOne: Used when many entities are associated with a single instance of another entity.
 - @ManyToMany: Used when entities can have relationships in both directions.
    Example: 
    ```java
    @Entity
    public class Post {
        @OneToMany(mappedBy = "post")
        private List<Comment> comments;
    }

    @Entity
    public class Comment {
        @ManyToOne
        private Post post;
    }
    ```

### 7. CascadeType and Orphan Removal:
 - cascade = CascadeType.ALL propagates all operations (persist, merge, remove, etc.) from a parent entity to its children.
 - orphanRemoval = true means that if a child entity is removed from the collection in the parent, it will also be deleted from the database.
 - Other CascadeTypes include:
  - PERSIST: Cascade only save operations.
  - MERGE: Cascade merge operations.
  - REMOVE: Cascade delete operations.
  - REFRESH: Cascade refresh operations.
  - DETACH: Cascade detach operations.

### 8. FetchType.LAZY vs. FetchType.EAGER:
 - LAZY: Data is only loaded when accessed, leading to better performance when entities are large or not always required.
 - EAGER: Data is loaded immediately, which can be simpler but more resource-intensive.
 - Usage: Choose LAZY for large data sets or when you want to avoid loading unnecessary data upfront. Use EAGER when you always need the data.

### 9. JPA Naming Convention: 
 - JPA method naming convention involves using keywords like 
    findBy, countBy, deleteBy

### 13. JPQL: 
 - JPQL (Java Persistence Query Language) is a query language used in JPA to interact with entities stored in relational databases, similar to SQL but operates on the entity model rather than directly on database tables.

### 14. @NamedQuery and @NamedQueries:
 - @NamedQuery is used to define a static query with a name that can be referenced later.
 - @NamedQueries allows defining multiple @NamedQuery annotations.

### 15. @Query:
 - Used to define a custom query in a repository interface, either with SQL or JPQL.

### 16. What is HQL and Criteria Queries?
 - HQL (Hibernate Query Language): Similar to JPQL, but specific to Hibernate. It is used for querying Hibernate entities and works on the object model.
 - Criteria Queries: These are object-oriented, type-safe queries that allow you to programmatically build queries using the Hibernate Criteria API.

### 17. What is EntityManager?
 - EntityManager is the primary JPA interface that manages the lifecycle of entities, handles queries, and interacts with the persistence context. It is responsible for operations such as persisting, finding, removing, and merging entities.

### 18. What is SessionFactory and Session?
 - SessionFactory: A Hibernate-specific interface that provides access to Hibernate sessions, typically used to interact with the database.
 - Session: A Hibernate session represents a connection to the database, where you can perform CRUD operations on your entities.

### 19. What is a Transaction, and how do you manage it?
 - A Transaction is a sequence of operations performed as a single logical unit of work. It ensures that either all operations succeed or none do (atomicity).
 - Transactions are managed using the @Transactional annotation in Spring, which automatically handles transaction boundaries and rollback/commit behavior.

### 20. What is Hibernate Caching?
 - Hibernate Caching: Hibernate uses caching mechanisms to improve performance by reducing the number of database hits. It can store entities, collections, and queries in memory.
 - Hibernate provides two levels of caching:
  1. First-level cache: Associated with the Hibernate Session, where entities are cached for the duration of the session.
  2. Second-level cache: Shared across sessions and persists even after a session ends, typically backed by a cache provider like EHCache.

### 21. What is the difference between first-level cache and second-level cache?
 - First-level cache is local to the session, and each session has its own first-level cache.
 - Second-level cache is shared among multiple sessions and can persist between sessions, useful for caching data across different parts of the application.

### 22. How do you understand @Transactional?
 - @Transactional: This annotation in Spring ensures that the methods or classes it annotates are executed within a transaction. If any method in the transaction fails, the entire transaction is rolled back. It handles transaction management declaratively without needing explicit code to begin and commit or rollback transactions.