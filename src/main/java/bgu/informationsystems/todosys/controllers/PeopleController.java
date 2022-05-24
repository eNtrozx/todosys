package bgu.informationsystems.todosys.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bgu.informationsystems.todosys.exceptions.NoSuchEntityException;
import bgu.informationsystems.todosys.models.Person;
import bgu.informationsystems.todosys.models.PersonUpdate;
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

    public void add(@Valid @RequestBody Person person, HttpServletResponse response) {
        peopleService.addPerson(person);
        response.setHeader(HttpHeaders.LOCATION, "/api/people/" + person.getId());
        response.setHeader("x-Created-Id", person.getId());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Person> get() {
        return peopleService.getPersons();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person get(@NotBlank @PathVariable String id) {
        return peopleService.getPerson(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Person update(@NotBlank @PathVariable String id, @Valid @RequestBody PersonUpdate personUpdate) {
        return peopleService.updatePerson(id, personUpdate);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@NotBlank @PathVariable String id) {
        peopleService.deletePerson(id);
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    public ResponseEntity<?> getTasks(@NotBlank @PathVariable String id,
            @RequestParam(value = "status", required = false) String status) {
        Task.Status enumStatus = null;
        if (status != null) {
            try {
                enumStatus = Task.Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException ignored) {
                String msg = String.format("Invalid status [%s]. Accepted values are: %s", status,
                        Arrays.toString(Task.Status.values()));
                return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok(peopleService.getTasksOfPerson(id, enumStatus));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.POST)
    public void addTask(@NotBlank @PathVariable String id,
            @Valid @RequestBody Task task, HttpServletResponse response) {
        task.setOwnerId(id);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HashMap<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleMessageInvalidFormat(HttpMessageNotReadableException formatException) {
        String error = formatException.getMessage().toString();
        if (error.contains("known type ids = [Chore, Homework, Task];"))
            return "Invalid task type";
        return "";
    }
}
