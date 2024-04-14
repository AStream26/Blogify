package com.app.blog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentDto {
    private UUID id;

    @Email(message = "Enter valid email id")
    private String email;

    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotBlank(message = "body should contain at ateast one character ")
    private String body;
    private LocalDateTime lastUpdatedAt;
    private LocalDateTime publishedAt;
}
