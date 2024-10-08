package com.chuwa.redbook.service.impl;
import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentServiceImpl commentService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateComment() {
        long postId = 1L;
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("This is a comment");

        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(post);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentDto result = commentService.createComment(postId, commentDto);

        assertNotNull(result);
        assertEquals("This is a comment", result.getBody());
    }

    @Test
    void testGetCommentsByPostId() {
        long postId = 1L;
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();

        when(commentRepository.findByPostId(postId)).thenReturn(Arrays.asList(comment1, comment2));

        List<CommentDto> comments = commentService.getCommentsByPostId(postId);

        assertEquals(2, comments.size());
    }

    @Test
    void testGetCommentById() {
        long postId = 1L;
        long commentId = 1L;

        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(post);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        CommentDto result = commentService.getCommentById(postId, commentId);

        assertNotNull(result);
    }

    @Test
    void testGetCommentById_ThrowsException() {
        long postId = 1L;
        long commentId = 1L;

        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(new Post());

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        assertThrows(BlogAPIException.class, () -> commentService.getCommentById(postId, commentId));
    }

    @Test
    void testUpdateComment() {
        long postId = 1L;
        long commentId = 1L;
        CommentDto commentDto = new CommentDto();
        commentDto.setName("Updated Name");
        commentDto.setBody("Updated Body");

        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(post);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentDto result = commentService.updateComment(postId, commentId, commentDto);

        assertNotNull(result);
        assertEquals("Updated Body", result.getBody());
    }

    @Test
    void testDeleteComment() {
        long postId = 1L;
        long commentId = 1L;

        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(post);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        commentService.deleteComment(postId, commentId);

        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    void testCommentServiceMapperUtil() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setBody("Sample body");
        comment.setName("John Doe");
        comment.setEmail("john@example.com");

        CommentDto result = CommentServiceImpl.commentServiceMapperUtil(comment);

        assertNotNull(result);
        assertEquals("Sample body", result.getBody());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }
}
