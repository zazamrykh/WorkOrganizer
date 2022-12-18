package com.example.workorganizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDay(t.getNewValue())
            );
            case "Task" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTask(t.getNewValue())
            );
            case "AllTime" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAllTime(t.getNewValue())
            );
            case "Completed" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setCompleted(t.getNewValue())
            );
            case "TimeLeft" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTimeLeft(t.getNewValue())
            );
            case "Description" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDescription(t.getNewValue())
            );
        }
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
            dayCol.setEditable(false);
            allTimeCol.setEditable(false);
            timeLeftCol.setEditable(false);

            return new ArrayList<>(Arrays.asList(dayCol, taskCol, allTimeCol,
                    completedCol, timeLeftCol, descriptionCol));
        }

        return new ArrayList<>(Arrays.asList(taskCol, allTimeCol,
                completedCol, descriptionCol));
    }

    public void refreshData() {
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void setData(ObservableList<Task> data) {
        this.data = data;
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
}
