module com.pensumeditor.pensumeditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pensumeditor.gui to javafx.fxml;
    opens com.pensumeditor.data to javafx.base;
    exports com.pensumeditor.gui;
    exports com.pensumeditor.data;
    exports com.pensumeditor.datastructures.linear;
    exports com.pensumeditor.datastructures.nonlinear;
}