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

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value ="/", method = RequestMethod.POST)
    public void add(@RequestBody Person person) {}
    
    @RequestMapping(value ="/", method = RequestMethod.GET)
    public List<Person> get() {
        return null;
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public Person get(@PathVariable String id) {
        return null;
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable String id, @RequestBody Person person) {}

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {}

    @RequestMapping(value ="/{id}/tasks", method = RequestMethod.GET)
    public void getTasks(@PathVariable String id) {}

    @RequestMapping(value ="/{id}/tasks", method = RequestMethod.POST)
    public void addTask(@PathVariable String id, @RequestBody Task task) {}

}
