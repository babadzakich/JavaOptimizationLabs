package ru.nsu.chuvashov;

class SimpleBean {
    private int id;
    public double score;

    public String name;

    public SimpleBean nestedBean;

    public int[] numbers;
    public String[] tags;

    public SimpleBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void sayShit() {
        System.out.println("Sometimes i dream about committing mass murder...");
    }

    public int getId() {
        return id;
    }

    public void printId() {
        System.out.println("My id is " + id + ", and thats all");
    }
}