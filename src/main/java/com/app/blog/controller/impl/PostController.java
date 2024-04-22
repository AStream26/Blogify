package com.app.blog.controller.impl;

import com.app.blog.Constant.AppConstants;
import com.app.blog.Constant.ResponseStatus;
import com.app.blog.controller.IPostController;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.Response;
import com.app.blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController implements IPostController {

    @Autowired
    IPostService PostServiceImpl;

    public ResponseEntity<PostDto> createPost(PostDto post) {
        System.out.println("post = " + post);
        PostDto response = PostServiceImpl.createPost(post);
        return new ResponseEntity<PostDto>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<PaginatedResponse<PostDto>>> fetchAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        System.out.println("PostController.fetchAllPosts");
        PaginatedResponse<PostDto> paginatedResponse = PostServiceImpl.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        Response<PaginatedResponse<PostDto>> response = new Response<>(ResponseStatus.SUCCESS,HttpStatus.OK.value(), paginatedResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<Response<PostDto>> fetchPostById(UUID id) {
        System.out.println("PostController.fetchPostById");
        PostDto post = PostServiceImpl.getPostById(id);
        Response<PostDto> response = new Response<>(ResponseStatus.SUCCESS,HttpStatus.OK.value(),post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("@PostServiceImpl.hasPermissionToEdit(#id) or hasRole('ADMIN')")
    public ResponseEntity<PostDto> updatePostById(UUID id, PostDto post) {
        System.out.println("PostController.updatePostById");
        PostDto updatedPost = PostServiceImpl.updatePostById(post, id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }


    @PreAuthorize("@PostServiceImpl.hasPermissionToEdit(#id) or hasRole('ADMIN')")
    public ResponseEntity<String> deletePostById(UUID id) {
        System.out.println("PostController.updatePostById");
        PostServiceImpl.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
