package com.brasilia.prueba.springboot.app.springboot_tareas.service; 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brasilia.prueba.springboot.app.springboot_tareas.model.Task;
import com.brasilia.prueba.springboot.app.springboot_tareas.model.UserApp;
import com.brasilia.prueba.springboot.app.springboot_tareas.repository.TaskRepository;
import com.brasilia.prueba.springboot.app.springboot_tareas.repository.UserRepository;
@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Task create(Long idUser, Task task) { 
      //  Optional<Task> newTask = new Task(); 
        Optional<UserApp> nUser = userRepository.findById(idUser);
        if (nUser.isPresent()){
            task.setUser(nUser.get());
            return  taskRepository.save(task);
        } else{
          throw new RuntimeException("User not found");
         }
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAll() { 
        return (List<Task>) this.taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Task>>getTaskByUserId(Long id) {  
        return this.taskRepository.findAllByUserId(id);
    }

    @Override 
    @Transactional()
    public void deleteAllTaskByUserId(long idUser) { 
         
        Optional<UserApp> nUser = userRepository.findById(idUser);
        if (nUser.isPresent()){ 
           Optional<List<Task>> tasks =   this.taskRepository.findAllByUserId(idUser);
            List<Long> taskIds = tasks.get().stream().map(Task::getId).collect(Collectors.toList()); 
            taskRepository.deleteAllById(taskIds); 
        } else{
          throw new RuntimeException("User not found");
         }
    }

    @Override 
    @Transactional()
    public void deleteTaskByUserId(long idUser, long idTask)  { 
         
        Optional<UserApp> nUser = userRepository.findById(idUser);
        if (nUser.isPresent()){  
            Optional< Task > task =   this.taskRepository.findByIdAndUserId(idTask,idUser);
            if(task.isPresent()){
                taskRepository.deleteById(task.get().getId()); 
            }else{
                throw new RuntimeException("Task does not belong to the user");}
        } else{
          throw new RuntimeException("User not found");
         }
    }
    @Override
    @Transactional()
    public void delete(long idTask) { 
        taskRepository.deleteById(idTask);
    }

    @Override
    public Task marckTaskByUserId(long idUser, long idTask) {
        Optional<UserApp> nUser = userRepository.findById(idUser);
        if (nUser.isPresent()){ 
            Optional<Task> task = taskRepository.findById(idTask);
            if (task.isPresent() ){ 
                Task t = task.get();
                if( t.getUser().getId().equals(idUser)  ){
                    t.setCompleted(true);
                    return  taskRepository.save(t);
                }else{
                    throw new RuntimeException("Task does not belong to the user");
                }
               
           }else{
                throw new RuntimeException("Task not found");
           }
        } else{
          throw new RuntimeException("User not found");
         }
        
    }

}
