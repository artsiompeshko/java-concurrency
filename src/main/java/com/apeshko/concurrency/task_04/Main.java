package com.apeshko.concurrency.task_04;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BlockingObjectPool blockingObjectPool = new BlockingObjectPool(10);

        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Got: " + blockingObjectPool.get());

                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    int value = new Random().nextInt();
                    blockingObjectPool.put(value);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
