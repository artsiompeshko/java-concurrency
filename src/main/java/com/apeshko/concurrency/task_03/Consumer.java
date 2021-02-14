package com.apeshko.concurrency.task_03;

public class Consumer implements Runnable {
    private final MessageBus messageBus;

    public Consumer(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = this.messageBus.take();

                if (message != null) {
                    System.out.println("Consumer: " + message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
