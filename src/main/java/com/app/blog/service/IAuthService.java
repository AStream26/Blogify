package com.app.blog.service;

import com.app.blog.dtos.LoginDto;
import com.app.blog.dtos.UserDto;

public interface IAuthService {

    String login(LoginDto loginDto);
    String register(UserDto userDto) throws Exception;
}
