package ru.nsu.chuvashov;

public class SingletonClass {
    private static SingletonClass instance = new SingletonClass();
    public static SingletonClass getInstance() {
        return instance;
    }
    private int value = 42;
    private SingletonClass() {}
    public int getValue() {
        return value;
    }
}
