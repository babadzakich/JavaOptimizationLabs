package ru.nsu.chuvashov;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<CompareClass> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(new CompareClass(r.nextInt(100)));
        }

        System.out.println("Before sorting: " + list);
        MethodClass.bubbleSort(list);
        System.out.println("After sorting: " + list);
    }
}