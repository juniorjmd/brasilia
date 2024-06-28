package com.brasilia.prueba.springboot.app.springboot_tareas.controller;

import com.brasilia.prueba.springboot.app.springboot_tareas.contoller.UserController;
import com.brasilia.prueba.springboot.app.springboot_tareas.model.UserApp;
import com.brasilia.prueba.springboot.app.springboot_tareas.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        UserApp user = new UserApp();
        user.setName("johndoe");

        Mockito.when(userService.createUser(Mockito.any(UserApp.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"johndoe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("johndoe"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserApp user = new UserApp();
        user.setName("johndoe");

        Mockito.when(userService.getAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("johndoe"));
    }

    @Test
    public void testGetAllUsers_NotFound() throws Exception {
        Mockito.when(userService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetUserById() throws Exception {
        UserApp user = new UserApp();
        user.setId(1L);
        user.setName("johndoe");

        Mockito.when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("johndoe"));
    }

    @Test
    public void testGetUserById_NotFound() throws Exception {
        Mockito.when(userService.getUserById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }
}
