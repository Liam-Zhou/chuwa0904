# Java Date

## 1.List all of the annotations you learned from class and homework to annotaitons.md
## 2.Type out the code for the Comment feature of the class project.
## 3.In postman, call all of the APIs in PostController and CommentController.
![My Image](../img/4.jpg)


## 4. What is JPA? and what is Hibernate?
- **JPA**:
  - provides a way to map Java objects to database table using annotations
  - defines a set of rules and interfaces that other frameworks can implement
  - it standardizes ORM, making it easier to persist data in relational databases
- **Hibernate**:
  - a framework that helps you persist Java objects into relational databases by providing the functionality defined in JPA
  - Automatic generation of SQL for CRUD operations
  - It provides different caching mechanisms
  - Lazy and eager loading: deciding when to load related entities

## 5.What is Hiraki? what is the benefits of connection pool?
- **Hiraki**: 
  - a fast, lightweight, highly optimzied connection pool.
  - manages a pool of database connections, allowing applications to reuse connections rather than constantly opening and closing them
  - benefits:
    - Connection pooling avoids the overhead of creating and closing database connections for each request
    - Without a connection pool, each database interaction would create a new connection, leading to higher resource consumption
    - A connection pool keeps connections alive for reuse, reducing the time taken for creating and establishing new connections
    - set a maximum number of connections in the pool

## 6.What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
- @OneToMany
```
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

}

```
- @ManyToOne

```
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}

```
- @ManyToMany
```
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<>();

}

```

## 7.What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?
- cascade = CascadeType.ALL
  - This option means that any operation (like persist, remove, refresh, merge, detach) done on the parent entity will be cascaded to the related child entities
- orphanRemoval = true
  - it means that if a child entity is no longer associated with the parent, it will be automatically deleted from the database.

### Other Cascade Types
- **CascadeType.PERSIST**: The child entities are saved automatically when the parent is saved. This is useful when you want to save child entities without explicitly saving them.
- **CascadeType.REMOVE**: If the parent is removed, the related child entities are also removed. This is particularly useful for deleting child records when the parent is deleted
- **CascadeType.MERGE**: When a parent entity is merged, any changes to the child entities are automatically merged as well.
- **CascadeType.REFRESH**:  If the parent is refreshed from the database, the child entities are also refreshed.
- **CascadeType.DETACH**: When the parent is detached, the child entities are also detached.


## 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which situation you choose which one?
- **FetchType.LAZY**: With lazy fetching, the associated entity (child or related data) is not loaded immediately when the parent entity is loaded
- **FetchType.EAGER**: With eager fetching, the associated entity (child or related data) is loaded immediately along with the parent entity.

- Difference: 
  - LAZY Fetching: Data is loaded on-demand
  - EAGER Fetching: Data is loaded immediately

- When to Choose Each:
  - FetchType.LAZY:
    - When the related data is large
  - FetchType.EAGER:
    - When the related data is small


## 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
- **JPA Naming Convention Rules**: 
  - method name should start with a keyword such as find, read, get, or delete
  - rest of the method name should describe the fields or relationships in the entity
  - JPA will automatically generate the query based on the method name

- Example:
```
List<User> findByUsername(String username);
List<User> findByFirstNameAndLastName(String firstName, String lastName);
List<Comment> findByPostId(Long postId);
List<User> findByLastNameOrderByFirstNameAsc(String lastName);


@Query("SELECT u FROM User u WHERE u.username = ?1")
User findUserByUsername(String username);

```


## 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.



## 11.(Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcTemplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.

## 12. type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with https://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.



## 13.What is JPQL?
- **JPQL**: is an object-oriented query language defined as part of the JPA. It is used to query entities stored in a relational database.
```
SELECT p FROM Post p
```

## 14.What is @NamedQuery and @NamedQueries?
- **@NamedQuery**: can be used to execute JPQL queries without having to write the JPQL string repeatedly in different parts of your code.
- **@NamedQueries**: is a container annotation that groups multiple @NamedQuery annotations

## 15.What is @Query? In which Interface we write the sql or JPQL? (what does the question mean?)
- **@Query**: allows developers to write their own queries when the standard query generation from method names is insufficient or too complex.

## 16.What is HQL and Criteria Queries?
- **@HQL**: HQL is based on the entity model, not the database schema, allowing you to write queries in a way that focuses on entities and relationships.
- **Criteria Queries**: allows for type-safe queries and more dynamic query building

# 17. What is EnityManager?
- **EnityManager**: manages the lifecycle of entities and provides APIs to perform CRUD operations, query execution, transaction management, and more

## 18.What is SessionFactory and Session?
- **SessionFactory**: represents a factory for creating Session objects. holds the configuration information needed to create and manage sessions.
- **Session**: represents a single unit of work (or transaction) and provides methods to create, read, update, or delete objects in the database

## 19.What is Transaction? how to manage your transaction?
- **Transaction**: a sequence of operations performed as a single logical unit of work. ensures that a group of operations either fully succeed or fail as a unit.
- using @Transactional 
  - calling beginTransaction(), commit(), rollback()
  - READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE


## 20.What is hibernate Caching? Explain Hibernate caching mechanism in detail.
- **Caching**: Caching in Hibernate is a mechanism to improve the performance of applications by reducing the number of database interactions.
- Mechanism:
  - First-Level Cache: When a transaction starts and a Session is opened, Hibernate automatically caches all the entity objects loaded within that session
  - Second-Level Cache: The second-level cache stores entities, collections, and query results, allowing the application to avoid redundant database queries across different sessions


## 21.What is the difference between first-level cache and second-level cache?
- First-Level Cache: associated with the Session object
- Second-Level Cache: associated with the SessionFactory object and is optional


## 22.How do you understand @Transactional?
- **@Transactional**: defines the scope of a single database transaction and manages commits and rollbacks automatically.





