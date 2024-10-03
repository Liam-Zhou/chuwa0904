package com.chuwa.redbook.service;

import com.chuwa.redbook.payload.PostDto;

import java.util.List;

/**
 * @author b1go
 * @date 8/22/22 6:51 PM
 */
public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}