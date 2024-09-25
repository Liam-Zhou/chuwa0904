# HW8

## 1. List all the annotations learned

See Annotations.md

## 2. Type out the code for comment feature of class project

## 3. In postman, call all the APIs in PostController and CommentController

POST http://localhost:8080/api/v1/posts/ 

201 CREATED

GET http://localhost:8080/api/v1/posts/

200 OK

GET http://localhost:8080/api/v1/posts/1

200 OK

UPDATE http://localhost:8080/api/v1/posts/1

200 OK

DELETE http://localhost:8080/api/v1/posts/1

200 OK. Post entity deleted successfully.

POST http://localhost:8080/api/v1/posts/2/comments

201 CREATED

GET http://localhost:8080/api/v1/posts/2/comments

200 OK

GET http://localhost:8080/api/v1/posts/2/comments/1

200 OK

UPDATE http://localhost:8080/api/v1/posts/2/comments/1

200 OK

DELETE http://localhost:8080/api/v1/posts/2/comments/3

200 OK Comment deleted Successfully

## 4. What is JPA, and What is Hibernate

In short, JPA(Java Persistence API) defines the interfaces, Hibernate provides the implementation for the JPA.

### JPA

JPA provides a standard way to manage relational data in Java applications. It defines a set of interfaces and rules for working with relational database in an object-oriented manner.

- Specification: JPA is a **specification** defines how java applications can interact with databases. It did not provides implementation.
- Object-Relational Mapping (**ORM**): JPA facilitates ORM, allowing developers to map Java objects to database tables, making it easier to work with data as Java objects instead of RAW SQL queries.
- Entity Management: JPA provides an **EntityManager** interface that handles the lifecycle of entity instances, including creating, reading, updating, deleting records.
- Query Language: **JPA defines JPQL** (Java persistence query language), an object-oriented query language that allows to write queries against entity objects rather than database tables 
- **Annotations**: JPA uses annotations to define how Java classes are mapped to database tables and columns (e.g., `@Entity`, `@Table`, `@Column`).

### Hibernate

It is a open source implementation of JPA specification. It provides a framework for mapping object-oriented domain model to a relational database.

- **Implementation** of JPA, while using JPA features, developers can take advantage of additional functions Hibernate offers
- **Powerful ORM**: provides advanced ORM capabilities, including caching, lazy loading, and batch processing. Enhance performance, reduce boilerplate code
- **Configuration Options**: Hibernate allows for both XML and annotation-based configuration, giving developers flexibility in how they set up their persistence layer.
- **Support Native SQL**: While JPA focus on JPQL, Hibernate allows native SQL queries if necessary. More flexible
- **Automatic Schema Generation**: Auto generate create and update database schemas based on entity model

## 5. What is Hikari? Benefits of connection pool?

It is lightweight and high-performance JDBC connection pool. It is designed to be fast and efficient, providing a way to manage database connections efficiently. HikariCP is often used in conjunction with ORM frameworks like Hibernate and JPA.

Benefits of Connection Pool:

- **Reduce connection overhead**, re-using connections handles request faster, benefit high-traffic applications where the demand for database connections is high
- Limited concurrent connections prevent overload.
- Connection lifetime management manage the lifecycle of connections, including validation and timeout settings.

## 6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.

Used to define relationships between entities.

### @OnetoMany

~~~java
//e.g. One post has many comments. one-to-many between two entities. One instance of an entity can be associated with multiple instances of another entity (the "many" side)
@Entity
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    // Getter & Setter
}
~~~

### @ManyToOne

~~~java
// e.g. many comments to one post
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Getters and setters
}
~~~

### ManyToMany

~~~java
//e.g. many students has many courses to take
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "student_course",
        joinColumns = { @JoinColumn(name = "student_id") },
        inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Set<Course> courses = new HashSet<>();

    // Getters and setters
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Getters and setters
}
~~~



## 7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?

**OrphanRemoval** indicate child entity is removed from parent entity, it will be removed from the database. (it is useful when want to automatically manage the lifecycle of child entities based on their association with parents)

**Cascade types**: how operations are propagated from parent to child entities.

CascadeType.All: Include all the operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH)

**`CascadeType.PERSIST`**: When the parent entity is persisted, the child entities are also persisted. (when want to auto persist child  entities when parent is saved. For example, when creating a new post with comments, you might want to save comment alongside the post)

