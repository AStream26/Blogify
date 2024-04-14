package com.app.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;

    @Column(nullable = false,length = 1000)

    private String content;

    @CreationTimestamp
    private LocalDateTime publishedAt;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private Set<Comment> comments;

}
