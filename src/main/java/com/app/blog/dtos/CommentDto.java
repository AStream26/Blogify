package com.app.blog.dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentDto {
    private UUID id;
    private String email;
    private String name;
    private String body;
    private LocalDateTime lastUpdatedAt;
    private LocalDateTime publishedAt;
}
