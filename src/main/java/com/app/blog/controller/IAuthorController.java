package com.app.blog.controller;

import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users")
public interface IAuthorController {

    @GetMapping("/{authorId}/posts")
    public ResponseEntity<List<PostDto>>fetchPostsByUserId(@PathVariable("authorId")UUID authorId);

    @GetMapping("/{userId}")
    public  ResponseEntity<UserDto> getUserDetails(@PathVariable("userId") UUID userId, @RequestBody UserDto userDto);

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserDetails(@PathVariable("userId") UUID userId,@RequestBody UserDto userDto);

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") UUID userId);

}
