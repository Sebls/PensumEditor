package com.pensumeditor.datastructures.linear;

import java.util.NoSuchElementException;

public class LinkedList <T> implements List <T> {

    class Node {
        private T data;
        private Node next;

        public Node(T x) {
            data = x;
            next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {
        head = null;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void pushFront(T x) {
        Node node = new Node(x);
        if (isEmpty()) {
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head = node;
        size++;
    }

    public void pushBack(T x) {
        Node node = new Node(x);
        if (isEmpty()) {
            head = node;
            tail = node;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }

    // Se establece por default el add como un pushBack
    public void add(T x) {
        pushBack(x);
    }

    public T popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = head.data;
        head = head.next;
        size --;
        if (isEmpty()) {
            tail = null;
        }
        return data;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = tail.data;
        Node node = head;
        while (node.next != null && node.next.next != null) {
            node = node.next;
        }
        node.next = null;
        tail = node;
        size--;
        return data;
    }

    public int search(T x) {
        if (isEmpty()) {
            return -1;
        }
        int index = 0;
        Node node = head;
        while (node!=null) {
            if (node.data == x) {
                return index;
            }
            index ++;
            node = node.next;
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        Node node = head;
        for (int i=0; i<index; i++){
            node = node.next;
        }
        return node.data;
    }

    public void set(T data, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.data = data;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        if (index == 0) {
            pushFront(data);
        } else if (index == size) {
            pushBack(data);
        } else {
            Node newNode = new Node(data);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public T remove(int index) {
        T data;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        } else if (index == 0) {
            data = popFront();
        } else if (index == size - 1){
            data = popBack();
        } else {
            Node node = head;
            for (int i=0; i<index-1; i++) {
                node = node.next;
            }
            data = node.next.data;
            node.next = node.next.next;
            size --;
        }
        return data;
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "[ ]";
        }
        String s = "[ ";
        Node node = head;
        while (node != null && node.next != null) {
            s += node.data + " ";
            node = node.next;
        }
        s += node.data + " ]";
        return s;
    }
}
