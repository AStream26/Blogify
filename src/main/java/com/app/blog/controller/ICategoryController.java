package com.app.blog.controller;


import com.app.blog.Constant.AppConstants;
import com.app.blog.dtos.CategoryDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/category")
public interface ICategoryController {

    @PostMapping
    ResponseEntity<Response<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categoryDto);

    @GetMapping
    ResponseEntity<Response<List<CategoryDto>>> getAllCategories();

    @GetMapping("/{id}")
    ResponseEntity<Response<CategoryDto>> getCategoryById(@PathVariable("id") UUID categoryId);

    @PutMapping("/{id}")
    ResponseEntity<Response<CategoryDto>> updateCategoryById(@PathVariable("id") UUID categoryId, @Valid @RequestBody CategoryDto categoryDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Response<Object>> deleteCategoryById(@PathVariable("id") UUID categoryId);

    @GetMapping("/{id}/posts")
    public ResponseEntity<Response<PaginatedResponse<PostDto>>> fetchPostByCategory(
            @PathVariable("id") UUID categoryId,
            @RequestParam(value = "pageNo",required = false,defaultValue = AppConstants.DEFAULT_PAGE_NO) Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value="sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY ) String sortBy,
            @RequestParam(value="sortDir",required = false, defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir);
}
