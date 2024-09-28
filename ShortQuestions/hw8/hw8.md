### 1. List all of the annotations you learned from class and homework to annotaitons.md

see `ShortQuestions/annotations.md`

### 2. Type out the code for the Comment feature of the class project.

1. Comment Entity

```
// src/main/java/com/example/project/entity/Comment.java

package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Many comments can belong to one post
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Post post;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
```

2. Comment Repository

```
// src/main/java/com/example/project/repository/CommentRepository.java

package com.example.project.repository;

import com.example.project.entity.Comment;
import com.example.project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Find all comments associated with a specific post
    List<Comment> findByPost(Post post);
}
```

3. Comment Service

- 3.1 CommentService Interface

```
// src/main/java/com/example/project/service/CommentService.java

package com.example.project.service;

import com.example.project.entity.Comment;
import com.example.project.entity.Post;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(Long postId, Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    Optional<Comment> getCommentById(Long commentId);
    Comment updateComment(Long commentId, Comment commentDetails);
    void deleteComment(Long commentId);
}
```

- 3.2 CommentServiceImpl Implementation

```
// src/main/java/com/example/project/service/impl/CommentServiceImpl.java

package com.example.project.service.impl;

import com.example.project.entity.Comment;
import com.example.project.entity.Post;
import com.example.project.repository.CommentRepository;
import com.example.project.repository.PostRepository;
import com.example.project.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + postId));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + postId));
        return commentRepository.findByPost(post);
    }

    @Override
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public Comment updateComment(Long commentId, Comment commentDetails) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + commentId));
        comment.setContent(commentDetails.getContent());
        comment.setAuthor(commentDetails.getAuthor());
        // You can update other fields as necessary
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + commentId));
        commentRepository.delete(comment);
    }
}
```

4. Comment Controller

```
// src/main/java/com/example/project/controller/CommentController.java

package com.example.project.controller;

import com.example.project.entity.Comment;
import com.example.project.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Create a new comment for a specific post
     */
    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable Long postId,
                                                 @Valid @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(postId, comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    /**
     * Get all comments for a specific post
     */
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Get a specific comment by ID
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long postId,
                                                 @PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    /**
     * Update a specific comment by ID
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long postId,
                                                 @PathVariable Long commentId,
                                                 @Valid @RequestBody Comment commentDetails) {
        Comment updatedComment = commentService.updateComment(commentId, commentDetails);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    /**
     * Delete a specific comment by ID
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId,
                                              @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
```

### 4. What is JPA? and what is Hibernate?

**JPA** stands for **Java Persistence API**. It is a specification provided by Java for managing relational data in Java applications. JPA defines a set of concepts and interfaces that enable developers to interact with databases in an object-oriented manner.

**Hibernate** is an **Object-Relational Mapping (ORM)** framework for Java. It is one of the most popular and widely used JPA implementations. While JPA provides the specification, Hibernate offers a concrete implementation of that specification, along with additional features.

### 5. What is Hiraki? what is the benefits of connection pool?

**HikariCP** is a high-performance, lightweight **JDBC (Java Database Connectivity)** connection pool library for Java applications. It is designed to manage and optimize the database connections required by your application, ensuring efficient and reliable communication with the database.

**Benefits of Using Connection Pools:**

- Improved Performance
- Enhanced Resource Management
- Scalability
- Reliability and Stability
- Simplified Configuration and Management
- Consistency Across Environments

### 6. What is the `@OneToMany, @ManyToOne, @ManyToMany` ? write some examples.

- `@OneToMany`  
  Indicates a one-to-many relationship between two entities. One instance of an entity is related to multiple instances of another entity.

  ```
  // src/main/java/com/example/project/entity/Post.java

    package com.example.project.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.List;

    @Entity
    @Table(name = "posts")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 200)
        private String title;

        @Column(nullable = false, length = 5000)
        private String content;

        // One Post has many Comments
        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        private List<Comment> comments;
    }
  ```

