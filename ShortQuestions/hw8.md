
### Question 3
Still have problem of setting up local MySql server
### Question 4
#### JPA
JPA (Java Persistence API) provides a way to map Java objects to relational database tables using object-relational mapping (ORM).
* JPA uses annotations to define how Java classes are mapped to database tables. 
* JPA provides the EntityManager API to interact with the database. The EntityManager is responsible for persisting entities, querying them, and managing transactions.
* JPA supports declarative transaction management using the @Transactional annotation, allowing you to handle database operations within transactions.
#### Hibernate
* Hibernate is an Object-Relational Mapping (ORM) framework in Java that provides an implementation of the JPA specification.
* Hibernate automates the process of mapping Java objects (entities) to database tables and vice versa
* Hibernate provides a JPA-compliant implementation, so you can use it as the persistence provider in JPA-based applications. When you use JPA with Hibernate, you can work with JPA annotations and APIs, but Hibernate will handle the underlying data access logic.
* Instead of using the JPA EntityManager, Hibernate uses its own API with Session and SessionFactory for interacting with the database.
* Hibernate can automatically generate the database schema based on your entity mappings and can update the schema when the model changes.
* Hibernate provides support for both l1 (session-level) and l2 (application-level) caching when configured explicitly. improving performance by reducing unnecessary database queries.

### Question 5
HikariCP is a fast JDBC connection pool library for Java. It is designed to be a high-performance solution for managing database connections in Java applications.
Benefits:
* High Performance: HikariCP is known for its lightning-fast performance, optimized to minimize connection overhead and maximize throughput.
* Low Latency: HikariCP provides low-latency access to database connections by keeping a pool of pre-established connections ready for reuse.
* Minimum Configuration: It offers sensible defaults, which means you can use it effectively with very little configuration.
* Lightweight: HikariCP has a small footprint and introduces minimal overhead, making it suitable for high-performance environments.
* Reliability: It is stable, well-maintained, and extensively tested in high-throughput production environments.

### Qustion 6
1. @OneToMany: A One-to-Many relationship means that one entity is related to many entities. 
2. @ManyToOne: It means that many entities are related to one entity.
3. @ManyToMany A Many-to-Many relationship means that many entities are related to many entities. 
Todo

#### Qustion 7
These are attributes of the relationship annotations in JPA.
1. cascade = CascadeType.ALL: CascadeType.ALL applying all available cascading operations (e.g. persist, remove, update) to the associated entity. (will be propagated to the associated child entities as well.)
   *
2. **orphanRemoval = true**: It means that when a child entity is removed from the parent’s collection (or its relationship to the parent is broken), the child entity will be automatically deleted from the database.
   * Use case: This is particularly useful for @OneToMany and @OneToOne relationships. If an entity becomes "orphaned" (i.e., it is no longer referenced by the parent entity), it will be removed from the database.
3. **CascadeType.PERSIST**: When the parent entity is persisted (inserted), the associated child entities are also persisted.
   * If you want child entities to be saved along with the parent when a new parent entity is created and persisted.
4. **CascadeType.MERGE**: When the parent entity is merged (updated), the associated child entities are also merged.
   * Use this when you want child entities to be updated when the parent is updated. It is useful when updating an existing parent entity with its associated children.
5.**CascadeType.REMOVE**: When the parent entity is removed, the associated child entities are also removed.
   Use Case: If you want to automatically delete all associated child entities when the parent entity is deleted.
6. **CascadeType.REFRESH**: When the parent entity is refreshed, the state of its associated child entities is also refreshed from the database.
   * Use Case: Use this if you want to reload the parent and its children from the database to reflect the most recent state in the database.
7. **CascadeType.DETACH**: When the parent entity is detached from the persistence context, the associated child entities are also detached.
   * Use Case: Use this if you want to detach the parent and its child entities from the persistence context, so they are no longer managed by the entity manager.


### Question 8
**FetchType.LAZY**: Lazy loading means that the related entities are not fetched immediately when the parent entity is loaded. Instead, they are fetched on-demand, meaning they are only loaded when you explicitly access the related entity. This can help improve performance.
   * Use when you don’t need the related entities immediately or in most use cases, lazy loading can reduce the initial load times.
**FetchType.EAGER**: Eager loading means that the related entities are fetched immediately when the parent entity is loaded. As soon as the parent entity is queried, all its associated entities are also fetched from the database, regardless of whether they are needed.
   * If related entities are always required in your use case, eager loading can simplify data access and reduce the number of queries.

### Question 9
Spring Data JPA provides a built-in repository pattern that automatically implements these methods. But, by extending JpaRepository or CrudRepository, we gain access to standard CRUD operations and can define custom queries based on method names without writing actual SQL or JPQL queries.
* Find by field: Method names should start with findBy, followed by the field name. The field name in the method name should match the entity field.
* Find by multiple fields: Chain fields with And or Or.
* Use operators: You can add keywords like GreaterThan, LessThan, Between, Like, Not, etc.
For example:
```
List<Customer> findByFirstName(String firstName);
List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
List<Customer> findByAgeGreaterThan(int age);
```

### Question 10
We can change this part of code in class object using JPA advanced methods **findByPostId**:
```
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
```

### Question 11 & 12
Skiped

### Question 13
JPQL (Java Persistence Query Language) is an object-oriented query language that is used in JPA (Java Persistence API) to retrieve and manipulate data stored in relational databases. JPQL is similar to SQL, but it works with JPA entities instead of database tables.

