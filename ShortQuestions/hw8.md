## 1. List all of the annotations you learned from class and homework to annotaitons.md
@RestController\
@ResponseBody\
@RequestMapping\
@PostMapping\
@RequestBody\
@PathVariable\
@ResponseStatus\
## 2. Type out the code for the Comment feature of the class project.
```
@RequestController
@RequestMapping("/api/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long id, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId) {
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") Long postId, @PathVariable(value="id") Long commentId, @RequestBody CommentDto commentDto) {
        CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}") 
    public ResponseEntity<String> deleteComment(@PathVariable(value="postId") Long postId, @PathVariable(value="id") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted Successfully, HttpStatus.OK);
    }
}
```
## 3. In postman, call all of the APIs in PostController and CommentController.
## 4. What is JPA? and what is Hibernate?
JPA stands for Java Persistence API. It is a specification in Java that provides a standard way to manage relational data in Java applications. JPA defines a set of concepts and interfaces for object-relational mapping (ORM) that enables developers to:\
Map Java Objects to Database Tables: JPA allows developers to define how Java classes correspond to database tables, and how their properties map to table columns.\
Persist Data: JPA provides a way to perform CRUD (Create, Read, Update, Delete) operations on persistent data using standard Java objects.\
Manage Relationships: JPA supports mapping relationships between entities, such as one-to-one, one-to-many, many-to-one, and many-to-many associations.\
Querying: JPA provides a query language called JPQL (Java Persistence Query Language), which allows for querying entities in a database in a more object-oriented way compared to SQL.\
Transaction Management: JPA integrates with Java transaction management APIs to manage transactions efficiently.\
\
Hibernate is an ORM (Object-Relational Mapping) framework that provides an implementation of the JPA specification. It is one of the most popular Java frameworks for data persistence and provides the following features:\
ORM Framework: Hibernate simplifies the interaction between Java objects and the database by automating the mapping of Java classes to database tables and vice versa.\
Session Management: Hibernate manages sessions and provides a powerful session API to interact with the database.\
Caching: Hibernate includes built-in caching mechanisms (first-level and second-level caching) to optimize database access and improve performance.\
Query Options: Hibernate supports multiple query options, including HQL (Hibernate Query Language), Criteria API, and native SQL queries, providing flexibility in how queries are constructed.\
Automatic Schema Generation: Hibernate can automatically generate database schemas from Java entity classes, making it easier to set up and manage the database.\
Integration with JPA: Hibernate fully supports the JPA specification, meaning it can be used as a JPA provider. This allows developers to use JPA's features while benefiting from Hibernate's advanced capabilities.\
## 5. What is Hiraki? what is the benefits of connection pool?
HikariCP is a lightweight and high-performance JDBC connection pool for Java applications. It is designed to provide a fast and efficient way to manage database connections, making it easier for applications to interact with relational databases. HikariCP is often favored for its performance and simplicity compared to other connection pool libraries.\
Connection pooling offers several advantages:\
Performance Improvement: Creating and closing database connections can be time-consuming and resource-intensive. Connection pools allow applications to reuse existing connections, reducing the overhead associated with establishing new connections.\
Resource Management: Connection pooling helps manage database connections efficiently. It limits the number of concurrent connections to a database, preventing resource exhaustion and maintaining optimal performance.\
Reduced Latency: With a pool of pre-established connections, applications can quickly obtain a connection from the pool, leading to reduced response times for database operations.\
Scalability: Connection pools can be configured to handle varying loads, allowing applications to scale more effectively as demand increases.\
Connection Validation: Connection pools can automatically validate connections before handing them out to ensure they are still active, which helps avoid issues with stale or invalid connections.\
Transaction Management: Connection pools simplify transaction management by maintaining the state of connections across multiple requests and transactions.\

## 6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
They are annotations used to define relationships between entity classes.\
### @OneToMany
The @OneToMany annotation is used to define a one-to-many relationship between two entities. In this relationship, one entity (the parent) can be associated with multiple instances of another entity (the child).\
### @ManyToOne
The @ManyToOne annotation is used to define the many-to-one side of a relationship. In this case, multiple instances of the child entity can be associated with a single instance of the parent entity.\

