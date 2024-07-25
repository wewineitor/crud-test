package com.crud.userManagement.controller;

import com.crud.userManagement.entity.User;
import com.crud.userManagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/management")
@AllArgsConstructor
@Tag(name = "User Controller", description = "Se encarga de las operaciones de escritura (creación, eliminación y actualización) relacionadas con los usuarios.")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Crea un nuevo usuario", description = "Cree un nuevo usuario utilizando los datos enviados. No se puede utilizar dos veces el mismo correo electrónico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creación con éxito"),
            @ApiResponse(responseCode = "400", description = "Creación fallida. Los campos no puedes ser nulos o estar vacíos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Creación fallida. El correo electrónico está duplicado.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar todo el usuario", description = "Actualiza los datos anteriores del usuario utilizando los datos enviados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modificado con éxito"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "El correo electrónico está duplicado.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Modificar parcialmente un usuario", description = "Actualiza los datos anteriores de forma parcial del usuario utilizando los datos enviados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modificado con éxito"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User user) {
        userService.patchUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario con el id dado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
