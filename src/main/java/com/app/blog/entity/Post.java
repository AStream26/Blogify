package com.app.blog.entity;

import com.app.blog.utils.SlugUtils;
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
@Table(name = "posts")
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

    @Column(nullable = true,unique = true)
    private String slug;

    @CreationTimestamp
    private LocalDateTime publishedAt;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post",fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId",referencedColumnName = "id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private Category category;

    @PrePersist
    private void generateSlug(){
        this.slug = SlugUtils.generateSlug(this.title);
    }

}
