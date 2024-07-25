package com.crud.userManagement.service.impl;

import com.crud.userManagement.entity.User;
import com.crud.userManagement.repository.UserRepository;
import com.crud.userManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User patchUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(userDetails.getName() != null) user.setName(userDetails.getName());
        if(userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
        if(userDetails.getPassword() != null) user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }
}
