package com.app.blog.repository;

import com.app.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthorId(UUID authorId);
    @Query(value = "SELECT author_id from posts WHERE id=:postId",nativeQuery = true)
    UUID getAuthorIdByPostId(UUID postId);

    Page<Post> findByCategoryId(UUID categoryId, Pageable pageable);

    Optional<Post> findBySlug(String slug);
}
