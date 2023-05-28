package com.pensumeditor.gui;

import com.pensumeditor.datastructures.nonlinear.AVLTree;

public class Test {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(6);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(8);
        tree.printTree();
        System.out.println(tree.findMin());
        System.out.println(tree.findMax());
        System.out.println(tree.height());
        System.out.println(tree.size());
        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
        System.out.println(tree.find(7).getKey());
        System.out.println(tree.getRoot().getKey());
        System.out.println(tree.rangeSearch(5,7));
    }
}
