package com.pensumeditor.datastructures.linear;

public interface Stack<T> {
    public T getTop();
    public int getSize();
    public boolean isEmpty();
    public void push(T data);
    public T pop();
    public String toString();

}