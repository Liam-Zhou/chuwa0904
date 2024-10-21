package com.chuwa.redbook.controller;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class PostGraphQLController {

    @Autowired
    private PostService postService;

    @QueryMapping
    public PostDto getPost(Long id) {
        return postService.getPostById(id);
    }

    @QueryMapping
    public List<PostDto> getAllPosts() {
        // You might want to handle pagination and sorting here
        return postService.getAllPost(); // Adjust as necessary
    }

    @MutationMapping
    public PostDto createPost(PostDto postDto) {
        return postService.createPost(postDto);
    }

    @MutationMapping
    public PostDto updatePost(Long id, PostDto postDto) {
        return postService.updatePost(postDto, id);
    }

    @MutationMapping
    public Boolean deletePost(Long id) {
        postService.deletePostById(id);
        return true; // or you can return a success message
    }
}
