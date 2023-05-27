package com.pensumeditor.datastructures.linear;

import java.util.NoSuchElementException;

public class ArrayList <T> implements List <T> {

    private T[] array;
    private int size;
    private int capacity;
    private int factor = 2;

    public ArrayList() {
        array = (T[]) new Object[factor];
        size = 0;
        capacity = factor;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void pushFront(T data) {
        if (isFull()) {
            ensureCapacity();
        }
        for (int i=size; i > 0; i--) {
            array[i] = array[i-1];
        }
        array[0] = data;
        size ++;
    }

    public void pushBack(T data) {
        if (isFull()) {
            ensureCapacity();
        }
        array[size] = data;
        size ++;
    }

    public void add(T data) {
        pushBack(data);
    }

    public T popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = array[0];
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size --;
        return data;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = array[size-1];
        size --;
        return data;
    }

    public int search(T data) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        return array[index];
    }

    public void set(T data, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        array[index] = data;
    }

    public void insert(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        if (index == 0) {
            pushFront(data);
        } else if (index == size - 1) {
            pushBack(data);
        } else {
            if (isFull()) {
                ensureCapacity();
            }
            for (int i = size; i > index; i--) {
                array[i+1] = array[i];
            }
            array[index] = data;
            size ++;
        }
    }

    public T remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        } else if (index == 0) {
            return popFront();
        } else if (index == size - 1) {
            return popBack();
        } else {
            T data = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i+1];
            }
            size --;
            return data;
        }
    }

    public void ensureCapacity() {
        T[] tempArray = (T[]) new Object[capacity*factor];
        for (int i = 0; i < size; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
        capacity *= factor;
    }

    @Override
    public String toString() {
        String s = "[ ";
        for (int i = 0; i < size; i ++) {
            s += array[i] + " ";
        }
        s += "]";
        return s;
    }

}

