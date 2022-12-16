package com.example.workorganizer;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LabelView {
    private final Label label;
    public LabelView(String labelText, Font font, Insets insets){
        label = new Label(labelText);
        label.setFont(font);
        label.setPadding(insets);
    }

    public Label getLabel() {
        return label;
    }
}
