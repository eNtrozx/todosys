package bgu.informationsystems.todosys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bgu.informationsystems.todosys.exceptions.NoSuchEntityException;
import bgu.informationsystems.todosys.models.Chore;
import bgu.informationsystems.todosys.models.Homework;
import bgu.informationsystems.todosys.models.Task;
import bgu.informationsystems.todosys.models.Task.Status;
import bgu.informationsystems.todosys.repository.TaskRepo;

@Service
public class TasksService {

    @Autowired
    TaskRepo tasksRepo;

    @Autowired
    PeopleService peopleService;

    public Task getTask(String id) {
        return tasksRepo.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("task", id));
    }

    public void updateTask(String id, Task task) {
        Task currentTask = getTask(id);

        if (currentTask instanceof Chore) {
            Chore currTask = (Chore) currentTask;
            Chore patchTask = (Chore) task;

            if (patchTask.getStatus() != null)
                currTask.setStatus(patchTask.getStatus());
            if (patchTask.getDescription() != null)
                currTask.setDescription(patchTask.getDescription());
            if (patchTask.getSize() != null)
                currTask.setSize(patchTask.getSize());

            tasksRepo.save(currTask);
        } else if (currentTask instanceof Homework) {
            Homework currTask = (Homework) currentTask;
            Homework patchTask = (Homework) task;

            if (patchTask.getCourse() != null)
                currTask.setCourse(patchTask.getCourse());
            if (patchTask.getDetails() != null)
                currTask.setDetails(patchTask.getDetails());
            if (patchTask.getDueDate() != null)
                currTask.setDueDate(patchTask.getDueDate());
            if (patchTask.getStatus() != null)
                currTask.setDueDate(patchTask.getDueDate());

            tasksRepo.save(currTask);
        }
    }

    public void deleteTask(String id) {
        try {
            tasksRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntityException("task", id);
        }
    }

    public void setOwnerId(String id, String ownerId) {
        Task task = getTask(id);
        task.setOwner(peopleService.getPerson(ownerId));
        tasksRepo.save(task);
    }

    public void setStatus(String id, Status status) {
        Task task = getTask(id);
        task.setStatus(status);
        tasksRepo.save(task);
    }

}
