package com.apeshko.concurrency.task_03;

public interface MessageBus {
    void put(String message) throws InterruptedException;
    String take() throws InterruptedException;
}
