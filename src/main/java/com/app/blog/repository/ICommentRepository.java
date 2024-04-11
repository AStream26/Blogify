package com.app.blog.repository;

import com.app.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICommentRepository extends JpaRepository<Comment, UUID> {
}
