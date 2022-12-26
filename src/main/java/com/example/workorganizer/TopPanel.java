package com.example.workorganizer;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TopPanel {
    private final MenuBar menuBar = new MenuBar();
    private final FileChooser fileChooser = new FileChooser();
    private final Desktop desktop = Desktop.getDesktop();
    private final ObservableList<Task> tasks;
    private int resolutionChanger = 1;

    public TopPanel(String menuLabel, Stage stage, TableChronology tableChronology, Scene addTasksScene,LabelView labelView,
                    MenuItem[] menuItems) {
        this.tasks = tableChronology.getData();
        Label response = new Label("Menu");
        Menu fileMenu = new Menu("_" + menuLabel);
        for (MenuItem menuItem : menuItems) {
            fileMenu.getItems().add(menuItem);
        }
        menuBar.getMenus().add(fileMenu);

        Menu Task = new Menu("_" + "Organizer");
        MenuItem addTask = new MenuItem("Make week plan");
        Task.getItems().add(addTask);
        menuBar.getMenus().add(Task);
        addTask.setOnAction(ae -> {
            labelView.refreshQuoteLabel();
            stage.setScene(addTasksScene);
            resolutionChanger *= (-1);
            stage.setWidth(stage.getWidth() + resolutionChanger);
        });

        Menu Quote = new Menu("_" + "Quote");
        MenuItem refresh = new MenuItem("New");
        Quote.getItems().add(refresh);
        refresh.setOnAction(ae -> labelView.refreshQuoteLabel());
        menuBar.getMenus().add(Quote);

        EventHandler<ActionEvent> MenuHandler =
                ae -> {
                    String name = ((MenuItem) ae.getTarget()).getText();
                    if ("Exit".equals(name)) {
                        Platform.exit();
                    }
                    response.setText(name + " selected");
                };

        for (MenuItem menuItem : menuItems) {
            menuItem.setOnAction(MenuHandler);
            String name =  menuItem.getText();
            switch (name) {
                case "Exit" -> menuItem.setOnAction(ae -> Platform.exit());
                case "Open..." -> menuItem.setOnAction(
                        e -> {
                            File opensFile = fileChooser.showOpenDialog(stage);
                            tasks.clear();
                            if (opensFile != null) {
                                openFile(opensFile);
                            }
                        });
                case "Save As..." -> menuItem.setOnAction(e -> {
                    File savesFile = fileChooser.showSaveDialog(stage);
                    if (savesFile != null) {
                        saveFile(savesFile);
                    }
                });
            }
        }
    }

    public Node getMenuBar() {
        return menuBar;
    }

    private void openFile(File opensFile) {

        try {
            FileReader fileReader = new FileReader(opensFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                if (!line.equals("")){
                    addTask(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTask(String taskString) {
        String[] values = taskString.split("\t");
        Task readTask = new Task(values[0], values[1], values[2], values[3], values[4], values[5]);
        tasks.add(readTask);
    }

    private void saveFile(File savesFile) {
        try (FileWriter writer = new FileWriter(savesFile, false)) {
            for (Task task : tasks) {
                writer.write(task.getDay() + " " + "\t"
                        + task.getTask() + " " + "\t"
                        + task.getAllTime() + " " +
                        "\t" + task.getCompleted() + " "
                        + "\t" + task.getTimeLeft() + " "
                        + "\t" + task.getDescription() + " " + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
