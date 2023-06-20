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

    public int getSubjectCode() {
        if (codeLabel.getText().equals("Codigo")) {
            return 0;
        }
        return Integer.parseInt(codeLabel.getText());
    }

    public void setSubjectColor(String hex) {
        colorPane.setStyle(colorPane.getStyle() + "-fx-background-color: #" + hex + ";");
    }

    public void setSubjectData(Subject subject) {
        codeLabel.setText(Integer.toString(subject.getCode()));
        creditsLabel.setText(Integer.toString(subject.getCredits()));
        nameLabel.setText(subject.getName());
        switch (subject.getGroup()) {
            case "Matemáticas" -> setSubjectColor("99ccff");
            case "Probabilidad y Estadística" -> setSubjectColor("95b3d7");
            case "Física" -> setSubjectColor("ffffcc");
            case "Ciencias de la Computación" -> setSubjectColor("cccc00");
            case "Ciencias Económicas y Administrativas" -> setSubjectColor("a9d08e");
            case "Métodos y Tecnologías de Software" -> setSubjectColor("fff2cc");
            case "Infraestructura Computacional, de Comunicaciones y de Información" -> setSubjectColor("ffff66");
            case "Computación Visual" -> setSubjectColor("d8e4bc");
            case "Sistemas Inteligentes" -> setSubjectColor("b7dee7");
            case "Modelos, Sistemas, Optimización y Simulación" -> setSubjectColor("99cc00");
            case "Contexto Profesional y Proyectos de Ingeniería" -> setSubjectColor("99ccf0");
            case "TRABAJO DE GRADO" -> setSubjectColor("d9e1f2");
            case "Libre elección" -> setSubjectColor("e4e4e4");
            default -> setSubjectColor("b7dee8");
        }
    }

}
