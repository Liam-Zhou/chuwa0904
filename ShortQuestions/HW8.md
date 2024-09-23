# HW8 Spring-Data

## 4. What is JPA? and what is Hibernate?

JPA: JPA (Java Persistence API) is a specification for object-relational mapping (ORM) in Java. It provides a standard way to manage relational data in Java applications by mapping Java objects (entities) to database tables.  
Hibernate: Hibernate is an open-source ORM (Object-Relational Mapping) framework that implements the JPA specification. It simplifies database interactions by automatically mapping Java objects to database tables, allowing developers to focus on the domain model instead of SQL queries and database management.

## 5. What is Hiraki? what is the benefits of connection pool?

Hiraki is a high-performance JDBC (Java Database Connectivity) connection pool library. It is known for being lightweight, fast, and efficient, making it a popular choice for managing database connections in Java applications.  
The benefits of connection pool include Improved Performance, increase scalability, reduce latency, better resource management.

## 6.@OneToMany, @ManyToOne, @ManyToMany:

`@OneToMany`: means that one entity (A) can be related to multiple instances of another entity (B), but each instance of B is related to only one instance of A.  
e.g. An entity Department can have many Employees, but each Employee belongs to only one Department.

```
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    // getters and setters
}

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // getters and setters
}
```

`@ManyToOne`: means that many instances of one entity (B) can be associated with one instance of another entity (A). This is often the inverse of a `@OneToMany` relationship  
e.g. Continue with Employee and Department example, each employee belongs to one department, and this relationship is declared on the employee side as `@ManyToOne`.

```
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // getters and setters
}
```

`@ManyToMany`: means that multiple instances of entity A can relate to multiple instances of entity B and vice versa. This requires a join table to hold the foreign key references from both entities.  
e.g. A Student can enroll in many Courses, and each Course can have many Students. This is a many-to-many relationship.

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
    private List<Course> courses = new ArrayList<>();

    // getters and setters
}

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    // getters and setters
}
```

## 7. About cascade and orphan

`cascade = CascadeType.ALL`: This setting applies cascading operations from the parent entity to its related child entities. Essentially, it ensures that any changes made to the parent entity (such as persist, update, delete) are automatically propagated to the related child entities.  
`orphanRemoval = true`: This option ensures that when a child entity is removed from the relationship (e.g., from a collection), it is automatically deleted from the database. If you remove an element from a collection, and orphanRemoval is set to true, that child entity is considered an orphan and will be removed from the database.

| **CascadeType**        | **Description**                                                                                         | **Use Case**                                                                                          |
| ---------------------- | ------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `CascadeType.PERSIST`  | Child entities are automatically persisted when the parent is persisted.                                | Use when saving the parent should automatically save its children.                                    |
| `CascadeType.MERGE`    | Child entities are automatically merged (updated) when the parent is merged.                            | Use when updating the parent should automatically update its children.                                |
| `CascadeType.REMOVE`   | Child entities are automatically removed when the parent is removed.                                    | Use when deleting the parent should also delete its children.                                         |
| `CascadeType.REFRESH`  | Child entities are refreshed (reloaded) from the database when the parent is refreshed.                 | Use when refreshing the parent should also refresh its children to reflect the latest database state. |
| `CascadeType.DETACH`   | Child entities are detached when the parent is detached from the persistence context.                   | Use when detaching the parent should also detach its children from the persistence context.           |
| `CascadeType.ALL`      | Applies all of the above cascades: `PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, `DETACH`.                   | Use when you want all entity lifecycle operations to be cascaded to the child entities.               |
| `orphanRemoval = true` | Orphaned child entities are removed from the database when they are no longer referenced by the parent. | Use when removing a child from a parent's collection should delete the child entity.                  |

## 8. As to fetch

