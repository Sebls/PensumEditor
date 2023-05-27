package com.pensumeditor.datastructures.linear;

import java.util.NoSuchElementException;

public class StackArrayList<T> implements Stack<T> {

    private T[] array;
    private int size;
    private int capacity;
    private int factor = 2;

    public StackArrayList() {
        array = (T[]) new Object[factor];
        size = 0;
        capacity = factor;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public T getTop() {
        if (isEmpty()){
            throw new NoSuchElementException("No Such Element Exception");
        }
        return array[size-1];
    }

    public void push(T data) {
        if (isFull()) {
            ensureCapacity();
        }
        array[size] = data;
        size ++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = array[size-1];
        size --;
        return data;
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
