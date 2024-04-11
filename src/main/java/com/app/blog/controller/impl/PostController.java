package com.app.blog.controller.impl;

import com.app.blog.Constant.AppConstants;
import com.app.blog.controller.IPostController;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController implements IPostController {

    @Autowired
    IPostService postService;

    public ResponseEntity<PostDto> createPost(PostDto post) {
        System.out.println("post = " + post);
        PostDto response = postService.createPost(post);
        return new ResponseEntity<PostDto>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<PostDto>> fetchAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        System.out.println("PostController.fetchAllPosts");
        PaginatedResponse<PostDto> response = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<PaginatedResponse<PostDto>>(response, HttpStatus.OK);
    }


    public ResponseEntity<PostDto> fetchPostById(UUID id) {
        System.out.println("PostController.fetchPostById");
        PostDto post = postService.getPostById(id);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }


    public ResponseEntity<PostDto> updatePostById(UUID id, PostDto post) {
        System.out.println("PostController.updatePostById");
        PostDto updatedPost = postService.updatePostById(post, id);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }


    public ResponseEntity<String> deletePostById(UUID id) {
        System.out.println("PostController.updatePostById");
        postService.deletePostById(id);
        return new ResponseEntity<String>("Post entity deleted successfully", HttpStatus.OK);
    }
}
