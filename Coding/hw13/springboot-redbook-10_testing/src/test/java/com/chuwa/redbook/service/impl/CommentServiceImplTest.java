package com.chuwa.redbook.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.chuwa.redbook.dao.CommentRepository;


import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    private CommentDto commentDto;
    private Comment comment;
    private Post post;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup sample objects
        post = new Post();
        post.setId(1L);

        comment = new Comment();
        comment.setId(1L);
        comment.setPost(post);

        commentDto = new CommentDto();
        commentDto.setId(1L);
    }

    @Test
    void testCreateCommentSuccess() {

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(modelMapper.map(commentDto, Comment.class)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        CommentDto result = commentServiceImpl.createComment(1L, commentDto);

        // Assertions
        assertNotNull(result);
        assertEquals(1L, result.getId());

        // Verify interactions
        verify(postRepository).findById(1L);
        verify(commentRepository).save(comment);
    }

    @Test
    void testCreateCommentPostNotFound() {

        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            commentServiceImpl.createComment(1L, commentDto);
        });

        verify(commentRepository, never()).save(any(Comment.class));
    }
    @Test
    void testGetCommentsByPostId() {
        // Setup
        List<Comment> comments = Arrays.asList(comment);
        when(commentRepository.findByPostId(1L)).thenReturn(comments);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        // Call the method
        List<CommentDto> result = commentServiceImpl.getCommentsByPostId(1L);

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());

        // Verify interactions
        verify(commentRepository).findByPostId(1L);
    }
    @Test
    void testGetCommentByIdSuccess() {
        // Mocking post and comment retrieval
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        // Call the method
        CommentDto result = commentServiceImpl.getCommentById(1L, 1L);

        // Assertions
        assertNotNull(result);
        assertEquals(1L, result.getId());

        // Verify interactions
        verify(postRepository).findById(1L);
        verify(commentRepository).findById(1L);
    }

    @Test
    void testGetCommentByIdPostNotFound() {
        // Mock post not found
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert that exception is thrown
        assertThrows(ResourceNotFoundException.class, () -> {
            commentServiceImpl.getCommentById(1L, 1L);
        });

        verify(commentRepository, never()).findById(anyLong());
    }

    @Test
    void testGetCommentByIdCommentNotFound() {
        // Mock post found, but comment not found
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert that exception is thrown
        assertThrows(ResourceNotFoundException.class, () -> {
            commentServiceImpl.getCommentById(1L, 1L);
        });
    }
    @Test
    void testUpdateCommentSuccess() {
        // Mocking retrieval and update
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        // Call the method
        CommentDto result = commentServiceImpl.updateComment(1L, 1L, commentDto);

        // Assertions
        assertNotNull(result);
        assertEquals(1L, result.getId());

        // Verify interactions
        verify(commentRepository).save(comment);
    }
    @Test
    void testDeleteCommentSuccess() {
        // Mocking post and comment retrieval
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // Call the method
        commentServiceImpl.deleteComment(1L, 1L);

        // Verify interactions
        verify(commentRepository).delete(comment);
    }

    @Test
    void testDeleteCommentPostNotFound() {
        // Mock post not found
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert that exception is thrown
        assertThrows(ResourceNotFoundException.class, () -> {
            commentServiceImpl.deleteComment(1L, 1L);
        });

        verify(commentRepository, never()).delete(any(Comment.class));
    }


}
