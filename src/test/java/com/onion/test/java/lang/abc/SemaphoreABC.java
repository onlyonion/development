package com.onion.test.java.lang.abc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author lijicong
 * @since 2019-12-05
 */
public class SemaphoreABC {

    public static void main(String[] args) throws InterruptedException {
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);

        ExecutorService poolService = Executors.newFixedThreadPool(3);
        Integer count = 10;
        poolService.execute(new Worker(a, b, "A", count));
        poolService.execute(new Worker(b, c, "B", count));
        poolService.execute(new Worker(c, a, "C", count));

        Thread.sleep(1000);
        poolService.shutdownNow();
    }

    public static class Worker implements Runnable {
        private String key;
        private Semaphore current;
        private Semaphore next;
        private Integer count;

        public Worker(Semaphore current, Semaphore next, String key, Integer count) {
            this.current = current;
            this.next = next;
            this.key = key;
            this.count = count;
        }

        public void run() {
            for (int i = 0; i < count; i++) {
                try {
                    //获取当前的锁
                    current.acquire(); //current - 1
                    System.out.println(i + "," + key);
                    //释放next的锁
                    next.release();    //next + 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
