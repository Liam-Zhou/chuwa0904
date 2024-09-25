1. List all of the annotations you learned from class and homework to annotations.md
   Refer to annotations.md

2. Type out the code for the Comment feature of the class project.

   1. Entity: Comment

   First, define a JPA entity for the comments. This entity will be linked to the `Post` entity, assuming each comment is associated with a specific post.

   ```java
   package com.chuwa.redbook.entity;
   
   import javax.persistence.*;
   import java.util.Date;
   
   @Entity
   @Table(name = "comments")
   public class Comment {
   
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
   
       @Column(nullable = false, length = 300)
       private String text;
   
       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "post_id", nullable = false)
       private Post post;
   
       @Column(nullable = false)
       private Date createdAt = new Date();
   
       // Getters and Setters
       public Long getId() {
           return id;
       }
   
       public void setId(Long id) {
           this.id = id;
       }
   
       public String getText() {
           return text;
       }
   
       public void setText(String text) {
           this.text = text;
       }
   
       public Post getPost() {
           return post;
       }
   
       public void setPost(Post post) {
           this.post = post;
       }
   
       public Date getCreatedAt() {
           return createdAt;
       }
   
       public void setCreatedAt(Date createdAt) {
           this.createdAt = createdAt;
       }
   }
   ```

   2. Repository: CommentRepository

   Create a repository interface for accessing comment data from the database.

   ```java
   package com.chuwa.redbook.dao;
   
   import com.chuwa.redbook.entity.Comment;
   import org.springframework.data.jpa.repository.JpaRepository;
   import org.springframework.stereotype.Repository;
   
   @Repository
   public interface CommentRepository extends JpaRepository<Comment, Long> {
   }
   ```

   3. Service: CommentService

   Define a service interface and its implementation to handle business logic.

   ```java
   package com.chuwa.redbook.service;
   
   import com.chuwa.redbook.entity.Comment;
   
   public interface CommentService {
       Comment saveComment(Long postId, Comment comment);
   }
   
   // Implementation
   package com.chuwa.redbook.service.impl;
   
   import com.chuwa.redbook.dao.CommentRepository;
   import com.chuwa.redbook.entity.Comment;
   import com.chuwa.redbook.entity.Post;
   import com.chuwa.redbook.exception.ResourceNotFoundException;
   import com.chuwa.redbook.service.CommentService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Service;
   
   @Service
   public class CommentServiceImpl implements CommentService {
   
       @Autowired
       private CommentRepository commentRepository;
   
       @Autowired
       private PostRepository postRepository;
   
       @Override
       public Comment saveComment(Long postId, Comment comment) {
           Post post = postRepository.findById(postId)
               .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
           comment.setPost(post);
           return commentRepository.save(comment);
       }
   }
   ```

   4. Controller: CommentController

   Create a controller to handle HTTP requests for managing comments.

   ```java
   package com.chuwa.redbook.controller;
   
   import com.chuwa.redbook.entity.Comment;
   import com.chuwa.redbook.service.CommentService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.http.ResponseEntity;
   import org.springframework.web.bind.annotation.*;
   
   @RestController
   @RequestMapping("/api/posts/{postId}/comments")
   public class CommentController {
   
       @Autowired
       private CommentService commentService;
   
       @PostMapping
       public ResponseEntity<Comment> addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment) {
           Comment savedComment = commentService.saveComment(postId, comment);
           return ResponseEntity.ok(savedComment);
       }
   }
   ```

   Explanation

   - **Entity**: The `Comment` entity represents the comment table in the database with basic attributes like `id`, `text`, `post`, and `createdAt`.
   - **Repository**: The `CommentRepository` provides CRUD operations for `Comment` objects via JPA.
   - **Service**: `CommentService` handles business logic, such as associating a comment with the correct post.
   - **Controller**: `CommentController` manages incoming HTTP requests to add comments to posts and delegates business operations to `CommentService`.

3. In postman, call all of the APIs in Post Controller and Comment Controller.
   ![image-20240923162757754](/Users/monicaq/Library/Application Support/typora-user-images/image-20240923162757754.png)
   ![image-20240923162913595](/Users/monicaq/Library/Application Support/typora-user-images/image-20240923162913595.png)

4. What is JPA? What is Hibernate?

   - **JPA (Java Persistence API)**: It is a specification that defines how Java objects should be mapped to relational database tables. JPA itself doesn't provide an implementation but sets the guidelines for ORM (Object-Relational Mapping) frameworks to follow.

   - **Hibernate**: It is an ORM framework that implements the JPA specification. Hibernate handles database interactions by mapping Java objects to database tables, enabling developers to work with databases using Java objects rather than SQL queries.

5. What is Hikari? What are the benefits of a connection pool?
   - **HikariCP**: It is a fast, lightweight JDBC connection pool used by default in Spring Boot applications for managing database connections efficiently.
   - **Benefits of Connection Pool**:
     - **Reduced Overhead**: Reusing database connections instead of opening a new one for every request reduces the overhead.
     - **Efficient Resource Management**: Limits the number of open connections, preventing resource exhaustion.
     - **Performance**: Faster response times due to the reuse of connections.

