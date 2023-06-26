package com.pensumeditor.gui;

import com.pensumeditor.data.Subject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SubjectItemController {

    @FXML
    private Label codeLabel;
    @FXML
    private Pane colorPane;
    @FXML
    private Label creditsLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label compLabel;
    @FXML
    private Label groupLabel;

    private String colorCode;

    public int getSubjectCode() {
        if (codeLabel.getText().equals("Codigo")) {
            return 0;
        }
        return Integer.parseInt(codeLabel.getText());
    }

    public void setSubjectColor(String colorCode) {
        colorPane.setStyle(colorPane.getStyle() + "-fx-background-color: #" + colorCode + ";");
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setSubjectData(Subject subject, String colorCode) {
        codeLabel.setText(Integer.toString(subject.getCode()));
        creditsLabel.setText(Integer.toString(subject.getCredits()));
        nameLabel.setText(subject.getName());
        setSubjectColor(colorCode);
    }

}
