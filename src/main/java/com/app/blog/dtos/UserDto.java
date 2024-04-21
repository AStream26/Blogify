package com.app.blog.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "name should not be blank")
    private String name;

    @NotBlank

    @Size(min = 5,message = "username should be of min length 5")
    private String username;

    @Size(min = 10,message = "password should contain at-least 10 character")
    private String password;
    @Email(message = "enter valid email")
    private String email;
}
