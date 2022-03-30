package bgu.informationsystems.todosys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.services.TasksService;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public Task get(@PathVariable String id) {
        return null;
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable String id, @RequestBody Task task) {}

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {}

    @RequestMapping(value ="/{id}/status", method = RequestMethod.GET)
    public String getStatus(@PathVariable String id) {
        return null;
    }

    @RequestMapping(value ="/{id}/status", method = RequestMethod.PUT)
    public void setStatus(@PathVariable String id, @RequestBody String status) {}

    @RequestMapping(value ="/{id}/owner", method = RequestMethod.GET)
    public String getOwner(@PathVariable String id) {
        return null;
    }

    @RequestMapping(value ="/{id}/owner", method = RequestMethod.GET)
    public void setOwner(@PathVariable String id, @RequestBody String ownerId) {}
    
}
