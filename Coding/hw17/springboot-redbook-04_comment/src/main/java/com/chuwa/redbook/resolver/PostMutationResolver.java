package com.chuwa.redbook.resolver;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.service.PostService;
import org.springframework.stereotype.Component;

@Component
public class PostMutationResolver {

    private final PostService postService;

    public PostMutationResolver(PostService postService) {
        this.postService = postService;
    }

    // Create a new post
    public PostDto createPost(String title, String description, String content) {
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setDescription(description);
        postDto.setContent(content);
        return postService.createPost(postDto);
    }

    // Update an existing post by ID
    public PostDto updatePost(Long id, String title, String description, String content) {
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setDescription(description);
        postDto.setContent(content);
        return postService.updatePost(postDto, id);
    }

    // Delete a post by ID
    public String deletePost(Long id) {
        postService.deletePostById(id);
        return "Post deleted";
    }
}
