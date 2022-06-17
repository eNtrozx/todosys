package bgu.informationsystems.todosys.repository;

import bgu.informationsystems.todosys.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, String> {
}