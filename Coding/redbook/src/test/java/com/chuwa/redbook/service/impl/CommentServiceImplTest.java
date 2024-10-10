package com.chuwa.redbook.service.impl;
import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.BDDMockito;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CommentServiceImpl.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Before
    public void setUp() {
        // Mock the static method commentServiceMapperUtil

        PowerMockito.mockStatic(CommentServiceImpl.class);
    }

    @Test
    public void testCreateComment() {
        long postId = 1L;
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("This is a comment");

        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(post);

        CommentDto mappedDto = new CommentDto();
        mappedDto.setBody("This is a comment");

        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        Mockito.when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        PowerMockito.when(CommentServiceImpl.commentServiceMapperUtil(any(Comment.class)))
                .thenReturn(mappedDto);

        CommentDto result = commentService.createComment(postId, commentDto);

        assertNotNull(result);
        assertEquals("This is a comment", result.getBody());
    }


}
