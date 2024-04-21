package com.app.blog.repository;

import com.app.blog.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAuthorRepositery extends JpaRepository<Author, UUID> {
    Optional<Author> findByUsernameOrEmail(String userName, String email);

    Optional<Author> findByUsername(String username);

    Optional<Author> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsernameOrEmail(String username,String email);

}
