package bgu.informationsystems.todosys.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;
 

import javax.persistence.*;
import javax.persistence.Id;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Chore.class, name = "Chore"),
    @JsonSubTypes.Type(value = Homework.class, name = "Homework")
})

@Entity
@Table(name = "Tasks")

public class Task {

    public static enum Status {
        Active("Active"),
        Done("Done");

        private String string;

        Status(String text) {
            string = text;
        }

        public void update(String status){
            string = status;
        } 
        @JsonGetter
        @Override
        public String toString() {
            return string;
        }
    }
    
    @JsonProperty(access = Access.READ_ONLY)
    @Id
    private String id;
    private String ownerId;
    private Status status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
