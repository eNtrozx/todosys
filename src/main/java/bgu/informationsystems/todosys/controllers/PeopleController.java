package bgu.informationsystems.todosys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bgu.informationsystems.todosys.models.Person;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.services.PeopleService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = "*")

public class PeopleController { 
    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value ="/", method = RequestMethod.POST)
    public void add(@RequestBody Person person) {
        peopleService.addPerson(person);
    }
    
    @RequestMapping(value ="/", method = RequestMethod.GET)
    public List<Person> get() {
        return peopleService.getPersons();
    }
    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public Person get(@PathVariable String id) {
        return peopleService.getPerson(id);
    }
    @RequestMapping(value ="/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable String id, @RequestBody Person person) {
        peopleService.updatePerson(id,person);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        peopleService.deletePerson(id);
    }

    @RequestMapping(value ="/{id}/tasks", method = RequestMethod.GET)
    List<Task> getTasks(@PathVariable String id) {
         return peopleService.getTasksOfPerson(id);
    }

    @RequestMapping(value ="/{id}/tasks", method = RequestMethod.POST)
    public void addTask(@PathVariable String id, @RequestBody Task task) {
        peopleService.addTaskToPerson(id,task);
    }

}
