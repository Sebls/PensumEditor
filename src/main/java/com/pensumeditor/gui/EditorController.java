package com.pensumeditor.gui;

import com.pensumeditor.data.*;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
    private AnchorPane InfoPaneBackground;
    @FXML
    private AnchorPane InfoPane;
    @FXML
    private AnchorPane PensumPane;

    // Control Variable
    private int option = 0;

    // Subject Style
    private String styleName = "modern";

    // Subject Distribution
    private final int distance_x = 230, distance_y = 130, space_x = 20, space_y = 20;

    // Color Map
    private HashMap<String, SubjectColor> colorMap = new HashMap<String, SubjectColor>();
    private final ArrayList<SpecialPositionColor> specialPositions = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IngSistemas Pensum = new IngSistemas();
        SubjectTree = Pensum.GeneratePensum();
        semesterNumber = 10;
        semesterSubjects = 6;
        createColorMap();
        ArrayList<PositionSubject> iterable = SubjectTree.preOrderIterable();
        for (int i=0; i < iterable.getSize(); i++) {
            colorMap.get(iterable.get(i).getSubject().getGroup()).getPositionSubjectArray().add(iterable.get(i));
        }
        createSubjectInfoPane();
        adjustPensumSize();
        createSubjectItemMatrix();
        createSemesterBar();
        updateSubjects();
        occultInfo();
    }

    private void createColorMap() {
        colorMap.put("Matemáticas", new SubjectColor("99ccff"));
        colorMap.put("Probabilidad y Estadística", new SubjectColor("95b3d7"));
        colorMap.put("Física", new SubjectColor("ffffcc"));
        colorMap.put("Ciencias de la Computación", new SubjectColor("cccc00"));
        colorMap.put("Ciencias Económicas y Administrativas", new SubjectColor("a9d08e"));
        colorMap.put("Métodos y Tecnologías de Software", new SubjectColor("fff2cc"));
        colorMap.put("Infraestructura Computacional, de Comunicaciones y de Información", new SubjectColor("ffff66"));
        colorMap.put("Computación Visual", new SubjectColor("d8e4bc"));
        colorMap.put("Sistemas Inteligentes", new SubjectColor("b7dee7"));
        colorMap.put("Modelos, Sistemas, Optimización y Simulación", new SubjectColor("99cc00"));
        colorMap.put("Contexto Profesional y Proyectos de Ingeniería", new SubjectColor("99ccf0"));
        colorMap.put("TRABAJO DE GRADO", new SubjectColor("d9e1f2"));
        colorMap.put("Libre elección", new SubjectColor("e4e4e4"));
    }

    public void adjustPensumSize() {
        PensumPane.setStyle("-fx-background-color: #d3d3d3");
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
                    fxmlLoader.setLocation(getClass().getResource("subjectstyle/" + styleName + "/SubjectItem.fxml"));
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
                    Subject subject = positionSubject.getSubject();
                    Controller.setSubjectData(subject, colorMap.get(subject.getGroup()).getColorCode());
                    SubjectNumber++;
                } catch (Exception ignored) {

                }
            }
        }
        for (int i=0; i < specialPositions.getSize(); i ++) {
            int[] position = specialPositions.get(i).getPosition();
            SubjectItemMatrix.get(position[0])[position[1]].getController().setSubjectColor(specialPositions.get(i).getColorCode());
        }
    }

    EventHandler<MouseEvent> onSubjectClicked = event -> {
        hideMessage();
        Pane pane = (Pane) event.getSource();
        int position_x = (int) Math.round((pane.getLayoutX()-space_x)/distance_x), position_y = (int) Math.round((pane.getLayoutY()-space_y)/distance_y);
        PositionSubject positionSubject = SubjectTree.search(new PositionSubject(getCodeBySubjectItem(pane)));

        //System.out.println(positionSubject);

        // Option:
        // 0 = Show info
        // 1 = Delete Subject
        // 2 = Replace Subject
        switch (option) {
            case 0 -> displayInfo(positionSubject, position_x, position_y);
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
            case 3 -> {
                try {
                    changeColorSubject(positionSubject, position_x, position_y);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    @FXML
    public void setDeleteOption() {
        option = 1;
        occultInfo();
        showMessage("Selecciona la materia que deseas eliminar");
    }

    @FXML
    public void setReplaceOption() {
        option = 2;
        occultInfo();
        showMessage("Selecciona la materia que deseas reemplazar");
    }

    @FXML
    public void setSubjectColorPickerOption() {
        option = 3;
        occultInfo();
        showMessage("Selecciona la materia a la que deseas modificar el color");
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
            PositionSubject positionSubject = new PositionSubject(position[0], position[1], subject);
            SubjectTree.insert(positionSubject);
            colorMap.get(subject.getGroup()).getPositionSubjectArray().add(positionSubject);
        }

        updateSubjects();
    }

    @FXML
    public void addSemester() {
        SubjectItemMatrix.add(new SubjectItemInfo[semesterSubjects]);
        for (int j=0; j<semesterSubjects; j++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("subjectstyle/" + styleName + "/SubjectItem.fxml"));
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
    private StackPane InfoSubjectPane;
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

    Pane InfoSubjectItem;
    SubjectItemController InfoSubjectController;

    public void createSubjectInfoPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("subjectstyle/" + styleName + "/SubjectItem.fxml"));
            InfoSubjectItem = fxmlLoader.load();
            InfoSubjectItem.getTransforms().add(new Scale(1.1, 1.1));
            InfoSubjectController = fxmlLoader.getController();
            InfoSubjectPane.getChildren().add(InfoSubjectItem);
            StackPane.setAlignment(InfoSubjectItem, Pos.CENTER);
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }

    public void displayInfo(PositionSubject positionSubject, int position_x, int position_y) {
        InfoPane.setVisible(true);
        InfoPaneBackground.setVisible(true);
        Subject subject = positionSubject.getSubject();
        String colorCode = SubjectItemMatrix.get(position_x)[position_y].getController().getColorCode();
        InfoSubjectController.setSubjectData(subject, colorCode);
        InfoNameLabel.setText("Nombre: " + subject.getName());
        InfoCodeLabel.setText("Código: " + Integer.toString(subject.getCode()));
        InfoCreditsLabel.setText("Creditos: " + Integer.toString(subject.getCredits()));
        InfoGroupLabel.setText("Grupo: " + subject.getGroup());
        InfoComponentLabel.setText("Componente: " + subject.getComponent());
        InfoPrerequisiteLabel.setText("Prerrequisitos: " + subject.getPrerequisite());
    }

    public void deleteSubject(PositionSubject positionSubject, int position_x, int position_y) {
        positionSubject.removePosition(position_x, position_y);
        SubjectItemMatrix.get(position_x)[position_y].getSubjectItem().setVisible(false);
        updateSubjects();
        hideMessage();
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
        hideMessage();
        option = 0;
    }

    public void occultInfo() {
        InfoPane.setVisible(false);
        InfoPaneBackground.setVisible(false);
    }

    private void menuVisible(boolean bool) {
        MenuPane.getChildren().get(0).setVisible(bool);
        MenuPane.getChildren().get(1).setVisible(bool);
    }

    Label messageLabel;
    private void showMessage(String message) {
        menuVisible(false);
        messageLabel = new Label(message);
        messageLabel.setPrefWidth(MenuPane.getWidth());
        messageLabel.setPrefHeight(MenuPane.getHeight());
        messageLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 19));
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setTextFill(Color.web("626260"));
        MenuPane.getChildren().add(messageLabel);
    }

    private void hideMessage() {
        MenuPane.getChildren().remove(messageLabel);
        menuVisible(true);
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
                selectedPane = new Pane();
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

                checkSpecialPositions(old_x, old_y, new_x, new_y);

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

    private void checkSpecialPositions(int old_x, int old_y, int new_x, int new_y) {
        // Check if one or both of the swapped subjects have a special position (Subject with color different from color in colorMap)
        int old_index = -1, new_index = -1;
        for (int i = 0; i < specialPositions.getSize(); i ++) {
            if (specialPositions.get(i).getPosition()[0] == old_x && specialPositions.get(i).getPosition()[1] == old_y) {
                old_index = i;
            } else if (specialPositions.get(i).getPosition()[0] == new_x && specialPositions.get(i).getPosition()[1] == new_y) {
                new_index = i;
            }
            if (old_index != -1 && new_index != -1) {
                String tempColorCode = specialPositions.get(new_index).getColorCode();
                specialPositions.get(new_index).setColorCode(specialPositions.get(old_index).getColorCode());
                specialPositions.get(old_index).setColorCode(tempColorCode);
                break;
            }
        }
        if (old_index != -1 && new_index == -1) {
            specialPositions.get(old_index).getPosition()[0] = new_x;
            specialPositions.get(old_index).getPosition()[1] = new_y;
        } else if (old_index == -1 && new_index != -1) {
            specialPositions.get(new_index).getPosition()[0] = old_x;
            specialPositions.get(new_index).getPosition()[1] = old_y;
        }
    }

    // Style Options
    @FXML
    public void changeColorBackground() throws IOException {
        String actualColor = PensumPane.getStyle().replace("-fx-background-color: ", "");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("BackgroundColorPicker.fxml"));
        VBox colorPicker = fxmlLoader.load();
        BackgroundColorPickerController cp = fxmlLoader.getController();
        cp.loadActualColor(actualColor);
        scene = new Scene(colorPicker, 330, 200);
        Stage stage = new Stage();
        stage.setTitle("Background Color Picker");
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

        String selectedColor = cp.getSelectedColor();
        if (selectedColor != null) {
            PensumPane.setStyle("-fx-background-color: " + selectedColor);
        }
    }

    public void changeColorSubject(PositionSubject positionSubject, int position_x, int position_y) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SubjectColorPicker.fxml"));
        AnchorPane colorPicker = fxmlLoader.load();
        SubjectColorPickerController cp = fxmlLoader.getController();
        cp.loadActualStyle(styleName);
        cp.loadActualColor(colorMap.get(positionSubject.getSubject().getGroup()).getColorCode());
        scene = new Scene(colorPicker, 490, 290);
        Stage stage = new Stage();
        stage.setTitle("Subject Color Picker");
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
        String hexColor = cp.getSelectedColor();
        if (hexColor != null) {
            if (cp.getSelectedOption()) {
                colorMap.get(positionSubject.getSubject().getGroup()).setColorCode(hexColor);

                ArrayList<PositionSubject> subjects = colorMap.get(positionSubject.getSubject().getGroup()).getPositionSubjectArray();
                for (int i = 0; i < subjects.getSize(); i++) {
                    ArrayList<Integer[]> positions = subjects.get(i).getPositions();
                    for (int j = 0; j < positions.getSize(); j++) {
                        SubjectItemMatrix.get(positions.get(j)[0])[positions.get(j)[1]].getController().setSubjectColor(hexColor);
                    }
                }

                for (int i = 0; i < specialPositions.getSize(); i++) {
                    int[] position = specialPositions.get(i).getPosition();
                    SubjectItemMatrix.get(position[0])[position[1]].getController().setSubjectColor(specialPositions.get(i).getColorCode());
                }
            } else {
                int[] position = new int[]{position_x, position_y};
                specialPositions.add(new SpecialPositionColor(hexColor, position));
                SubjectItemMatrix.get(position[0])[position[1]].getController().setSubjectColor(hexColor);
            }
        }


        hideMessage();
    }

    // Export options
    @FXML
    public void exportPDF() {

    }

    @FXML
    public void exportPNG() {
        showMessage("Exportando como PNG...");
        // Zoom-in the panel with the subjects
        Scale scaleTransform = new Scale(PaneScroll.getScaleX() * 2, PaneScroll.getScaleY() * 2, 0, 0);
        PaneScroll.getTransforms().add(scaleTransform);

        // Take the screenshot
        WritableImage nodeshot = PaneScroll.snapshot(new SnapshotParameters(), null);

        // Delete the semester labels and hide the watermark
        PaneScroll.getTransforms().remove(scaleTransform);

        // Get the path where the user want to save the file
        DirectoryChooser chooser = new DirectoryChooser();

        chooser.setTitle("Selecciona la ubicación donde deseas guardar el pensum");

        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);

        Stage stage = (Stage) PaneScroll.getScene().getWindow();
        File choosed = chooser.showDialog(stage);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        // Save the file
        File file = new File(choosed.getAbsolutePath() + "/Pensum_"+ dtf.format(now) +".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", file);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);

        } catch (IOException ex) {
            //ex.printStackTrace();
        }
        hideMessage();
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