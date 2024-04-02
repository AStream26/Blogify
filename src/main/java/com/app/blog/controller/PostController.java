package com.app.blog.controller;

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
public class PostController {

    @Autowired
    IPostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post){
        System.out.println("post = " + post);
        PostDto response = postService.createPost(post);
        return  new ResponseEntity<PostDto>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> fetchAllPosts(){
        System.out.println("PostController.fetchAllPosts");
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> fetchPostById(@PathVariable("id") UUID id){
        System.out.println("PostController.fetchPostById");
        PostDto post = postService.getPostById(id);

        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") UUID id,@RequestBody PostDto post){
        System.out.println("PostController.updatePostById");
        PostDto updatedPost = postService.updatePostById(post,id);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") UUID id){
        System.out.println("PostController.updatePostById");
        postService.deletePostById(id);
        return new ResponseEntity<String>("Post entity deleted successfully",HttpStatus.OK);
    }


}
