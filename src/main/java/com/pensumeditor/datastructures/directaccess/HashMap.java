package com.pensumeditor.datastructures.directaccess;

import com.pensumeditor.datastructures.exceptions.TypeOfKeyStillNotSupportedException;
import com.pensumeditor.datastructures.linear.ArrayList;
import com.pensumeditor.datastructures.linear.LinkedList;
import com.pensumeditor.datastructures.trees.AVLTree;

import java.util.Objects;

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
        if (Objects.nonNull(hashTable.get(index)) && hashTable.get(index).getSize() > 0) {
            for (int i=0; i<size; i++) {
                if (hashTable.get(index).get(i).getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int hash(K key) {
        if (key instanceof String value) {
            int hashed = 0;
            for (int i = 0; i < value.length(); i++) {
                hashed += value.charAt(i);
            }
            return hashed % size;
        } else if (key instanceof Integer value){
            return value % size;
        } else {
            throw new TypeOfKeyStillNotSupportedException();
        }
    }

    public void put(K key, V value) {
        int index = hash(key);
        hashTable.get(index).pushFront(new Node(key, value));
        numElements++;
    }

}
