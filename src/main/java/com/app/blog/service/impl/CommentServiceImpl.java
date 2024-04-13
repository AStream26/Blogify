package com.app.blog.service.impl;

import com.app.blog.dtos.CommentDto;
import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.entity.Comment;
import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.ICommentRepository;
import com.app.blog.repository.IPostRepository;
import com.app.blog.service.ICommentService;
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
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    IPostRepository postRepository;

    @Override
    public CommentDto createComment(UUID postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        Comment comment = ObjectMapperUtils.mapEntity(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return ObjectMapperUtils.mapEntity(savedComment, CommentDto.class);
    }

    @Override
    public PaginatedResponse<CommentDto> getAllCommentByPostId(UUID postId, Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Comment> pages = commentRepository.findByPostId(postId, pageable);

        List<CommentDto> commentDtoList = pages.getContent().stream().map(comment -> ObjectMapperUtils.mapEntity(comment, CommentDto.class)).toList();
        PaginatedResponse<CommentDto> postResponse = new PaginatedResponse<>();
        postResponse.setData(commentDtoList);
        postResponse.setPageNo(pages.getNumber());
        postResponse.setPageSize(pages.getSize());
        postResponse.setTotalElements(pages.getNumberOfElements());
        postResponse.setLast(pages.isLast());
        return postResponse;
    }

    @Override
    public CommentDto getCommentById(UUID postId, UUID commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId.toString()));
        return ObjectMapperUtils.mapEntity(comment, CommentDto.class);
    }

    @Override
    public CommentDto updateCommentById(UUID postId, CommentDto comment, UUID commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        Comment savedComment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId.toString()));

        savedComment.setBody(comment.getBody());
        savedComment.setName(comment.getName());
        savedComment.setEmail(comment.getEmail());
        Comment updatedComment = commentRepository.save(savedComment);
        return ObjectMapperUtils.mapEntity(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteCommentById(UUID postId, UUID commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        Comment savedComment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId.toString()));
        commentRepository.deleteById(commentId);
    }
}
