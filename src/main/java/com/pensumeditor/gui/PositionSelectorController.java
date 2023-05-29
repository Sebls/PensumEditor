package com.pensumeditor.gui;

import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.datastructures.linear.ArrayList;
import com.pensumeditor.datastructures.nonlinear.AVLTree;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PositionSelectorController {

    @FXML
    private GridPane gridPane;

    private AVLTree<PositionSubject> SubjectsArray;
    private int semesterNumber;
    private int[] position;

    public void loadSubjectsArray(AVLTree<PositionSubject> SubjectsArray, int semesterNumber) {
        this.SubjectsArray = SubjectsArray;
        this.semesterNumber = semesterNumber;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void loadFreeGridPane() {
        ArrayList<int[]> matrix = new ArrayList<>();
        for (int i = 0; i < semesterNumber; i++) {
            matrix.add(new int[6]);
        }

        ArrayList<PositionSubject> iterable = SubjectsArray.preOrderIterable();
        for (int i = 0; i < iterable.getSize(); i++) {
            PositionSubject positionSubject = iterable.get(i);
            matrix.get(positionSubject.getColumn())[positionSubject.getRow()] = 1;
        }

        // Positionate free places
        for (int i = 0; i < semesterNumber; i++) {
            for (int e = 0; e < 6; e++) {
                if (matrix.get(i)[e]==0){
                    Rectangle subject = new Rectangle(30, 30, Color.web("e4e4e4"));
                    subject.setStroke(Color.web("3d3e3b"));
                    gridPane.add(subject, i, e);
                    gridPane.setHalignment(subject, javafx.geometry.HPos.CENTER);
                    gridPane.setValignment(subject, javafx.geometry.VPos.CENTER);
                    subject.setOnMouseEntered(mouseEvent -> {
                        if (!mouseEvent.isPrimaryButtonDown()) {
                            subject.getScene().setCursor(Cursor.HAND);
                        }
                    });
                    subject.setOnMouseExited(mouseEvent -> {
                        if (!mouseEvent.isPrimaryButtonDown()) {
                            subject.getScene().setCursor(Cursor.DEFAULT);
                        }
                    });
                    subject.setOnMousePressed(mouseEvent -> {
                        position = new int[2];
                        position[1] = gridPane.getRowIndex(subject);
                        position[0] = gridPane.getColumnIndex(subject);
                        Stage stage = (Stage) gridPane.getScene().getWindow();
                        stage.close();
                    });
                }
            }
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth((double) 100 /semesterNumber);
            gridPane.getColumnConstraints().add(column);
        }
    }

}