- `@ManyToOne`
  Indicates a many-to-one relationship between two entities. Many instances of an entity are related to one instance of another entity.

  ```
  // src/main/java/com/example/project/entity/Comment.java

  package com.example.project.entity;

  import jakarta.persistence.*;
  import lombok.*;

  import java.time.LocalDateTime;

  @Entity
  @Table(name = "comments")
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public class Comment {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(nullable = false, length = 1000)
      private String content;

      @Column(nullable = false)
      private String author;

      @Column(nullable = false)
      private LocalDateTime createdAt;

      // Many Comments belong to one Post
      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "post_id", nullable = false)
      @ToString.Exclude
      @EqualsAndHashCode.Exclude
      private Post post;

      @PrePersist
      public void prePersist() {
          this.createdAt = LocalDateTime.now();
      }
  }
  ```

- `@ManyToMany`
  Indicates a many-to-many relationship between two entities. Multiple instances of one entity are related to multiple instances of another entity.

  ```
  // src/main/java/com/example/project/entity/Student.java

  package com.example.project.entity;

  import jakarta.persistence.*;
  import lombok.*;

  import java.util.Set;

  @Entity
  @Table(name = "students")
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public class Student {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(nullable = false, length = 100)
      private String name;

      // Many Students can enroll in Many Courses
      @ManyToMany
      @JoinTable(
          name = "student_courses",
          joinColumns = @JoinColumn(name = "student_id"),
          inverseJoinColumns = @JoinColumn(name = "course_id")
      )
      @ToString.Exclude
      @EqualsAndHashCode.Exclude
      private Set<Course> courses;
  }
  ```

### 7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?

`cascade = CascadeType.ALL` means that all operations performed on the parent entity (such as `persist`, `merge`, `remove`, etc.) will be cascaded to the associated child entities. Essentially, any operation you perform on the parent entity will be automatically applied to its related entities.

`orphanRemoval = true` ensures that if a child entity is removed from its parent (e.g., removed from a collection in a one-to-many relationship), it will also be deleted from the database. This helps ensure that there are no "orphaned" records (child records without a parent) in the database.

- Cascade Types in JPA

1. `CascadeType.PERSIST`: Use when you want child entities to be automatically saved when the parent entity is saved.
2. `CascadeType.MERGE`: Use when you want child entities to be automatically updated when the parent entity is updated.
3. `CascadeType.REMOVE`: Use when you want child entities to be automatically deleted when the parent entity is deleted.
4. `CascadeType.REFRESH`: Use when you want the parent and child entities to be synchronized with the database state.
5. `CascadeType.DETACH`: Use when you want to detach both the parent and child entities from the persistence context.
6. `CascadeType.ALL`: Use when you want all operations performed on the parent to be cascaded to the child entities.

### 8. What is the `fetch = FetchType.LAZY, fetch = FetchType.EAGER` ? what is the difference? In which situation you choose which one?

- With `fetch = FetchType.LAZY`, the associated entities are not loaded immediately when the parent entity is retrieved. Instead, the related entities are loaded on-demand when they are accessed for the first time in the code. With `fetch = FetchType.EAGER`, the associated entities are loaded immediately along with the parent entity, regardless of whether or not they are accessed later in the code.

| Aspect                          | **`FetchType.LAZY`**                                         | **`FetchType.EAGER`**                                  |
| ------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------ |
| **Loading Strategy**            | Associated entities are loaded **on-demand**                 | Associated entities are loaded **immediately**         |
| **Default for Collections**     | Default for `@OneToMany` and `@ManyToMany` relationships     | Not default for collections                            |
| **Default for Single Entities** | Not default for `@OneToOne` or `@ManyToOne`                  | Default for `@OneToOne` and `@ManyToOne`               |
| **Performance**                 | Better performance since data is fetched only when needed    | May lead to over-fetching and performance degradation  |
| **Use Case**                    | Use when the associated entities are **not always required** | Use when the associated entities are **always needed** |

- When to Use `LAZY`: Use `LAZY` when you are working with large datasets, collections, or related entities that are not always needed. It improves performance by avoiding unnecessary database queries.
- When to Use `EAGER`: Use `EAGER` when the related entities are always needed and the dataset is relatively small or single-valued. Be cautious of over-fetching or the N+1 problem when using `EAGER` with collections.

### 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?

- **Entity and Table Naming Conventions**
  1. Entity to Table Mapping:
     - By default, the class name is mapped to the table name. For example, a `User` class is mapped to a `user` table.
     - This default mapping can be overridden using the `@Table(name = "custom_table_name")` annotation.
  2. Field to Column Mapping:
     - By default, the field names are mapped to column names in lowercase, possibly converted using snake_case if necessary.
     - This can be overridden using the `@Column(name = "custom_column_name")` annotation.
