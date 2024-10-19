package com.chuwa.redbook.controller;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;
import com.chuwa.redbook.service.PostService;
import com.chuwa.redbook.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import javax.validation.Valid;

/**
 * debug it to show the flow
 * @author b1go
 * @date 6/17/22 12:17 AM
 */
//@Api(value = "CRUD REST APIs for Post resources")
//@RestController
//@RequestMapping("/api/v1/posts")  // localhost:8080/api/posts
@Controller
@Validated
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @ApiOperation(value = "Create Post REST API")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @PostMapping
//    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
//        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
//    }
    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public PostDto createPost(@Argument PostDto postInput) {
        return postService.createPost(postInput);
    }

//    @ApiOperation(value = "Get Post By Id REST API")
//    @GetMapping("/{id}")
//    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
//        return ResponseEntity.ok(postService.getPostById(id));
//    }
    @QueryMapping
    public PostDto getPostById(@Argument long id) {
        return postService.getPostById(id);
    }

//    @ApiOperation(value = "Get all Posts REST API")
//    @GetMapping
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

//    @ApiOperation(value = "Update Post By Id REST API")
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/{id}")
//    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
//        PostDto postResponse = postService.updatePost(postDto, id);
//        return new ResponseEntity<>(postResponse, HttpStatus.OK);
//    }
    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PostDto updatePostById(@Argument long id, @Argument PostDto postInput) {
        return postService.updatePost(postInput, id);
    }

//    @ApiOperation(value = "Delete Post By Id REST API")
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
//        postService.deletePostById(id);
//        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
//    }
    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(@Argument long id) {
        postService.deletePostById(id);
        return "Post entity deleted successfully.";
    }
}
