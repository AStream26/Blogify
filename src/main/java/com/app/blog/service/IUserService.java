package com.app.blog.service;

import com.app.blog.dtos.PostDto;
import com.app.blog.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface IUserService extends UserDetailsService {
    List<PostDto> getAllPostsByUserIdOrUserName(String userIdOrUserName);

    UserDto getUserByIdOrUserName(String userIdOrUserName);
    UserDto updateUserByIdOrUserName(String userIdOrUserName,UserDto userDto);
    List<UserDto> getAllUsers();
    String deleteUserById(UUID userID);

}
