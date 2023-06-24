package com.pensumeditor.datastructures.directaccess;

public class HashTable {
    private class Node {
        String key;
        Integer value;
        Node next;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] table;
    private int size;

    public HashTable(int size) {
        this.table = new Node[size];
        this.size = size;
    }

    public void put(String key, Integer value) {
        int hash = key.hashCode() % size;
        Node node = new Node(key, value);

        // Check if the key already exists in the hash table.
        Node current = table[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // The key does not exist, so add it to the hash table.
        node.next = table[hash];
        table[hash] = node;
    }

    public Integer get(String key) {
        int hash = key.hashCode() % size;
        Node current = table[hash];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        // The key does not exist, so return null.
        return null;
    }

    public void remove(String key) {
        int hash = key.hashCode() % size;
        Node current = table[hash];
        Node previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    table[hash] = current.next;
                } else {
                    previous.next = current.next;
                }
                return;
            }
            previous = current;
            current = current.next;
        }

        // The key does not exist, so do nothing.
    }
}
