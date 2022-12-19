package com.example.workorganizer;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddTaskPanel {
    private final HBox userEntryHBox = new HBox();

    public AddTaskPanel(TableChronology tableChronology) {
        userEntryHBox.setSpacing(8);
        userEntryHBox.setPadding(new Insets(10, 10, 10, 10));

        SetDayForTaskList addDay = new SetDayForTaskList();
        ComboBox<WeekDays> addDayComboBox = addDay.getComboBox();

        TextField addTask = new TextField();
        TextField addAllTime = new TextField();
        TextField addCompleted = new TextField();
        TextField addDescription = new TextField();

        addDayComboBox.setValue(WeekDays.MONDAY);
        addTask.setText("Enter task");
        addAllTime.setText("Enter all time");
        addCompleted.setText("Completed");
        addDescription.setText("Add specific descriptions");

        addTask.setPrefWidth(170);
        addDayComboBox.setPrefWidth(100);
        addAllTime.setPrefWidth(100);
        addCompleted.setPrefWidth(100);
        addDescription.setPrefWidth(200);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            if (!TableChronology.isNumeric(addAllTime.getText())){
                Warning wrongValueCompleted = new Warning("Wrong value of all time");
                wrongValueCompleted.show();
                return;
            }

            double addAllTimeDouble = Double.parseDouble(addAllTime.getText());
            if (addAllTimeDouble < 0) {
                Warning wrongValueCompleted = new Warning("Wrong value of all time");
                wrongValueCompleted.show();
                return;
            }

            if (!TableChronology.isNumeric(addCompleted.getText())){
                Warning wrongValueCompleted = new Warning("Wrong value of completed");
                wrongValueCompleted.show();
                return;
            }

            double addCompletedDouble = Double.parseDouble(addCompleted.getText());
            if (addCompletedDouble < 0 || addCompletedDouble > 100) {
                Warning wrongValueCompleted = new Warning("Wrong value of completed");
                wrongValueCompleted.show();
                return;
            }

            tableChronology.getData().add(new Task(
                    addDayComboBox.getValue().toString(),
                    addTask.getText(),
                    addAllTime.getText(),
                    addCompleted.getText(),
                    addDescription.getText()));
            addDayComboBox.setValue(WeekDays.MONDAY);
            addTask.setText("Enter task");
            addAllTime.setText("Enter all time");
            addCompleted.setText("Completed");
            addDescription.setText("Add specific descriptions");
            tableChronology.refreshData();
        });

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            Task selectedItem = tableChronology.getTableView().getSelectionModel().getSelectedItem();
            tableChronology.getData().remove(selectedItem);
        });

        userEntryHBox.getChildren().addAll(addDayComboBox, addTask, addAllTime, addCompleted,
                addDescription, addButton, removeButton);
    }

    public HBox getUserEntryHBox() {
        return userEntryHBox;
    }
}