In JPA, fetch = FetchType.LAZY and fetch = FetchType.EAGER are used to control how related entities are loaded from the database. These options determine whether the associated entities are fetched immediately or on demand when the parent entity is retrieved.  
`FetchType.LAZY`: Lazy loading means the associated entities are not loaded from the database until they are explicitly accessed. The associated entities are loaded on demand when their getter method is invoked.  
`FetchType.EAGER`: Eager loading means the associated entities are loaded immediately when the parent entity is fetched. All associated entities are retrieved in the same query as the parent entity, and there is no need for additional queries to load them.

| **FetchType.LAZY**                                                         | **FetchType.EAGER**                                                                             |
| -------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| Associated entities are **loaded on demand**.                              | Associated entities are **loaded immediately** when the parent is fetched.                      |
| Reduces initial performance impact and memory usage.                       | Increases initial load and memory usage, as everything is fetched upfront.                      |
| May result in additional database queries when accessing related entities. | No additional queries required; everything is loaded in one query.                              |
| **Good for large collections** or rarely accessed relationships.           | **Good for small, frequently accessed** relationships or when you always need the related data. |

Choosing Between LAZY and EAGER:  
Use FetchType.LAZY when:

- You don’t always need the related entities.
- The relationship contains a large number of records.
- You want to optimize performance by loading only the necessary data.
- You expect to access the related data only within the same persistence context (to avoid LazyInitializationException).

Use FetchType.EAGER when:

- You always need the related data along with the parent entity.
- The associated data is lightweight and doesn't impact performance or memory usage significantly.
- You want to avoid potential LazyInitializationException issues, especially in web applications.

## 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?

JPA follows specific naming conventions when mapping Java entities to database tables and fields. These conventions help avoid ambiguity and improve code consistency. By default, JPA uses certain rules to map entities and their fields to corresponding database tables and columns, but these mappings can be customized using annotations like `@Table` and `@Column`.

## 13. What is JPQL

`JPQL (Java Persistence Query Language` is the query language used in JPA (Java Persistence API) to interact with entities stored in a relational database. It is an object-oriented query language that operates on entity objects, rather than directly on database tables, unlike SQL which works with tables and columns.

## 14. What is @NamedQuery and @NamedQueries

The `@NamedQuery` annotation is used to define a single static query that can be invoked by name.  
The `@NamedQueries` annotation is a container annotation used to define multiple `@NamedQuery` annotations for a single entity.

## 15. What is @Query? In which Interface we write the sql or JPQL?

The `@Query` annotation in JPA is used to define custom JPQL (Java Persistence Query Language) or native SQL queries directly within repository methods. It allows you to write more complex or specialized queries that aren't covered by JPA's default methods like findById() or save().  
We typically define these queries in repository interfaces that extend `JpaRepository` or `CrudRepository`. These interfaces are part of the `Spring Data JPA module`, which simplifies data access by providing common repository functionality and reducing boilerplate code.

## 16. What is HQL and Criteria Queries?

HQL: HQL is an object-oriented query language similar to JPQL (Java Persistence Query Language), but it is specific to Hibernate. HQL works with Hibernate entities rather than directly interacting with database tables. It is designed to be database-independent, meaning it abstracts away the details of SQL and operates on entity objects.  
Criteria Queries: Criteria Queries provide a programmatic, object-oriented way to construct queries. Instead of writing the query as a string (like HQL), you use method calls to build queries dynamically. This is particularly useful when the query is complex or needs to be built based on user input or dynamic conditions.

| Feature             | **HQL (Hibernate Query Language)**                 | **Criteria Queries**                                     |
| ------------------- | -------------------------------------------------- | -------------------------------------------------------- |
| **Query Type**      | String-based, similar to SQL syntax                | Programmatic, using Java objects and methods             |
| **Flexibility**     | Static queries, less suited for dynamic conditions | Highly dynamic, ideal for runtime conditions             |
| **Type-Safety**     | Not type-safe; potential runtime errors            | Type-safe; reduces risk of runtime errors                |
| **Readability**     | Easier for those familiar with SQL                 | Can become verbose and harder to read for simple queries |
| **Use Case**        | Good for standard, pre-defined queries             | Best for dynamic, runtime-generated queries              |
| **Complex Queries** | Supports complex joins and subqueries              | Supports dynamic conditions and complex filters          |

