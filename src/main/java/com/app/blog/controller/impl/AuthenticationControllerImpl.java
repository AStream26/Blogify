package com.app.blog.controller.impl;

import com.app.blog.controller.IAuthenticationController;
import com.app.blog.dtos.JWTResponse;
import com.app.blog.dtos.LoginDto;
import com.app.blog.dtos.UserDto;
import com.app.blog.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationControllerImpl implements IAuthenticationController {

    @Autowired
    IAuthService authService;
    @Override
    public ResponseEntity<JWTResponse> login(LoginDto loginDto) {
        System.out.println("AuthControllerImpl.login");
        String token = authService.login(loginDto);
        JWTResponse response = new JWTResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> register(UserDto userDto) throws Exception {
        System.out.println("AuthControllerImpl.register");
        String message = authService.register(userDto);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
}
