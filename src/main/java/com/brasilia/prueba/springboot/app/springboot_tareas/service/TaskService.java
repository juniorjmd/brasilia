package com.brasilia.prueba.springboot.app.springboot_tareas.service;

import java.util.List;
import java.util.Optional;

import com.brasilia.prueba.springboot.app.springboot_tareas.model.Task;

public interface TaskService {
    Task create (Long idUser ,  Task task);
    List<Task> getAll();
    Optional<List<Task>> getTaskByUserId( Long id);
    void deleteAllTaskByUserId (long idUser);
    void delete (long idTask);
    Task marckTaskByUserId(long idUser , long idTask);
    void deleteTaskByUserId(long idUser , long idTask);
    
}
