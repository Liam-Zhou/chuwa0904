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
2. orphanRemoval = true: It means that when a child entity is removed from the parent’s collection (or its relationship to the parent is broken), the child entity will be automatically deleted from the database.
   * Use case: This is particularly useful for @OneToMany and @OneToOne relationships. If an entity becomes "orphaned" (i.e., it is no longer referenced by the parent entity), it will be removed from the database.
3. CascadeType.PERSIST: When the parent entity is persisted (inserted), the associated child entities are also persisted.
   * If you want child entities to be saved along with the parent when a new parent entity is created and persisted.
4. CascadeType.MERGE: When the parent entity is merged (updated), the associated child entities are also merged.
   * Use this when you want child entities to be updated when the parent is updated. It is useful when updating an existing parent entity with its associated children.
5. CascadeType.REMOVE: When the parent entity is removed, the associated child entities are also removed.
   Use Case: If you want to automatically delete all associated child entities when the parent entity is deleted.
6. CascadeType.REFRESH: When the parent entity is refreshed, the state of its associated child entities is also refreshed from the database.
   * Use Case: Use this if you want to reload the parent and its children from the database to reflect the most recent state in the database.
7. CascadeType.DETACH: When the parent entity is detached from the persistence context, the associated child entities are also detached.
   * Use Case: Use this if you want to detach the parent and its child entities from the persistence context, so they are no longer managed by the entity manager.


### Question 8
FetchType.LAZY: Lazy loading means that the related entities are not fetched immediately when the parent entity is loaded. Instead, they are fetched on-demand, meaning they are only loaded when you explicitly access the related entity. This can help improve performance.
   * Use when you don’t need the related entities immediately or in most use cases, lazy loading can reduce the initial load times.
FetchType.EAGER: Eager loading means that the related entities are fetched immediately when the parent entity is loaded. As soon as the parent entity is queried, all its associated entities are also fetched from the database, regardless of whether they are needed.
   * If related entities are always required in your use case, eager loading can simplify data access and reduce the number of queries.

### Question 9
#### Entity class name
* Class names should typically be singular to represent a single instance of an entity.(e.g. Customer, Employer)