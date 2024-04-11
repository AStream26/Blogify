package com.app.blog.service.impl;

import com.app.blog.dtos.CommentDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.entity.Comment;
import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.ICommentRepository;
import com.app.blog.repository.IPostRepository;
import com.app.blog.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    IPostRepository postRepository;

    @Override
    public CommentDto createComment(UUID postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        System.out.println("savedComment = " + savedComment);
        CommentDto saveDto = new CommentDto();
        BeanUtils.copyProperties(savedComment, saveDto);
        System.out.println("saveDto = " + saveDto);
        return saveDto;
    }

    @Override
    public PaginatedResponse<CommentDto> getAllPosts(UUID postId, CommentDto comment, Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public CommentDto getCommentById(UUID postId, UUID commentId) {
        return null;
    }

    @Override
    public CommentDto updateCommentById(UUID postId, CommentDto comment, UUID commentId) {
        return null;
    }

    @Override
    public void deleteCommentById(UUID postId, UUID commentId) {

    }
}
