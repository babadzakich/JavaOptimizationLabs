package ru.nsu.chuvashov;

public class MyJNIClass {
    static {
        System.loadLibrary("myjni");
    }

    public native void devourMemoryAndKMS();

    public native void allocate1kb();

    public native void brickInside(int depth);

    public native int stringLength(String str);

    public native void callNigger(SimpleBean bean);

    public native void changeCottonField(SimpleBean bean, int newStuff);

    public native long allocSwag(int val1, double val2);
    public native String readSwag(long ptr);
    public native void freeSwag(long ptr);
}