- **JPA Repository Naming Convention**

  1. The method name must start with a prefix such as `findBy`, `readBy`, `getBy`, `queryBy`, or `countBy`.
  2. The method name must then specify the entity field name on which the query is based.
  3. You can combine multiple fields using keywords like `And`, `Or`, etc.

- With Spring Data JPA, you do not need to implement methods yourself for simple queries. The framework will automatically generate the appropriate SQL based on the method names following the naming conventions described above.

- Examples:
  - Single Field Queries
    ```
    List<User> findByFirstName(String firstName);
    // SQL: SELECT * FROM users WHERE first_name = ?
    ```
  - Multiple Field Queries
    ```
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    // SQL: SELECT * FROM users WHERE first_name = ? AND last_name = ?
    ```
  - Queries with Comparisons
    ```
    List<User> findByAgeGreaterThan(int age);
    // SQL: SELECT * FROM users WHERE age > ?
    ```
  - Queries with Like/Containing
    ```
    List<User> findByEmailContaining(String email);
    // SQL: SELECT * FROM users WHERE email LIKE '%email%'
    ```

### 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.

- UserRepository

```
public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by last name
    List<User> findByLastName(String lastName);

    // Find users by first name and last name
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    // Find users whose email contains a specific string
    List<User> findByEmailContaining(String keyword);

    // Find users older than a certain age
    List<User> findByAgeGreaterThan(int age);

    // Find users by age between a range
    List<User> findByAgeBetween(int startAge, int endAge);

    // Find the top 5 oldest users
    List<User> findTop5ByOrderByAgeDesc();
}
```

- PostRepository

```
public interface PostRepository extends JpaRepository<Post, Long> {

    // Find posts by title containing a keyword
    List<Post> findByTitleContaining(String keyword);

    // Find posts by user
    List<Post> findByUser(User user);

    // Find posts created after a specific date
    List<Post> findByCreatedAtAfter(LocalDateTime date);

    // Find posts created between two dates
    List<Post> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Count posts by a specific user
    int countByUser(User user);
}
```

- CommentRepository

```
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find comments by post
    List<Comment> findByPost(Post post);

    // Find comments by user
    List<Comment> findByUser(User user);

    // Find comments created after a specific date
    List<Comment> findByCreatedAtAfter(LocalDateTime date);

    // Count comments by post
    int countByPost(Post post);

    // Find top 3 latest comments on a specific post
    List<Comment> findTop3ByPostOrderByCreatedAtDesc(Post post);
}
```

### 13. What is JPQL?

**JPQL (Java Persistence Query Language)** is a powerful, object-oriented query language designed to work with JPA entities. It abstracts the underlying SQL, making it easier to write database-agnostic queries. JPQL allows you to retrieve data using JPA entities and supports standard querying features like `SELECT`, `WHERE`, `JOIN`, `ORDER BY`, and more, but operates in a more object-oriented and entity-focused manner compared to SQL.

### 14. What is @NamedQuery and @NamedQueries?

- `@NamedQuery`: Defines a single pre-defined JPQL query associated with an entity class, allowing you to execute that query by referring to its name.

- `@NamedQueries`: Allows you to define multiple named queries for an entity by grouping several `@NamedQuery` annotations together.

### 15. What is @Query? In which Interface we write the sql or JPQL?

- The `@Query` annotation in JPA (Java Persistence API) is used to define custom **JPQL (Java Persistence Query Language)** or native SQL queries directly in repository methods. This allows you to write complex queries that go beyond what can be generated by Spring Data JPA's method naming conventions.

- The `@Query` annotation is used in repository interfaces in Spring Data JPA, specifically in the interfaces that extend `JpaRepository` or `CrudRepository`. You define the query at the method level of the repository interface.

### 16. What is HQL and Criteria Queries?

- **HQL (Hibernate Query Language)** is an object-oriented query language provided by Hibernate that is very similar to **JPQL (Java Persistence Query Language)**. HQL is used to perform operations on Hibernate entities and their relationships rather than directly on database tables.
- **Criteria Queries** are another way of querying data in Hibernate. They provide a more **programmatic and type-safe** approach to building dynamic queries. Unlike HQL, which is written as a string, criteria queries are constructed using Java objects and methods.

