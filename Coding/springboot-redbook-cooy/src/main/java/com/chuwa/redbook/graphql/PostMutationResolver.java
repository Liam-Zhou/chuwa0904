package com.chuwa.redbook.graphql;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.service.PostService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {

    private final PostService postService;

    @Autowired
    public PostMutationResolver(PostService postService) {
        this.postService = postService;
    }

    public PostDto createPost(PostDto postDto) {
        return postService.createPost(postDto);
    }

    public PostDto updatePostById(Long id, PostDto postDto) {
        return postService.updatePost(postDto, id);
    }

    public String deletePostById(Long id) {
        postService.deletePostById(id);
        return "Post entity deleted successfully.";
    }
}
