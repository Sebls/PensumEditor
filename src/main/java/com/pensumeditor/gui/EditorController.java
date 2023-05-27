package com.pensumeditor.gui;

import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.data.Subject;
import com.pensumeditor.data.SubjectItemInfo;
import com.pensumeditor.pensums.IngSistemas;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    // Variables globales
    private int semesterNumber;
    private int semesterSubjects;
    private ArrayList<PositionSubject> SubjectArray;

    private ArrayList<SubjectItemInfo[]> SubjectItemMatrix;

    @FXML
    private VBox menuBar;
    @FXML
    private AnchorPane PensumPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IngSistemas Pensum = new IngSistemas();
        SubjectArray = Pensum.GeneratePensum();
        semesterNumber = 10;
        semesterSubjects = 6;
        createSubjectItemMatrix();
    }

    public void updateSubjects() {
        int distance_x = 200;
        int distance_y = 130;
        PensumPane.getChildren().clear();
        int SubjectNumber = 0;
        for(PositionSubject positionSubject : SubjectArray) {
            for (int[] position : positionSubject.getPositions()) {
                Pane SubjectItem = SubjectItemMatrix.get(position[0])[position[1]].getSubjectItem();
                SubjectItemController Controller = SubjectItemMatrix.get(position[0])[position[1]].getController();
                PensumPane.getChildren().add(SubjectItem);
                SubjectItem.addEventHandler(MouseEvent.MOUSE_CLICKED, onSubjectClicked);
                SubjectItem = (Pane) PensumPane.getChildren().get(SubjectNumber);
                SubjectItem.setVisible(true);
                SubjectItem.setLayoutX(distance_x *position[0]+20);
                SubjectItem.setLayoutY(distance_y *position[1]);
                Controller.setSubjectData(positionSubject.getSubject());
                SubjectNumber ++;
            }
        }
    }

    public void createSubjectItemMatrix() {
        SubjectItemMatrix = new ArrayList<>();
        for (int i=0; i<semesterNumber; i++) {
            SubjectItemMatrix.add(new SubjectItemInfo[semesterSubjects]);
            for (int j=0; j<semesterSubjects; j++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("SubjectItem.fxml"));
                    Pane SubjectItem = fxmlLoader.load();
                    SubjectItem.setVisible(false);
                    SubjectItemController Controller = fxmlLoader.getController();
                    SubjectItemInfo subjectItemInfo = new SubjectItemInfo(SubjectItem, Controller);
                    SubjectItemMatrix.get(i)[j] = subjectItemInfo;
                } catch (IOException ex) {
                    //ex.printStackTrace();
                }
            }
        }
    }

    EventHandler<MouseEvent> onSubjectClicked = event -> {
        Pane pane = (Pane) event.getSource();
        Pane codePane = (Pane) pane.getChildren().get(1);
        Label codeLabel = (Label) codePane.getChildren().get(0);
        int code = Integer.parseInt(codeLabel.getText());
        Subject subject = SubjectArray.get(SubjectArray.indexOf(new PositionSubject(code))).getSubject();
        System.out.println("Subject: " + subject);
    };

}