```
import javax.persistence.*;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;

    // Getters and setters
}

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    // Getters and setters
}
```
### @ManyToMany
The @ManyToMany annotation is used to define a many-to-many relationship between two entities. In this relationship, multiple instances of one entity can be associated with multiple instances of another entity.\
```
import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    // Getters and setters
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    // Getters and setters
}
```
## 7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?
CascadeType.ALL propagates all operations (persist, merge, remove, refresh, detach) from the parent to the child entities. This means that if you save, update, or delete the parent entity, the same operation will be applied to all its child entities.\
CascadeType.PERSIST: When the parent entity is persisted, any child entities that are associated with it will also be persisted.\

CascadeType.MERGE: When the parent entity is merged (updated), the same operation will be applied to its child entities.\

CascadeType.REMOVE: When the parent entity is removed (deleted), its child entities will also be removed.\

CascadeType.REFRESH: When the parent entity is refreshed, its child entities will also be refreshed.\

CascadeType.DETACH: When the parent entity is detached from the persistence context, the same will apply to its child entities.\

Use CascadeType.ALL when you want complete synchronization between the parent and child entities. This is common in tightly coupled relationships.\

Use CascadeType.PERSIST if you want to automatically save child entities when the parent is saved but do not want to remove them when the parent is deleted.\

Use CascadeType.REMOVE when you want to automatically delete child entities when the parent is deleted, but do not need to cascade the persist or merge operations.\

Use CascadeType.MERGE when you want to update child entities automatically when the parent entity is updated but do not need to persist or remove them.\

Use CascadeType.REFRESH and CascadeType.DETACH in scenarios where you want to synchronize the state of the child entities with the persistence context without affecting their lifecycle.\

## 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which situation you choose which one?
### fetch = FetchType.LAZY
If we don't need data from comment, JPA doesn't fetch it\
Onyly fetch data from post\
when we need data from comment, then JPA fecth comment data\
### fetch = FetchType.EAGER
when we fetch post, JPA also fetch comment at the same time.\
### Use FetchType.LAZY:

When you want to optimize performance and only load related entities when needed.\
For collections or large associations, where loading all related entities immediately could lead to performance degradation or unnecessary data retrieval.\
In scenarios where the parent entity is often accessed without the need for its related entities.\
### Use FetchType.EAGER:

When you always need the related entities along with the parent entity and want to avoid additional queries.\
For small, frequently accessed associations where the overhead of additional queries is minimal.\
In use cases where the application logic requires that the related entities are always available immediately after the parent entity is loaded.\

### Difference
| Feature              | FetchType.LAZY                          | FetchType.EAGER                          |
|----------------------|-----------------------------------------|------------------------------------------|
| Loading Behavior      | Loads related entities on demand        | Loads related entities immediately       |
| Performance          | Better performance in many cases due to reduced data retrieval | Can lead to performance issues due to fetching unnecessary data |
| Memory Consumption   | Uses less memory initially, as only the parent entity is loaded | Can consume more memory as all related entities are loaded |
| Database Queries     | May result in additional queries when accessing related entities | May result in a larger single query to fetch all data at once |

