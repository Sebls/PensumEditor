package com.pensumeditor.gui;

import com.pensumeditor.data.StyleInfo;
import com.pensumeditor.datastructures.directaccess.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class StyleSelectorController {

    HashMap<String, StyleInfo> stylesInfo = new HashMap<>();
    StyleInfo selectedStyle;

    @FXML
    private VBox StyleSelectorPane;

    public void loadStyles() throws IOException, ParserConfigurationException, SAXException {
        String path = "src/main/resources/com/pensumeditor/gui/subjectstyle";
        File dir = new File(path);
        File[] directoryFiles = dir.listFiles();
        if (Objects.nonNull(directoryFiles)) {
            int stylesNumber = directoryFiles.length;
            StyleSelectorPane.setPrefHeight(stylesNumber*226+(stylesNumber+3)*30);
            for (int i = 0; i < stylesNumber; i++) {
                if (directoryFiles[i].isDirectory()) {
                    String style = directoryFiles[i].toString().replace("src\\main\\resources\\com\\pensumeditor\\gui\\subjectstyle\\", "");
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("StylePane.fxml"));
                    HBox stylePane = fxmlLoader.load();

                    File xmlFile = new File(directoryFiles[i].toString() + "/StyleInfo.xml");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(xmlFile);
                    doc.getDocumentElement().normalize();

                    String styleName = doc.getElementsByTagName("styleName").item(0).getTextContent();
                    String styleAuthor = doc.getElementsByTagName("styleAuthor").item(0).getTextContent();
                    String id = doc.getElementsByTagName("id").item(0).getTextContent();
                    int distance_x = Integer.parseInt(doc.getElementsByTagName("distance_x").item(0).getTextContent());
                    int distance_y = Integer.parseInt(doc.getElementsByTagName("distance_y").item(0).getTextContent());
                    int space_x = Integer.parseInt(doc.getElementsByTagName("space_x").item(0).getTextContent());
                    int space_y = Integer.parseInt(doc.getElementsByTagName("space_y").item(0).getTextContent());
                    String version = doc.getElementsByTagName("version").item(0).getTextContent();

                    StyleInfo styleInfo = new StyleInfo(styleName, styleAuthor, id, distance_x, distance_y, space_x, space_y, version);
                    stylesInfo.put(styleName, styleInfo);

                    ((Label) ((VBox) stylePane.getChildren().get(1)).getChildren().get(0)).setText(styleName);
                    ((Label) ((VBox) stylePane.getChildren().get(1)).getChildren().get(1)).setText(styleAuthor);

                    fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("subjectstyle/" + style + "/SubjectItem.fxml"));
                    Pane SubjectItem = fxmlLoader.load();
                    SubjectItem.getTransforms().add(new Scale(1.1, 1.1));
                    ((StackPane) stylePane.getChildren().get(0)).getChildren().add(SubjectItem);
                    StyleSelectorPane.getChildren().add(stylePane);

                    ((Button) ((VBox) stylePane.getChildren().get(1)).getChildren().get(2)).setOnAction( e -> {
                        selectedStyle = stylesInfo.get(styleName);
                        ((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
                    });
                }
            }
        }
    }

    public StyleInfo getSelectedStyle() {
        return selectedStyle;
    }

}
