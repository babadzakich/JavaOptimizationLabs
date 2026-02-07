package ru.nsu.chuvashov;

import java.util.ArrayList;

public class SimpleBean {
    private String name;
    private byte[] data;
    private ArrayList<String> list = new ArrayList<>();
    private int id;
    private double score;
    private SimpleBean next;

    public SimpleBean(String name, int id) {
        this.name = name;
        this.id = id;
        this.score = Math.random();
        this.data = new byte[1024];
        this.list.add("Item1 created at " + System.currentTimeMillis());
    }

    public void setNext(SimpleBean next) {
        this.next = next;
    }
}