### 17. What is EnityManager?

The `EntityManager` is a core interface in the **Java Persistence API (JPA)** that provides an API to interact with the persistence context. It is responsible for managing the lifecycle of entities and facilitating the interaction between Java objects (entities) and the underlying database. Essentially, the `EntityManager` provides methods to **persist**, **remove**, **find**, and **query** entities.

### 18. What is SessionFactory and Session?

- `SessionFactory` is a heavyweight, thread-safe object in Hibernate that is responsible for creating and managing multiple `Session` instances. It acts as a **factory for** `Session` objects, which are used to interact with the database. Typically, an application creates a single `SessionFactory` for the entire lifecycle of the application.

### 19. What is Transaction? how to manage your transaction?

- A **transaction** is a sequence of one or more database operations (like insert, update, delete, or select) executed as a single unit of work. A transaction ensures that these operations are executed **atomically** and **consistently**: either all operations succeed and are committed to the database, or none of them are, ensuring the database remains in a consistent state.

- How to Manage Transactions
  - In **JPA**, transactions are managed using the **EntityManager** interface. You can explicitly begin, commit, and roll back transactions using methods like `beginTransaction()`, `commit()`, and rollback().
  - In **Hibernate**, you manage transactions using the **Session** and **Transaction** objects. Like JPA, you explicitly begin, commit, and roll back transactions.
  - In **Spring**, transactions are typically managed declaratively using annotations or programmatically through the **TransactionManager**.

### 20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.

1. **First-Level Cache (Session Cache)**

   1. **Per Session Cache**: The first-level cache is session-specific, meaning it is tied to the Hibernate `Session`. Each `Session` has its own first-level cache.

   2. **Automatic and Default**: The first-level cache is always enabled in Hibernate. You don't need to configure it, and you cannot disable it.

   3. **Scope**: Objects are stored in the first-level cache during the lifetime of a session. Once the session is closed, the cache is invalidated, and cached entities are lost.

   4. **Entity Identity**: If an entity is loaded multiple times within the same session (by primary key), Hibernate will retrieve it from the first-level cache rather than querying the database again.

2. **Second-Level Cache (SessionFactory Cache)**
   1. **Shared Across Sessions**: Unlike the first-level cache, which is session-specific, the second-level cache is **shared across multiple sessions**.
   2. **Configurable**: The second-level cache must be explicitly enabled and configured. You can selectively enable or disable caching for specific entities, collections, or queries.
   3. **Persistence Across Sessions**: Entities stored in the second-level cache persist across multiple sessions, meaning that if an entity is loaded in one session, it can be reused in another session without querying the database.
   4. **Cache Providers**: Hibernate does not implement the second-level cache by itself but uses third-party cache providers like:
      - Ehcache
      - Infinispan
      - Hazelcast
      - OSCache
   5. **Data Synchronization**: The second-level cache must be synchronized with the database. If the database is updated outside of Hibernate, the cache might hold stale data unless properly configured to handle invalidation.

### 21. What is the difference between first-level cache and second-level cache?

| **Cache Level**         | **First-Level Cache**                                        | **Second-Level Cache**                                                  |
| ----------------------- | ------------------------------------------------------------ | ----------------------------------------------------------------------- |
| **Scope**               | Per session                                                  | Shared across sessions (`SessionFactory`)                               |
| **Default Status**      | Enabled by default, always active                            | Disabled by default, must be explicitly configured                      |
| **Purpose**             | Caches entities within a session                             | Caches entities across sessions                                         |
| **Concurrency Control** | No special concurrency control                               | Supports concurrency strategies like `READ_WRITE`                       |
| **Lifespan**            | Lasts for the duration of a session                          | Lasts for the duration of the `SessionFactory` lifecycle                |
| **Typical Use**         | Short-term caching, repeated data access in the same session | Long-term caching for frequently accessed data across multiple sessions |

### 22. How do you understand @Transactional? (<https://github.com/TAIsRich/tutorial-transaction>)

The `@Transactional` annotation is a cornerstone of transaction management in the **Spring Framework**. It provides a declarative approach to handling transactions, allowing developers to define transactional boundaries directly in their code without the need for explicit transaction management. This enhances code readability, maintainability, and reduces boilerplate code associated with transaction handling.