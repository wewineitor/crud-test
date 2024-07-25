package com.crud.userQuery.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.crud.userQuery.controller.UserController;
import com.crud.userQuery.entity.User;
import com.crud.userQuery.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "Edwin", "edwin@gmail.com", "1234");
        User user2 = new User(2L, "Isaac", "isaac@gmail.com", "4321");
        List<User> allUsers = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(allUsers);

        mockMvc.perform(get("/query")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'Edwin', 'email': 'edwin@gmail.com', 'password': '1234'}, {'id': 2, 'name': 'Isaac', 'email': 'isaac@gmail.com', 'password': '4321'}]"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1L, "Edwin", "edwin@gmail.com", "1234");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/query/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'Edwin', 'email': 'edwin@gmail.com', 'password': '1234'}"));
    }
}