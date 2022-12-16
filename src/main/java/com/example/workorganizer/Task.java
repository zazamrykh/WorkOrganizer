package com.example.workorganizer;

import javafx.beans.property.SimpleStringProperty;

public class Task {

    private final SimpleStringProperty task;
    private final SimpleStringProperty day;
    private final SimpleStringProperty time;
    private final SimpleStringProperty deadline;
    private final SimpleStringProperty mentor;
    private final SimpleStringProperty description;

    Task(String task, String day, String time, String deadline, String mentor, String description) {

        this.task = new SimpleStringProperty(task);
        this.day = new SimpleStringProperty(day);
        this.time = new SimpleStringProperty(time);
        this.deadline = new SimpleStringProperty(deadline);
        this.mentor = new SimpleStringProperty(mentor);
        this.description = new SimpleStringProperty(description);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public String getDay() {
        return day.get();
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDeadline() {
        return deadline.get();
    }

    public void setDeadline(String deadline) {
        this.deadline.set(deadline);
    }

    public String getMentor() {
        return mentor.get();
    }

    public void setMentor(String mentor) {
        this.mentor.set(mentor);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String deadline) {
        this.description.set(deadline);
    }

}