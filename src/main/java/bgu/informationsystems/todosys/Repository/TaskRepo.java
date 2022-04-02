package bgu.informationsystems.todosys.Repository;

import bgu.informationsystems.todosys.models.Task;
import org.springframework.data.jpa.repository.JpaRepository; 
import java.util.List;
 
public interface TaskRepo extends JpaRepository<Task,String> {
    Task findByid(String id);
    void deleteById(String id);
    List<Task> findAllByOwnerId(String ownerId);
}