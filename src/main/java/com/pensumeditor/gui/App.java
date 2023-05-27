package com.pensumeditor.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Editor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 720);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("editor/pensum_editor_icon.png")));
        stage.setTitle("Pensum Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}