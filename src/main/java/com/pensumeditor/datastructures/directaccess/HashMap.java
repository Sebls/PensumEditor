package com.pensumeditor.datastructures.directaccess;

import com.pensumeditor.datastructures.exceptions.TypeOfKeyStillNotSupportedException;
import com.pensumeditor.datastructures.exceptions.KeyNotFoundException;
import com.pensumeditor.datastructures.linear.LinkedList;

import java.util.Objects;

public class HashMap<K,V>{
    public class MapNode {
        private K key;
        private V value;
        public MapNode(K key, V value) {
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
        return (numElements/size) > 0.9;
    }

    private LinkedList<MapNode>[] hashTable;
    public HashMap(int size) {
        this.size = size;
        hashTable = new LinkedList[size];
    }

    private boolean containsKey(K key) {
        int index = hash(key,size);
        if (Objects.nonNull(hashTable[index]) && hashTable[index].getSize() > 0) {
            for (int i=0; i<size; i++) {
                if (hashTable[index].get(i).getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int hash(K key,int m) {
        if (key instanceof String value) {
            int hashed = 0;
            for (int i = 0; i < value.length(); i++) {
                hashed += value.charAt(i);
            }
            return hashed % m;
        } else if (key instanceof Integer value){
            return value % m;
        } else {
            throw new TypeOfKeyStillNotSupportedException();
        }
    }

    public void put(K key, V value) {
        int index = hash(key,size);
        if (Objects.isNull(hashTable[index])) {
            hashTable[index] = new LinkedList<MapNode>();
        }
        hashTable[index].add(new MapNode(key,value));
        numElements++;
    }

    public V get(K key) {
        if (containsKey(key)) {
            int index = hash(key,size);
            for (int i=0; i<size; i++) {
                if (hashTable[index].get(i).getKey().equals(key)) {
                    return hashTable[index].get(i).getValue();
                }
            }
        }
        throw new KeyNotFoundException();
    }

    public void remove(K key) {
        if (containsKey(key)) {
            int index = hash(key,size);
            for (int i=0; i<size; i++) {
                if (hashTable[index].get(i).getKey().equals(key)) {
                    hashTable[index].remove(i);
                }
            }
        }
        throw new KeyNotFoundException();
    }

    private void rehash(){
        LinkedList<MapNode>[] newTable = new LinkedList[size*2];
        for (int i = 0;i < hashTable.length;i++){
             if (Objects.nonNull(hashTable[i])) {
                 for (int j = 0;j < hashTable[i].getSize();j++){
                     K key = hashTable[i].get(j).getKey();
                     V value = hashTable[i].get(j).getValue();
                     int index = hash(key,size*2);
                     if (Objects.isNull(newTable[index])) {
                         newTable[index] = new LinkedList<MapNode>();
                     }
                     newTable[index].add(new MapNode(key,value));
                 }
             }
        }
        size *= 2;
        hashTable = newTable;
    }
}
