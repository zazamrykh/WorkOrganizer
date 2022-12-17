package com.example.workorganizer;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SetDayForTaskList {
    public ComboBox<WeekDays> setDayForTask;
    public ObservableList<WeekDays> weekDays;

    public SetDayForTaskList() {
        weekDays = FXCollections.observableArrayList();
        weekDays.addAll(Arrays.asList(WeekDays.values()));
        setDayForTask = new ComboBox<>(weekDays);
    }

    public ComboBox<WeekDays> getComboBox() {
        return setDayForTask;
    }
}