**`CascadeType.MERGE`**: When the parent entity is merged, the child entities are also merged. (When want to update existing child  entities when parent is updated)

**`CascadeType.REMOVE`**: When the parent entity is removed, the child entities are also removed. (When want to delete the child entities automatically when parent is  deleted. This make sure the child  data is not linger in the database when parent is deleted)

**`CascadeType.REFRESH`**: When the parent entity is refreshed, the child entities are also refreshed.

**`CascadeType.DETACH`**: When the parent entity is detached, the child entities are also detached.

**`CascadeType.ALL`**: This includes all the above operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH).

~~~
~~~



## 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which situation you choose which one

Fetch attribute in relationship annotations (@onetomany, @manytoone, etc) specifies how related entities are loaded from the database. Two primary are LAZY and EAGER.

**When to use**: 

**Lazy** when large collections or deep relationships are not always needed. When want to optimize performance, avoid loading unnecessary data and reduce the initial loading time of the entities.

**EAGER** when you know the related entities will always needed to be needed immediately after the parent entity is loaded

When the dataset is small and loading at once not impact performance significantly.

When want to simplify the code logic, knowing the related data is readily available.



**LAZY**: related entities are not loaded form the database until they are explicitly accessed. This means that when the parent entity is loaded, its relationship. 

- Advantage:
  - Performance, reduce the initial loading time and memory consumption, avoid large collections or complex relationships unnecessarily
  - Resource Management: it improves application performance, in cases where related data is not always needed
- Disadvantages:
  - Potential Lazy initialization exceptions: If you try to access lazy loaded collection outside of an active session (like after the entity manager is closed), may encounter LazyInitializationException.

**EAGER**: 

The related entities are loaded immediately when parent entity is retrieved. All the related data is fetched in a single query or additional queries is necessary

- Advantage:
  - Immediate Access: The related entities are readily available, no need for additional queries when accessing, simplify coding in some situation
- Disadvantage:
  - Performance overhead: Load all related entities immediately lead to performance issues, if the relationships are large and deep
  - N+1 query problem: If you have multiple parents entities, each with eager loaded relationships, this can lead to multiple queries being executed.



## 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you listsome examples?

1. Entity class naming: should be **singular** and typically follow the naming of corresponding database table. For example, if you have a table called `posts`, the entity class should be named `Post`

2. Table Naming:

   - **By default, JPA maps the entity name to a table name** in the database using the same name. If want a different name, you can use the @Table annotation

   - ~~~java
     @Entity
     @Table(name = "posts")
     public class Post { ... }
     ~~~

3. Field naming

   - Field names in entity class should match the column names in the database table. If a column has a name that doesn't conform to a Java naming conventions (like underscores), you can use @Column annotation to specify to specify a column name.

   - ~~~java
     @Column(name = "post_title")
     private String title;
     ~~~

4, Relationship naming (废话):

- When defining relationships (@onetomany, @manytoone), it's common to name the collection fields in a way that reflects their relationship. For example, a Post entity might have a collection of `Comment` entities.

- ~~~java
  @OneToMany(mappedBy = "post")
  private List<Comment> comments;
  ~~~

5. Method naming:
   - method names should clearly indicate the action being performed, such as `findBy`, `save`, or `deleteBy`.

## 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.

- The `findByPostId(long postId)`

  - `findBy`: indicates that the method is a query to find entities

  - `PostId`: correspond to a field in the `Comment` entity that should be mapped to the `PostId`

  - ~~~java
    // In comment entity, a section defines the post, allows findByPostId in CommentRepository work automatically
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    ~~~

## 11. Replace the DAO with JDBCTemplate



## 12. Type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with h
ttps://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL

## 13. What is JPQL

**Java Persistence Query Language**, is a query language defined by (**JPA**)Java Persistence API. It allows to write queries against entity model rather than directly to database tables. Similar to SQL but designed to work with JPA entities (OOP concept).

## 14. What is @NamedQuery and @NamedQueries?

Predefined query that can be referenced by its name.

In @NamedQueries, you can define multiple @NameQuery

~~~java
@Entity
@NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    // Other fields, getters, and setters
}
~~~

## 15. What is @Query? In which Interface we write the sql or JPQL?

Is used to define custom SQL or JPQL queries directly in repository interfaces. Allow write complex queries.

