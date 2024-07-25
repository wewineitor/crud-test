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

    /**
     * Obtiene todos los usuarios.
     *
     * @return Una lista de todos los usuarios.
     * @throws UserServiceException Si ocurre un error al recuperar los usuarios.
     */
    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new UserServiceException("Error al recuperar usuarios");
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario a recuperar.
     * @return El usuario con el ID proporcionado.
     * @throws UserNotFoundException Si no se encuentra un usuario con el ID proporcionado.
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario con el ID " + id + " no fue encontrado"));
    }
}
