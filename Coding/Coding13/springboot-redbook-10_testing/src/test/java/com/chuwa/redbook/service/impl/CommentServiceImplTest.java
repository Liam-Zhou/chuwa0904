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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;
    private Post post;
    private CommentDto commentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        post = new Post();
        post.setId(1L);

        comment = new Comment();
        comment.setId(1L);
        comment.setName("John Doe");
        comment.setEmail("john@example.com");
        comment.setBody("Sample comment body");
        comment.setPost(post);

        commentDto = new CommentDto();
        commentDto.setName("John Doe");
        commentDto.setEmail("john@example.com");
        commentDto.setBody("Sample comment body");
    }

    @Test
    void createComment_ShouldReturnCommentDto_WhenValidInput() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentDto createdComment = commentService.createComment(1L, commentDto);

        assertNotNull(createdComment);
        assertEquals("John Doe", createdComment.getName());
        assertEquals("john@example.com", createdComment.getEmail());
        assertEquals("Sample comment body", createdComment.getBody());

        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void createComment_ShouldThrowException_WhenCommentDtoIsNull() {
        Exception exception = assertThrows(BlogAPIException.class, () -> {
            commentService.createComment(1L, null);
        });

        String expectedMessage = "Comment data cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getCommentsByPostId_ShouldReturnListOfComments_WhenPostExists() {
        when(postRepository.existsById(1L)).thenReturn(true);
        when(commentRepository.findByPostId(1L)).thenReturn(Arrays.asList(comment));

        List<CommentDto> comments = commentService.getCommentsByPostId(1L);

        assertNotNull(comments);
        assertFalse(comments.isEmpty());
        assertEquals(1, comments.size());
        assertEquals("John Doe", comments.get(0).getName());
    }

    @Test
    void getCommentsByPostId_ShouldThrowException_WhenNoCommentsFound() {
        when(postRepository.existsById(1L)).thenReturn(true);
        when(commentRepository.findByPostId(1L)).thenReturn(Arrays.asList());

        Exception exception = assertThrows(BlogAPIException.class, () -> {
            commentService.getCommentsByPostId(1L);
        });

        String expectedMessage = "No comments found for the post";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getCommentById_ShouldReturnCommentDto_WhenValidInput() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        CommentDto returnedComment = commentService.getCommentById(1L, 1L);

        assertNotNull(returnedComment);
        assertEquals("John Doe", returnedComment.getName());
        assertEquals("john@example.com", returnedComment.getEmail());
    }

    @Test
    void getCommentById_ShouldThrowException_WhenCommentDoesNotBelongToPost() {
        Post wrongPost = new Post();
        wrongPost.setId(2L);

        Comment wrongComment = new Comment();
        wrongComment.setPost(wrongPost);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(wrongComment));

        Exception exception = assertThrows(BlogAPIException.class, () -> {
            commentService.getCommentById(1L, 1L);
        });

        String expectedMessage = "Comment does not belong to post";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateComment_ShouldUpdateComment_WhenValidInput() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentDto updatedComment = commentService.updateComment(1L, 1L, commentDto);

        assertNotNull(updatedComment);
        assertEquals("John Doe", updatedComment.getName());
        assertEquals("john@example.com", updatedComment.getEmail());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void deleteComment_ShouldDeleteComment_WhenValidInput() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertDoesNotThrow(() -> commentService.deleteComment(1L, 1L));

        verify(commentRepository, times(1)).delete(any(Comment.class));
    }
}
