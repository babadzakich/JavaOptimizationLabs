package ru.nsu.chuvashov;

import java.lang.instrument.Instrumentation;

public class JavaAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Java Agent started with args: " + agentArgs);
        inst.addTransformer(new RetryTransformer());
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Java Agent attached with args: " + agentArgs);
        inst.addTransformer(new RetryTransformer(), true);

        try {
            for (Class<?> clazz : inst.getAllLoadedClasses()) {
                if (clazz.getName().equals("ru.nsu.chuvashov.TargetClass")) {
                    inst.retransformClasses(clazz);
                    System.out.println("Transformed class: " + clazz.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
