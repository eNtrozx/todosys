package bgu.informationsystems.todosys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.services.TasksService;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public Task get(@PathVariable String id) {
        return tasksService.getTask(id);
    }
    @RequestMapping(value ="/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable String id, @RequestBody Task task) {
        tasksService.updateTask(id,task);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
         tasksService.deleteTask(id);
    }

    @RequestMapping(value ="/{id}/status", method = RequestMethod.GET)
    public String getStatus(@PathVariable String id) {
        return tasksService.getTask(id).getStatus().toString();
    }

    @RequestMapping(value ="/{id}/status", method = RequestMethod.PUT)
    public void setStatus(@PathVariable String id, @RequestBody String status) { 
        tasksService.getTask(id).getStatus().update(status);
    }

    @RequestMapping(value ="/{id}/owner", method = RequestMethod.GET)
    public String getOwner(@PathVariable String id) {
        return tasksService.getTask(id).getOwnerId();
    }
    @RequestMapping(value ="/{id}/owner", method = RequestMethod.PUT)
    public void setOwner(@PathVariable String id, @RequestBody String ownerId) {
        tasksService.getTask(id).setOwnerId(ownerId);
    }
    
}
