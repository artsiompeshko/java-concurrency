package com.apeshko.concurrency.task_03;

import java.util.Random;

public class Producer implements Runnable {
    private final MessageBus messageBus;
    private final Random random = new Random();

    public Producer(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                String message = "Message " + random.nextInt();
                this.messageBus.put(message);

                System.out.println("Producer: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
