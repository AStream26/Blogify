package com.app.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Category {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    Set<Post> posts;

    @UpdateTimestamp
    LocalDateTime lastUpdatedAt;

    @CreationTimestamp
    LocalDateTime publishedAt;
}
