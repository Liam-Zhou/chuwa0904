### 1. List all of the annotations you learned from class and homework to annotaitons.md

see `/annotations.md`

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
