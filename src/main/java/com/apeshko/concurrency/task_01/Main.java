package com.apeshko.concurrency.task_01;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /**
         * The first run with HashMap will fail because of ConcurrentModificationException.
         * The second one will work fine because of ConcurrentHashMap usage.
         * The third one will work fine, but ~10 time slower because of locking the whole map on reading and writing.
         */
        new DasExperiment(new HashMap<>()).run();
        new DasExperiment(new ConcurrentHashMap<>()).run();
        new DasExperiment(new ThreadSafeMap<>()).run();
    }
}
