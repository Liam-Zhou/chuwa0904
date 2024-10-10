package com.chuwa.redbook.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import com.chuwa.redbook.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCommentServiceMapperUtil() {
        // Setup
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Chuwa");
        comment.setEmail("Chuwa@gmail.com");
        comment.setBody("Comment.");

        // Perform mapping
        CommentDto commentDto = CommentServiceImpl.commentServiceMapperUtil(comment);

        // Assertions
        assertNotNull(commentDto);
        assertEquals(comment.getId(), commentDto.getId());
        assertEquals(comment.getName(), commentDto.getName());
        assertEquals(comment.getEmail(), commentDto.getEmail());
        assertEquals(comment.getBody(), commentDto.getBody());
    }


    @Test
    void testCreateComment_Success() {
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // Test
        CommentDto result = commentService.createComment(1L, commentDto);

        // Verify
        assertNotNull(result);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testCreateComment_PostNotFound() {
        // Setup
        CommentDto commentDto = new CommentDto();
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Test & Verify
        assertThrows(ResourceNotFoundException.class, () -> commentService.createComment(1L, commentDto));
    }

    @Test
    void testGetCommentsByPostId_Success() {
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        List<Comment> comments = Arrays.asList(comment);

        when(postRepository.existsById(anyLong())).thenReturn(true);
        when(commentRepository.findByPostId(anyLong())).thenReturn(comments);

        // Test
        List<CommentDto> result = commentService.getCommentsByPostId(1L);

        // Verify
        assertEquals(1, result.size());
    }

    @Test
    void testGetCommentsByPostId_NoCommentsFound() {
        // Setup
        when(postRepository.existsById(anyLong())).thenReturn(true);
        when(commentRepository.findByPostId(anyLong())).thenReturn(Arrays.asList());

        // Test & Verify
        assertThrows(BlogAPIException.class, () -> commentService.getCommentsByPostId(1L));
    }

    @Test
    void testGetCommentById_Success() {
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        post.setId(1L);
        comment.setPost(post);

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        // Test
        CommentDto result = commentService.getCommentById(1L, 1L);

        // Verify
        assertNotNull(result);
    }

    @Test
    void testGetCommentById_PostNotFound() {
        // Setup
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Test & Verify
        assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(1L, 1L));
    }

    @Test
    void testGetCommentById_CommentNotFound() {
        // Setup
        Post post = new Post();
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Test & Verify
        assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(1L, 1L));
    }

    @Test
    void testGetCommentById_CommentDoesNotBelongToPost() {
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        post.setId(1L);
        comment.setPost(new Post());

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        // Test & Verify
        assertThrows(BlogAPIException.class, () -> commentService.getCommentById(1L, 1L));
    }

    @Test
    void testUpdateComment_Success() {
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        CommentDto commentDtoRequest = new CommentDto();
        post.setId(1L);
        comment.setPost(post);

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // Test
        CommentDto result = commentService.updateComment(1L, 1L, commentDtoRequest);

        // Verify
        assertNotNull(result);
    }

    @Test
    void testDeleteComment_Success() {
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        post.setId(1L);
        comment.setPost(post);

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        // Test
        commentService.deleteComment(1L, 1L);

        // Verify
        verify(commentRepository, times(1)).delete(any(Comment.class));
    }
}
