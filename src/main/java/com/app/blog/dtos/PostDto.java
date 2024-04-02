package com.app.blog.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PostDto {

    private UUID id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime publishedAt;
    private LocalDateTime lastUpdatedAt;
}
