package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;
import net.logstash.logback.encoder.CompositeJsonEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImplTest.class);

    @Mock
    private CommentRepository commentRepositoryMock;

    @Mock
    private PostRepository postRepositoryMock;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Post post;
    private PostDto postDto;
    private Comment comment;
    private CommentDto commentDto;

    @BeforeAll
    static void beforeAll() {
        logger.info("START test");
    }

    @BeforeEach
    void setUp() {
        logger.info("set up Post for each test");
        this.post = new Post(1L, "xiao ruishi", "wanqu", "wanqu xiao ruishi",
                LocalDateTime.now(), LocalDateTime.now());
        this.postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("xiao ruishi");
        postDto.setContent("wanqu xiao ruishi");
        postDto.setDescription("wanqu");

        logger.info("set up Comment for each test");
        this.comment = new Comment(1L, "xiao ruizhi", "test1@gmail.com", "This is a test comment body");
        this.comment.setPost(post);
        this.commentDto = new CommentDto();
        this.commentDto.setId(1L);
        this.commentDto.setName("xiao ruizhi");
        this.commentDto.setBody("This is a test comment body");
        this.commentDto.setEmail("test1@gmail.com");
    }

    @Test
    public void testCommentToCommentDto() {
        Comment commentTest = new Comment();
        commentTest.setId(1L);
        //comment.setPost(post);
        commentTest.setName("xiao ruizhi");
        commentTest.setEmail("test1@gmail.com");
        commentTest.setBody("This is a test comment body");

        CommentDto commentDtoTest =  CommentServiceImpl.commentServiceMapperUtil(comment);

        assertNotNull(commentDtoTest);
        assertEquals(1L, commentDtoTest.getId());
        //assertEquals(post, comment.getName());
        assertEquals("xiao ruizhi", commentDtoTest.getName());
        assertEquals("This is a test comment body", commentDtoTest.getBody());
        assertEquals("test1@gmail.com", commentDtoTest.getEmail());
    }

    @Test
    public void testCommentDtoToComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1L);
        //commentDto.(post);
        commentDto.setName("xiao ruizhi");
        commentDto.setEmail("test1@gmail.com");
        commentDto.setBody("This is a test comment body");

        Comment comment =  CommentServiceImpl.commentServiceMapperUtil(commentDto);

        assertNotNull(commentDto);
        assertEquals(1L, comment.getId());
        //assertEquals(post, comment.getName());
        assertEquals("xiao ruizhi", comment.getName());
        assertEquals("This is a test comment body", comment.getBody());
        assertEquals("test1@gmail.com", comment.getEmail());
    }

    @Test
    public void testCreateComment() {
        try(MockedStatic<CommentServiceImpl> mockedStatic = Mockito.mockStatic(CommentServiceImpl.class)) {
            mockedStatic.when(() -> CommentServiceImpl.commentServiceMapperUtil(any(CommentDto.class)))
                    .thenReturn(comment);
            mockedStatic.when(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)))
                    .thenReturn(commentDto);

            when(postRepositoryMock.findById(anyLong())).thenReturn(Optional.of(post));
            when(commentRepositoryMock.save(any(Comment.class))).thenReturn(comment);
            CommentDto result = commentService.createComment(1L, commentDto);
            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("xiao ruizhi", result.getName());
            assertEquals("This is a test comment body", result.getBody());
            assertEquals("test1@gmail.com", result.getEmail());

            mockedStatic.verify(() -> CommentServiceImpl.commentServiceMapperUtil(any(CommentDto.class)), times(1));
            mockedStatic.verify(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)), times(1));
        }
    }

    @Test
    public void testFindAll() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        // Mock
        try (var mockedStatic = Mockito.mockStatic(CommentServiceImpl.class)) {
            mockedStatic.when(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)))
                    .thenReturn(commentDto);

            when(commentRepositoryMock.findByPostId(anyLong())).thenReturn(comments);

            // Execute the service method
            List<CommentDto> result = commentService.getCommentsByPostId(1L);

            // Assert the result
            assertNotNull(result);
            assertEquals(1, result.size());
            CommentDto dto = result.get(0);
            assertEquals(commentDto.getName(), dto.getName());

            // Verify that the static method was called
            mockedStatic.verify(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)), times(1));
        }
    }

    @Test
    public void testFindById() {
        try (MockedStatic<CommentServiceImpl> mockedStatic = Mockito.mockStatic(CommentServiceImpl.class)) {
            // Mock static method for Comment -> CommentDto
            mockedStatic.when(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)))
                    .thenReturn(commentDto);

            // Mock repository behavior
            when(postRepositoryMock.findById(anyLong())).thenReturn(Optional.of(post));
            when(commentRepositoryMock.findById(anyLong())).thenReturn(Optional.of(comment));
            // exec
            CommentDto result = commentService.getCommentById(1L, 1L);
            // assert
            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("xiao ruizhi", result.getName());
            assertEquals("This is a test comment body", result.getBody());
            assertEquals("test1@gmail.com", result.getEmail());
            assertEquals(comment.getPost().getId(), post.getId());
            mockedStatic.verify(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)), times(1));
        }
    }

    @Test
    public void testGetCommentById_ResourceNotFoundException() {
        // define the behaviors
        when(postRepositoryMock.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentRepositoryMock.findById(anyLong()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 2L));

        // execute and assertions
        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(1L, 2L));
    }

    @Test
    public void testUpdateComment() {
        String newBody = "Updated body: " + comment.getBody();
        commentDto.setBody(newBody);
        Comment updatedComment = new Comment();
        updatedComment.setId(comment.getId());
        updatedComment.setName(comment.getName());
        updatedComment.setBody(newBody);
        updatedComment.setEmail(comment.getEmail());

        try (MockedStatic<CommentServiceImpl> mockedStatic = Mockito.mockStatic(CommentServiceImpl.class)) {
            // Mock the static methods for CommentDto -> Comment and Comment -> CommentDto
            mockedStatic.when(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)))
                    .thenReturn(commentDto);

            // Mock repository interactions
            when(postRepositoryMock.findById(anyLong())).thenReturn(Optional.of(post));
            when(commentRepositoryMock.findById(anyLong())).thenReturn(Optional.of(comment));
            when(commentRepositoryMock.save(any(Comment.class))).thenReturn(updatedComment);

            // Execute the service method
            CommentDto result = commentService.updateComment(1L, 1L, commentDto);

            // Assert the result
            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("xiao ruizhi", result.getName());
            assertEquals(newBody, result.getBody());
            assertEquals("test1@gmail.com", result.getEmail());

            // Verify the static methods were called
            mockedStatic.verify(() -> CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)), times(1));
        }
    }
    @Test
    public void testUpdateComment_ResourceNotFoundException() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 2L));

        // execute and assertions
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.updateComment(1L,2L, commentDto));
    }

    @Test
    public void testDeleteById() {
        when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        when(commentRepositoryMock.findById(anyLong())).thenReturn(Optional.of(comment));
        doNothing().when(commentRepositoryMock).delete(any(Comment.class));

        commentService.deleteComment(1L, 1L);

        verify(commentRepositoryMock, times(1)).delete(any(Comment.class));
    }
    @Test
    public void testDeleteCommentById_ResourceNotFoundException() {
        // define the behaviors
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 2L));

        // execute and assertions
        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.deleteComment(1L, 2L));
    }

}
