package com.app.blog.controller;

import com.app.blog.Constant.AppConstants;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.service.IPostService;
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
public interface IPostController {
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post);

    @GetMapping
    public ResponseEntity<PaginatedResponse<PostDto>> fetchAllPosts(
            @RequestParam(value = "pageNo",required = false,defaultValue = AppConstants.DEFAULT_PAGE_NO) Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value="sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY ) String sortBy,
            @RequestParam(value="sortDir",required = false, defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir);

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> fetchPostById(@PathVariable("id") UUID id);

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") UUID id,@Valid @RequestBody PostDto post);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") UUID id);
}
