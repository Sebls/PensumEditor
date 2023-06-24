package com.pensumeditor.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SubjectColorPickerController {

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private CheckBox checkBox;

    private Pane SubjectItem;
    private SubjectItemController SubjectController;
    private String selectedColor;

    // x: 35 - y: 60
    public void loadActualStyle(String styleName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("subjectstyle/" + styleName + "/SubjectItem.fxml"));
        SubjectItem = fxmlLoader.load();
        SubjectController = fxmlLoader.getController();
        AnchorPane pane = (AnchorPane) colorPicker.getParent();
        pane.getChildren().add(SubjectItem);
        SubjectItem.setLayoutX(50);
        SubjectItem.setLayoutY(90);
    }
    public void loadActualColor(String hexColor) {
        SubjectController.setSubjectColor(hexColor);
    }
    public String getHexByColor(Color color) {
        return String.format( "%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
    @FXML
    public void updateSelectedColor() {
        SubjectController.setSubjectColor(getHexByColor(colorPicker.getValue()));
    }
    @FXML
    public void saveSelectedColor() {
        selectedColor = getHexByColor(colorPicker.getValue());
        ((Stage) colorPicker.getScene().getWindow()).close();
    }
    public String getSelectedColor() {
        return selectedColor;
    }

    public boolean getSelectedOption() {
        return checkBox.isSelected();
    }
}
