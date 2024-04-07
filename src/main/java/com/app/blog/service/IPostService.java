package com.app.blog.service;

import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;

import java.util.List;
import java.util.UUID;

public interface IPostService {

    PostDto createPost(PostDto post);
    PaginatedResponse<PostDto> getAllPosts(Integer pageNo,Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(UUID postId);

    PostDto updatePostById(PostDto post,UUID postId);

    void deletePostById(UUID postId);
}
