package com.pensumeditor.gui;

import com.pensumeditor.datastructures.nonlinear.AVLTree;
import com.pensumeditor.datastructures.nonlinear.BinarySearchTree;
import com.pensumeditor.datastructures.nonlinear.Tree;

public class Test {
    public static void main(String[] args) {
        Tree tree = new AVLTree();
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
        tree.inOrderTraversal();
        tree.preOrderTraversal();
        tree.postOrderTraversal();
        System.out.println(tree.search(7));
        System.out.println(tree.search(10));
        System.out.println(tree.rangeSearch(0,10));
    }
}
