package com.app.blog.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PostDto {

    private UUID id;

    @NotEmpty
    @Size(min = 10, max=50, message = "title should be of 10-50 length")
    private String title;

    @NotEmpty
    @Size(min = 10,message = "description should contain at least 10 character")
    private String description;

    @NotBlank(message = "content should not be blank")
    private String content;

    private LocalDateTime publishedAt;
    private LocalDateTime lastUpdatedAt;
}
