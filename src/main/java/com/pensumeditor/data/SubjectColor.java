package com.pensumeditor.data;

import com.pensumeditor.datastructures.linear.ArrayList;

public class SubjectColor {
    private String colorCode;
    private ArrayList<PositionSubject> positionSubjectArray;

    public SubjectColor(String colorCode) {
        this.colorCode = colorCode;
        positionSubjectArray = new ArrayList<>();
    }

    public String getColorCode() {
        return colorCode;
    }

    public ArrayList<PositionSubject> getPositionSubjectArray() {
        return positionSubjectArray;
    }

    public void addPositionSubjectArray(PositionSubject positionSubject) {
        positionSubjectArray.add(positionSubject);
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
