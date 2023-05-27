package com.pensumeditor.data;

import java.util.ArrayList;

public class PositionSubject implements Comparable<PositionSubject> {

    private Subject subject;
    private ArrayList<int[]> positions = new ArrayList<>();
    private int subjectCode;

    public PositionSubject(Subject subject) {
        this.subjectCode = subject.getCode();
        this.subject = subject;
    }

    public PositionSubject(int column, int row, Subject subject) {
        int[] position = new int[2];
        position[0] = column;
        position[1] = row;
        this.positions.add(position);
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

    public ArrayList<int[]> getPositions() {
        return positions;
    }

    public void addPosition(int column, int row) {
        int[] position = new int[2];
        position[0] = column;
        position[1] = row;
        positions.add(position);
    }

    @Override
    public String toString() {
        return "PositionSubject{" +
                "subject=" + subject +
                ", positions=" + positions +
                '}';
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
}
