package com.example;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        long count = 0;
        while (true) {
            System.out.println("App still running, count " + count);
            Thread.sleep(1000);
            count++;
        }
    }

}
