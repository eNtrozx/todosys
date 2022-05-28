package bgu.informationsystems.todosys.models;
 
 
 
import javax.persistence.Entity; 

import bgu.informationsystems.todosys.validations.DateTimeValid;
import bgu.informationsystems.todosys.validations.NullOrNotBlank;

@Entity
public class Homework extends Task {

    @NullOrNotBlank
    private String course;
   
    @DateTimeValid 
    private String dueDate;

    @NullOrNotBlank
    private String details;

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
