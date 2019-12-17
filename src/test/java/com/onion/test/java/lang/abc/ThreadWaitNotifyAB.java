package com.onion.test.java.lang.abc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;

public class ThreadWaitNotifyAB {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(2);
        int count = 10;
        Object lock = new Object();
        pool.execute(new Worker("A", count, lock));
        pool.execute(new Worker("B", count, lock));
        Thread.sleep(1000);
        pool.shutdownNow();
    }

    @AllArgsConstructor
    public static class Worker implements Runnable {
        private String key;
        private int count;
        private Object lock;

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                synchronized (lock) {
                    System.out.println(i + "," + key);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}