package com.brasilia.prueba.springboot.app.springboot_tareas.contoller;

import org.springframework.web.bind.annotation.RestController;
 
import com.brasilia.prueba.springboot.app.springboot_tareas.model.UserApp;
import com.brasilia.prueba.springboot.app.springboot_tareas.service.UserService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 

@RestController
@RequestMapping("api/users")   
public class UserController {
       @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserApp user) { 
         try { 
             UserApp createdUser = userService.createUser(user);
            URI location = new URI("/api/users/" + createdUser.getId() );
            return ResponseEntity.created(location).body(createdUser);



        } catch (URISyntaxException  e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
           List<UserApp>   user  =  userService.getAll() ;  
          if(!user.isEmpty() ){
            return ResponseEntity.ok( user );
          }else{
           return ResponseEntity.notFound().build();
          }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable Long userId ) {
          Optional<UserApp>   user  =  userService.getUserById(userId) ;  
          if(user.isPresent() ){
            return ResponseEntity.ok( user.orElseThrow() );
          }else{
           return ResponseEntity.notFound().build();
          }
 
    }
    

}
