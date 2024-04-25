package com.app.blog.service.impl;

import com.app.blog.dtos.PaginatedResponse;
import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.UserDetails;
import com.app.blog.entity.Author;
import com.app.blog.entity.Category;
import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.ICategoryRepository;
import com.app.blog.repository.IPostRepository;
import com.app.blog.service.IPostService;
import com.app.blog.utils.ObjectMapperUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("PostServiceImpl")
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    public boolean hasPermissionToEdit(UUID postId) {
        com.app.blog.dtos.UserDetails userDetails = (com.app.blog.dtos.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        return userDetails.getId().equals(post.getAuthor().getId());
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = ObjectMapperUtils.mapEntity(postDto, Post.class);
        UserDetails currentAuthorDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByName(postDto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", postDto.getCategory()));
        Author author = new Author();
        author.setId(currentAuthorDetails.getId());
        post.setAuthor(author);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        PostDto postSaved = ObjectMapperUtils.mapEntity(newPost, PostDto.class);
        postSaved.setCategory(newPost.getCategory().getName());
        return postSaved;
    }

    @Override
    public PaginatedResponse<PostDto> getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pages = postRepository.findAll(pageable);
        List<PostDto> postDtoList = new ArrayList<>();
        List<PostDto> postDto = pages.getContent().stream().map((post) -> {
            PostDto dto = new PostDto();
            BeanUtils.copyProperties(post, dto);
            dto.setCategory(post.getCategory().getName());
            return dto;
        }).toList();
        PaginatedResponse<PostDto> postResponse = new PaginatedResponse<>();
        postResponse.setList(postDto);
        postResponse.setPageNo(pages.getNumber());
        postResponse.setPageSize(pages.getSize());
        postResponse.setTotalElements(pages.getNumberOfElements());
        postResponse.setLast(pages.isLast());
        return postResponse;
    }


    @Override
    public PostDto getPostBySlugOrId(String slugOrId){

        Optional<Post> optional;
        try{
            UUID postId = UUID.fromString(slugOrId);
            optional = postRepository.findById(postId);
        }catch (IllegalArgumentException exception){
            optional = postRepository.findBySlug(slugOrId);
        }

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Post", "Id", slugOrId.toString());
        }
        System.out.println("PostServiceImpl.getPostBySlugOrId" + optional.get().getAuthor());
        PostDto post = new PostDto();
        BeanUtils.copyProperties(optional.get(), post);
        post.setCategory(optional.get().getCategory().getName());
        return post;
    }

    @Override
    public PostDto updatePostById(PostDto postDto, UUID postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));
        System.out.println("PostServiceImpl.updatePostById");
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        if(!postDto.getCategory().equals(post.getCategory().getName())){
            Category category = categoryRepository.findByName(postDto.getCategory())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "name", postDto.getCategory()));
            post.setCategory(category);
        }
        Post updatedPost = postRepository.save(post);
        PostDto dto = new PostDto();
        BeanUtils.copyProperties(updatedPost, dto);
        return dto;
    }

    @Override
    public void deletePostById(UUID postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));

        postRepository.deleteById(postId);
    }

}
