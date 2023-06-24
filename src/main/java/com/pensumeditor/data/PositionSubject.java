package com.pensumeditor.data;

import com.pensumeditor.datastructures.linear.ArrayList;

public class PositionSubject implements Comparable<PositionSubject> {

    private Subject subject;
    private ArrayList<Integer[]> positions;
    private int column;
    private int row;
    private int subjectCode;

    public PositionSubject(Subject subject) {
        this.subjectCode = subject.getCode();
        this.subject = subject;
    }

    public PositionSubject(int column, int row, Subject subject) {
        this.positions = new ArrayList<>();
        positions.add(new Integer[]{column, row});
        this.subjectCode = subject.getCode();
        this.subject = subject;
    }

    public PositionSubject(int code) {
        this.subjectCode = code;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /*public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }*/

    public ArrayList<Integer[]> getPositions() {
        return positions;
    }

    public void addPosition(int column, int row) {
        positions.add(new Integer[]{column, row});
    }

    public boolean removePosition(int column, int row) {
        for (int i=0; i < positions.getSize(); i ++) {
           if (positions.get(i)[0] == column && positions.get(i)[1] == row) {
               positions.remove(i);
               return true;
           }
        }
        return false;
    }

    @Override
    public String toString() {
        String positionsString = "[";
        for (int i = 0; i < positions.getSize(); i++) {
            positionsString += "x= " + positions.get(i)[0] + " - y= " + positions.get(i)[1] + "\n";
        }
        positionsString += "]";
        String s = "PositionSubject{" +
                "subject=" + subject +
                ", positions=\n" + positionsString +
                '}';

        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionSubject that = (PositionSubject) o;

        return subjectCode == that.subjectCode;
    }

    @Override
    public int hashCode() {
        return subjectCode;
    }

    @Override
    public int compareTo(PositionSubject o) {
        return Integer.compare(this.subjectCode, o.subjectCode);
    }

    public int getCode() {
        return subjectCode;
    }
}
