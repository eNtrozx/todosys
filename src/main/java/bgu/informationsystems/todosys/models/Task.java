package bgu.informationsystems.todosys.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class Task {

    public static enum Status {
        ACTIVE("active"),
        DONE("done");

        private String string;

        Status(String text) {
            string = text;
        }

        @JsonGetter
        @Override
        public String toString() {
            return string;
        }
    }
    
    @JsonProperty(access = Access.READ_ONLY)
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
