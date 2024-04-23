package com.app.blog.controller;

import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users")
@Tag(name = "CRUD REST APIs for Users Resource")
public interface IAuthorController {

    @Operation(
            summary = "Get Post By User ID",
            description = "It is used to fetch all the post created by an User"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{authorId}/posts")
    public ResponseEntity<List<PostDto>> fetchPostsByUserId(@PathVariable("authorId") UUID authorId);

    @Operation(
            summary = "Get User by ID",
            description = "It is used to get user details  by Id",
            hidden = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 201 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("userId") UUID userId, @RequestBody UserDto userDto);

    @Operation(
            summary = "Update User by ID",
            description = "It is used to update user details",
            hidden = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserDetails(@PathVariable("userId") UUID userId, @RequestBody UserDto userDto);

    @Operation(
            summary = "Delete User by ID",
            description = "It is used to delete a user",
            hidden = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") UUID userId);

}