### Question 14
**@NamedQuery** and **@NamedQueries** are annotations used to define static, reusable queries that can be referenced throughout your application. These queries are defined at the entity level and are typically written using JPQL or native SQL.
The **@NamedQuery** annotation is used to define a single, static query. The query is associated with an entity and can be executed by referencing its name.
The **@NamedQueries** annotation is a container for defining multiple named queries for the same entity.

### Question 15
**@Query** is typically written in a Spring Data JPA repository interface, such as an interface that extends JpaRepository, CrudRepository, or PagingAndSortingRepository. These interfaces allow you to define and manage database operations on entities.

### Question 16
**HQL** is a query language used in Hibernate, similar to SQL, but it operates on Hibernate entities rather than directly on database tables. HQL is an object-oriented query language that allows you to query against the object model (entities and their attributes) instead of the relational model (tables and columns).
**Criteria Queries** in Hibernate (and JPA) provide a programmatic way to build dynamic queries using a type-safe and object-oriented approach.

### Question 17
EntityManager is the central interface in JPA that manages the lifecycle of entities and performs CRUD operation on these entities. 
1. Entity Persistence: Persisting and removing entities in the database.
2. Entity Retrieval: Querying for entities using JPQL or native SQL.
3. Transaction Management: Managing transactions.
4. Entity Lifecycle Management: Managing entities’ states (transient, managed, detached, or removed).

### Question 18
1. SessionFactory
   * **SessionFactory** is an interface in Hibernate that is responsible for creating Session objects.
   * A SessionFactory is typically a singleton, meaning that there is usually one instance per application, and it is designed as thread-safe.
   * It manages the connections to the database, including connection pooling and caching.
   * Once created, a SessionFactory is immutable and does not change during the application's lifecycle.
2. Session
   * Session is an interface in Hibernate that represents a single unit of work with the database. It is the core interface used for interacting with the database through Hibernate.
   * Session perform create, read, update, and delete operations on entities.
   * Manages transactions (begin, commit, rollback).
   * It holds the first-level cache, which is specific to a session and holds entities retrieved during that session. It ensures that entities are only retrieved once from the database per session.
  
### Question 19
A transaction in the context of databases and persistence layers like JPA or Hibernate is a sequence of operations (like read, write, update, or delete) performed as a single unit of work. A transaction ensures data integrity and consistency by adhering to the ACID properties:

* Atomicity: All operations within a transaction either complete successfully as a whole or none of them are applied. This means that if one part of the transaction fails, the entire transaction fails and all operations are rolled back.
* Consistency: A transaction takes the database from one consistent state to another, ensuring that all rules (like foreign key constraints, triggers, etc.) are respected.
* Isolation: Transactions operate independently of each other, preventing data inconsistencies when multiple transactions run concurrently.
* Durability: Once a transaction is committed, its changes are permanent and survive system crashes.

### Question 20
Hibernate Caching mechanism provided by the Hibernate ORM. It stores frequently accessed data in memory, so repeated access to the same data can be retrieved faster, without hitting the database every time. Hibernate provides a multi-level caching mechanism, consisting of the first-level cache, and a optional second-level cache.
First-Level Cache (Session Cache):
* Scope: It is associated with the Hibernate Session and is enabled by default.
* Characteristics: The first-level cache is maintained per session and stores entities that are fetched within the same session. When an entity is requested within a session for the first time, it is retrieved from the database, and any subsequent requests for that entity within the same session will fetch it from the cache.
* Cache Invalidation: The first-level cache is cleared when the session is closed, so data stored here is not shared across sessions.
* Usage: Automatic and does not require any configuration. It's internal to Hibernate

Second-Level Cache:
* Configuration: Hibernate does not provide a second-level cache implementation by default. Instead, it integrates with third-party caching providers such as EhCache, Infinispan, Hazelcast, and OSCache.
* Usage: The second-level cache is optional and must be explicitly enabled and configured.
* Scope: It is associated with the SessionFactory and shared across all sessions created by the SessionFactory.
* Characteristics: The second-level cache is used to store entities or collections across multiple sessions. It stores data beyond the scope of the session, so once cached, the same data can be accessed by different sessions.
* Cache Invalidation: Cached data can be invalidated based on configurations like time-to-live (TTL), expiration, or changes made to the entity.

### Question 21
The l1 cache of Hibernate is tied to the Session object. It is enabled by default and cannot be disabled. Every entity that is loaded within a session is cached in the first-level cache. Hibernate uses the first-level cache to avoid repetitive database queries for the same entity in the same session.
The l2 cache of Hibernate is shared across multiple sessions and allows cached entities to be used across different transactions and sessions. Unlike the first-level cache, which is local to the session, the second-level cache is global to the SessionFactory.

### Question 22
@Transactional is a key annotation in Spring Framework used to manage transactions declaratively. 
Features: 
  1. Declarative Transaction Management: Instead of managing transactions manually (starting, committing, and rolling back transactions), Spring allows you to use @Transactional to declaratively control transactions.
  2. Automatic Rollback: Transactions are automatically rolled back in case of an unhandled exception. By default, it rolls back for unchecked exceptions (subclasses of RuntimeException).
  3. Timeout: Transactions can be configured to automatically roll back after a specified timeout period.
