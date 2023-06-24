package com.pensumeditor.data;

public class SpecialPositionColor {

    private String colorCode;
    private int[] position;

    public SpecialPositionColor(String colorCode, int[] position) {
        this.colorCode = colorCode;
        this.position = position;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int[] getPosition() {
        return position;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
