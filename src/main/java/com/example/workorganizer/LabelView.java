package com.example.workorganizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class LabelView {
    private final HBox hBox;
    private final Insets insets;
    private final Font quoteFont;
    private final Label quoteLabel;
    private final List<String> quotes = new ArrayList<>();

    public LabelView(String labelText, Font font, Insets insets, boolean hasQuote){
        this.insets = insets;
        this.quoteFont = new Font(font.getSize()/1.5);

        if (hasQuote) {
            try {
                FileReader fileReader = new FileReader("media//quotes.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    if (!line.equals("")){
                        quotes.add(line);
                    }
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Label mainLabel = new Label(labelText);
        mainLabel.setFont(font);
        mainLabel.setPadding(insets);

        Font quoteFont = new Font(font.getSize()/1.5);
        Random rnd = new Random();
        quoteLabel = new Label(quotes.get(rnd.nextInt(quotes.size())));
        quoteLabel.setFont(quoteFont);
        quoteLabel.setPadding(insets);

        hBox = new HBox();
        hBox.getChildren().addAll(mainLabel, quoteLabel);
        hBox.setSpacing(10);
    }

    public HBox getMainLabel() {
        return hBox;
    }

    public void refreshQuoteLabel() {
        Random rnd = new Random();
        quoteLabel.setText(quotes.get(rnd.nextInt(quotes.size())));
        quoteLabel.setFont(quoteFont);
        quoteLabel.setPadding(insets);
    }
}
