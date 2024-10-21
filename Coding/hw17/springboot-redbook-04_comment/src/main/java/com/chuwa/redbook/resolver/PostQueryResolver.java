package com.chuwa.redbook.resolver;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.service.PostService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostQueryResolver {

    private final PostService postService;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public PostDto getPost(Long id) {
        return postService.getPostById(id);
    }

    public List<PostDto> getAllPosts() {
        System.out.println("Fetching all posts");  // Debugging
        return postService.getAllPost();
    }
}

