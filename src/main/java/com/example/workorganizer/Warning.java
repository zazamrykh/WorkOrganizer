package com.example.workorganizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Warning {
    private final Stage dialogStage;
    public Warning(String wrongMessage) {
        dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Button okButton = new Button("Ok.");
        okButton.setOnAction(actionEvent -> dialogStage.close());
        VBox vbox = new VBox(new Text(wrongMessage), okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(7);
        vbox.setPadding(new Insets(15));

        dialogStage.setMinWidth(250);
        dialogStage.setMinWidth(250);
        dialogStage.setScene(new Scene(vbox));
    }

    public void show() {
        dialogStage.show();
    }
}
