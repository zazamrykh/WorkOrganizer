package com.example.workorganizer;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddTaskPanel {
    private final HBox userEntryHBox = new HBox();

    public AddTaskPanel(TableChronology tableChronology) {
        userEntryHBox.setSpacing(8);
        userEntryHBox.setPadding(new Insets(10, 10, 10, 10));

        TextField addDay = new TextField();
        TextField addTask = new TextField();
        TextField addAllTime = new TextField();
        TextField addCompleted = new TextField();
        TextField addTimeLeft = new TextField();
        TextField addDescription = new TextField();

        addDay.setText("Enter day");
        addTask.setText("Enter task");
        addAllTime.setText("Enter all time");
        addCompleted.setText("Completed");
        addTimeLeft.setText("Time left");
        addDescription.setText("Add specific descriptions");

        addTask.setPrefWidth(120);
        addDay.setPrefWidth(75);
        addAllTime.setPrefWidth(65);
        addCompleted.setPrefWidth(65);
        addTimeLeft.setPrefWidth(120);
        addDescription.setPrefWidth(120);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            tableChronology.getData().add(new Task(
                    addDay.getText(),
                    addTask.getText(),
                    addAllTime.getText(),
                    addCompleted.getText(),
                    addTimeLeft.getText(),
                    addDescription.getText()));
            addDay.setText("Enter day");
            addTask.setText("Enter task");
            addAllTime.setText("Enter all time");
            addCompleted.setText("Completed");
            addTimeLeft.setText("Time left");
            addDescription.setText("Add specific descriptions");
        });

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            Task selectedItem = tableChronology.getTableChronology().getSelectionModel().getSelectedItem();
            tableChronology.getData().remove(selectedItem);
        });

        userEntryHBox.getChildren().addAll(addDay, addTask, addAllTime, addCompleted,
                addTimeLeft, addDescription, addButton, removeButton);
    }

    public HBox getUserEntryHBox() {
        return userEntryHBox;
    }
}
