package com.app.blog.controller.impl;

import com.app.blog.controller.ICommentController;
import com.app.blog.dtos.CommentDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CommentController implements ICommentController {

    @Autowired
    ICommentService commentService;
    @Override
    public ResponseEntity<CommentDto> createComment(UUID postId, CommentDto comment) {
        System.out.println("postId = " + postId);
        CommentDto savedComment = commentService.createComment(postId,comment);
        return new ResponseEntity<CommentDto>(savedComment, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaginatedResponse<CommentDto>> fetchAllComments(UUID postId, Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public ResponseEntity<CommentDto> fetchCommentById(UUID postId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<PostDto> updateCommentById(UUID postId, UUID id, CommentDto comment) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteCommentById(UUID postId, UUID id) {
        return null;
    }
}
