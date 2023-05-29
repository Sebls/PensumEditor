package com.pensumeditor.datastructures.linear;

public interface List<T> {
    public int getSize();
    public boolean isEmpty();
    public void pushFront(T data);
    public void pushBack(T data);
    public T popFront();
    public T popBack();
    public int search(T data);
    public T get(int index);
    public void set(T data, int index);
    public void insert(int index, T data);
    public T remove(int index);
    public void add(T data);
    public String toString();

    public default boolean equalsTo(List<T> list) {
        return (list.toString()).equals(this.toString());
    }
}