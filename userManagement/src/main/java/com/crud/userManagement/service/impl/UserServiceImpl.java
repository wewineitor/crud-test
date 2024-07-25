package com.crud.userManagement.service.impl;

import com.crud.userManagement.entity.User;
import com.crud.userManagement.exception.exceptions.EmailAlreadyExistsException;
import com.crud.userManagement.exception.exceptions.InvalidUserException;
import com.crud.userManagement.exception.exceptions.UserNotFoundException;
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
        validateUser(user);
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Ya hay un usuario con el correo electrónico " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Usuario con el ID " + id + " no fue encontrado");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        validateUser(userDetails);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario con el ID " + id + " no fue encontrado"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        if(userRepository.existsByEmail(userDetails.getEmail())) {
            throw new EmailAlreadyExistsException("Ya hay un usuario con el correo electrónico " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User patchUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario con el ID " + id + " no fue encontrado"));
        if (userDetails.getName() != null) user.setName(userDetails.getName());
        if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null) user.setPassword(userDetails.getPassword());
        validateUser(user);
        return userRepository.save(user);
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new InvalidUserException("El nombre de usuario no puede ser nulo o estar vacío");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidUserException("El correo electrónico del usuario no puede ser nulo o estar vacío");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidUserException("La contraseña de usuario no puede ser nula o estar vacía");
        }
    }
}
