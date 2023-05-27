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


    public void setSubjectData(Subject subject) {
        codeLabel.setText(Integer.toString(subject.getCode()));
        creditsLabel.setText(Integer.toString(subject.getCredits()));
        nameLabel.setText(subject.getName());
    }

}
