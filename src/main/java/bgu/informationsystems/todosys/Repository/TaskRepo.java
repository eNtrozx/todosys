package bgu.informationsystems.todosys.repository;

import bgu.informationsystems.todosys.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, String> {

    List<Task> findAllByOwnerId(String ownerId);

}