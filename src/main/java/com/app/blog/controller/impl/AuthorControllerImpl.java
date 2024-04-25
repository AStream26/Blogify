package com.app.blog.controller.impl;

import com.app.blog.Constant.ResponseStatus;
import com.app.blog.controller.IAuthorController;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.Response;
import com.app.blog.dtos.UserDto;
import com.app.blog.entity.Post;
import com.app.blog.service.IPostService;
import com.app.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AuthorControllerImpl implements IAuthorController {

    @Autowired
    IUserService userService;

    @Override
    public ResponseEntity<Response<List<PostDto>>> fetchPostsByUserIdOrUserName(String userIdOrUserName) {
        System.out.println("AuthorControllerImpl.fetchPostsByUserIdOrUserName");
        List<PostDto> postDtoList = userService.getAllPostsByUserIdOrUserName(userIdOrUserName);
        Response<List<PostDto>> response = new Response<>(ResponseStatus.SUCCESS,HttpStatus.OK.value(),postDtoList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<UserDto>> getUserDetails(String userIdOrUserName) {
        System.out.println("AuthorControllerImpl.getUserDetails");
        UserDto userDto = userService.getUserByIdOrUserName(userIdOrUserName);
        Response<UserDto> response = new Response<>(ResponseStatus.SUCCESS,HttpStatus.OK.value(),userDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> updateUserDetails(String userIdOrUserName, UserDto userDto) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(UUID userId) {
        return null;
    }
}
