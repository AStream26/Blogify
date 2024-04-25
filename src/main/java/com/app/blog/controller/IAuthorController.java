package com.app.blog.controller;

import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.Response;
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
            summary = "Get All Post By User ID or username",
            description = "It is used to fetch all the post created by an User"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{userIdOrUserName}/posts")
    public ResponseEntity<Response<List<PostDto>>> fetchPostsByUserIdOrUserName(@PathVariable("userIdOrUserName") String userIdOrUserName);

    @Operation(
            summary = "Get User by UserId or Username",
            description = "It is used to get user details  by userId or userName",
            hidden = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 201 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @GetMapping("/{userIdOrUserName}")
    public ResponseEntity<Response<UserDto>> getUserDetails(@PathVariable("userIdOrUserName") String userIdOrUserName);

    @Operation(
            summary = "Update User by userId or UserName",
            description = "It is used to update user details",
            hidden = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PutMapping("/{userIdOrUserName}")
    public ResponseEntity<UserDto> updateUserDetails(@PathVariable("userId") String userIdOrUserName, @RequestBody UserDto userDto);

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
