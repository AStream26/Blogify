package com.app.blog.controller;


import com.app.blog.dtos.JWTResponse;
import com.app.blog.dtos.LoginDto;
import com.app.blog.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface IAuthenticationController {

    @PostMapping("/login")
    ResponseEntity<JWTResponse> login(@RequestBody LoginDto loginDto);

    @PostMapping("/register")
    ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) throws Exception;


}
