package com.pensumeditor.data;


import com.pensumeditor.datastructures.linear.ArrayList;

import java.util.Objects;

public class PositionSubject implements Comparable<PositionSubject> {

    private Subject subject;
    private int column;
    private int row;
    private int subjectCode;

    public PositionSubject(Subject subject) {
        this.subjectCode = subject.getCode();
        this.subject = subject;
    }

    public PositionSubject(int column, int row, Subject subject) {
        this.column = column;
        this.row = row;
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

    public int getColumn() {
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
    }

    @Override
    public String toString() {
        return "PositionSubject{" +
                "subject=" + subject +
                ", column=" + column +
                ", row=" + row +
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
