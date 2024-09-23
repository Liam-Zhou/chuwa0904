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