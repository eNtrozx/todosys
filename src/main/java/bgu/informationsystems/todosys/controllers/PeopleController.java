package bgu.informationsystems.todosys.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bgu.informationsystems.todosys.excpetions.NoSuchEntityException;
import bgu.informationsystems.todosys.models.Person;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.services.PeopleService;

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = "*")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void add(@Valid @RequestBody Person person, HttpServletResponse response) { // TODO @Valid not working
        peopleService.addPerson(person);
        response.setHeader(HttpHeaders.LOCATION, "/api/people/" + person.getId());
        response.setHeader("x-Created-Id", person.getId());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Person> get() {
        return peopleService.getPersons();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person get(@PathVariable String id) {
        return peopleService.getPerson(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable String id, @RequestBody Person person) {
        peopleService.updatePerson(id, person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        peopleService.deletePerson(id);
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    List<Task> getTasks(@PathVariable String id) {
        return peopleService.getTasksOfPerson(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.POST)
    public void addTask(@PathVariable String id, @RequestBody Task task, HttpServletResponse response) {
        peopleService.addTaskToPerson(id, task);
        response.setHeader(HttpHeaders.LOCATION, "/api/tasks/" + task.getId());
        response.setHeader("x-Created-Id", task.getId());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntityException.class)
    public String entityNotFoundHandler(NoSuchEntityException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String duplicateEmailHandler(DataIntegrityViolationException ex) {
        if (ex.getCause().getCause().getMessage().contains("Unique index")) {
            return "A person with that email already exists";
        }
        return "";
    }

}
