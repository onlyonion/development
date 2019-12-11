package com.onion.test.java.lang.abc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lijicong
 * @since 2019-12-05
 */
public class SynchronizedABC {

    private static volatile Integer state = new Integer(1);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService poolService = Executors.newFixedThreadPool(3);
        Integer count = 10;
        Object lock = new Object();
        poolService.execute(new Worker("A", count, lock));
        poolService.execute(new Worker("B", count, lock));
        poolService.execute(new Worker("C", count, lock));
        Thread.sleep(1000);
        poolService.shutdownNow();
    }

    public static class Worker implements Runnable {
        private String key;
        private Integer count;
        private Object lock;
        public Worker(String key, Integer count, Object lock) {
            this.key = key;
            this.count = count;
            this.lock  = lock;
        }
        public void run() {
            for(int i = 0; i < count; i++) {
                if(key == "A") {
                    synchronized (lock) {
                        while (state != 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(i+","+key);
                        state = 2;
                        lock.notifyAll();
                    }
                } else if(key == "B") {
                    synchronized (lock) {
                        while (state != 2) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(i+","+key);
                        state = 3;
                        lock.notifyAll();
                    }
                } else if (key == "C") {
                    synchronized (lock) {
                        while (state != 3) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(i+","+key);
                        state = 1;
                        lock.notifyAll();
                    }
                }
            }
        }
    }
}