~~~java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// bind method parameters to the query using named parameters (e.g., :parameterName) or positional parameters (e.g., ?1, ?2).
public interface PostRepository extends JpaRepository<Post, Long> {    
    @Query("SELECT p FROM Post p WHERE p.title = :title")
    List<Post> findByTitle(@Param("title") String title);
}
public interface PostRepository extends JpaRepository<Post, Long> {    
    @Query(value = "SELECT * FROM posts WHERE title = ?1", nativeQuery = true)
    List<Post> findByTitleNative(String title);
}
~~~



## 16. What is HQL and Criteria Queries?

Hibernate Query Language and Criteria Queries. Both are methods of querying data in Hibernate, which is a popular ORM (Object-Relationship Mapping) framework for Java. Here's a breakdown of each. 

### HQL (Hibernate Query Language)

1. Definition: HQL is an object-oriented query language similar to SQL. But it operates on Hibernate entity objects rather than directly on database tables.

2. JPQL compatable

3. Syntax more natural:

   - ~~~JAVA
     String hql = "FROM Post p WHERE p.title = :title";
     Query query = session.createQuery(hql);
     query.setParameter("title", "My First Post");
     List<Post> posts = query.list();
     ~~~

4. Advantage: Readability, Object-Oriented

### Criteria Queries

- Definition: Provide programmatic way to construct queries.

- Type Safe, reduce runtime type error

- ~~~java
  CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
  CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
  Root<Post> root = criteriaQuery.from(Post.class);
  criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("title"), "My First Post"));
  
  List<Post> posts = session.createQuery(criteriaQuery).getResultList();
  ~~~

## 17. What is EnityManager?

Central interface in JPA. Manage the lifecycle of entity instances and provides the necessary operations for interacting with the database. It acts as a bridge between your Java application and the underlying database, allow for CRUD

- Entity Lifecycle Management: 

  - transition entities between different states: transient(not persisted), managed(persisted in the current context), detached(no longer managed), and removed(marked for deletion)

- Persistence Context:

  - maintains a persistence context, which a set of entity instances that are managed by it. This context help in caching and tracking changes to entities. 

- CRUD: `persist` `remove`  `merge` `find(entityClass, primaryKey)`

- Transition Management: JTA(Java Transaction API) environment, the EntityManager can participate in transactions, allowing you to commit or roll back changes.

- ~~~java
  import javax.persistence.EntityManager;
  import javax.persistence.EntityManagerFactory;
  import javax.persistence.Persistence;
  
  public class Main {
      public static void main(String[] args) {
          // Create EntityManagerFactory
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
          EntityManager em = emf.createEntityManager();
  
          // Start a transaction
          em.getTransaction().begin();
  
          // Create a new entity
          Post post = new Post();
          post.setTitle("My First Post");
          post.setContent("This is the content of my first post.");
  
          // Persist the entity
          em.persist(post);
  
          // Commit the transaction
          em.getTransaction().commit();
  
          // Find an entity by its ID
          Post foundPost = em.find(Post.class, post.getId());
          System.out.println("Found post: " + foundPost.getTitle());
  
          // Close the EntityManager
          em.close();
          emf.close();
      }
  }
  ~~~

  

## 18. What is SessionFactory and Session?

Key concept in Hibernate. Which is an implementation of JPA. Manage the interaction and lifecycle of entities.

### Sesscion Factory

- Thread-safe factory for creating session instances. Heavyweight object that is expensive to create once per application or per data source.

- Configuration: Database connection details and Hibernate properties. (hibernate.cfg.xml) or through programmatic configuration

- Usually like a pool, created at the application startup and reused through the application

- ~~~java
  import org.hibernate.SessionFactory;
  import org.hibernate.cfg.Configuration;
  
  public class HibernateUtil {
      private static final SessionFactory sessionFactory = buildSessionFactory();
  
      private static SessionFactory buildSessionFactory() {
          try {
              return new Configuration().configure().buildSessionFactory();
          } catch (Throwable ex) {
              throw new ExceptionInInitializerError(ex);
          }
      }
  
      public static SessionFactory getSessionFactory() {
          return sessionFactory;
      }
  }
  ~~~



### Session

-  lightweight object, single units of work with the database. Used to do CRUD.

- Not thread-safe and should be used by a single thread at a time. It is typically opened for the duration of a single transaction or single business operation.

- `Session` manages the state of entity instances. It tracks changes to entities and synchronizes them with database based on transaction commit

