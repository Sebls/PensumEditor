package com.pensumeditor.datastructures.linear;

import java.util.NoSuchElementException;

public class StackLinkedList<T> implements Stack<T> {

    class Node {
        private T data;
        private Node next;

        public Node (T x) {
            data = x;
            next = null;
        }
    }

    private Node head;
    private int size;

    public StackLinkedList() {
        head = null;
        size = 0;
    }

    public T getTop() {
        if (isEmpty()){
            throw new NoSuchElementException("No Such Element Exception");
        }
        return head.data;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void push(T data) {
        Node node = new Node(data);
        if (isEmpty()) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size ++;
    }

    public T pop() {
        T data = head.data;
        head = head.next;
        size --;
        return data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        String s = "]";
        Node node = head;
        while (node.next != null) {
            s = node.data + " " + s;
            node = node.next;
        }
        s = "[" + " " + node.data + " " +s;
        return s;
    }

}