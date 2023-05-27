module com.pensumeditor.pensumeditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pensumeditor.gui to javafx.fxml;
    exports com.pensumeditor.gui;
}