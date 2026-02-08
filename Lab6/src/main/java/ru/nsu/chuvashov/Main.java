package ru.nsu.chuvashov;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void bubbleSort(List<CompareClass> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getValue() > list.get(j + 1).getValue()) {
                    CompareClass temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
    public static void main(String[] args) {
        List<CompareClass> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new CompareClass((int)(Math.random() * 100)));
        }
        System.out.println("Before sorting: " + list.stream().map(CompareClass::getValue).toList());
        bubbleSort(list);
        System.out.println("After sorting: " + list.stream().map(CompareClass::getValue).toList());
    }
}
class Ghost1 {
    public void usedMethod() {}
    public void unused1() {}
    public void unused2() {}
    public void unused3() {}
    public void unused4() {}
}

class Ghost2 { public void m1(){} public void m2(){} public void m3(){} public void m4(){} public void m5(){} }
class Ghost3 { public void m1(){} public void m2(){} public void m3(){} public void m4(){} public void m5(){} }
class Ghost4 { public void m1(){} public void m2(){} public void m3(){} public void m4(){} public void m5(){} }
class Ghost5 { public void m1(){} public void m2(){} public void m3(){} public void m4(){} public void m5(){} }