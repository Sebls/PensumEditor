package com.pensumeditor.datastructures.nonlinear;

import com.pensumeditor.datastructures.linear.List;

public interface Tree <T extends Comparable<T>> {
    public void insert(T key);
    public void delete(T key);
    public T search(T key);
    public T findMax();
    public T findMin();
    public List<T> rangeSearch(T a, T b);
    public void inOrderTraversal();
    public void preOrderTraversal();
    public void postOrderTraversal();
    public void printTree();
    public int size();
    public int height();

}
