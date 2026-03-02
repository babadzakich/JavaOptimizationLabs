package ru.nsu.chuvashov;

import com.sun.tools.attach.VirtualMachine;
import java.io.File;

public class Attacher {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java Attacher <PID>");
            return;
        }
        String pid = args[0];
        String agentPath = new File("myagent.jar").getAbsolutePath();
        
        System.out.println("Attaching to " + pid + " with " + agentPath);
        VirtualMachine vm = VirtualMachine.attach(pid);
        vm.loadAgent(agentPath);
        vm.detach();
        System.out.println("Detached.");
    }
}