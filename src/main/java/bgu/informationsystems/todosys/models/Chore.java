package bgu.informationsystems.todosys.models;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonValue;

@Entity
public class Chore extends Task {

    public static enum Size {
        SMALL("Small"),
        MEDIUM("Medium"),
        LARGE("Large");

        private String string;

        Size(String text) {
            string = text;
        }

        @JsonValue
        @Override
        public String toString() {
            return string;
        }
    }

    private String description;
    private Size size;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
