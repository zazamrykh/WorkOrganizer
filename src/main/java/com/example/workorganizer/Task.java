package com.example.workorganizer;

import javafx.beans.property.SimpleStringProperty;

public class Task {

    private WeekDays weekDay;
    private final SimpleStringProperty day;
    private final SimpleStringProperty task;
    private final SimpleStringProperty allTime;
    private final SimpleStringProperty completed;
    private final SimpleStringProperty timeLeft;
    private final SimpleStringProperty description;

    public Task(String day, String task, String allTime, String completed, String timeLeft, String description) {
        this.day = new SimpleStringProperty(day);
        this.task = new SimpleStringProperty(task);
        this.allTime = new SimpleStringProperty(allTime);
        this.completed = new SimpleStringProperty(completed);
        this.timeLeft = new SimpleStringProperty(timeLeft);
        this.description = new SimpleStringProperty(description);
    }

    Task(String day, String task, String allTime, String completed, String description) {
        this.day = new SimpleStringProperty(day);
        this.task = new SimpleStringProperty(task);
        this.allTime = new SimpleStringProperty(allTime);
        this.completed = new SimpleStringProperty(completed);
        this.timeLeft = new SimpleStringProperty("-1");
        this.description = new SimpleStringProperty(description);
    }


    public Task() {
        this.day = new SimpleStringProperty("Enter day");
        this.task = new SimpleStringProperty("Enter task");
        this.allTime = new SimpleStringProperty("0");
        this.completed = new SimpleStringProperty("0");
        this.timeLeft = new SimpleStringProperty("Time left");
        this.description = new SimpleStringProperty("Specific description");
    }

    public String getDay() {
        return day.get();
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public String getAllTime() {
        return allTime.get();
    }

    public void setAllTime(String allTime) {
        this.allTime.set(allTime);
    }

    public String getCompleted() {
        return completed.get();
    }

    public void setCompleted(String completed) {
        this.completed.set(completed);
    }

    public String getTimeLeft() {
        return timeLeft.get();
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft.set(timeLeft);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String deadline) {
        this.description.set(deadline);
    }

}