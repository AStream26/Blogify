package com.app.blog.controller;


import com.app.blog.Constant.AppConstants;
import com.app.blog.dtos.CategoryDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/category")
@Tag(name = "CRUD REST APIs for Category Resource")
public interface ICategoryController {

    @Operation(
            summary = "Create Category",
            description = "It is used create a category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping
    ResponseEntity<Response<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categoryDto);

    @Operation(
            summary = "Get All Categories",
            description = "It is used to fetch all the category created"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    ResponseEntity<Response<List<CategoryDto>>> getAllCategories();


    @Operation(
            summary = "Get Category by ID",
            description = "It is used to get category by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{id}")
    ResponseEntity<Response<CategoryDto>> getCategoryById(@PathVariable("id") UUID categoryId);

    @Operation(
            summary = "Update Category by ID",
            description = "It is used to update a category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PutMapping("/{id}")
    ResponseEntity<Response<CategoryDto>> updateCategoryById(@PathVariable("id") UUID categoryId, @Valid @RequestBody CategoryDto categoryDto);

    @Operation(
            summary = "Delete Category",
            description = "It is used to delete a category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @DeleteMapping("/{id}")
    ResponseEntity<Response<Object>> deleteCategoryById(@PathVariable("id") UUID categoryId);

    @Operation(
            summary = "Get Post by Category",
            description = "It is used to fetch all the post under a given category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{id}/posts")
    public ResponseEntity<Response<PaginatedResponse<PostDto>>> fetchPostByCategory(
            @PathVariable("id") UUID categoryId,
            @RequestParam(value = "pageNo", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NO) Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir);
}
