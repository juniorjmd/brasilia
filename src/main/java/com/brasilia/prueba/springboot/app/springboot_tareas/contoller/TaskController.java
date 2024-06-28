package com.brasilia.prueba.springboot.app.springboot_tareas.contoller;

import org.springframework.web.bind.annotation.RestController;

import com.brasilia.prueba.springboot.app.springboot_tareas.model.Task;
import com.brasilia.prueba.springboot.app.springboot_tareas.service.TaskService;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("api/users/{userId}/tasks" ) 
public class TaskController {

    @Autowired
    private TaskService tService ;

    @GetMapping
    public ResponseEntity<?> getByUser(@PathVariable Long userId ) {
           Optional<List<Task>>  lTaks  =  tService.getTaskByUserId(userId) ;  
          if(lTaks.isPresent()){
            return ResponseEntity.ok(lTaks.orElseThrow());
          }else{
           return ResponseEntity.notFound().build();
          }
    }
    
     @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long userId, @PathVariable Long taskId) {
        try {
            return ResponseEntity.ok(tService.marckTaskByUserId(userId, taskId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PostMapping
    public ResponseEntity<Task> creatTask(@PathVariable Long userId, @RequestBody Task task) {
        try {
            return ResponseEntity.ok(tService.create(userId, task));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        try {
            tService.deleteTaskByUserId(userId, taskId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
