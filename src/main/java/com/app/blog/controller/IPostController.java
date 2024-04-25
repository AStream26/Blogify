package com.app.blog.controller;

import com.app.blog.Constant.AppConstants;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.Response;
import com.app.blog.service.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "CRUD REST APIs for POST Resource")
public interface IPostController {
    @SecurityRequirement(name = "Bearer Authorization")
    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save Post to Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post);

    @Operation(
            summary = "Get All post REST API",
            description = "It is used to fetch all the posts created"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<Response<PaginatedResponse<PostDto>>> fetchAllPosts(
            @RequestParam(value = "pageNo", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NO) Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir);


    @Operation(
            summary = "Get Post By ID or Slug",
            description = "It is used to fetch post by id or slug"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{slugOrId}")
    public ResponseEntity<Response<PostDto>> fetchPostBySlugOrId(@PathVariable("slugOrId") String slugOrId);

    @Operation(
            summary = "Update Post By ID",
            description = "It is used to update post by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") UUID id, @Valid @RequestBody PostDto post);

    @Operation(
            summary = "Delete Post By ID",
            description = "It is used to delete post by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") UUID id);
}
