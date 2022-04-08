package bgu.informationsystems.todosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bgu.informationsystems.todosys.models.Person;

public interface PersonRepo extends JpaRepository<Person, String> {

    boolean existsByEmail(String email);
}