package bgu.informationsystems.todosys.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonGetter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
        ACTIVE("active"),
        DONE("done");

        private String string;

        Status(String text) {
            string = text;
        }

        @JsonValue
        @Override
        public String toString() {
            return string;
        }
    }

    @JsonProperty(access = Access.READ_ONLY)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person owner;

    private Status status = Status.ACTIVE;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getOwner() {
        return this.owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @JsonGetter(value = "ownerId")
    public String getOwnerId() {
        return this.getOwner().getId();
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
