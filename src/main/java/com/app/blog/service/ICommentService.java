package com.app.blog.service;

import com.app.blog.dtos.CommentDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;

import java.util.UUID;

public interface ICommentService {

    CommentDto createComment(UUID postId,CommentDto comment);
    PaginatedResponse<CommentDto> getAllCommentByPostId(UUID postId,Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    CommentDto getCommentById(UUID postId,UUID commentId);

    CommentDto updateCommentById(UUID postId,CommentDto comment,UUID commentId);

    void deleteCommentById(UUID postId,UUID commentId);
}
