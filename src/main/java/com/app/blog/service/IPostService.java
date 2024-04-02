package com.app.blog.service;

import com.app.blog.dtos.PostDto;

import java.util.List;
import java.util.UUID;

public interface IPostService {

    PostDto createPost(PostDto post);
    List<PostDto> getAllPosts();

    PostDto getPostById(UUID postId);

    PostDto updatePostById(PostDto post,UUID postId);

    void deletePostById(UUID postId);
}
