package com.pensumeditor.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
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
                    ((Label) ((VBox) stylePane.getChildren().get(1)).getChildren().get(0)).setText(styleName);
                    ((Label) ((VBox) stylePane.getChildren().get(1)).getChildren().get(1)).setText(styleAuthor);

                    fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("subjectstyle/" + style + "/SubjectItem.fxml"));
                    Pane SubjectItem = fxmlLoader.load();
                    SubjectItem.getTransforms().add(new Scale(1.1, 1.1));
                    ((StackPane) stylePane.getChildren().get(0)).getChildren().add(SubjectItem);
                    StyleSelectorPane.getChildren().add(stylePane);
                }
            }
        }
    }

}