- Query Execution: Use session to create and execute HQL or Criteria queries.

- ~~~java
  import org.hibernate.Session;
  import org.hibernate.Transaction;
  
  public class PostService {
      public void savePost(Post post) {
          Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction transaction = null;
  
          try {
              transaction = session.beginTransaction();
              session.save(post); // Persist the Post entity
              transaction.commit();
          } catch (Exception e) {
              if (transaction != null) transaction.rollback();
              e.printStackTrace();
          } finally {
              session.close(); // Close the session
          }
      }
  }
  ~~~



## 19. What is Transaction? how to manage your transaction?

A unit of database operation. Ensure data integrity and consistence by allowing a set of operations to be treated as single logical operation. If any operation within a transaction fails, entire transaction can be rolled back. 

**Management** Either by EntityManager or Session. If want to use cache in Hibernate, choose session.

- Programmatic transaction management with EntityManager:

- ~~~java
  import javax.persistence.EntityManager;
  import javax.persistence.EntityTransaction;
  
  public class PostService {
      private EntityManager entityManager;
  
      public PostService(EntityManager entityManager) {
          this.entityManager = entityManager;
      }
  
      public void savePost(Post post) {
          EntityTransaction transaction = entityManager.getTransaction();
          
          try {
              transaction.begin(); // Start transaction
              entityManager.persist(post); // Perform operation
              transaction.commit(); // Commit transaction
          } catch (RuntimeException e) {
              if (transaction.isActive()) {
                  transaction.rollback(); // Rollback if an error occurs
              }
              throw e; // Re-throw the exception
          }
      }
  }
  ~~~

## 20. What is hibernate Caching? Explain Hibernate caching mechanism in detail

Improve performance by storing frequently accessed data in memory, reducing the number of database query. It provides **first-level cache (session cache)** and **second-level cache**.

### First-level Cache

- Definition: associated with the session object in Hibernate. A session-specific cache  that stores entities and their state for duration of a session. 

- Behavior: 

  - Every time an entity is loaded or saved within a session, it is stored in this cache. 
  - If same entity is requested again within the same session, hibernate retrieves it from first-level cache instead of querying from database
  - The cache is not shared across sessions

- ~~~java
  Session session = sessionFactory.openSession();
  Post post1 = session.get(Post.class, 1); // Loads from the database and caches the entity
  Post post2 = session.get(Post.class, 1); // Loads from the first-level cache
  ~~~

### Second-level cache

- Definition: session-independent cache, stores data across multiple sessions. Cache can be shared among multiple sessions and is used to cache entities, collections and query result. 
- Behavior:
  - Need to configure to use it. (through configuration file or annotation)
  - When a entity is requested and not found in the first-level cache, Hibernate checks the second-level cache before querying the database.
  - When found in second level cache, return. Otherwise, Fetch from database and may cache it.
  - Can specify caching strategy (read-only, read-write, non-strict read-write).

### Cache Strategies

- Read-only: Once loaded, cached and never update
- Read-Write: Hibernate manages concurrency control for these entities, ensuring cached values remain consistent.
- Non-Strict Read-Write: Similar to read-write but with relaxed consistency requirements, which can improve performance.
- Transactional: Ensure consistency with database

## 21. What is the difference between first-level cache and second-level cache?

Refers to above answer 20.

**First-Level Cache**: Session-specific, automatically managed, and cleared when the session is closed.

**Second-Level Cache**: Shared across sessions, configurable, and can use various cache providers.

**Cache Strategies**: Allow customization based on the nature of the data and its usage patterns.

## 22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)

 Using annotations simplify the transaction lifecycle of transaction begin, commit, rollback.

- Declarative transaction management
- Isolation level: you can define the isolation level, which controls how transaction interact with other concurrent transactions (e.g. `READ_COMMITTED` `SERIALIZABLE`)
- Rollback behavior: by default spring roll back transactions for unchecked exceptions (subclassed of `RuntimeException`) but not for checked exceptions. You can customize this behavior using the `rollbackFor` and `noRollbackfor` attribute

**Example**

Class-Level Annotation

~~~java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {
    public void createPost(Post post) {
        // Transaction starts when this method is called
        postRepository.save(post);
        // Other operations can be added here
    }
}
~~~

 Method-level Annotation

~~~java
import org.springframework.transaction.annotation.Transactional;

public class CommentService {

    @Transactional
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
~~~

