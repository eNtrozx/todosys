package bgu.informationsystems.todosys.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bgu.informationsystems.todosys.exceptions.NoSuchEntityException;
import bgu.informationsystems.todosys.models.Chore;
import bgu.informationsystems.todosys.models.Homework;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.services.PeopleService;
import bgu.informationsystems.todosys.services.TasksService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TasksController {

    @Autowired
    private TasksService tasksService;
    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task get(@NotBlank @PathVariable String id) {
        return tasksService.getTask(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void update(@NotBlank @PathVariable String id, @Valid @RequestBody Task task) {

        Task currentTask = tasksService.getTask(id);
        boolean tasksAreSameType = (currentTask instanceof Chore && task instanceof Chore)
                || (currentTask instanceof Homework && task instanceof Homework);

        if (tasksAreSameType) // Same objects
            tasksService.updateTask(id, task);
        else {
            // Not the same objects and hence it would be only logical to remove the old
            // entry and readd the new one.
            // There are two possible scnearios:
            // 1. Previous task was homework and new task is Chore
            // 2. Previous task was Chore and new task is homework

            tasksService.deleteTask(id);
            peopleService.addTask(currentTask.getOwnerId(), task);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@NotBlank @PathVariable String id) {
        tasksService.deleteTask(id);
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
    public String getStatus(@NotBlank @PathVariable String id) {
        return tasksService.getTask(id).getStatus().toString();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    public void setStatus(@NotBlank @PathVariable String id, @RequestBody Task.Status status) {
        tasksService.setStatus(id, status);
    }

    @RequestMapping(value = "/{id}/owner", method = RequestMethod.GET)
    public String getOwner(@NotBlank @PathVariable String id) {
        return tasksService.getTask(id).getOwnerId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}/owner", method = RequestMethod.PUT)
    public void setOwner(@NotBlank @PathVariable String id, @RequestBody String ownerId) {
        tasksService.setOwnerId(id, ownerId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntityException.class)
    public String entityNotFoundHandler(NoSuchEntityException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleMessageInvalidFormat(HttpMessageNotReadableException formatException) {
        String error = formatException.getMessage().toString();
        if (error.contains("Task$Status")) {
            String taskStatusInputted = error.split("from String \"")[1].split("\":")[0];
            return "value '" + taskStatusInputted + "' is not a legal task status.";
        }
        return "";
    }
}
