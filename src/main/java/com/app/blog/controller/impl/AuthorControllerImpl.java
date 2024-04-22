package com.app.blog.controller.impl;

import com.app.blog.controller.IAuthorController;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.UserDto;
import com.app.blog.entity.Post;
import com.app.blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AuthorControllerImpl implements IAuthorController {

    @Autowired
    IPostService postService;
    @Override
    public ResponseEntity<List<PostDto>>fetchPostsByUserId(UUID authorId) {
        System.out.println("UserControllerImpl.fetchPostsByUserId");
        List<PostDto> postDtoList = postService.getAllPostsByUserId(authorId);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getUserDetails(UUID userId, UserDto userDto) {
        return null;
    }


    @Override
    public ResponseEntity<UserDto> updateUserDetails(UUID userId, UserDto userDto) {
        return null;
    }

    @Override
    public String deleteUser(UUID userId) {
        return null;
    }
}