## 17. What is EnityManager?

`EntityManager` is the primary interface used to interact with the persistence context and manage the lifecycle of entities. It serves as the core API for working with JPA, responsible for performing operations like querying, persisting, merging, and removing entities in a relational database.

## 18. What is SessionFactory and Session?

In Hibernate, SessionFactory and Session are core interfaces responsible for managing the interaction between the application and the database. They are used to handle database connections, manage the lifecycle of entities, and perform various database operations such as saving, updating, and querying data.

## 19. What is Transaction? how to manage your transaction?

A transaction in the context of a database is a sequence of operations performed as a `single logical unit of work`. These operations are executed with certain guarantees to ensure consistency and correctness of the data. A transaction must adhere to the ACID properties.

Transactions can be managed in different ways depending on the framework or API you are using. In Java, transactions can be managed manually using APIs like JPA (Java Persistence API) or Hibernate, or automatically using Spring Framework.

## 20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.

Hibernate caching is a mechanism that helps optimize the performance of data retrieval and storage by reducing the number of database queries executed. By caching frequently accessed data in memory, Hibernate reduces the overhead of accessing the database repeatedly for the same data, which can be time-consuming and resource-intensive.

Hibernate caching works at two levels:

- First-level cache (Session-level cache)
- Second-level cache (SessionFactory-level cache)

Both levels of caching aim to enhance performance by minimizing database access.

## 21. What is the difference between first-level cache and second-level cache?

| **Aspect**              | **First-Level Cache**                                                                               | **Second-Level Cache**                                                                                               |
| ----------------------- | --------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| **Scope**               | Session-level: Each Hibernate `Session` has its own first-level cache.                              | SessionFactory-level: Shared across multiple `Session` instances created by the same `SessionFactory`.               |
| **Enabled by Default**  | Yes, always enabled.                                                                                | No, must be explicitly configured and enabled.                                                                       |
| **Lifespan**            | Exists for the duration of a `Session`.                                                             | Persists beyond a single `Session`, across multiple sessions.                                                        |
| **Configuration**       | No configuration required, cannot be disabled.                                                      | Requires explicit configuration in Hibernate settings and entity annotations.                                        |
| **Caching Scope**       | Caches entities loaded or persisted within the current session.                                     | Caches entities, collections, and sometimes query results across multiple sessions.                                  |
| **Data Retention**      | Cache is cleared when the session is closed.                                                        | Data remains in the cache as long as the `SessionFactory` is active, even after sessions close.                      |
| **Use Case**            | Reduces redundant database queries within the same session.                                         | Reduces database access for frequently accessed data across multiple sessions.                                       |
| **Granularity**         | Works only for entities loaded in the same session.                                                 | Can cache entities globally for all sessions associated with a `SessionFactory`.                                     |
| **Cache Provider**      | No external cache provider needed; built-in to Hibernate.                                           | Requires external cache providers like EHCache, Infinispan, or Hazelcast.                                            |
| **Cache Invalidations** | Managed by the session itself; cache is cleared after the session ends.                             | Entities can be invalidated automatically based on cache concurrency strategies like `READ_ONLY`, `READ_WRITE`, etc. |
| **Performance Impact**  | Reduces database access within the same session, offering a small but consistent performance boost. | Can significantly reduce database load, especially for static or frequently used data across sessions.               |
| **Transaction Scope**   | Transactions within the session benefit from the cache.                                             | Works across multiple transactions and sessions.                                                                     |

## 22. How do you understand @Transactional?

@Transactional is a powerful and commonly used annotation in Spring Framework that helps manage transaction boundaries declaratively. It defines the scope of a single database transaction and provides a consistent way to manage the transactional behavior of methods and classes in a Spring application.
