package com.crud.userQuery.service;

import com.crud.userQuery.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
}
