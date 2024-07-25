package com.crud.userManagement.repository;

import com.crud.userManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional getUserById(Long id);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
}
