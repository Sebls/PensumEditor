package com.pensumeditor.data;

import java.util.Arrays;
import java.util.Objects;

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
    public void setPosition(int[] position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialPositionColor that = (SpecialPositionColor) o;
        return Objects.equals(colorCode, that.colorCode) && Arrays.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(colorCode);
        result = 31 * result + Arrays.hashCode(position);
        return result;
    }

    @Override
    public String toString() {
        return "SpecialPositionColor{" +
                "colorCode='" + colorCode + '\'' +
                ", position=" + Arrays.toString(position) +
                '}';
    }
}