6. What is @OneToMany, @ManyToOne, @ManyToMany? Write some examples.

   **@OneToMany**: Defines a one-to-many relationship between two entities (e.g., a parent can have many children).

   Example:

   ```java
   @OneToMany(mappedBy = "parent")
   private List<Child> children;
   ```

   **@ManyToOne**: Defines a many-to-one relationship between two entities (e.g., many children belong to one parent).

   Example:

   ```java
   @ManyToOne
   @JoinColumn(name = "parent_id")
   private Parent parent;
   ```

   **@ManyToMany**: Defines a many-to-many relationship (e.g., a student can enroll in many courses, and a course can have many students).

   Example:

   ```java
   @ManyToMany
   @JoinTable(
     name = "student_course",
     joinColumns = @JoinColumn(name = "student_id"),
     inverseJoinColumns = @JoinColumn(name = "course_id")
   )
   private Set<Course> courses;
   ```

7. What is `cascade = CascadeType.ALL`, `orphanRemoval = true`? What are the other CascadeType and their features? In which situation do we choose which one?

   **CascadeType.ALL**: Applies all cascade operations (e.g., persist, merge, remove) to the related entity.

   **orphanRemoval = true**: Automatically deletes child entities when they are no longer referenced by the parent.

   Other Cascade Types:

   - **PERSIST**: Automatically saves the child entity when the parent is saved.
   - **REMOVE**: Deletes the child entity when the parent is deleted.
   - **MERGE**: Updates the child entity when the parent is updated.
   - **REFRESH**: Refreshes the child entity when the parent is refreshed.
   - **DETACH**: Detaches child entities when the parent is detached.

   Use `CascadeType.ALL` when you want all actions on the parent to affect the child entities. Use specific cascades (like `PERSIST` or `REMOVE`) based on your use case, like when you want more control over the behavior.

8. What is `fetch = FetchType.LAZY`, `fetch = FetchType.EAGER`? What is the difference? In which situation do you choose which one?**FetchType.LAZY**: The related entities are not loaded from the database until they are explicitly accessed (delayed loading).
   **FetchType.EAGER**: The related entities are loaded immediately when the parent entity is fetched (immediate loading).
   **Use Cases**:
   - **LAZY**: Choose this for performance optimization when the related entities are not always needed.
   - **EAGER**: Choose this when the related entities are always needed, and you want to avoid extra database queries.

9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?

   **JPA Naming Convention**: JPA allows methods in repositories to follow a specific naming convention, like `findBy`, `getBy`, `countBy`, etc., which will automatically generate queries.

   Example:

   ```java
   List<Post> findByTitle(String title);
   Long countByAuthor(String author);
   ```

10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.
    In your project, make sure to use JPA repository methods like `findBy`, `deleteBy`, `countBy`, etc., to make use of the built-in JPA query generation features.

13. What is JPQL?

- **JPQL (Java Persistence Query Language)**: It is a query language that operates on the entity object model rather than the database tables directly. JPQL is database-independent and works with entity objects and their properties.

14. What is @NamedQuery and @NamedQueries?

- **@NamedQuery**: Defines a static query with a name that can be reused across multiple methods.

- **@NamedQueries**: A container for multiple `@NamedQuery` annotations.

  Example:

  ```
  java
  
  
  Copy code
  @NamedQueries({
    @NamedQuery(name = "findAllPosts", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "findPostById", query = "SELECT p FROM Post p WHERE p.id = :id")
  })
  ```

15. What is @Query? In which interface do we write the SQL or JPQL?

- **@Query**: This annotation is used to define custom JPQL or native SQL queries in the repository interface.

  Example:

  ```
  java
  
  
  Copy code
  @Query("SELECT p FROM Post p WHERE p.title = ?1")
  List<Post> findPostsByTitle(String title);
  ```

You typically write `@Query` in Spring Data JPA repository interfaces.

16. What is HQL and Criteria Queries?

- **HQL (Hibernate Query Language)**: A query language similar to JPQL but specific to Hibernate. It is used to query entities based on their mapped object properties.
- **Criteria Queries**: A programmatic way to construct database queries using the Criteria API without writing the query string directly.

17. What is EntityManager?

- **EntityManager**: The interface in JPA that provides methods to manage entity lifecycle (e.g., persist, merge, remove) and to run queries. It interacts with the persistence context, which holds the entity instances.

18. What is SessionFactory and Session?

- **SessionFactory**: A Hibernate interface used to create `Session` objects that represent a single unit of work.
- **Session**: An interface representing a connection between the application and the database, used to perform CRUD operations and manage transactions.

19. What is a Transaction? How to manage your transaction?

- **Transaction**: A sequence of operations performed as a single logical unit of work. Either all operations succeed, or none do.
- In Spring, you can manage transactions using the `@Transactional` annotation, which ensures that the method is executed in a transactional context.

20. What is Hibernate Caching? Explain Hibernate caching mechanism in detail.

- Hibernate Caching

  : Hibernate provides two levels of caching to improve performance by reducing database access:

  - **First-Level Cache**: Session-level cache; entities are cached within the same session.
  - **Second-Level Cache**: SessionFactory-level cache; entities can be cached across sessions.

21. What is the difference between first-level cache and second-level cache?

- **First-Level Cache**: Exists for the duration of a session; each session has its own cache.
- **Second-Level Cache**: Shared across multiple sessions; can be configured to cache entities, collections, etc., across multiple transactions.

22. How do you understand @Transactional?

- **@Transactional**: This annotation ensures that the annotated method or class is executed within a transaction. If any exception occurs, the transaction is rolled back, and the data changes are not committed to the database.

### 