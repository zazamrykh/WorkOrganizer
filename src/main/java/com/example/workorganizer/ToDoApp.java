package com.example.workorganizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ToDoApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox allItems = new VBox();
        Scene mainScene = new Scene(allItems);
        Scene addTasksScene = new Scene(new VBox());

        allItems.setSpacing(7);

        TopPanel topPanel = new TopPanel("File", primaryStage, mainScene, addTasksScene,
                new MenuItem[] {new MenuItem("Open..."),
                new MenuItem("Save As..."),
                new MenuItem("Print..."),
                new MenuItem("Exit")});

        final LabelView label = new LabelView("Todo List", new Font("Arial", 18),
                new Insets(10, 10, 10, 10));

        final TableView<Task> tableChron = new TableView<>();
        tableChron.setEditable(true);

        TableChronology tableChronology = new TableChronology();

        AddTaskPanel addTaskPanel = new AddTaskPanel(tableChronology);

        allItems.getChildren().addAll(topPanel.getMenuBar(), label.getLabel(),
                tableChronology.getTableChronology(), addTaskPanel.getUserEntryHBox());

        primaryStage.setWidth(750);
        primaryStage.setHeight(500);
        primaryStage.setTitle("Work Organizer");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
