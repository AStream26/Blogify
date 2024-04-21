package com.app.blog.controller.impl;

import com.app.blog.controller.ICommentController;
import com.app.blog.dtos.CommentDto;
import com.app.blog.dtos.PaginatedResponse;
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
        CommentDto savedComment = commentService.createComment(postId, comment);
        return new ResponseEntity<CommentDto>(savedComment, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaginatedResponse<CommentDto>> fetchAllComments(UUID postId, Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        System.out.println("CommentController.fetchAllComments");

        PaginatedResponse<CommentDto> comments = commentService.getAllCommentByPostId(postId, pageNo, pageSize, sortBy, sortDir);

        return new ResponseEntity<PaginatedResponse<CommentDto>>(comments, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CommentDto> fetchCommentById(UUID postId, UUID commentId) {

        CommentDto comment = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommentDto> updateCommentById(UUID postId, UUID commentId, CommentDto comment) {
        System.out.println("CommentController.updateCommentById");
        CommentDto updatedComment = commentService.updateCommentById(postId, comment, commentId);
        return new ResponseEntity<CommentDto>(updatedComment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCommentById(UUID postId, UUID commentId) {
        System.out.println("CommentController.deleteCommentById");
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<String>("Comment deleted successfully", HttpStatus.OK);
    }
}
