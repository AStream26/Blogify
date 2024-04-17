package com.app.blog.repository;

import com.app.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepositery extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameorEmail(String userName, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> existsByUsername(String username);

    Optional<User> existsByEmail(String email);

}