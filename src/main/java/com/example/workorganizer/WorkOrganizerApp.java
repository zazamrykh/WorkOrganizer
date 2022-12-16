package com.example.workorganizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WorkOrganizerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox allItemsMainScene = new VBox();
        VBox allItemsAddTasksScene = new VBox();
        Scene mainScene = new Scene(allItemsMainScene);
        Scene addTasksScene = new Scene(allItemsAddTasksScene);

        allItemsMainScene.setSpacing(7);
        allItemsAddTasksScene.setSpacing(7);

        TopPanel topPanelMainScene = new TopPanel("File", primaryStage, addTasksScene,
                new MenuItem[] {new MenuItem("Open..."),
                new MenuItem("Save As..."),
                new MenuItem("Print..."),
                new MenuItem("Exit")});

        TopPanel topPanelAddTaskScene = new TopPanel("File", primaryStage, addTasksScene,
                new MenuItem[] {new MenuItem("Open..."),
                        new MenuItem("Save As..."),
                        new MenuItem("Print..."),
                        new MenuItem("Exit")});

        final LabelView labelMainScene = new LabelView("Work Organizer", new Font("Arial", 18),
                new Insets(10, 10, 10, 10));
        final LabelView labelAddTaskScene = new LabelView("Work Organizer", new Font("Arial", 18),
                new Insets(10, 10, 10, 10));

        TableChronology tableChronologyMainScene = new TableChronology(true);
        TableChronology tableChronologyAddTasksScene = new TableChronology(false);

        AddTaskPanel addTaskPanel = new AddTaskPanel(tableChronologyMainScene);

        OrganizeTaskPanel organizeTaskPanel = new OrganizeTaskPanel(primaryStage, mainScene,
                tableChronologyAddTasksScene, tableChronologyMainScene);


        allItemsMainScene.getChildren().addAll(topPanelMainScene.getMenuBar(), labelMainScene.getLabel(),
                tableChronologyMainScene.getTableChronology(), addTaskPanel.getUserEntryHBox());

        allItemsAddTasksScene.getChildren().addAll(topPanelAddTaskScene.getMenuBar(),labelAddTaskScene.getLabel(),
                tableChronologyAddTasksScene.getTableChronology(), organizeTaskPanel.getOrganizeTaskHBox());

        primaryStage.setWidth(750);
        primaryStage.setHeight(500);
        primaryStage.setTitle("Work Organizer");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
