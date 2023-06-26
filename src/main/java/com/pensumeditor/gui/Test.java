package com.pensumeditor.gui;

import com.pensumeditor.data.Subject;
import com.pensumeditor.datastructures.directaccess.HashMap;
import com.pensumeditor.datastructures.trees.AVLTree;

import java.util.Random;

public class Test {
    static Random random = new Random();
    static double duracion_put = 0;
    static double duracion_get = 0;
    static double duracion_remove = 0;
    static int testnumber = 0;
    public static void main(String[] args) {
        HashMap<Integer,String> hash = new HashMap<>(16);

        executeTest(hash, 10000); // 10.000
        executeTest(hash, 40000); // 50.000
        executeTest(hash, 50000); // 100.000
        executeTest(hash, 100000); // 200.000
        executeTest(hash, 300000); // 500.000
        executeTest(hash, 500000); // 1.000.000
        executeTest(hash, 1000000); // 2.000.000
        executeTest(hash, 3000000); // 5.000.000
        //executeTest(hash, 5000000); // 10.000.000

    }
    public static void executeTest(HashMap hashMap, int n) {
        System.out.println("---------------");
        testnumber += n;
        System.out.println(testnumber + " Datos: ");
        long inicio = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.put(random.nextInt(255), random.nextInt(255));
        }
        long fin = System.nanoTime();
        duracion_put += (fin - inicio) / 1000000.0;
        System.out.print("Put Duration: ");
        System.out.printf("%.3f%n", duracion_put); // Imprime el valor con 2 decimales
        System.out.println("Colisiones totales: " + hashMap.getCollisions());
        System.out.println("Cola mas larga: " + hashMap.getLargestTailSize());

        inicio = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.get(random.nextInt(255));
        }
        fin = System.nanoTime();
        duracion_get += (fin - inicio) / 1000000.0;
        System.out.print("Get Duration: ");
        System.out.printf("%.3f%n", duracion_get); // Imprime el valor con 2 decimales

        inicio = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.remove(random.nextInt(255));
        }
        fin = System.nanoTime();
        duracion_remove += (fin - inicio) / 1000000.0;
        System.out.print("Remove Duration: ");
        System.out.printf("%.3f%n", duracion_remove); // Imprime el valor con 2 decimales
    }

}
