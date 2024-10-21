package com.chuwa.redbook.controller;

import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;
import com.chuwa.redbook.service.PostService;
import com.chuwa.redbook.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostControllerGraphql {

    @Autowired
    private PostService postService;

    @QueryMapping
    public PostDto getPost(@Argument Long id) {
        return postService.getPostById(id);
    }

    @QueryMapping
    public PostResponse getAllPosts(
            @Argument int pageNo,
            @Argument int pageSize,
            @Argument String sortBy,
            @Argument String sortDir
            ){
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }



    @MutationMapping
    public PostDto createPost(
            @Argument String title,
            @Argument String description,
            @Argument String content
    ){
        // Create PostDto from the arguments
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setDescription(description);
        postDto.setContent(content);

        return postService.createPost(postDto);
    }







}
