package com.brasilia.prueba.springboot.app.springboot_tareas.repository;

import org.springframework.data.repository.CrudRepository; 
import com.brasilia.prueba.springboot.app.springboot_tareas.model.UserApp; 
import java.util.Optional;
 

public interface UserRepository extends CrudRepository<UserApp , Long> {
    Optional<UserApp> findById(Long id);
}
