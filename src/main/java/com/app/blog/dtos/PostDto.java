package com.app.blog.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(
        description = "POST Request body Model description"
)
public class PostDto {

    private UUID id;

    @Schema(
            description = "Title of the Post"
    )
    @NotEmpty
    @Size(min = 10, max=50, message = "title should be of 10-50 length")
    private String title;

    @NotEmpty
    @Size(min = 10,message = "description should contain at least 10 character")
    private String description;

    @NotBlank(message = "content should not be blank")
    private String content;

    @NotNull(message = "post should belong to a category")
    private String category;

    private String slug;
    private LocalDateTime publishedAt;
    private LocalDateTime lastUpdatedAt;
}
