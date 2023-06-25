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

    //HashFunction for integers parameters
    private final Random random;
    private int a, b;
    private int p;

    public HashMap() {
        this(16, 0.9);
    }

    public HashMap(int capacity) {
        this(capacity, 0.9);
    }

    public HashMap(int capacity, double loadFactor) {
        random = new Random();
        this.p = 10000019;
        this.a = random.nextInt(1, p-1);
        this.b = random.nextInt(0, p-1);
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        hashTable = new LinkedList[capacity];
    }


    private int hash(K key, int m) {
        if (key instanceof String value) {
            return modFunction(PolyHash(value), capacity);
        } else if (key instanceof Integer value){
            return modFunction(modFunction(a*value+b, p), m);
        } else {
            throw new TypeOfKeyStillNotSupportedException();
        }
    }

    public int PolyHash(String s) {
        int hash = 0;
        for (int i = s.length()-1; i >= 0 ; i --) {
            hash = modFunction(hash * 31 + s.charAt(i), p);
        }
        return hash;
    }

    public int modFunction(int a, int mod) {
        return (a % mod + mod) % mod;
    }

    public void put(K key, V value) {
        if (checkLoadFactor()) {
            rehash();
        }
        int index = hash(key, capacity);
        if (Objects.isNull(hashTable[index])) {
            hashTable[index] = new LinkedList<MapNode>();
        }
        hashTable[index].add(new MapNode(key,value));
        size++;
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
        throw new KeyNotFoundException();
    }

    public V remove(K key) {
        int index = hash(key, capacity);
        if (Objects.nonNull(hashTable[index]) && hashTable[index].getSize() > 0) {
            for (int i = 0; i < capacity; i++) {
                if (hashTable[index].get(i).getKey().equals(key)) {
                    return hashTable[index].remove(i).getValue();
                }
            }
        }
        throw new KeyNotFoundException();
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
        this.a = random.nextInt(1, p-1);
        this.b = random.nextInt(0, p-1);
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
