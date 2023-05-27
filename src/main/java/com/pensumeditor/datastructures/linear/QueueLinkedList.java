package com.pensumeditor.datastructures.linear;

import java.util.NoSuchElementException;

public class QueueLinkedList<T> implements Queue<T> {

    class Node {
        private T data;
        private Node next;

        public Node (T x) {
            data = x;
            next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public QueueLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public T getFront() {
        if (isEmpty()){
            throw new NoSuchElementException("No Such Element Exception");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void enqueue(T data) {
        Node node = new Node(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size ++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element Exception");
        }
        T data = head.data;
        head = head.next;
        size --;
        return data;
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "[ ]";
        }
        String s = "[ ";
        Node node = head;
        while (node.next != null) {
            s += node.data + " ";
            node = node.next;
        }
        s += node.data + " ]";
        return s;
    }

}
