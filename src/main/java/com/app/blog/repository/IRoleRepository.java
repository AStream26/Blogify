package com.app.blog.repository;

import com.app.blog.Constant.AppRole;
import com.app.blog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(AppRole role);
}
