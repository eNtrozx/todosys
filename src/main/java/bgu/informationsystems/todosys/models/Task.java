package bgu.informationsystems.todosys.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Chore.class, name = "Chore"),
        @JsonSubTypes.Type(value = Homework.class, name = "Homework")
})
@Entity
@Table(name = "Tasks")
public class Task {

    public static enum Status {
        ACTIVE("Active"),
        DONE("Done");

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

    @NotBlank(message = "ownerId can not be empty")
    private String ownerId;
    private Status status = Status.ACTIVE;

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