## 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
## 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.
## 13. What is JPQL?
JPQL (Java Persistence Query Language) is a query language used in the Java Persistence API (JPA) to perform database operations on entities. It is similar to SQL (Structured Query Language), but instead of working directly with database tables and columns, JPQL operates on the entity objects and their relationships defined in the JPA model.
## 14. What is @NamedQuery and @NamedQueries?
@NamedQuery and @NamedQueries are annotations used to define static, pre-defined queries that can be reused throughout the application. These named queries are typically declared at the entity level and can be called by name, making them useful for standard queries that you may want to execute multiple times.\
### @NamedQuery
The @NamedQuery annotation is used to define a single named query. It allows you to specify a JPQL (Java Persistence Query Language) query and associate it with a specific entity. This query can then be referenced and executed using its name.
```
@Entity
@NamedQuery(
    name = "Author.findByName",
    query = "SELECT a FROM Author a WHERE a.name = :name"
)
public class Author {
    @Id
    private Long id;

    private String name;
    // other fields, getters, and setters
}

```
### @NamedQueries
The @NamedQueries annotation is used to define multiple named queries at once. It is a container annotation that holds one or more @NamedQuery annotations.
```
@Entity
@NamedQueries({
    @NamedQuery(
        name = "Author.findAll",
        query = "SELECT a FROM Author a"
    ),
    @NamedQuery(
        name = "Author.findByName",
        query = "SELECT a FROM Author a WHERE a.name = :name"
    )
})
public class Author {
    @Id
    private Long id;

    private String name;
    // other fields, getters, and setters
}

```
```
EntityManager em = entityManagerFactory.createEntityManager();
em.getTransaction().begin();

// Execute the named query to find all authors
List<Author> authors = em.createNamedQuery("Author.findAll", Author.class).getResultList();

// Execute the named query to find an author by name
TypedQuery<Author> query = em.createNamedQuery("Author.findByName", Author.class);
query.setParameter("name", "John Doe");
Author author = query.getSingleResult();

em.getTransaction().commit();
em.close();

```
## 15. What is @Query? In which Interface we write the sql or JPQL?
The @Query annotation in Spring Data JPA is used to define custom queries directly on repository methods. It allows you to specify a JPQL (Java Persistence Query Language) or SQL query directly, overriding the default behavior of Spring Data JPA that usually relies on method naming conventions to generate queries.\
```
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // JPQL query
    @Query("SELECT a FROM Author a WHERE a.name = :name")
    Author findByName(@Param("name") String name);

    // Native SQL query
    @Query(value = "SELECT * FROM authors WHERE age > ?1", nativeQuery = true)
    List<Author> findAuthorsOlderThan(int age);
}
```
## 16. What is HQL and Criteria Queries?
### HQL (Hibernate Query Language)
Definition:\
HQL is an object-oriented query language similar to SQL but operates on persistent objects instead of database tables.\
It allows developers to execute queries against the Java objects (entities) mapped to the database.\
```
String hql = "FROM Author a WHERE a.name = :name";
Query query = session.createQuery(hql);
query.setParameter("name", "John Doe");
List<Author> authors = query.list();
```
### Criteria Queries
Definition:\
Criteria Queries provide a programmatic way to create queries using a fluent API.\
They allow you to build queries dynamically, making them suitable for scenarios where query parameters are not known at compile time.\
```
CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
Root<Author> root = criteriaQuery.from(Author.class);
criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), "John Doe"));
List<Author> authors = session.createQuery(criteriaQuery).getResultList();
```
## 17. What is EnityManager?
A JPA EntityManager manages connection to a database as well as to database operations. EntityManager is associated with a PersistenceContext. All operations that are performed in a specific session are stored inside the PersistenceContext. EntityManager is the interface to the Persistence Context. All operations on the entity go through the EntityManager.

## 18. What is SessionFactory and Session?
The session object provides an interface between the application and data stored in the database.\
Configuration => 制造⼿册\
sessionFactory => 拿到了制造⼿册，⼜准备了⽣产的设备的⼀个⼯⼚。\
Session => laptop\
## 19. What is Transaction? how to manage your transaction?
A transaction simply represents a unit of work.\
In such case, if one step fails, the whole transaction fails (which is termed as atomicity). A transaction can be
described by ACID properties.\
## 20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
Hibernate provides two levels of caching:\
### First-level cache
Hibernate first-level is associated with a Hibernate session, entity instances stored in a session can be considered as "cached" inside session object, it is enabled by default, there's no way to disable it.\
Session provides methods through which we can persist, merge, remove, and find entity instances inside the "cache" (session). Objects cached in session will NOT be visible to other sessions, so Hibernate first-level cache only works within a session.\
### Second-level cache
Second-level cache is NOT enabled unless configured explicitly.\
Second-level cache works across sessions, in sessionFactory level.\
Hibernate itself does NOT implement second-level cache, instead, it relies on third party caching providers such as Redis, Ehcache, etc.\
\
All in all, priority: first-level cache > second-level cache > database
## 21. What is the difference between first-level cache and second-level cache?
| First Level Cache | Second Level Cache |
|-------------------|--------------------|
| This is local to the Session object and cannot be shared between multiple sessions.| This cache is maintained at the SessionFactory level and shared among all sessions in Hibernate.|
| This cache is enabled by default and there is no way to disable it.| This is disabled by default, but we can enable it through configuration. |
|The first level cache is available only until the session is open, once the session is closed, the first level cache is destroyed. |The second-level cache is available through the application’s life cycle, it is only destroyed and recreated when an application is restarted.|
## 22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)
@EnableTransactionManagement in application\
@Transactional in method.\