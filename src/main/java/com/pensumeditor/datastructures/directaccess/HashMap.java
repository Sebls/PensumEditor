package com.pensumeditor.datastructures.directaccess;

import com.pensumeditor.datastructures.exceptions.TypeOfKeyStillNotSupportedException;
import com.pensumeditor.datastructures.exceptions.KeyNotFoundException;
import com.pensumeditor.datastructures.linear.LinkedList;

import java.util.Objects;
import java.util.Random;

public class HashMap<K, V>{

    private class MapNode {
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
        public void setKey(K key) {
            this.key = key;
        }
        public void setValue(V value) {
            this.value = value;
        }
    }

    // HashTable attributes
    private LinkedList<MapNode>[] hashTable;
    private int size;
    private int capacity;
    private double loadFactor;

    public HashMap() {
        this(16, 0.9);
    }

    public HashMap(int capacity) {
        this(capacity, 0.9);
    }

    public HashMap(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        hashTable = new LinkedList[capacity];
    }

    private int hash(K key, int m) {
        /*if (key instanceof Integer value){
            //return key.hashCode() % m;
            //return UniversalFamily(value, m);
            //return value % m;
        } else if (key instanceof String value) {
            return PolyHash(value, m);
            // return HashString(value, m);
        } else {
            return key.hashCode() % m;
        }*/
        return key.hashCode() % m;
    }


    // Hash Functions
    public int HashString(String s, int m) {
        int hash = 0;
        for (int i = 0; i < s.length(); i ++) {
            hash += s.charAt(i);
        }
        return hash % m;
    }

    public int PolyHash(String s, int m) {
        int hash = 0;
        for (int i = s.length()-1; i >= 0 ; i --) {
            hash = (hash * 31 + s.charAt(i)) % m;
        }
        return hash;
    }

    public int UniversalFamily(int key, int m) {
        int p = 10000019;
        int a = 31;
        int b = 7544;
        return ((a*key) % p + b % p) % m;
    }
    private int largestTailSize = 0;
    private int collisions = 0;

    public void put(K key, V value) {
        if (checkLoadFactor()) {
            rehash();
        }
        int index = hash(key, capacity);
        if (Objects.isNull(hashTable[index])) {
            hashTable[index] = new LinkedList<MapNode>();
        } /*else if (hashTable[index].getSize() > 0) {
            collisions ++;
        }
        if (hashTable[index].getSize() + 1 > largestTailSize) {
            largestTailSize = hashTable[index].getSize() + 1;
        }*/
        hashTable[index].add(new MapNode(key,value));
        size++;
    }

    public int getCollisions() {
        return collisions;
    }
    public int getLargestTailSize() {
        return largestTailSize;
    }

    public V get(K key) {
        int index = hash(key, capacity);
        if (Objects.nonNull(hashTable[index]) && hashTable[index].getSize() > 0) {
            for (int i = 0; i< capacity; i++) {
                if (hashTable[index].get(i).getKey().equals(key)) {
                    return hashTable[index].get(i).getValue();
                }
            }
        }
        return null;
        //throw new KeyNotFoundException();
    }

    public boolean remove(K key) {
        int index = hash(key, capacity);
        if (Objects.nonNull(hashTable[index]) && hashTable[index].getSize() > 0) {
            for (int i = 0; i < hashTable[index].getSize(); i++) {
                if (hashTable[index].get(i).getKey().equals(key)) {
                    hashTable[index].remove(i).getValue();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsKey(K key) {
        int index = hash(key, capacity);
        if (Objects.nonNull(hashTable[index]) && hashTable[index].getSize() > 0) {
            for (int i = 0; i < capacity; i++) {
                if (hashTable[index].get(i).getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLoadFactor() {
        return ((double) size / capacity) > loadFactor;
    }

    private void rehash(){
        LinkedList<MapNode>[] newTable = new LinkedList[capacity * 2];
        //this.a = random.nextInt(1, p-1);
        //this.b = random.nextInt(0, p-1);
        for (int i = 0;i < hashTable.length;i++){
             if (Objects.nonNull(hashTable[i])) {
                 for (int j = 0; j < hashTable[i].getSize(); j++){
                     K key = hashTable[i].get(j).getKey();
                     V value = hashTable[i].get(j).getValue();
                     int index = hash(key, capacity * 2);
                     if (Objects.isNull(newTable[index])) {
                         newTable[index] = new LinkedList<MapNode>();
                     }
                     newTable[index].add(new MapNode(key,value));
                 }
             }
        }
        capacity *= 2;
        hashTable = newTable;
    }
}
