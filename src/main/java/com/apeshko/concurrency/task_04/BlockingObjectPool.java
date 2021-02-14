package com.apeshko.concurrency.task_04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool {
    private int size;
    private final Queue<Object> pool;
    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final AtomicInteger length = new AtomicInteger();

    private void enqueue(Object object) {
        this.pool.add(object);

        this.length.incrementAndGet();

        System.out.println("Put new value: " + object.toString());

        notEmpty.signal();
    }

    private Object dequeue() {
        Object result = this.pool.poll();

        this.length.decrementAndGet();

        notFull.signal();

        return result;
    }

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.size = size;
        pool = new LinkedList<Object>();
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() throws InterruptedException {
        lock.lockInterruptibly();

        try {
            while (this.length.get() == 0) {
                notEmpty.await();
            }

            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void put(Object object) throws InterruptedException {
        lock.lockInterruptibly();

        try {
            while (this.length.get() == size) {
                System.out.println("Limit is reached. Waiting...");
                notFull.await();
            }

            enqueue(object);
        } finally {
             lock.unlock();
        }
    }
}
