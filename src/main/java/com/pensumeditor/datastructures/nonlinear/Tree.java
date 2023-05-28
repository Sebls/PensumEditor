package com.pensumeditor.datastructures.nonlinear;

import com.pensumeditor.datastructures.linear.List;

public interface Tree {
    public void insert(int key);
    public void delete(int key);
    public int search(int key);
    public int findMax();
    public int findMin();
    public List<Integer> rangeSearch(int a, int b);
    public void inOrderTraversal();
    public void preOrderTraversal();
    public void postOrderTraversal();
    public void printTree();
    public int size();
    public int height();

}
