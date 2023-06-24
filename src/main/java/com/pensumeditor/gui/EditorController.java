package com.pensumeditor.gui;

import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.data.Subject;
import com.pensumeditor.data.SubjectItemInfo;
import com.pensumeditor.datastructures.linear.ArrayList;
import com.pensumeditor.datastructures.trees.AVLTree;
import com.pensumeditor.pensums.IngSistemas;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    // Global Variables
    private int semesterNumber;
    private int semesterSubjects;

    // Data Structures
    private AVLTree<PositionSubject> SubjectTree;
    private ArrayList<SubjectItemInfo[]> SubjectItemMatrix;

    // Visual Nodes
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

    // Control Variable
    private int option = 0;

    // Subject Distribution
    private final int distance_x = 230, distance_y = 130, space_x = 20, space_y = 20;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IngSistemas Pensum = new IngSistemas();
        SubjectTree = Pensum.GeneratePensum();
        semesterNumber = 10;
        semesterSubjects = 6;
        adjustPensumSize();
        createSubjectItemMatrix();
        createSemesterBar();
        updateSubjects();
        occultAll();
    }

    public void adjustPensumSize() {
        PaneScroll.setPrefWidth(155*semesterNumber+10);
        PensumPane.setPrefWidth(155*semesterNumber+10);
        PensumPane.setPrefHeight(135*semesterSubjects);
        SemesterBar.setPrefWidth(155*semesterNumber+10);
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
                    makeDraggable(SubjectItem);
                    SubjectItemController Controller = fxmlLoader.getController();
                    SubjectItemInfo subjectItemInfo = new SubjectItemInfo(SubjectItem, Controller);
                    SubjectItemMatrix.get(i)[j] = subjectItemInfo;
                } catch (IOException ex) {
                    //ex.printStackTrace();
                }
            }
        }
    }

    public void createSemesterBar() {
        for (int i=0; i<semesterNumber; i++) {
            createSemesterLabel(i);
        }
    }

    public void updateSubjects() {
        PensumPane.getChildren().clear();
        int SubjectNumber = 0;
        ArrayList<PositionSubject> iterable = SubjectTree.preOrderIterable();
        for(int i=0; i < iterable.getSize(); i++) {
            PositionSubject positionSubject = iterable.get(i);
            ArrayList<Integer[]> positions = positionSubject.getPositions();
            for (int j=0; j < positions.getSize(); j ++){
                int position_x = positions.get(j)[0];
                int position_y = positions.get(j)[1];
                Pane SubjectItem = SubjectItemMatrix.get(position_x)[position_y].getSubjectItem();
                SubjectItemController Controller = SubjectItemMatrix.get(position_x)[position_y].getController();
                try {
                    PensumPane.getChildren().add(SubjectItem);
                    SubjectItem = (Pane) PensumPane.getChildren().get(SubjectNumber);
                    SubjectItem.addEventHandler(MouseEvent.MOUSE_CLICKED, onSubjectClicked);
                    SubjectItem.setVisible(true);
                    SubjectItem.setLayoutX(distance_x * position_x + space_x);
                    SubjectItem.setLayoutY(distance_y * position_y + space_y);
                    Controller.setSubjectData(positionSubject.getSubject());
                    SubjectNumber++;
                } catch (Exception ignored) {

                }
            }
        }
    }

    EventHandler<MouseEvent> onSubjectClicked = event -> {
        Pane pane = (Pane) event.getSource();
        int position_x = (int) Math.round((pane.getLayoutX()-space_x)/distance_x), position_y = (int) Math.round((pane.getLayoutY()-space_y)/distance_y);
        PositionSubject positionSubject = SubjectTree.search(new PositionSubject(getCodeBySubjectItem(pane)));

        //System.out.println(positionSubject);

        // Option:
        // 0 = Show info
        // 1 = Delete Subject
        // 2 = Replace Subject
        switch (option) {
            case 0 -> displayInfo(positionSubject);
            case 1 -> {
                deleteSubject(positionSubject, position_x, position_y);
                option = 0;
            }
            case 2 -> {
                try {
                    replaceSubject(positionSubject, position_x, position_y);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
        ssc.loadSubjectTree(SubjectTree, semesterNumber);
        scene = new Scene(selector, 1100, 600);
        Stage stage = new Stage();
        stage.setTitle("Subject Selector");
        stage.setScene(scene);
        stage.showAndWait();

        ssc.openPositionSelector();
        Subject subject = (Subject) ssc.getSelectedSubject();
        int[] position = ssc.getPosition();

        PositionSubject searchedPositionSubject = SubjectTree.search(new PositionSubject(subject.getCode()));
        if (searchedPositionSubject.getCode() == subject.getCode()) {
            searchedPositionSubject.addPosition(position[0], position[1]);
        } else {
            SubjectTree.insert(new PositionSubject(position[0], position[1], subject));
        }

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
        createSemesterLabel(semesterNumber);
        semesterNumber ++;
        adjustPensumSize();
    }

    public void createSemesterLabel(int i) {
        Label semesterLabel = new Label("Semestre " + getRomanNumber(i + 1));
        semesterLabel.setPrefWidth(155);
        semesterLabel.setPrefHeight(40);
        semesterLabel.setAlignment(Pos.CENTER);
        SemesterBar.getChildren().add(semesterLabel);
        semesterLabel = (Label) SemesterBar.getChildren().get(i);
        semesterLabel.setLayoutX(distance_x*i + 20);
    }

    @FXML
    public void removeSemester() {
        semesterNumber --;
        SubjectItemInfo[] SubjectItems = SubjectItemMatrix.popBack();
        for (SubjectItemInfo subjectItemInfo : SubjectItems) {
            int code = subjectItemInfo.getController().getSubjectCode();
            SubjectTree.delete(new PositionSubject(code));
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

    public void deleteSubject(PositionSubject positionSubject, int position_x, int position_y) {
        positionSubject.removePosition(position_x, position_y);
        updateSubjects();
        MenuPane.setVisible(true);
    }

    public void replaceSubject(PositionSubject positionSubject, int position_x, int position_y) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SubjectSelector.fxml"));
        VBox selector = fxmlLoader.load();
        SubjectSelectorController ssc = fxmlLoader.getController();
        ssc.loadSubjectTree(SubjectTree, semesterNumber);
        scene = new Scene(selector, 1100, 600);
        Stage stage = new Stage();
        stage.setTitle("Subject Selector");
        stage.setScene(scene);
        stage.showAndWait();

        Subject subject = (Subject) ssc.getSelectedSubject();
        positionSubject.removePosition(position_x, position_y);
        PositionSubject searchedPositionSubject = SubjectTree.search(new PositionSubject(subject.getCode()));
        if (searchedPositionSubject.getCode() == subject.getCode()) {
            searchedPositionSubject.addPosition(position_x, position_y);
        } else {
            SubjectTree.insert(new PositionSubject(position_x, position_y, subject));
        }

        updateSubjects();
        MenuPane.setVisible(true);
        option = 0;
    }

    public void occultWindow(MouseEvent event) {
        Pane window = (Pane) ((ImageView) event.getSource()).getParent();
        window.setVisible(false);
    }

    public void occultAll() {
        InfoPane.setVisible(false);
    }


    // Draggable Subjects
    private double mouseAnchor_x;
    private double mouseAnchor_y;
    private double mouseDropped_x;
    private double mouseDropped_y;
    private Pane selectedPane;
    private Bounds initialBounds;
    private boolean swapOption;
    public void makeDraggable(Pane pane) {

        pane.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                pane.getScene().setCursor(Cursor.HAND);
            }
        });

        pane.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                pane.getScene().setCursor(Cursor.DEFAULT);
                pane.setScaleX(1);
                pane.setScaleY(1);
                pane.setOpacity(1);
                selectedPane = null;
            }
        });

        pane.setOnMousePressed(mouseEvent -> {
            mouseAnchor_x = mouseEvent.getX();
            mouseAnchor_y = mouseEvent.getY();
            selectedPane = pane;
            selectedPane.toFront();
            initialBounds = selectedPane.getBoundsInParent();
            draggable();
        });

        pane.setOnMouseReleased(mouseEvent -> {
            if (swapOption && !mouseEvent.isPrimaryButtonDown()) {

                int old_x = (int) Math.round((initialBounds.getMinX()-space_x)/distance_x);
                int old_y = (int) Math.round((initialBounds.getMinY()-space_y)/distance_y);
                int new_x = (int) Math.round((mouseDropped_x-space_x)/distance_x);
                int new_y = (int) Math.round((mouseDropped_y-space_y)/distance_y);

                PositionSubject oldSubject = SubjectTree.search(new PositionSubject(getCodeBySubjectItem(pane)));
                oldSubject.addPosition(new_x, new_y);
                oldSubject.removePosition(old_x, old_y);

                Pane newSubjectPane = SubjectItemMatrix.get(new_x)[new_y].getSubjectItem();
                if (newSubjectPane.isVisible()) {
                    PositionSubject newSubject = SubjectTree.search(new PositionSubject(getCodeBySubjectItem(newSubjectPane)));
                    newSubject.addPosition(old_x, old_y);
                    newSubject.removePosition(new_x, new_y);
                }

                SubjectItemInfo temp = SubjectItemMatrix.get(new_x)[new_y];
                SubjectItemMatrix.get(new_x)[new_y] = SubjectItemMatrix.get(old_x)[old_y];
                SubjectItemMatrix.get(old_x)[old_y] = temp;

                updateSubjects();

                selectedPane.setScaleX(1);
                selectedPane.setScaleY(1);
                pane.setOpacity(1);
                selectedPane = new Pane();
                swapOption = false;
            }
        });

    }
    public void draggable() {
        PensumPane.setOnMouseDragged(mouseEvent -> {
            selectedPane.setLayoutX(mouseEvent.getX() - mouseAnchor_x);
            selectedPane.setLayoutY(mouseEvent.getY() - mouseAnchor_y);
            mouseDropped_x = mouseEvent.getX() - mouseAnchor_x;
            mouseDropped_y = mouseEvent.getY() - mouseAnchor_y;
            selectedPane.setScaleX(0.9);
            selectedPane.setScaleY(0.9);
            selectedPane.setOpacity(0.8);
            swapOption = true;
        });
    }

    public int getCodeBySubjectItem(Pane pane) {
        return Integer.parseInt(((Label) ((Pane) pane.getChildren().get(1)).getChildren().get(0)).getText());
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