package com.pensumeditor.datastructures.directaccess;

import com.pensumeditor.datastructures.linear.ArrayList;
import com.pensumeditor.datastructures.linear.LinkedList;
import com.pensumeditor.datastructures.trees.AVLTree;

public class HashMap<K,V>{
    private ArrayList<LinkedList<Node>> hashTable;
    public class Node {
        private K key;
        private V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    private int numElements;
    private int size;
    private boolean loadFactor() {
        return (numElements/size) >= 1;
    }
    public HashMap(int size) {
        this.size = size;
        hashTable = new ArrayList<>(size);
        for(int i=0; i<hashTable.getSize(); i++)
            hashTable.add(new LinkedList<>());
    }

    private boolean containsKey(K key) {
        int index = hash(key);
        return hashTable.get(index).search(Node(key,Node.get)) >= 0; //no se como hacer esta parte
    }
    private int hash(K key) {
        if (key instanceof String || key instanceof Integer) {
            if (key instanceof String valueM) {
                int hashed = 0;
                for (int i = 0; i < valueM.length(); i++) {
                    hashed += valueM.charAt(i);
                }
                return hashed % size;
            } else {
                return (int) key % size;
            }
        }
        else {
            throw new TypeOfKeyStillNotSupportedException(); //falta revisar
        }
    }

    public void put(K key, V value) {
        int index = hash(key);
        hashTable.get(index).pushFront(new Node(key, value));
        numElements++;
    }
}
