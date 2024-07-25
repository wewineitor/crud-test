package com.crud.userQuery.service.impl;

import com.crud.userQuery.entity.User;
import com.crud.userQuery.exception.exceptions.UserNotFoundException;
import com.crud.userQuery.exception.exceptions.UserServiceException;
import com.crud.userQuery.repository.UserRepository;
import com.crud.userQuery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new UserServiceException("Error al recuperar usuarios");
        }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario con el ID " + id + " no fue encontrado"));
    }
}
