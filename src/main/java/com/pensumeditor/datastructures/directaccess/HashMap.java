package com.pensumeditor.datastructures.directaccess;

import com.pensumeditor.datastructures.linear.ArrayList;
import com.pensumeditor.datastructures.linear.LinkedList;

public class HashMap<K,V>{
    private ArrayList<LinkedList<V>> hashTable;
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

    public boolean containsKey(int key) {
        return hashTable.get(key).isEmpty();
    }

    public boolean containsValue(V data) {
        int key = hash(data);
        return hashTable.get(key).search(data) >= 0;
    }

    private int hash(V value) {
        if (value instanceof String valueM) {
            int key = 0;
            for (int i = 0; i < valueM.length(); i++) {
                key += valueM.charAt(i);
            }
            return key % size;
        } else {
            return (int) value % size;
        }
    }

    public void put(V data) {
        int key = hash(data);
        hashTable.get(key).pushFront(data);
    }

    /*public V get(V data) {
        if (containsValue(data)) {
            return data;
        }
        else {

        }
    }*/
}
