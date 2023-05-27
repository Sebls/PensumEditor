package com.pensumeditor.datastructures.linear;

import java.util.NoSuchElementException;

public class CircularArrayList <T> implements List <T> {

    private T[] array;
    private int start;
    private int end;
    private int size;
    private int capacity;
    private int factor = 2;

    public CircularArrayList() {
        capacity = 2;
        array = (T[]) new Object[capacity];
        start = 0;
        end = 0;
        size = 0;
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
        start --;
        array[(start % capacity + capacity) % capacity] = data;
        size ++;
    }

    public void pushBack(T data) {
        if (isFull()) {
            ensureCapacity();
        }
        array[(end % capacity + capacity) % capacity] = data;
        end ++;
        size ++;
    }

    public void add(T data) {
        pushBack(data);
    }

    public T popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = array[(start % capacity + capacity) % capacity];
        start ++;
        size --;
        return data;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = array[(end % capacity + capacity) % capacity];
        end --;
        size --;
        return data;
    }

    public int search(T data) {
        int indexCount = 0;
        for (int i = start; i < end; i++) {
            if (array[(i % capacity + capacity) % capacity].equals(data)) {
                return indexCount;
            }
            indexCount ++;
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        return array[((start + index) % capacity + capacity) % capacity];
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
            size ++;
            int indexM = ((start + index) % capacity + capacity) % capacity;
            for (int i = end; i > start + index - 1; i --) {
                array[((i + 1) % capacity + capacity) % capacity] = array[(i % capacity + capacity) % capacity];
            }
            end ++;
            array[indexM] = data;
        }
    }
    public T remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        } else {
            int indexM = ((start + index) % capacity + capacity) % capacity;
            T data = array[indexM];
            for (int i = indexM; i < (end % capacity + capacity) % capacity; i++) {
                array[(i % capacity + capacity) % capacity] = array[((i + 1) % capacity + capacity) % capacity];
            }
            end --;
            size --;
            return data;
        }
    }

    public void ensureCapacity() {
        T[] tempArray = (T[]) new Object[capacity*factor];
        for (int i = 0, j = start;j < end; i++, j++) {
            tempArray[i] = array[(j % capacity + capacity) % capacity];

        }
        /*System.out.println("Ensure capacity: ");
        System.out.println("Initial Capacity: " + capacity + " - Final Capacity: " + capacity*factor);
        System.out.println("Prev Start: " + start + " - Final Start: 0");
        System.out.println("Prev End: " + end + " - Final End: " + size);*/
        start = 0;
        end = size;
        capacity *= factor;
        array = tempArray;
        /*String s = "[";
        for (int i = start; i < end; i++) {
            if (tempArray[(i % capacity + capacity) % capacity]== null) {
                s += "-,";
                continue;
            }
            s += tempArray[(i % capacity + capacity) % capacity].toString() + ",";

        }
        s += "]";

        s += "\n[";
        for (int i=0; i < capacity; i++) {
            if (tempArray[i] != null) {
                s += tempArray[i] + ",";
            } else {
                s += "-,";
            }
        }
        s += "]";
        System.out.println(s); */
    }

    @Override
    public String toString() {
        String s = "[ ";
        for (int i = start; i < end; i++) {
            s += array[(i % capacity + capacity) % capacity].toString() + " ";
        }
        s += "]";

        /*
        s += "\nReal Array: [";
        for (int i=0; i < capacity; i++) {
            if (array[i] != null) {
                s += array[i] + ",";
            } else {
                s += "-,";
            }
        }
        s += "]";
        */

        return s;
    }

}

