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

        TextField addTask = new TextField();
        TextField addDay = new TextField();
        TextField addTime = new TextField();
        TextField addDeadline = new TextField();
        TextField addMentor = new TextField();
        TextField addDescription = new TextField();

        addTask.setText("Enter task");
        addDay.setText("Enter day");
        addTime.setText("Time");
        addDeadline.setText("Deadline");
        addMentor.setText("Enter mentor");
        addDescription.setText("Add specific descriptions");

        addTask.setPrefWidth(120);
        addDay.setPrefWidth(75);
        addTime.setPrefWidth(65);
        addDeadline.setPrefWidth(65);
        addMentor.setPrefWidth(120);
        addDescription.setPrefWidth(120);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            tableChronology.getData().add(new Task(
                    addTask.getText(),
                    addDay.getText(),
                    addTime.getText(),
                    addDeadline.getText(),
                    addMentor.getText(),
                    addDescription.getText()));
            addTask.setText("Enter task");
            addDay.setText("Enter day");
            addTime.setText("Time");
            addDeadline.setText("Deadline");
            addMentor.setText("Enter mentor");
            addDescription.setText("Add specific descriptions");
        });

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            Task selectedItem = tableChronology.getTableChronology().getSelectionModel().getSelectedItem();
            tableChronology.getData().remove(selectedItem);
        });

        userEntryHBox.getChildren().addAll(addTask, addDay, addTime, addDeadline,
                addMentor, addDescription, addButton, removeButton);
    }

    public HBox getUserEntryHBox() {
        return userEntryHBox;
    }
}
