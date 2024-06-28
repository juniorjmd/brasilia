package com.brasilia.prueba.springboot.app.springboot_tareas.service;

import java.util.List;
import java.util.Optional;
 
import com.brasilia.prueba.springboot.app.springboot_tareas.model.UserApp;

public interface UserService {
     
    UserApp createUser(UserApp user);
    List<UserApp> getAll();
    Optional<UserApp> getUserById(Long id);
}
