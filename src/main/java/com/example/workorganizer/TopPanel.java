package com.example.workorganizer;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
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
    public TopPanel(String menuLabel, Stage stage, Scene mainScene, Scene addTasksScene, MenuItem[] menuItems) {
        Label response = new Label("Menu");
        Menu fileMenu = new Menu("_" + menuLabel);
        for (MenuItem menuItem : menuItems) {
            fileMenu.getItems().add(menuItem);
        }
        menuBar.getMenus().add(fileMenu);

        Menu Task = new Menu("_" + "Task");
        MenuItem addTask = new MenuItem("Add");
        Task.getItems().add(addTask);
        menuBar.getMenus().add(Task);
        addTask.setOnAction(ae -> {
            stage.setScene(addTasksScene);
        });

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
                case "Print..." -> menuItem.setOnAction(ae -> {
                    PrinterJob job = PrinterJob.createPrinterJob();
                    if (job != null) {
                        boolean showPrintDialog = job.showPrintDialog(stage.getOwner());
                        if (showPrintDialog) {
                            job.endJob();
                        }
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
            desktop.open(opensFile);
        } catch (IOException ex) {
            Logger.getLogger(
                    ToDoApp.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

    private void saveFile(File savesFile) {
        try {
            desktop.open(savesFile);
        } catch (IOException ex) {
            Logger.getLogger(
                    ToDoApp.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
}
