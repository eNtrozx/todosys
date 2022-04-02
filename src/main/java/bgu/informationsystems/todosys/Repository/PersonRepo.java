package bgu.informationsystems.todosys.Repository;

import bgu.informationsystems.todosys.models.Person;

import org.springframework.data.jpa.repository.JpaRepository; 
 
public interface PersonRepo extends JpaRepository<Person,String> {
    Person findByid(String id);
    void   deleteById(String id);   
}