package bgu.informationsystems.todosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import bgu.informationsystems.todosys.models.Person;

@Transactional
public interface PersonRepo extends JpaRepository<Person, String> {

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE Person p set p.activeTaskCount = p.activeTaskCount + 1 WHERE p.id = :id")
    void incrementActiveTaskCount(@Param("id") String id);

    @Modifying
    @Query("UPDATE Person p set p.activeTaskCount = p.activeTaskCount - 1 WHERE p.id = :id")
    void decrementActiveTaskCount(@Param("id") String id);
}