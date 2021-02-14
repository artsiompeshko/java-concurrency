package com.apeshko.concurrency.task_03;

public class Main {
    public static void main(String[] args) {
        MessageBus messageBus = new MessageBusImpl();

        Consumer consumer = new Consumer(messageBus);
        Producer producer = new Producer(messageBus);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
