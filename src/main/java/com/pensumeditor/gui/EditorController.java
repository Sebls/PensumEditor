package com.pensumeditor.gui;

import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.data.Subject;
import com.pensumeditor.data.SubjectItemInfo;
import com.pensumeditor.datastructures.linear.ArrayList;
import com.pensumeditor.pensums.IngSistemas;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    // Variables globales
    private int semesterNumber;
    private int semesterSubjects;
    private ArrayList<PositionSubject> SubjectArray;

    private ArrayList<SubjectItemInfo[]> SubjectItemMatrix;

    @FXML
    private VBox PaneScroll;
    @FXML
    private AnchorPane SemesterBar;
    @FXML
    private Pane MenuPane;
    @FXML
    private AnchorPane InfoPane;
    @FXML
    private AnchorPane PensumPane;

    private int option = 0;

    private int distance_x = 200;
    private int distance_y = 130;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IngSistemas Pensum = new IngSistemas();
        SubjectArray = Pensum.GeneratePensum();
        semesterNumber = 10;
        semesterSubjects = 6;
        adjustPensumSize();
        createSubjectItemMatrix();
        createSemesterBar();
        updateSubjects();
        occultAll();
    }

    public void updateSubjects() {
        PensumPane.getChildren().clear();
        int SubjectNumber = 0;
        for(int i=0; i<SubjectArray.getSize(); i++) {
            PositionSubject positionSubject = SubjectArray.get(i);
            int position_x = positionSubject.getColumn();
            int position_y = positionSubject.getRow();
            if (position_x < semesterNumber && position_y < semesterSubjects) {
                Pane SubjectItem = SubjectItemMatrix.get(position_x)[position_y].getSubjectItem();
                SubjectItemController Controller = SubjectItemMatrix.get(position_x)[position_y].getController();
                PensumPane.getChildren().add(SubjectItem);
                SubjectItem.addEventHandler(MouseEvent.MOUSE_CLICKED, onSubjectClicked);
                SubjectItem = (Pane) PensumPane.getChildren().get(SubjectNumber);
                SubjectItem.setVisible(true);
                SubjectItem.setLayoutX(distance_x * position_x + 20);
                SubjectItem.setLayoutY(distance_y * position_y + 20);
                Controller.setSubjectData(positionSubject.getSubject());
                SubjectNumber++;
            }
        }
    }

    public void adjustPensumSize() {
        PaneScroll.setPrefWidth(155*semesterNumber+10);
        PensumPane.setPrefWidth(155*semesterNumber+10);
        PensumPane.setPrefHeight(135*semesterSubjects);
        SemesterBar.setPrefWidth(155*semesterNumber+10);
    }

    public void createSemesterBar() {
        for (int i=0; i<semesterNumber; i++) {
            Label semesterLabel = new Label("Semestre " + getRomanNumber(i + 1));
            semesterLabel.setPrefWidth(155);
            semesterLabel.setPrefHeight(40);
            semesterLabel.setAlignment(Pos.CENTER);
            SemesterBar.getChildren().add(semesterLabel);
            semesterLabel = (Label) SemesterBar.getChildren().get(i);
            semesterLabel.setLayoutX(distance_x*i + 20);
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
        Label codeLabel = (Label) ((Pane) pane.getChildren().get(1)).getChildren().get(0);
        int code = Integer.parseInt(codeLabel.getText());
        PositionSubject subject = SubjectArray.get(SubjectArray.search(new PositionSubject(code)));
        //int position_x = (int) Math.round((pane.getLayoutX()-20)/distance_x);
        //int position_y = (int) Math.round((pane.getLayoutY()-20)/distance_y);
        // Option:
        // 0 = Show info
        // 1 = Delete Subject
        switch (option) {
            case 0:
                displayInfo(subject);
                break;
            case 1:
                deleteSubject(subject);
                option = 0;
                break;
            case 2:
                try {
                    replaceSubject(subject);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    };

    @FXML
    public void setDeleteOption() {
        option = 1;
        occultAll();
        MenuPane.setVisible(false);
    }

    @FXML
    public void setReplaceOption() {
        option = 2;
        occultAll();
        MenuPane.setVisible(false);
    }

    private Scene scene;
    @FXML
    public void addSubject() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SubjectSelector.fxml"));
        VBox selector = fxmlLoader.load();
        SubjectSelectorController ssc = fxmlLoader.getController();
        ssc.loadSubjectsArray(SubjectArray, semesterNumber);
        scene = new Scene(selector, 1100, 600);
        Stage stage = new Stage();
        stage.setTitle("Subject Selector");
        stage.setScene(scene);
        stage.showAndWait();

        ssc.openPositionSelector();
        Subject subject = (Subject) ssc.getSelectedSubject();
        int[] position = ssc.getPosition();

        SubjectArray.add(new PositionSubject(position[0], position[1], subject));

        updateSubjects();
    }

    @FXML
    public void addSemester() {
        SubjectItemMatrix.add(new SubjectItemInfo[semesterSubjects]);
        for (int j=0; j<semesterSubjects; j++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("SubjectItem.fxml"));
                Pane SubjectItem = fxmlLoader.load();
                SubjectItem.setVisible(false);
                SubjectItemController Controller = fxmlLoader.getController();
                SubjectItemInfo subjectItemInfo = new SubjectItemInfo(SubjectItem, Controller);
                SubjectItemMatrix.get(SubjectItemMatrix.getSize() - 1)[j] = subjectItemInfo;
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        }
        Label semesterLabel = new Label("Semestre " + getRomanNumber(semesterNumber + 1));
        semesterLabel.setPrefWidth(155);
        semesterLabel.setPrefHeight(40);
        semesterLabel.setAlignment(Pos.CENTER);
        SemesterBar.getChildren().add(semesterLabel);
        semesterLabel = (Label) SemesterBar.getChildren().get(semesterNumber);
        semesterLabel.setLayoutX(distance_x*semesterNumber + 20);
        semesterNumber ++;
        adjustPensumSize();
    }

    @FXML
    public void removeSemester() {
        semesterNumber --;
        SubjectItemInfo[] SubjectItems = SubjectItemMatrix.popBack();
        for (SubjectItemInfo subjectItemInfo : SubjectItems) {
            int code = subjectItemInfo.getController().getSubjectCode();
            int index = SubjectArray.search(new PositionSubject(code));
            if (index != -1){
                SubjectArray.remove(index);
            }
        }
        updateSubjects();
        SemesterBar.getChildren().remove(semesterNumber);
        adjustPensumSize();
    }


    @FXML
    private Label InfoNameLabel;
    @FXML
    private Label InfoCodeLabel;
    @FXML
    private Label InfoCreditsLabel;
    @FXML
    private Label InfoComponentLabel;
    @FXML
    private Label InfoGroupLabel;
    @FXML
    private Label InfoPrerequisiteLabel;

    public void displayInfo(PositionSubject positionSubject) {
        InfoPane.setVisible(true);
        Subject subject = positionSubject.getSubject();
        InfoNameLabel.setText("Nombre: " + subject.getName());
        InfoCodeLabel.setText("Código: " + Integer.toString(subject.getCode()));
        InfoCreditsLabel.setText("Creditos: " + Integer.toString(subject.getCredits()));
        InfoGroupLabel.setText("Group: " + subject.getGroup());
        InfoComponentLabel.setText("Componente: " + subject.getComponent());
        InfoPrerequisiteLabel.setText("Prerrequisitos: " + subject.getPrerequisite());
    }

    public void deleteSubject(PositionSubject positionSubject) {
        int index = SubjectArray.search(positionSubject);
        SubjectArray.remove(index);
        updateSubjects();
        MenuPane.setVisible(true);
    }

    public void replaceSubject(PositionSubject positionSubject) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SubjectSelector.fxml"));
        VBox selector = fxmlLoader.load();
        SubjectSelectorController ssc = fxmlLoader.getController();
        ssc.loadSubjectsArray(SubjectArray, semesterNumber);
        scene = new Scene(selector, 1100, 600);
        Stage stage = new Stage();
        stage.setTitle("Subject Selector");
        stage.setScene(scene);
        stage.showAndWait();

        Subject subject = (Subject) ssc.getSelectedSubject();
        positionSubject.setSubject(subject);

        updateSubjects();
        MenuPane.setVisible(true);
        option = 0;
    }

    public void occultWindow(MouseEvent event) {
        Pane window = (Pane) ( (ImageView) event.getSource() ).getParent();
        window.setVisible(false);
    }

    public void occultAll() {
        InfoPane.setVisible(false);
    }

    // From André Kramer Orten - https://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java
    public String getRomanNumber(int number) {
        return "I".repeat(number)
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L");
    }

}
