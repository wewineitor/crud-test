package com.crud.userQuery.repository;

import com.crud.userQuery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
}
