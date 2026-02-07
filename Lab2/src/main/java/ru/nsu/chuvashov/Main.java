package ru.nsu.chuvashov;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<SimpleBean> beans = new java.util.ArrayList<>();
        beans.add(new SimpleBean("Bean1", 1));
        beans.add(new SimpleBean("Bean2", 2));
        beans.add(new SimpleBean("Bean3", 3));
        beans.get(0).setNext(beans.get(1));
        beans.get(1).setNext(beans.get(2));
        beans.get(2).setNext(beans.get(0));

        System.out.println("Starting " + ProcessHandle.current().pid());
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new GoodThread("GoodThread-" + i));
            t.start();
        }

        if (args.length > 0 && args[0].equals("oom")) {
            Thread t = new Thread(new BadThread("OOM  DESTROYER"));
            t.start();
        }
    }
}
