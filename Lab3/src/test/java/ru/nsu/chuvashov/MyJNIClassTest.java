package ru.nsu.chuvashov;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyJNIClassTest {
    MyJNIClass myJNIClass = new MyJNIClass();

    @Test
    void devourMemoryAndKMS() {
        System.out.println("And now ill show you true suffering...");
        myJNIClass.devourMemoryAndKMS();
        System.out.println("If you see this, you are GAY NIGGER FAGGOT");
    }

    @Test
    void allocate1kb() {
        System.out.println("Lets check native memory prikoli");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total: " + rt.totalMemory());
        System.out.println("Free: " + rt.freeMemory());
        myJNIClass.allocate1kb();
        System.out.println("And what happened now?");
        rt = Runtime.getRuntime();
        System.out.println("Total: " + rt.totalMemory());
        System.out.println("Free: " + rt.freeMemory());
    }

    @Test
    void brickInside() {
        System.out.println("Calling my homies to catch your ass");
        myJNIClass.brickInside(0);
        System.out.println("Wow nigga, you escaped");
    }

    @Test
    void stringLength() {
        String psalm = "Turn my lights on";
        assertEquals(psalm.length(), myJNIClass.stringLength(psalm), "Wow, jni doesnt work");
    }

    @Test
    void callNigger() {
        SimpleBean bean = new SimpleBean(42, "Epstein");
        myJNIClass.callNigger(bean);
    }

    @Test
    void changeCottonField() {
        SimpleBean bean = new SimpleBean(1488, "Adolf");
        myJNIClass.changeCottonField(bean, 322);
    }

    @Test
    void allocSwag() {
        long ptr = myJNIClass.allocSwag(42, 1.488);
        System.out.println("Указатель на структуру: " + Long.toHexString(ptr));
        System.out.println("Данные из структуры: " + myJNIClass.readSwag(ptr));
        myJNIClass.freeSwag(ptr);
        System.out.println("Память освобождена.");
    }
}