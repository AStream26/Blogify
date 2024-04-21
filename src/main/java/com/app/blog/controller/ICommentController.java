package com.app.blog.controller;


import com.app.blog.Constant.AppConstants;
import com.app.blog.dtos.CommentDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/posts/{postId}/comments")
public interface ICommentController {

    @PostMapping
     ResponseEntity<CommentDto> createComment(@PathVariable UUID postId,@Valid @RequestBody CommentDto comment);

    @GetMapping
    public ResponseEntity<PaginatedResponse<CommentDto>> fetchAllComments(
            @PathVariable UUID postId,
            @RequestParam(value = "pageNo", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NO) Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir);

    @GetMapping("/{id}")
    ResponseEntity<CommentDto> fetchCommentById(@PathVariable UUID postId,@PathVariable("id") UUID id);

    @PutMapping("/{id}")
    ResponseEntity<CommentDto> updateCommentById(@PathVariable UUID postId,@PathVariable("id") UUID id,@Valid @RequestBody CommentDto comment);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCommentById(@PathVariable UUID postId,@PathVariable("id") UUID id);

}
