package com.pensumeditor.datastructures.nonlinear;

import com.pensumeditor.datastructures.linear.CircularArrayList;
import com.pensumeditor.datastructures.linear.List;

import java.nio.BufferUnderflowException;

public class BinarySearchTree {
    public class Node {
        private int key;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int key) {
            this.key = key;
            this.left = this.right = this.parent = null;
        }

        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            return Integer.toString(key);
        }
    }

    private Node root;

    public BinarySearchTree(int key) {
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

    public Node insert(int key) {
        root = insert(key, root);
        return root;
    }

    private Node insert(int key, Node node) {
        if (node == null) {
            return new Node(key);
        }
        if (key > node.key) {
            node.right = insert(key, node.right);
            node.right.parent = node;
        } else if (key < node.key) {
            node.left = insert(key, node.left);
            node.left.parent = node;
        }
        return node;
    }

    public Node find(int value) {
        return find(value, root);
    }

    private Node find(int value, Node node) {
        if (node != null) {
            if (node.key == value) {
                return node;
            } else if (node.key > value) {
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

    public int findMin(){
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

    public int findMax() {
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
            if (node.parent != null && node.key < node.parent.key) {
                return node.parent;
            } else {
                return RightAncestor(node.parent);
            }
        }
        return null;
    }

    public List<Node> rangeSearch(int x, int y) {
        return rangeSearch(x,y,root);
    }

    private List<Node> rangeSearch(int x, int y, Node R) {
        List<Node> L = new CircularArrayList<>();
        Node N = find(x, R);
        while (N != null && N.key <= y) {
            if (N.key >= x) {
                L.add(N);
            }
            N = next(N);
        }
        return L;
    }

    public void delete(int value) {
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
}
