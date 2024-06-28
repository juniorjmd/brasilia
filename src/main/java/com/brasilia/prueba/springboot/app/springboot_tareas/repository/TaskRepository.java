package com.brasilia.prueba.springboot.app.springboot_tareas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.brasilia.prueba.springboot.app.springboot_tareas.model.Task;

public interface TaskRepository extends CrudRepository<Task,Long>{
    Optional<List<Task>> findAllByUserId(Long userId);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
}
