package com.onion.test.java.lang.abc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;

public class ThreadWaitNotifyAB {

    private static volatile int state = 1;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(2);
        int count = 2;
        Object lock = new Object();
        pool.execute(new Worker("B", count, lock));
        pool.execute(new Worker("A", count, lock));
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
                if (key.equals("A")) {
                    synchronized (lock) {
                        while (state != 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(i + "," + key);
                        state = 2;
                        lock.notify();
                    }
                } else if (key.equals("B")) {
                    synchronized (lock) {
                        while (state != 2) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(i + "," + key);
                        state = 1;
                        lock.notify();
                    }
                }
            }
        }
    }

}