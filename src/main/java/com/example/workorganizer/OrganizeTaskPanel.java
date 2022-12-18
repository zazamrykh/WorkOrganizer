package com.example.workorganizer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OrganizeTaskPanel {
    private int resolutionChanger = 1;
    private final HBox organizeTaskHBox;
    public OrganizeTaskPanel (Stage stage, Scene scene, TableChronology tableChronologyAddTasksScene,
                              TableChronology tableChronologyMainScene, LabelView labelView) {
        Button organizeTaskButton = new Button("Organize Tasks");
        organizeTaskButton.setOnAction(actionEvent -> {
            labelView.refreshQuoteLabel();
            stage.setScene(scene);
            resolutionChanger *= (-1);
            stage.setWidth(stage.getWidth() + resolutionChanger);
            TransferTasks.transfer(tableChronologyAddTasksScene, tableChronologyMainScene);
//            tableChronologyMainScene.getData().setAll(tableChronologyAddTasksScene.getData());
            tableChronologyAddTasksScene.refreshData();
        });

        Button addButton = new Button("Add task");
        addButton.setOnAction(e -> tableChronologyAddTasksScene.getData().add(new Task()));

        this.organizeTaskHBox = new HBox(organizeTaskButton, addButton);
        organizeTaskHBox.setSpacing(8);
        organizeTaskHBox.setPadding(new Insets(10, 10, 10, 10));
    }

    public HBox getOrganizeTaskHBox() {
        return organizeTaskHBox;
    }
}
