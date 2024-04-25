package com.app.blog.service.impl;

import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.UserDto;
import com.app.blog.entity.Author;
import com.app.blog.entity.Post;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.IAuthorRepository;
import com.app.blog.service.IUserService;
import com.app.blog.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IAuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        Author user = authorRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail).orElseThrow(() -> new UsernameNotFoundException("username or email does not exist"));
        System.out.println("UserServiceImpl.loadUserByUsername");
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toSet());
        return new com.app.blog.dtos.UserDetails(user.getUsername(), user.getPassword(), true, true,
                true, true, authorities, user.getId(), user.getEmail(), user.getName());
    }

    @Override
    public List<PostDto> getAllPostsByUserIdOrUserName(String userIdOrUserName) {
        System.out.println("PostServiceImpl.getAllPostsByUserId");
        Optional<Author> author;
        try{
            author = authorRepository.findById(UUID.fromString(userIdOrUserName));
        }catch (IllegalArgumentException exp){
            author = authorRepository.findByUsername(userIdOrUserName);
        }
        Author user = author.orElseThrow(() -> new ResourceNotFoundException("Author", "userName or Id", userIdOrUserName));
        return user.getPosts().stream().map(post -> {
            PostDto dto = ObjectMapperUtils.mapEntity(post, PostDto.class);
            dto.setCategory(post.getCategory().getName());
            return dto;
        }).toList();
    }

    @Override
    public UserDto getUserByIdOrUserName(String userIdOrUserName) {
        System.out.println("UserServiceImpl.getUserByIdOrUserName");
        Optional<Author> author;
        try{
            author = authorRepository.findById(UUID.fromString(userIdOrUserName));
        }catch (IllegalArgumentException exp){
            author = authorRepository.findByUsername(userIdOrUserName);
        }
        Author user = author.orElseThrow(() -> new ResourceNotFoundException("Author", "userName or Id", userIdOrUserName));
        user.setPassword(null);
        return ObjectMapperUtils.mapEntity(user, UserDto.class);
    }

    @Override
    public UserDto updateUserByIdOrUserName(String userIdOrUserName, UserDto userDto) {
       return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        System.out.println("UserServiceImpl.getAllUsers");
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map((author -> ObjectMapperUtils.mapEntity(author, UserDto.class))).collect(Collectors.toList());
    }

    @Override
    public String deleteUserById(UUID userID) {
        return null;
    }
}
