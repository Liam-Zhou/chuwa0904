package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;

import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {
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
    public void setUp(){
        // Initialize mocks CommentRepository and PostRepository
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testCommentServiceMapperUtil(){
        Comment comment = new Comment();

        // Test the static method directly
        CommentDto dto = CommentServiceImpl.commentServiceMapperUtil(comment);

        assertNotNull(dto);

    }


    @Test
    public void testCreateComment_success(){
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();

        //stubbing
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.save(ArgumentMatchers.any(Comment.class))).thenReturn(comment);

        // Test
        CommentDto result = commentService.createComment(1L, commentDto);

        // Verify
        assertNotNull(result);
        verify(commentRepository, times(1)).save(ArgumentMatchers.any(Comment.class));
    }

    @Test
    public void testCreateComment_notFound(){
        //setup
        CommentDto commentDto = new CommentDto();

        //define return a null post
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        //call the createComment() in interface to test, assert and verify the ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class,()->commentService.createComment(1L,commentDto));

    }

    @Test
    public void getCommentsById_success(){
        //setup
        Post post = new Post();
        post.setId(1L);
        Comment comment = new Comment();
        comment.setPost(post);

        //stubbing: define return a post
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        //call to test passing post id and  comment id
        CommentDto commentDto = commentService.getCommentById(post.getId(),comment.getId());

        //verify dto
        assertNotNull(commentDto);

    }

    @Test
    public void getCommentsById_postNotFound() {
         //stubbing:define to return an empty null post
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        //call to test(empty post so could not find post of id = 1L)
        assertThrows(ResourceNotFoundException.class,()->commentService.getCommentById(1L,1L));

    }

    @Test
    public void getCommentsById_commentNotFound(){
        //setup
        Post post = new Post();

        //stubbing
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());

        //call to test(empty comment so could not find comment of id = 1L)
        assertThrows(ResourceNotFoundException.class,()->commentService.getCommentById(1L,1L));
    }

    @Test
    void testGetCommentById_CommentDoesNotBelongToPost() {
        // Setup
        Post post = new Post();
        Comment comment = new Comment();
        post.setId(1L);
        comment.setPost(new Post());

        //stubbing
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        //Comment object is associated with a different post (not the one with ID 1L).
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        // Test & Verify
        assertThrows(BlogAPIException.class, () -> commentService.getCommentById(1L, 1L));
    }

    @Test
    void testGetCommentById_mapperUtil(){
        //setup
        Post post = new Post();
        post.setId(1L);
        Comment comment = new Comment();
        comment.setPost(post);
        //stubbing
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        //Comment object is associated with a different post (not the one with ID 1L).
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        //call to test passing post id and  comment id
        CommentDto commentDto = commentService.getCommentById(post.getId(),comment.getId());

        //verify dto
        assertNotNull(commentDto);
    }


}
