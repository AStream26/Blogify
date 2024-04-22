package com.app.blog.controller.impl;

import com.app.blog.Constant.ResponseStatus;
import com.app.blog.controller.ICategoryController;
import com.app.blog.dtos.CategoryDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.Response;
import com.app.blog.service.ICategoryService;
import com.app.blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class CategoryControllerImpl implements ICategoryController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IPostService postService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<CategoryDto>> createCategory(CategoryDto categoryDto) {

        CategoryDto savedCategory = categoryService.createCategory(categoryDto);
        Response<CategoryDto> response = new Response<>(ResponseStatus.SUCCESS, HttpStatus.CREATED.value(), savedCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response<List<CategoryDto>>> getAllCategories() {

        List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
        Response<List<CategoryDto>> response = new Response<>(ResponseStatus.SUCCESS, HttpStatus.OK.value(), categoryDtoList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<CategoryDto>> getCategoryById(UUID categoryId) {

        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        Response<CategoryDto> response = new Response<>(ResponseStatus.SUCCESS, HttpStatus.OK.value(), categoryDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<CategoryDto>> updateCategoryById(UUID categoryId, CategoryDto categoryDto) {

        CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
        Response<CategoryDto> response = new Response<>(ResponseStatus.SUCCESS, HttpStatus.OK.value(), updatedCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Object>> deleteCategoryById(UUID categoryId) {

        String message = categoryService.deleteCategory(categoryId);
        Response<Object> response = new Response<>(ResponseStatus.SUCCESS, HttpStatus.OK.value(), message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<PaginatedResponse<PostDto>>> fetchPostByCategory(UUID categoryId, Integer pageNo,
                                                                                    Integer pageSize, String sortBy,
                                                                                    String sortDir) {

        PaginatedResponse<PostDto> postDtoPaginatedResponse = categoryService.getPostsByCategory(categoryId, pageNo, pageSize, sortBy, sortDir);

        Response<PaginatedResponse<PostDto>> response = new Response<>(ResponseStatus.SUCCESS, HttpStatus.OK.value(), postDtoPaginatedResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
