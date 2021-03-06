package bgu.informationsystems.todosys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bgu.informationsystems.todosys.exceptions.NoSuchEntityException;
import bgu.informationsystems.todosys.models.Person;
import bgu.informationsystems.todosys.models.PersonUpdate;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.repository.PersonRepo;
import bgu.informationsystems.todosys.repository.TaskRepo;

@Service
public class PeopleService {

    @Autowired
    private PersonRepo personsRepo;

    @Autowired
    private TaskRepo tasksRepo;

    public void addPerson(Person person) {
        personsRepo.save(person);
    }

    public List<Person> getPersons() {
        return personsRepo.findAll();
    }

    public Person getPerson(String id) {
        return personsRepo.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("person", id));
    }

    public Person updatePerson(String id, PersonUpdate personUpdate) {
        Person person = getPerson(id);
        if (personUpdate.getEmail() != null)
            person.setEmail(personUpdate.getEmail());
        if (personUpdate.getFavoriteProgrammingLanguage() != null)
            person.setFavoriteProgrammingLanguage(personUpdate.getFavoriteProgrammingLanguage());
        if (personUpdate.getName() != null)
            person.setName(personUpdate.getName());
        return personsRepo.save(person);
    }

    public void deletePerson(String id) {
        try {
            personsRepo.deleteById(id); // This will also delete person's tasks
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntityException("person", id);
        }
    }

    public void addTask(String ownerId, Task task) {
        task.setOwner(getPerson(ownerId));
        tasksRepo.save(task);
    }
}
