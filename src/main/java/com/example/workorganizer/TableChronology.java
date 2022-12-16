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
    private final ObservableList<Task> data =
            FXCollections.observableArrayList(
                    new Task("Занятие с учеником", "Воскресенье",
                            "11:00am", "Еженедельно", "Егор", "Термодинамика"),
                    new Task("Занятие с учеником", "Воскресенье",
                            "20:00am", "Еженедельно", "Настя", "Механика"));

    public TableChronology(){
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
            case "Task" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTask(t.getNewValue())
            );
            case "Day" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDay(t.getNewValue())
            );
            case "Time" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTime(t.getNewValue())
            );
            case "Deadline" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDeadline(t.getNewValue())
            );
            case "Mentor" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setMentor(t.getNewValue())
            );
            case "Description" -> column.setOnEditCommit(
                    t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDescription(t.getNewValue())
            );
        }
    }

    private List<TableColumn<Task, String>> createAndGetColumns() {
        TableColumn<Task, String> taskCol = new TableColumn<>("Task");
        TableColumn<Task, String> dayCol = new TableColumn<>("Day");
        TableColumn<Task, String> timeCol = new TableColumn<>("Time");
        TableColumn<Task, String> deadlineCol = new TableColumn<>("Deadline");
        TableColumn<Task, String> mentorCol = new TableColumn<>("Mentor");
        TableColumn<Task, String> descriptionCol = new TableColumn<>("Description");

        taskCol.setMinWidth(150);
        dayCol.setMinWidth(70);
        timeCol.setMinWidth(60);
        deadlineCol.setMinWidth(60);
        mentorCol.setMinWidth(120);
        descriptionCol.setMinWidth(200);

        setColumnProperties(taskCol, "Task");
        setColumnProperties(dayCol, "Day");
        setColumnProperties(timeCol, "Time");
        setColumnProperties(deadlineCol, "Deadline");
        setColumnProperties(mentorCol, "Mentor");
        setColumnProperties(descriptionCol, "Description");

        return new ArrayList<>(Arrays.asList(taskCol, dayCol, timeCol,
                deadlineCol, mentorCol, descriptionCol));
    }
}


