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

//    /**
//     * use this modelMapper to replace the mapToDto, mapToEntity methods.
//     */
//    @Autowired
//    private ModelMapper modelMapper;

    // Static method to replace ModelMapper
    // Convert Comment to CommentDto
    public static CommentDto commentServiceMapperUtil(Comment comment) {
        ModelMapper modelMapper = new ModelMapper();  // Local ModelMapper object
        return modelMapper.map(comment, CommentDto.class);
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        /*check null comment dto*/
        if(commentDto == null){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment cannot be null");
        }

//        //copying from dto to entity class using mapper
//        Comment comment = modelMapper.map(commentDto, Comment.class);

        /*copying from dto to entity using static method*/
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

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

        /*check if postid Post is valid*/
        if(postId < 0){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid postId");
        }

        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        /*check if comment is found*/
        if(comments.isEmpty()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comments not found");
        }
//
//        // convert list of comment entities to list of comment dto's using mapper
//        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());

        /*convert comment to dto using static method*/
        return  comments.stream()
                .map(CommentServiceImpl::commentServiceMapperUtil)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));


        /*findById(commentId) in Spring Data JPA (which is used here) returns an Optional<Comment>*/
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // 业务逻辑
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }


        /*convert comment to dto using static method*/
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

        /*null check for getter*/
        if(commentDtoRequest.getName() != null){
            comment.setName(commentDtoRequest.getName());
        }

        if (commentDtoRequest.getEmail() != null) {
            comment.setEmail(commentDtoRequest.getEmail());
        }
        if (commentDtoRequest.getBody() != null) {
            comment.setBody(commentDtoRequest.getBody());
        }

        Comment updatedComment = commentRepository.save(comment);

//        //convert comment to dto using mapper
//        return modelMapper.map(updatedComment, CommentDto.class);

        /*convert comment to dto using static method*/
        return commentServiceMapperUtil(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        /* Check if post exists */
        if (post == null) {
            throw new BlogAPIException(HttpStatus.NOT_FOUND, "Post not found");
        }

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
    }
}
