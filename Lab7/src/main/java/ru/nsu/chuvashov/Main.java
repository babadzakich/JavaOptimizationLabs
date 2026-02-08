package ru.nsu.chuvashov;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TargetApp app = new TargetApp();
        System.out.println("App started. PID: " + ProcessHandle.current().pid());

        while (true) {
            try {
                System.out.println("\n--- New Request ---");
                System.out.println(app.processData("User-" + System.currentTimeMillis()));
            } catch (Throwable e) {
                System.err.println("--- Request Failed globally ---");
            }
            Thread.sleep(3000);
        }
    }
}