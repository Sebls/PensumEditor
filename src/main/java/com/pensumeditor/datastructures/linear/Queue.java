package com.pensumeditor.datastructures.linear;

public interface Queue <T> {
    public int getSize();
    public T getFront();
    public boolean isEmpty();
    public void enqueue(T data);
    public T dequeue();
    public String toString();
}
