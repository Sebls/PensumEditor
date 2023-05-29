package com.pensumeditor.gui;

import com.pensumeditor.data.Subject;
import com.pensumeditor.datastructures.nonlinear.AVLTree;
import com.pensumeditor.datastructures.nonlinear.BinarySearchTree;
import com.pensumeditor.datastructures.nonlinear.Tree;

public class Test {
    public static void main(String[] args) {
        AVLTree<Subject> tree = new AVLTree<>();
        tree.insert(new Subject(233242));
        tree.insert(new Subject(212333));
        tree.insert(new Subject(120012));
        tree.insert(new Subject(421412));
        tree.insert(new Subject(531323));
        tree.insert(new Subject(121235));
        tree.printTree();
        System.out.println(tree.findMin());
        System.out.println(tree.findMax());
        System.out.println(tree.height());
        System.out.println(tree.size());
        tree.inOrderTraversal();
        tree.preOrderTraversal();
        tree.postOrderTraversal();
        System.out.println(tree.search(new Subject(121235)));
        System.out.println(tree.search(new Subject(121236)));
        System.out.println(tree.rangeSearch(new Subject(100000),new Subject(500000)));
    }
}
