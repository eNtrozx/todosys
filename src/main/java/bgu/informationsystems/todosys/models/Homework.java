package bgu.informationsystems.todosys.models;

import java.util.Date;

import javax.persistence.Entity;

import bgu.informationsystems.todosys.validations.NullOrNotBlank;

@Entity
public class Homework extends Task {

    @NullOrNotBlank
    private String course;

    // TODO validate and make it work
    private Date dueDate;

    @NullOrNotBlank
    private String details;

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
