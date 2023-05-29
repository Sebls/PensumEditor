package com.pensumeditor.data;

import com.pensumeditor.gui.SubjectItemController;
import javafx.scene.layout.Pane;

public class SubjectItemInfo {

    private Pane SubjectItem;
    private SubjectItemController Controller;

    public SubjectItemInfo(Pane subjectItem, SubjectItemController controller) {
        SubjectItem = subjectItem;
        Controller = controller;
    }

    public Pane getSubjectItem() {
        return SubjectItem;
    }

    public void setSubjectItem(Pane subjectItem) {
        SubjectItem = subjectItem;
    }

    public SubjectItemController getController() {
        return Controller;
    }

    public void setController(SubjectItemController controller) {
        Controller = controller;
    }


}
