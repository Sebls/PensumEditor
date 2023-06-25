package com.pensumeditor.gui;

import com.pensumeditor.data.Subject;
import com.pensumeditor.datastructures.directaccess.HashMap;
import com.pensumeditor.datastructures.trees.AVLTree;

public class Test {
    public static void main(String[] args) {
        HashMap<Integer,String> hash = new HashMap<>(16);
        hash.put(543,"mate");
        hash.put(559,"arqui");
        System.out.println(hash.get(543));
        System.out.println(hash.get(559));
        hash.remove(543);
        System.out.println(hash.get(543));

    }
}
