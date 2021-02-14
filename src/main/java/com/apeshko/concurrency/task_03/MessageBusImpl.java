package com.apeshko.concurrency.task_03;

import java.util.LinkedList;
import java.util.Queue;

public class MessageBusImpl implements MessageBus {
    private final Queue<String> queue;

    public MessageBusImpl() {
        queue = new LinkedList<>();
    }

    @Override
    public void put(String message) throws InterruptedException {
        synchronized (queue) {
            queue.add(message);
        }
    }

    @Override
    public String take() throws InterruptedException {
        synchronized (queue) {
            return queue.poll();
        }
    }
}
