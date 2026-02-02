package ru.nsu.chuvashov;

import java.util.List;

public class BadThread implements Runnable {
    private final String name;

    public BadThread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("OOM GENERATOR THREAD " + name + " STARTED.");
        List<byte[]> memoryHog = new java.util.ArrayList<>();
        while (true){
            try {
                memoryHog.add(new byte[1024 * 1024]);
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
