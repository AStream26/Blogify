package com.app.blog.service.impl;

import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.IPostRepository;
import com.app.blog.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto){

        Post post = new Post();
        BeanUtils.copyProperties(postDto,post);
        Post newPost = postRepository.save(post);
        PostDto postSaved  = new PostDto();
        BeanUtils.copyProperties(newPost,postSaved);
        return postSaved;
    }

    @Override
    public PaginatedResponse<PostDto> getAllPosts(Integer pageNo,Integer pageSize,String sortBy,String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
                                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pages = postRepository.findAll(pageable);
        List<PostDto> postDtoList = new ArrayList<>();
        List<PostDto> postDto = pages.getContent().stream().map((post)->{
            PostDto dto = new PostDto();
            BeanUtils.copyProperties(post,dto);
            return dto;
        }).toList();
        PaginatedResponse<PostDto> postResponse = new PaginatedResponse<>();
        postResponse.setData(postDto);
        postResponse.setPageNo(pages.getNumber());
        postResponse.setPageSize(pages.getSize());
        postResponse.setTotalElements(pages.getNumberOfElements());
        postResponse.setLast(pages.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(UUID postId) {

        Optional<Post> optional = postRepository.findById(postId);

        if(optional.isEmpty()){
            throw new ResourceNotFoundException("Post","Id",postId.toString());
        }

        PostDto post = new PostDto();
        BeanUtils.copyProperties(optional.get(),post);
        return post;
    }

    @Override
    public PostDto updatePostById(PostDto postDto, UUID postId) {

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        System.out.println("updatedPost = " + updatedPost);
        PostDto dto = new PostDto();
        BeanUtils.copyProperties(updatedPost,dto);
        return dto;
    }

    @Override
    public void deletePostById(UUID postId) {

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));

        postRepository.deleteById(postId);
    }

}
