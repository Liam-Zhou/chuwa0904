package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    private Post post;
    private Comment comment;
    private CommentDto commentDto;

    @BeforeEach
    void setUp() {
        post = new Post();
        post.setId(1L);

        comment = new Comment();
        comment.setId(1L);
        comment.setPost(post);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setBody("Test comment body");

        commentDto = new CommentDto();
        commentDto.setName("Test Name");
        commentDto.setEmail("test@example.com");
        commentDto.setBody("Test comment body");
    }

    @Test
    void createComment_shouldReturnCommentDto() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(modelMapper.map(any(CommentDto.class), any())).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        when(modelMapper.map(any(Comment.class), any())).thenReturn(commentDto);

        CommentDto createdComment = commentService.createComment(1L, commentDto);

        assertNotNull(createdComment);
        assertEquals(commentDto.getName(), createdComment.getName());
        verify(postRepository).findById(1L);
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void getCommentsByPostId_shouldReturnListOfCommentDto() {
        when(commentRepository.findByPostId(1L)).thenReturn(Collections.singletonList(comment));
        when(modelMapper.map(any(Comment.class), any())).thenReturn(commentDto);

        List<CommentDto> comments = commentService.getCommentsByPostId(1L);

        assertNotNull(comments);
        assertEquals(1, comments.size());
        assertEquals(commentDto.getName(), comments.get(0).getName());
    }

    @Test
    void getCommentById_shouldReturnCommentDto() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(modelMapper.map(any(Comment.class), any())).thenReturn(commentDto);

        CommentDto retrievedComment = commentService.getCommentById(1L, 1L);

        assertNotNull(retrievedComment);
        assertEquals(commentDto.getName(), retrievedComment.getName());
    }

    @Test
    void updateComment_shouldReturnUpdatedCommentDto() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        when(modelMapper.map(any(Comment.class), any())).thenReturn(commentDto);

        CommentDto updatedComment = commentService.updateComment(1L, 1L, commentDto);

        assertNotNull(updatedComment);
        assertEquals(commentDto.getName(), updatedComment.getName());
    }

    @Test
    void deleteComment_shouldNotThrowException() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertDoesNotThrow(() -> commentService.deleteComment(1L, 1L));
        verify(commentRepository).delete(comment);
    }

    @Test
    void createComment_postNotFound_shouldThrowResourceNotFoundException() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> commentService.createComment(1L, commentDto));
    }

    @Test
    void getCommentById_postNotFound_shouldThrowResourceNotFoundException() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(1L, 1L));
    }

//    @Test
//    void updateComment_commentNotBelongToPost_shouldThrowBlogAPIException() {
//        comment.setPost(new Post()); // Create a new post that is not the same as the one we are checking against
//        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
//        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
//
//        assertThrows(BlogAPIException.class, () -> commentService.updateComment(1L, 1L, commentDto));
//    }
//
//    @Test
//    void deleteComment_commentNotBelongToPost_shouldThrowBlogAPIException() {
//        comment.setPost(new Post()); // Create a new post that is not the same as the one we are checking against
//        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
//        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
//
//        assertThrows(BlogAPIException.class, () -> commentService.deleteComment(1L, 1L));
//    }
}
