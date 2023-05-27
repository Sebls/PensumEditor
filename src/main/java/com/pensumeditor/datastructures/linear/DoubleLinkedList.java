package com.pensumeditor.datastructures.linear;

import java.util.NoSuchElementException;

public class DoubleLinkedList <T> implements List <T> {
    class Node {
        private T data;
        private Node prev;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T data) {
        pushBack(data);
    }

    public void pushFront(T data) {
        Node node = new Node(data);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void pushBack(T data) {
        Node node = new Node(data);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T data = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public int search(T data) {
        int index = 0;
        Node node = head;
        while (node != null) {
            if (node.data.equals(data)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }


    public void set(T data, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.data = data;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            pushFront(data);
        } else if (index == size) {
            pushBack(data);
        } else {
            Node node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            Node newNode = new Node(data);
            newNode.prev = node.prev;
            newNode.next = node;
            node.prev.next = newNode;
            node.prev = newNode;
            size++;
        }
    }


    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }

        Node nodeToRemove;
        if (index == 0) {
            nodeToRemove = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            nodeToRemove = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            nodeToRemove = node;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        size--;
        return nodeToRemove.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder("[ ");
        Node node = head;
        while (node != null && node.next != null) {
            sb.append(node.data).append(" ");
            node = node.next;
        }
        sb.append(node.data).append(" ]");
        return sb.toString();
    }
}
