package com.chuwa.redbook.controller;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;
import com.chuwa.redbook.service.PostService;
import com.chuwa.redbook.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

/**
 * @author b1go
 * @date 8/22/22 7:14 PM
 */
@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @MutationMapping
    public PostDto createPost(@Argument PostDto postInput) {
        return postService.createPost(postInput);
    }

//    @GetMapping()
//    public PostResponse getAllPosts(
//            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
//    ) {
//        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
//    }
    @QueryMapping
    public PostResponse getAllPosts(
            @Argument Integer pageNo,
            @Argument Integer pageSize,
            @Argument String sortBy,
            @Argument String sortDir
    ) {
        int defaultPageNo = Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
        int defaultPageSize = Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
        String defaultSortBy = AppConstants.DEFAULT_SORT_BY;
        String defaultSortDir = AppConstants.DEFAULT_SORT_DIR;
        return postService.getAllPost(
                pageNo != null ? pageNo : defaultPageNo,
                pageSize != null ? pageSize : defaultPageSize,
                sortBy != null ? sortBy : defaultSortBy,
                sortDir != null ? sortDir : defaultSortDir
        );
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
//        return ResponseEntity.ok(postService.getPostById(id));
//    }
    @QueryMapping
    public PostDto getPostById(@Argument long id) {
        return postService.getPostById(id);
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
//        PostDto postResponse = postService.updatePost(postDto, id);
//        return new ResponseEntity<>(postResponse, HttpStatus.OK);
//    }
    @MutationMapping
    public PostDto updatePostById(@Argument long id, @Argument PostDto postInput) {
        return postService.updatePost(postInput, id);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
//        postService.deletePostById(id);
//        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
//    }
    @MutationMapping
    public String deletePost(@Argument long id) {
        postService.deletePostById(id);
        return "Post entity deleted successfully.";
    }

}
