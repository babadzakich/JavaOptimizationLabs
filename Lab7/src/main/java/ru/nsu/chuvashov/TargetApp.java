package ru.nsu.chuvashov;

import java.util.Random;

public class TargetApp {
    @Reliable
    public String processData(String input) {
        System.out.println("   (Inside Method) Processing: " + input);

        if (new Random().nextInt(10) < 7) { // 70% шанс ошибки
            throw new RuntimeException("Something went wrong!");
        }
        return "Processed-" + input;
    }
}
