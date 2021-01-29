package com.example;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;

public class Agent {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        var executor = Executors.newFixedThreadPool(1);
        executor.submit((Runnable) () -> {
            long count = 0;
            while (true) {
                System.out.println("Agent still running, count " + count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Error!", e);
                }
                count++;
            }
        });
    }

}