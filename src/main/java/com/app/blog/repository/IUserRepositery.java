package com.app.blog.repository;

import com.app.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepositery extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameOrEmail(String userName, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsernameOrEmail(String username,String email);

}
