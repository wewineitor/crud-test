package com.crud.userManagement.service;

import com.crud.userManagement.entity.User;

public interface UserService {
    User saveUser(User user);
    void deleteUser(Long id);
    User updateUser(Long id, User userDetails);
    User patchUser(Long id, User userDetails);
}
