package com.brasilia.prueba.springboot.app.springboot_tareas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brasilia.prueba.springboot.app.springboot_tareas.model.Task;
import com.brasilia.prueba.springboot.app.springboot_tareas.model.UserApp;
import com.brasilia.prueba.springboot.app.springboot_tareas.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserApp createUser(UserApp user) { 
        return userRepository.save(user);
    }
     

    @Override
    public  List<UserApp> getAll() { 
        return  (List<UserApp>) userRepository.findAll();
    }

    @Override
    public Optional<UserApp> getUserById(Long id) {  
        return   userRepository.findById(id);

    }

}
