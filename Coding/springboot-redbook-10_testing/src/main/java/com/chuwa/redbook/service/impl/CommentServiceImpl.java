package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import com.chuwa.redbook.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author b1go
 * @date 6/23/22 11:14 PM
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * use this modelMapper to replace the mapToDto, mapToEntity methods.
     */
    @Autowired
    private ModelMapper modelMapper;

    public static CommentDto commentServiceMapperUtil(Comment comment) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        //Test
        if (commentDto.getName() == null || commentDto.getName().isEmpty()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment name cannot be empty");
        }
        if (commentDto.getEmail() == null || commentDto.getEmail().isEmpty()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email cannot be empty");
        }
        if (commentDto.getBody() == null || commentDto.getBody().isEmpty()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment body cannot be empty");
        }


        Comment comment = modelMapper.map(commentDto, Comment.class);
        // retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // set post to comment entity
        comment.setPost(post);

        // comment entity to DB
        Comment savedComment = commentRepository.save(comment);

//        return modelMapper.map(savedComment, CommentDto.class);
        return commentServiceMapperUtil(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //Test: Check if the post exists
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post", "id", postId);
        }

        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        //Test
        if (comments.isEmpty()) {
            throw new BlogAPIException(HttpStatus.NOT_FOUND, "No comments found for this post");
        }

        // convert list of comment entities to list of comment dto's
//        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        return comments.stream().map(comment -> commentServiceMapperUtil(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // 业务逻辑
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

//        return modelMapper.map(comment, CommentDto.class);
        return commentServiceMapperUtil(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDtoRequest) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // 业务逻辑
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        //Test: email and comment cannot be empty
        if (commentDtoRequest.getEmail() == null || commentDtoRequest.getEmail().isEmpty()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email cannot be empty");
        }
        if (commentDtoRequest.getBody() == null || commentDtoRequest.getBody().isEmpty()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment body cannot be empty");
        }

        comment.setName(commentDtoRequest.getName());
        comment.setEmail(commentDtoRequest.getEmail());
        comment.setBody(commentDtoRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

//        return modelMapper.map(updatedComment, CommentDto.class);
        return commentServiceMapperUtil(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
    }
}
