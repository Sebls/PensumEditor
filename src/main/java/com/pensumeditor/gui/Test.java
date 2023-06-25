package com.pensumeditor.gui;

import com.pensumeditor.data.Subject;
import com.pensumeditor.datastructures.directaccess.HashMap;
import com.pensumeditor.datastructures.trees.AVLTree;

public class Test {
    public static void main(String[] args) {
        HashMap<Integer,String> hash = new HashMap<>(4);
        hash.put(2016722,"Matematicas");
        hash.put(1016735,"Arquitectura");
        hash.put(6216723,"Probabilidad");
        hash.put(7026738,"Algebra");
        hash.put(8286719,"Mecanica");
        hash.put(4086730,"Electricidad");
        System.out.println(hash.get(2016722));
        System.out.println(hash.get(1016735));
        System.out.println(hash.get(6216723));
        System.out.println(hash.get(7026738));
        System.out.println(hash.get(8286719));
        System.out.println(hash.get(4086730));

        /*hash.put("abcd", 304354);
        System.out.println(hash.get("abcd"));*/

    }
}
