package bgu.informationsystems.todosys.services;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import bgu.informationsystems.todosys.Repository.TaskRepo;
import bgu.informationsystems.todosys.models.Task;

@Service
public class TasksService {
    
    @Autowired TaskRepo   taskDBHandler;

    public Task getTask(String id){
        return taskDBHandler.findByid(id);
    } 
    public void updateTask(String id, Task task){ 
       //@TODO
    }
    public void deleteTask(String id){
       taskDBHandler.deleteById(id);
    }
}
