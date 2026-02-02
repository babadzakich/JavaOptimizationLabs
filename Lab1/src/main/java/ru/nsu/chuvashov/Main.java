package ru.nsu.chuvashov;

public class Main {
    public static void main(String[] args) {
        MyJNIClassTest testClass = new MyJNIClassTest();

//        testClass.devourMemoryAndKMS();
//        testClass.brickInside();
        testClass.allocate1kb();
        testClass.stringLength();
        testClass.callNigger();
        testClass.changeCottonField();
        testClass.allocSwag();
    }
}

class MyJNIClassTest {
    MyJNIClass myJNIClass = new MyJNIClass();

    void devourMemoryAndKMS() {
        System.out.println("And now ill show you true suffering...");
        myJNIClass.devourMemoryAndKMS();
        System.out.println("If you see this, you are NIGGER");
    }

    void allocate1kb() {
        System.out.println("Lets check native memory prikoli");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total: " + rt.totalMemory());
        System.out.println("Free: " + rt.freeMemory());
        myJNIClass.allocate1kb();
        rt = Runtime.getRuntime();
        System.out.println("Total: " + rt.totalMemory());
        System.out.println("Free: " + rt.freeMemory());
        System.out.println("Java and native heap are two different heaps");
    }

    void brickInside() {
        System.out.println("Calling my homies to catch your ass");
        myJNIClass.brickInside(0);
        System.out.println("Wow nigga, you escaped");
    }

    void stringLength() {
        String psalm = "Turn my lights on";
        if (psalm.length() != myJNIClass.stringLength(psalm)) {
            System.err.println("Wow, jni doesnt work");
        } else {
            System.out.println("JNI WORKS " + psalm.length());
        }
    }

    void callNigger() {
        SimpleBean bean = new SimpleBean(42, "Epstein");
        myJNIClass.callNigger(bean);
    }

    void changeCottonField() {
        SimpleBean bean = new SimpleBean(1487, "Adolf");
        bean.printId();
        myJNIClass.changeCottonField(bean, 322);
        bean.printId();
    }

    void allocSwag() {
        long ptr = myJNIClass.allocSwag(42, 1.487);
        System.out.println("Указатель на структуру: " + Long.toHexString(ptr));
        System.out.println("Данные из структуры: " + myJNIClass.readSwag(ptr));
        myJNIClass.freeSwag(ptr);
        System.out.println("Память освобождена.");
    }
}
