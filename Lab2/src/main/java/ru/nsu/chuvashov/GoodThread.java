package ru.nsu.chuvashov;

public class GoodThread implements Runnable {
    private final String name;
    private final SingletonClass singletonRef;
    private final SimpleBean beanRef;


    public GoodThread(String name) {
        this.name = name;
        this.singletonRef = SingletonClass.getInstance();
        this.beanRef = new SimpleBean("Bean for " + name, name.hashCode());
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " is running.");
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
