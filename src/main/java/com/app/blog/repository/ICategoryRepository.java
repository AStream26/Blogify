package com.app.blog.repository;

import com.app.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByName(String name);
}
