package com.onion.test.java.lang.abc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockABC {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();
    private static volatile int state = 1;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService poolService = Executors.newFixedThreadPool(3);
        Integer count = 10;
        poolService.execute(new Worker("A", count, 1, lock, conditionA, conditionB));
        poolService.execute(new Worker("B", count, 2, lock, conditionB, conditionC));
        poolService.execute(new Worker("C", count, 3, lock, conditionC, conditionA));
        Thread.sleep(5000);
        poolService.shutdownNow();
    }

    public static class Worker implements Runnable {
        private String key;
        private Integer count;
        private Lock lock;
        private Condition current;
        private Condition next;
        private int targetState;

        public Worker(String key, Integer count, int targetState, Lock lock, Condition cur, Condition next) {
            this.key = key;
            this.count = count;
            this.lock = lock;
            this.current = cur;
            this.next = next;
            this.targetState = targetState;
        }

        public void run() {
            this.lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (state != targetState) {
                        current.await();
                    }
                    System.out.println(i + "," + key);
                    state++;
                    if (state > 3) {
                        state = 1;
                    }
                    next.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
        }
    }
}
