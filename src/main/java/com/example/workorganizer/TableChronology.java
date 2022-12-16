package com.example.workorganizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableChronology {
    private final TableView<Task> tableChronology = new TableView<>();
    private final boolean isMainSceneTableChronology;
    private final ObservableList<Task> data =
            FXCollections.observableArrayList(
                    new Task("Занятие с учеником", "Воскресенье",
                            "11:00am", "Еженедельно", "Егор", "Термодинамика"),
                    new Task("Занятие с учеником", "Воскресенье",
                            "20:00am", "Еженедельно", "Настя", "Механика"));

    public TableChronology(boolean isMainSceneTableChronology){
        this.isMainSceneTableChronology = isMainSceneTableChronology;
        List<TableColumn<Task, String>> columns = createAndGetColumns();
        tableChronology.setEditable(true);
        tableChronology.setItems(data);
        for (TableColumn<Task, String> column : columns) {
            tableChronology.getColumns().add(column);
        }
        tableChronology.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public TableView<Task> getTableChronology() {
        return tableChronology;
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

        if (isMainSceneTableChronology){
            dayCol.setEditable(false);
            taskCol.setEditable(false);
            allTimeCol.setEditable(false);
            timeLeftCol.setEditable(false);

            return new ArrayList<>(Arrays.asList(dayCol, taskCol, allTimeCol,
                    completedCol, timeLeftCol, descriptionCol));
        }

        return new ArrayList<>(Arrays.asList(taskCol, allTimeCol,
                completedCol, descriptionCol));
    }
}


