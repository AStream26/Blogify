package com.app.blog.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @Email(message = "Enter valid email")
    String usernameOrEmail;
    @NotBlank(message = "Password should not be blank")
    String password;
}
