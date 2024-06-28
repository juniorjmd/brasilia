package com.brasilia.prueba.springboot.app.springboot_tareas.controller;

import com.brasilia.prueba.springboot.app.springboot_tareas.contoller.TaskController;
import com.brasilia.prueba.springboot.app.springboot_tareas.model.Task;
import com.brasilia.prueba.springboot.app.springboot_tareas.service.TaskService;
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

@WebMvcTest(TaskController.class)
public class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task();
        task.setTitle("Buy groceries");

        Mockito.when(taskService.create(Mockito.anyLong(), Mockito.any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/users/1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Buy groceries\",\"description\":\"Milk, Bread, Butter\",\"completed\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Buy groceries"));
    }

    @Test
    public void testGetTasksByUserId() throws Exception {
        Task task = new Task();
        task.setTitle("Buy groceries");

        Mockito.when(taskService.getTaskByUserId(Mockito.anyLong())).thenReturn(Optional.of(Collections.singletonList(task)));

        mockMvc.perform(get("/api/users/1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Buy groceries"));
    }
}
