package com.app.blog.service;

import com.app.blog.dtos.CategoryDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {

    CategoryDto createCategory(CategoryDto category);
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(UUID categoryId);
    CategoryDto updateCategory(UUID categoryId,CategoryDto category);
    String deleteCategory(UUID categoryId);

    PaginatedResponse<PostDto> getPostsByCategory(UUID categoryId,Integer pageNo, Integer pageSize, String sortBy, String sortDir);

}
