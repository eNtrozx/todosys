package bgu.informationsystems.todosys.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.GenericGenerator;

import bgu.informationsystems.todosys.models.Task.Status;
import bgu.informationsystems.todosys.validations.NullOrNotBlank;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Persons")
public class Person {

    @JsonProperty(access = Access.READ_ONLY)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NullOrNotBlank(message = "name can not be empty")
    private String name;

    @NotNull(message = "email is required")
    @Email(message = "Invalid email address")
    @Column(unique = true)
    private String email;

    @NullOrNotBlank(message = "favoriteProgrammingLanguage can not be empty")
    private String favoriteProgrammingLanguage;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Task> tasks;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoriteProgrammingLanguage() {
        return this.favoriteProgrammingLanguage;
    }

    public void setFavoriteProgrammingLanguage(String favoriteProgrammingLanguage) {
        this.favoriteProgrammingLanguage = favoriteProgrammingLanguage;
    }

    @JsonGetter("activeTaskCount")
    public int getActiveTaskCount() {
        return this.getTasks(Status.ACTIVE).size();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public List<Task> getTasks(Status status) {
        return tasks.stream().filter(t -> t.getStatus() == status).toList();
    }

}
