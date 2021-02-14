package com.apeshko.concurrency.task_01;

import java.util.Map;

public class DasExperiment {
    private Map<Integer, Integer> items;

    public DasExperiment(Map<Integer, Integer> items) {
        this.items = items;
    }

    public void run() throws InterruptedException {
        long started = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                items.put(i, i);
            }
        });

        Thread thread2 = new Thread(() -> {
            int sum = 0;

            for (int i = 0; i < 1_000; i++) {
                for (Integer key : items.keySet()) {
                    sum += items.get(key);
                }
            }

            System.out.println(sum);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Finished after " + (System.currentTimeMillis() - started)+ " ms");
    }
}
