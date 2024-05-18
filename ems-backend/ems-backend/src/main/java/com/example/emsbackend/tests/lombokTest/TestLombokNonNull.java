package com.example.emsbackend.tests.lombokTest;

import lombok.NonNull;

public class TestLombokNonNull {
    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(() -> {
            autoDetectNull(null);
        },"t1");
        thread0.start();

        Thread thread1 = new Thread(() -> {
            manualDetectNull(null);
        },"t2");
        thread1.start();
        thread0.join();
        thread1.join();
    }

    public static void autoDetectNull(@NonNull String input) {
        System.out.println(input);
    }

    public static void manualDetectNull(String input) {
        if (input == null) {
            throw new NullPointerException("Input param is null");
        }
        System.out.println(input);
    }

}
