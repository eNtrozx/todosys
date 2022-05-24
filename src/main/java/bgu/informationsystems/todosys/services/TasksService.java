package bgu.informationsystems.todosys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bgu.informationsystems.todosys.exceptions.NoSuchEntityException;
import bgu.informationsystems.todosys.models.Person;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.models.Task.Status;
import bgu.informationsystems.todosys.repository.PersonRepo;
import bgu.informationsystems.todosys.repository.TaskRepo;

@Service
public class TasksService {

    @Autowired
    TaskRepo tasksRepo;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    PeopleService peopleService;

    public Task getTask(String id) {
        return tasksRepo.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("task", id));
    }

    public void updateTask(String id, Task task) {
        // @TODO
    }

    public void deleteTask(String id) {
        try {
            tasksRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntityException("task", id);
        }
    }

    public void setOwnerId(String taskId, String ownerId) {
        peopleService.getPerson(ownerId);
        Task task = getTask(taskId);
        task.setOwnerId(ownerId);
        peopleService.addTaskToPerson(ownerId, task);
    }

    public void setStatus(String id, Status status) {
        Task task = getTask(id);
        task.setStatus(status);
        tasksRepo.save(task);
        Person owner = peopleService.getPerson(task.getOwnerId());
        switch (status) {
            case ACTIVE:
                personRepo.incrementActiveTaskCount(owner.getId());
                break;
            case DONE:
                personRepo.decrementActiveTaskCount(owner.getId());
                break;
        }
    }

}
