package bgu.informationsystems.todosys.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.GenericGenerator;

import bgu.informationsystems.todosys.validations.NullOrNotBlank;

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

    @JsonProperty(access = Access.READ_ONLY)
    private int activeTaskCount = 0;

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

    public int getActiveTaskCount() {
        return this.activeTaskCount;
    }

}
