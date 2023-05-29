package com.pensumeditor.datastructures.nonlinear;

import com.pensumeditor.datastructures.linear.CircularArrayList;
import com.pensumeditor.datastructures.linear.List;

import java.nio.BufferUnderflowException;

public class BinarySearchTree <T extends Comparable<T>> implements Tree <T> {
    public class Node {
        private T key;
        private Node left;
        private Node right;
        private Node parent;

        public Node(T key) {
            this.key = key;
            this.left = this.right = this.parent = null;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    private Node root;

    public BinarySearchTree(T key) {
        root = new Node(key);
    }

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T key) {
        root = insert(key, root);
    }

    private Node insert(T key, Node node) {
        if (node == null) {
            return new Node(key);
        }
        if (key.compareTo(node.key) < 0) {
            node.right = insert(key, node.right);
            node.right.parent = node;
        } else if (key.compareTo(node.key) > 0) {
            node.left = insert(key, node.left);
            node.left.parent = node;
        }
        return node;
    }

    public T search(T key) {
        return find(key).key;
    }

    public Node find(T value) {
        return find(value, root);
    }

    private Node find(T value, Node node) {
        if (node != null) {
            if (node.key == value) {
                return node;
            } else if (node.key.compareTo(value) > 0) {
                if (node.left != null) {
                    return find(value, node.left);
                }
                return node;
            } else {
                if (node.right != null) {
                    return find(value, node.right);
                }
            }
            return node;
        }
        return null;
    }

    public T findMin(){
        if (isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMin(root).key;
    }

    private Node findMin(Node node){
        if (node == null){
            return null;
        } else if (node.left == null){
            return node;
        }
        return findMin(node.left);
    }

    public T findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).key;
    }

    private Node findMax(Node node) {
        if (node == null) {
            return null;
        } else if (node.right == null) {
            return node;
        }
        return findMax(node.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int size(){
        return size(root);
    }
    public int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public Node next(Node node) {
        if (node.right != null) {
            return LeftDescendant(node.right);
        } else {
            return RightAncestor(node);
        }
    }

    private Node LeftDescendant(Node node) {
        if (node.left != null) {
            return LeftDescendant(node.left);
        } else {
            return node;
        }
    }

    private Node RightAncestor(Node node) {
        if (node != null) {
            if (node.parent != null && node.key.compareTo(node.parent.key) < 0) {
                return node.parent;
            } else {
                return RightAncestor(node.parent);
            }
        }
        return null;
    }

    public List<T> rangeSearch(T x, T y) {
        return rangeSearch(x,y,root);
    }

    private List<T> rangeSearch(T x, T y, Node R) {
        List<T> L = new CircularArrayList<>();
        Node N = find(x, R);
        while (N != null && N.key.compareTo(y) <= 0) {
            if (N.key.compareTo(x) >= 0) {
                L.add(N.key);
            }
            N = next(N);
        }
        return L;
    }

    public void delete(T value) {
        if (find(value).key == value) {
            delete(find(value));
        }
        else {
            System.out.println("Element doesn't exist.");
        }
    }
    private void delete(Node N) {
        if (N.right == null) {
            if (N.parent != null) {
                if (N.parent.left == N) {
                    N.parent.left = N.left;
                } else {
                    N.parent.right = N.left;
                }
                if (N.left != null) {
                    N.left.parent = N.parent;
                }
            } else {
                root = N.left;
                if (N.left != null) {
                    N.left.parent = null;
                }
            }
        } else {
            Node X = next(N);
            if (X.left != null) {
                X.left.parent = X.parent;
            }
            if (X.parent.left == X) {
                X.parent.left = X.right;
            } else {
                X.parent.right = X.right;
            }
            if (X.right != null) {
                X.right.parent = X.parent;
            }
            N.key = X.key;
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void printTree(){
        printTree(root,"",true);
    }
    private void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.key);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }
}
