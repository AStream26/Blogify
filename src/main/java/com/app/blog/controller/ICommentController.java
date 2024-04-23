package com.app.blog.controller;


import com.app.blog.Constant.AppConstants;
import com.app.blog.dtos.CommentDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/posts/{postId}/comments")
@Tag(name = "CRUD REST APIs for Comment Resource")
public interface ICommentController {

    @Operation(
            summary = "Create Comment",
            description = "It is used to create comment on a Post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping
    ResponseEntity<CommentDto> createComment(@PathVariable UUID postId, @Valid @RequestBody CommentDto comment);

    @Operation(
            summary = "Get All comment REST API",
            description = "It is used to fetch all the comments created on a post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<PaginatedResponse<CommentDto>> fetchAllComments(
            @PathVariable UUID postId,
            @RequestParam(value = "pageNo", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NO) Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir);

    @Operation(
            summary = "Get Comment BY ID on a Post",
            description = "It is used to fetch a comment on a particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{id}")
    ResponseEntity<CommentDto> fetchCommentById(@PathVariable UUID postId, @PathVariable("id") UUID id);


    @Operation(
            summary = "Update Comment BY ID on a Post",
            description = "It is used to update a comment on a particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PutMapping("/{id}")
    ResponseEntity<CommentDto> updateCommentById(@PathVariable UUID postId, @PathVariable("id") UUID id, @Valid @RequestBody CommentDto comment);

    @Operation(
            summary = "Delete Comment BY ID on a Post",
            description = "It is used to delete a comment on a particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCommentById(@PathVariable UUID postId, @PathVariable("id") UUID id);

}
