package com.apeshko.concurrency.task_02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DeadLocks {
    private List<Integer> items = new ArrayList<>();
    private Random random = new Random();
    private Integer lock = 0;

    public void run() {
        Thread thread1 = new Thread(() -> {
            while(true) {
                synchronized (lock) {
                    items.add(random.nextInt());

                    System.out.println("New item");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while(true) {
                synchronized (lock) {
                    int sum = 0;

                    for (Integer item : items) {
                        sum += item;
                    }

                    System.out.println("Sum is" + sum);
                    System.out.println("length: " + items.size());
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while(true) {
                synchronized (lock) {
                    int squareSum = 0;

                    for (Integer item: items) {
                        squareSum += Math.pow(item, 2);
                    }

                    System.out.println("square root of sum of squares " + Math.sqrt(squareSum));
                    System.out.println("length: " + items.size());
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
