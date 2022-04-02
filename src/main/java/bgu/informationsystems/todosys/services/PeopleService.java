package bgu.informationsystems.todosys.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.models.Person;
import bgu.informationsystems.todosys.Repository.PersonRepo;
import bgu.informationsystems.todosys.Repository.TaskRepo;

@Service
public class PeopleService {

    @Autowired
    PersonRepo personDBHandler;
    @Autowired
    TaskRepo taskDBHandler;

    // @TODO : Check invalid input

    public void addPerson(Person person) {
        person.setId(UUID.randomUUID().toString());
        personDBHandler.save(person);
    }

    public List<Person> getPersons() {
        return personDBHandler.findAll();
    }

    public Person getPerson(String id) {
        return personDBHandler.findByid(id);
    }

    public void updatePerson(String id, Person person) {
        Person personInDb = personDBHandler.findByid(id);
        if (personInDb != null) {
            personInDb.setEmail(person.getEmail());
            personInDb.setFavoriteProgrammingLanguage(person.getFavoriteProgrammingLanguage());
            personInDb.setName(person.getName());
            personDBHandler.save(personInDb);
        }
    }

    public void deletePerson(String id) {
        personDBHandler.deleteById(id);
    }

    public List<Task> getTasksOfPerson(String id) {
        return taskDBHandler.findAllByOwnerId(id);
    }

    public void addTaskToPerson(String id, Task task) {
        task.setId(UUID.randomUUID().toString());
        task.setOwnerId(id);
        taskDBHandler.save(task);
    }
}
