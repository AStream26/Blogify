package com.app.blog.repository;

import com.app.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthorId(UUID authorId);
}
