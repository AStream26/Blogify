package com.app.blog.controller;


import com.app.blog.dtos.JWTResponse;
import com.app.blog.dtos.LoginDto;
import com.app.blog.dtos.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
@Tag(name = "CRUD REST APIs for Authentication")
public interface IAuthenticationController {

    @Operation(
            summary = "Login REST API",
            description = "It is used to login/sign-in to the application"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PostMapping("/login")
    ResponseEntity<JWTResponse> login(@RequestBody LoginDto loginDto);

    @Operation(
            summary = "Register User",
            description = "It is used to register in the application"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PostMapping("/register")
    ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) throws Exception;
}
