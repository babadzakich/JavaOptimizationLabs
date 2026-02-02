package ru.nsu.chuvashov;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static class HeavyObject {
        private int[] data;

        private String name;

        private HeavyObject related;

        public HeavyObject(int size, String name) {
            this.data = new int[size];
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<HeavyObject> list = new ArrayList<>();
        Runtime r = Runtime.getRuntime();
        System.out.println("Step;Max(MB);Total(MB);Free(MB);Used(MB)");
        long i = 0;
        try {
            while (true) {
                list.add(new HeavyObject(1000, "Object " + i));
                if (i % 10000 == 0) {
                    long maxMemory = r.maxMemory();
                    long totalMemory = r.totalMemory();
                    long freeMemory = r.freeMemory();
                    long usedMemory = totalMemory - freeMemory;

                    System.out.printf("%d;%d;%d;%d;%d%n",
                            i,
                            maxMemory / 1024 / 1024,
                            totalMemory / 1024 / 1024,
                            freeMemory / 1024 / 1024,
                            usedMemory / 1024 / 1024
                    );
                    Thread.sleep(10);
                }
                i++;
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Out of memory after allocating " + i + " objects.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}