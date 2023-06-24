package com.pensumeditor.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BackgroundColorPickerController {

    @FXML
    private ColorPicker colorPicker;
    private String selectedColor;

    public void loadActualColor(String hexColor) {
        colorPicker.setValue(Color.web(hexColor));
    }

    @FXML
    public void saveSelectedColor() {
        Color color = colorPicker.getValue();
        selectedColor = String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
        ((Stage) colorPicker.getScene().getWindow()).close();
    }

    public String getSelectedColor() {
        return this.selectedColor;
    }

}
