package com.app.blog.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Comment {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false,length = 300)
    private String body;

    @UpdateTimestamp
    LocalDateTime lastUpdatedAt;

    @CreationTimestamp
    LocalDateTime createdAt;

    @ManyToOne
    Post post;


}
