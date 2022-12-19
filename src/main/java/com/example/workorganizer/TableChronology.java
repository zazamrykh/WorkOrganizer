package com.example.workorganizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableChronology {
    private final TableView<Task> tableView = new TableView<>();
    private final boolean isMainSceneTableChronology;
    private ObservableList<Task> data = FXCollections.observableArrayList();

    public TableChronology(boolean isMainSceneTableChronology){
        this.isMainSceneTableChronology = isMainSceneTableChronology;
        List<TableColumn<Task, String>> columns = createAndGetColumns();
        tableView.setEditable(true);

        tableView.setItems(data);
        for (TableColumn<Task, String> column : columns) {
            tableView.getColumns().add(column);
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public TableView<Task> getTableView() {
        return tableView;
    }

    public ObservableList<Task> getData() {
        return data;
    }

    private void setColumnProperties(TableColumn<Task, String> column, String columnName){
        column.setCellValueFactory(
                new PropertyValueFactory<>(column.getText()));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        switch (columnName) {
            case "Day" -> column.setOnEditCommit(
                    t -> {
                        String newValueString = t.getNewValue();
                        if (isWeekDay(newValueString)) {
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setDay(newValueString);
                        } else {
                            Warning warning = new Warning("Wrong value of day");
                            warning.show();
                        }
                        tableView.refresh();
                    }
            );
            case "Task" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTask(t.getNewValue())
            );
            case "AllTime" -> column.setOnEditCommit(
                t -> {
                    String newValueString = t.getNewValue();
                    if (isNumeric(newValueString)) {
                        double newValue = Double.parseDouble(t.getNewValue());
                        if (newValue < 0) {
                            Warning warning = new Warning("Wrong value of all time");
                            warning.show();
                            tableView.refresh();
                        } else {
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setAllTime(newValueString);
                            refreshData();
                        }
                    } else {
                        Warning warning = new Warning("Wrong value of day");
                        warning.show();
                        tableView.refresh();
                    }
                }
            );
            case "Completed" -> column.setOnEditCommit(
                t -> {
                    String newValueString = t.getNewValue();
                    if (isNumeric(newValueString)) {
                        double newValue = Double.parseDouble(t.getNewValue());
                        if (newValue < 0 || newValue > 100) {
                            Warning warning = new Warning("Wrong value of completed");
                            warning.show();
                            tableView.refresh();
                        } else {
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setCompleted(newValueString);
                            refreshData();
                        }
                    } else {
                        Warning warning = new Warning("Wrong value of day");
                        warning.show();
                        tableView.refresh();
                    }
                }
            );
            case "TimeLeft" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTimeLeft(t.getNewValue())
            );
            case "Description" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDescription(t.getNewValue())
            );
        }
    }

    private boolean isWeekDay(String newValueString) {
        for (WeekDays weekDay: WeekDays.getValues()) {
            if (newValueString.equals(weekDay.toString())) {
                return true;
            }
        }
        return false;
    }

    private List<TableColumn<Task, String>> createAndGetColumns() {
        TableColumn<Task, String> dayCol = new TableColumn<>("Day");
        TableColumn<Task, String> taskCol = new TableColumn<>("Task");
        TableColumn<Task, String> allTimeCol = new TableColumn<>("AllTime");
        TableColumn<Task, String> completedCol = new TableColumn<>("Completed");
        TableColumn<Task, String> timeLeftCol = new TableColumn<>("TimeLeft");
        TableColumn<Task, String> descriptionCol = new TableColumn<>("Description");

        dayCol.setMinWidth(70);
        taskCol.setMinWidth(150);
        allTimeCol.setMinWidth(60);
        completedCol.setMinWidth(60);
        timeLeftCol.setMinWidth(120);
        descriptionCol.setMinWidth(200);

        setColumnProperties(dayCol, "Day");
        setColumnProperties(taskCol, "Task");
        setColumnProperties(allTimeCol, "AllTime");
        setColumnProperties(completedCol, "Completed");
        setColumnProperties(timeLeftCol, "TimeLeft");
        setColumnProperties(descriptionCol, "Description");

        Comparator<String> dayComparator = (d1, d2) -> getDayNumber(d2) - getDayNumber(d1);

        dayCol.setComparator(dayComparator);
        if (isMainSceneTableChronology){
            timeLeftCol.setEditable(false);
            return new ArrayList<>(Arrays.asList(dayCol, taskCol, allTimeCol,
                    completedCol, timeLeftCol, descriptionCol));
        }

        return new ArrayList<>(Arrays.asList(taskCol, allTimeCol,
                completedCol, descriptionCol));
    }

    public void removeData() {
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    private int getDayNumber(String day) {
        int dayNumber;
        switch (day) {
            case "Monday" -> dayNumber = 0;
            case "Tuesday" -> dayNumber = 1;
            case "Wednesday" -> dayNumber = 2;
            case "Thursday" -> dayNumber = 3;
            case "Friday" -> dayNumber = 4;
            case "Saturday" -> dayNumber = 5;
            case "Sunday" -> dayNumber = 6;
            default -> dayNumber = -1;
        }
        return dayNumber;
    }

    public void refreshData() {
        for (Task task :data) {
            double completed = Double.parseDouble(task.getCompleted());
            double allTime = Double.parseDouble(task.getAllTime());
            double timeLeft = allTime * (1 - completed / 100);
            task.setTimeLeft(String.valueOf(timeLeft));
        }
        tableView.refresh();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
