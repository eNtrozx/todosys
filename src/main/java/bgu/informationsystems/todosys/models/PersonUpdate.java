package bgu.informationsystems.todosys.models;

import javax.validation.constraints.Email;

import bgu.informationsystems.todosys.validations.NullOrNotBlank;

public class PersonUpdate {

    @NullOrNotBlank(message = "name can not be empty")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @NullOrNotBlank(message = "favoriteProgrammingLanguage can not be empty")
    private String favoriteProgrammingLanguage;

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFavoriteProgrammingLanguage() {
        return this.favoriteProgrammingLanguage;
    }

}
