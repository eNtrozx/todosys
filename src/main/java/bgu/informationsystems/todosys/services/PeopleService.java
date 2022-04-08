package bgu.informationsystems.todosys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bgu.informationsystems.todosys.excpetions.NoSuchEntityException;
import bgu.informationsystems.todosys.models.Person;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.repository.PersonRepo;
import bgu.informationsystems.todosys.repository.TaskRepo;

@Service
public class PeopleService {

    @Autowired
    private PersonRepo personsRepo;

    @Autowired
    private TaskRepo tasksRepo;

    // TODO : Check invalid input

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

    public void updatePerson(String id, Person person) {
        personsRepo.findById(id).ifPresent(personInDb -> {
            personInDb.setEmail(person.getEmail());
            personInDb.setFavoriteProgrammingLanguage(person.getFavoriteProgrammingLanguage());
            personInDb.setName(person.getName());
            personsRepo.save(personInDb);
        });
    }

    public void deletePerson(String id) {
        try {
            personsRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntityException("person", id);
        }
    }

    public List<Task> getTasksOfPerson(String id) {
        getPerson(id);
        return tasksRepo.findAllByOwnerId(id);
    }

    public void addTaskToPerson(String id, Task task) {
        getPerson(id);
        task.setOwnerId(id);
        tasksRepo.save(task);
    }
}
