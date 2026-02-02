package ru.nsu.chuvashov;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double [] a1, b1, a2, b2;
        a1 = new double[128 * 128];
        b1 = new double[128 * 128];
        a2 = new double[1024 * 1024];
        b2 = new double[1024 * 1024];
        for (int i = 0; i < 128 * 128; i++) {
            a1[i] = Math.random();
            b1[i] = Math.random();
        }
        for (int i = 0; i < 1024 * 1024; i++) {
            a2[i] = Math.random();
            b2[i] = Math.random();
        }

        JNIMatrixMul jniMul = new JNIMatrixMul();
        JavaMatrixMul javaMul = new JavaMatrixMul();
        long startTime, endTime;
        System.out.println("Java 128x128:");
        startTime = System.nanoTime();
        javaMul.multiply(a1, b1, 128);
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000 + " ms");
        System.out.println("JNI 128x128:");
        startTime = System.nanoTime();
        jniMul.multiply(a1, b1, 128);
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000 + " ms");
        System.out.println("Java 1024x1024:");
        startTime = System.nanoTime();
        javaMul.multiply(a2, b2, 1024);
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000 + " ms");
        System.out.println("JNI 1024x1024:");
        startTime = System.nanoTime();
        jniMul.multiply(a2, b2, 1024);
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000 + " ms");
    }
}