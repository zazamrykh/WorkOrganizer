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

        final LabelView labelMainScene = new LabelView("Work Organizer", new Font("Arial", 18),
                new Insets(10, 10, 10, 10), true);
        final LabelView labelAddTaskScene = new LabelView("Work Organizer", new Font("Arial", 18),
                new Insets(10, 10, 10, 10), true);

        TopPanel topPanelMainScene = new TopPanel("File", primaryStage, addTasksScene, labelMainScene,
                new MenuItem[] {new MenuItem("Open..."),
                        new MenuItem("Save As..."),
                        new MenuItem("Print..."),
                        new MenuItem("Exit")});

        TopPanel topPanelAddTaskScene = new TopPanel("File", primaryStage, addTasksScene, labelAddTaskScene,
                new MenuItem[] {new MenuItem("Open..."),
                        new MenuItem("Save As..."),
                        new MenuItem("Print..."),
                        new MenuItem("Exit")});

        TableChronology tableChronologyMainScene = new TableChronology(true);
        TableChronology tableChronologyAddTasksScene = new TableChronology(false);

        AddTaskPanel addTaskPanel = new AddTaskPanel(tableChronologyMainScene);

        OrganizeTaskPanel organizeTaskPanel = new OrganizeTaskPanel(primaryStage, mainScene,
                tableChronologyAddTasksScene, tableChronologyMainScene, labelMainScene);


        allItemsMainScene.getChildren().addAll(topPanelMainScene.getMenuBar(), labelMainScene.getMainLabel(),
                tableChronologyMainScene.getTableView(), addTaskPanel.getUserEntryHBox());

        allItemsAddTasksScene.getChildren().addAll(topPanelAddTaskScene.getMenuBar(), labelAddTaskScene.getMainLabel(),
                tableChronologyAddTasksScene.getTableView(), organizeTaskPanel.getOrganizeTaskHBox());

        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Work Organizer");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
