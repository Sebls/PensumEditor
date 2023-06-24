package com.pensumeditor.datastructures.trees;

public class Heap {
    private int end;
    private int size;
    private int[] array;

    public Heap(int size) {
        this.end = -1;
        this.size = size;
        this.array = new int[size];
    }

    public int getParent(int index) {
        return Math.floorDiv(index - 1, 2);
    }

    public int getLeftChild(int index) {
        return 2*index + 1;
    }

    public int getRightChild(int index) {
        return 2*index + 2;
    }

    public void SiftUp(int index) {
        while (index > 0 && array[getParent(index)] < array[index]) {
            int temp = array[getParent(index)];
            array[getParent(index)] = array[index];
            array[index] = temp;
            index = getParent(index);
        }
    }

    public void SiftDown(int index) {
        int maxIndex = index;
        int l = getLeftChild(index);
        if (l <= end && array[l] > array[maxIndex]) {
            maxIndex = l;
        }
        int r = getRightChild(index);
        if (r <= end && array[r] > array[maxIndex]) {
            maxIndex = r;
        }
        if (index != maxIndex) {
            int temp = array[index];
            array[index] = array[maxIndex];
            array[maxIndex] = temp;
            SiftDown(maxIndex);
        }
    }

    public void insert(int p) {
        if (end == size) {
            System.out.println("El array esta lleno");
            return;
        }
        end ++;
        array[end] = p;
        SiftUp(end);
    }

    public int getMax() {
        return array[0];
    }

    public int extractMax() {
        int result = array[0];
        array[0] = array[end];
        end --;
        SiftDown(0);
        return result;
    }

    public int remove(int index) {
        int removed = array[index];
        array[index] = 2147483647;
        SiftUp(index);
        extractMax();
        return removed;
    }

    public int ChangePriority(int index, int p) {
        int oldp = array[index];
        array[index] = p;
        if (p > oldp) {
            SiftUp(index);
        } else {
            SiftDown(index);
        }
        return oldp;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i=0; i <= end; i++ ) {
            s += " " + array[i] + " ";
        }
        s += "]";
        return s;
    }
}
