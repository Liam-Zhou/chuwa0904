package org.fionagu.restdemo.controller;

import org.fionagu.restdemo.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/*
* blog posts api:
* GET /posts: retrieve all blog posts
* GET /posts/{postID}: retrieve particular post by post id
* POST /posts : create a new post
* PUT /posts/{postID}: update a post base on post id
* DELETE /posts/{postID}: delete a post by id
* */

//using @Controller need to use @ResponseBody on each method tell Spring serialize res to JOSON
//@RestController is a combination of @Controller and @ResponseBody
@RestController
@RequestMapping("/posts")
public class BlogController {

    /** GET /posts: retrieve all blog posts*/
    /*url: http://localhost:8080/posts */
    @GetMapping
    public List<Blog> getAllPost(){
        //simulate fecthing all blog posts
        List<Blog> listBlog = Arrays.asList(
                new Blog(0L,"First blog"),
                new Blog(1L,"Second blog")
        );

        return listBlog;
    }


    /** GET /posts/{postID}: retrieve particular post by post id**/
    /*url: http://localhost:8080/posts/1 */
    @GetMapping("/{postID}")
    public String getPost(@PathVariable Long postID){
        //simulate fecthing blog post by id
        Blog blog = new Blog(postID,"Third blog");
        return blog.getTitle();
    }


    /** POST /posts : create a new post*/
    //@RequestBody Blog newPost tells Spring to map this json into java class Blog object
    /*url: http://localhost:8080/posts */
    /*
    * {
    *   "postID":11,
        "title":"Forth title"
    * }*/
    @PostMapping
    public Blog addPost(@RequestBody Blog newPost){
         //Blog newPost = new Blog(3L,"new post");
        return newPost;
    }


    /** PUT /posts/{postID}: update a post base on post id*/
    /*url: http://localhost:8080/posts/1*/
    @PutMapping("/{postID}")
    public String updatePost(@PathVariable Long postID){
        Blog blog = new Blog(postID,"updated a post by id");
        return blog.getTitle();
    }

    /**DELETE /posts/{postID}: delete a post by id*/
    /*url: http://localhost:8080/posts/1 */
    @DeleteMapping("/{postID}")
    public String deletePost(@PathVariable Long postID){
        return "The blog of id:"+postID+" has been deleted";
    }


}
