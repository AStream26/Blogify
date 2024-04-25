package com.app.blog.service.impl;

import com.app.blog.dtos.CategoryDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.entity.Category;
import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.ICategoryRepository;
import com.app.blog.repository.IPostRepository;
import com.app.blog.service.ICategoryService;
import com.app.blog.service.IPostService;
import com.app.blog.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    IPostRepository postRepository;

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        System.out.println("CategoryServiceImpl.createCategory");
        Category categoryEntity = ObjectMapperUtils.mapEntity(category, Category.class);
        Category savedCategory = categoryRepository.save(categoryEntity);
        return ObjectMapperUtils.mapEntity(savedCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        System.out.println("CategoryServiceImpl.getAllCategory");

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map((category -> ObjectMapperUtils.mapEntity(category, CategoryDto.class)))
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(UUID categoryId) {
        System.out.println("CategoryServiceImpl.getCategoryById");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId.toString()));
        return ObjectMapperUtils.mapEntity(category, CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto) {

        System.out.println("CategoryServiceImpl.updateCategory");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId.toString()));
        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(category);
        return ObjectMapperUtils.mapEntity(updatedCategory, CategoryDto.class);
    }

    @Override
    public String deleteCategory(UUID categoryId) {
        System.out.println("CategoryServiceImpl.deleteCategory");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId.toString()));
        categoryRepository.deleteById(categoryId);
        return "Category deleted successfully";
    }

    @Override
    public PaginatedResponse<PostDto> getPostsByCategory(UUID categoryId, Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId.toString()));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findByCategoryId(categoryId,pageable);
        List<PostDto> postDtoList = posts.getContent().stream().map(post -> {
            PostDto postDto = ObjectMapperUtils.mapEntity(post, PostDto.class);
            postDto.setCategory(post.getCategory().getName());
            return postDto;
        }).toList();

        PaginatedResponse<PostDto> postDtoPaginatedResponse = new PaginatedResponse<>();
        postDtoPaginatedResponse.setList(postDtoList);
        postDtoPaginatedResponse.setTotalElements(posts.getNumberOfElements());
        postDtoPaginatedResponse.setLast(posts.isLast());
        postDtoPaginatedResponse.setPageNo(posts.getNumber());
        postDtoPaginatedResponse.setPageSize(posts.getSize());

        return postDtoPaginatedResponse;
    }
}
