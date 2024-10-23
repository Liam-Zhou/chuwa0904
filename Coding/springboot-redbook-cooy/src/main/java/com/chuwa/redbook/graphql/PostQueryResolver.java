package com.chuwa.redbook.graphql;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;
import com.chuwa.redbook.service.PostService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostQueryResolver implements GraphQLQueryResolver {
    private final PostService postService;

    @Autowired
    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public PostDto getPostById(Long id) {
        return postService.getPostById(id);
    }

    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }
}
