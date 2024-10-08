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

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        // Validate input
        if (commentDto == null) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment data cannot be null");
        }

        // Convert DTO to entity
        Comment comment = commentServiceMapperUtil(commentDto);

        // Retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // Set post to comment entity
        comment.setPost(post);

        // Save comment entity to DB
        Comment savedComment = commentRepository.save(comment);

        // Convert saved comment entity to DTO and return
        return commentServiceMapperUtil(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // Check if post exists
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post", "id", postId);
        }

        // Retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // Handle case if no comments are found
        if (comments.isEmpty()) {
            throw new BlogAPIException(HttpStatus.NOT_FOUND, "No comments found for the post");
        }

        // Convert list of comment entities to list of comment DTOs
        return comments.stream()
                .map(CommentServiceImpl::commentServiceMapperUtil)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // Retrieve post and comment entities by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Validate if comment belongs to the post
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        // Convert comment entity to DTO
        return commentServiceMapperUtil(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDtoRequest) {
        // Validate input
        if (commentDtoRequest == null) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment update data cannot be null");
        }

        // Retrieve post and comment entities by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Validate if comment belongs to the post
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        // Update comment fields
        comment.setName(commentDtoRequest.getName());
        comment.setEmail(commentDtoRequest.getEmail());
        comment.setBody(commentDtoRequest.getBody());

        // Save updated comment entity to DB
        Comment updatedComment = commentRepository.save(comment);

        // Convert updated comment entity to DTO
        return commentServiceMapperUtil(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // Retrieve post and comment entities by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Validate if comment belongs to the post
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        // Delete comment from DB
        commentRepository.delete(comment);
    }

    public static CommentDto commentServiceMapperUtil(Comment comment) {
        if (comment == null) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment entity cannot be null");
        }
        //return new CommentDto(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody(), comment.getPost().getId());
        return new CommentDto(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
    }

    public static Comment commentServiceMapperUtil(CommentDto commentDto) {
        if (commentDto == null) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment DTO cannot be null");
        }
//        return new Comment(commentDto.getName(), commentDto.getEmail(), commentDto.getBody());
        return new Comment(commentDto.getId(),commentDto.getName(), commentDto.getEmail(), commentDto.getBody());
    }
}
