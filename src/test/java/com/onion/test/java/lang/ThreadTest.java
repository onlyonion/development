package com.onion.test.java.lang;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadTest {

    @Test
    @SneakyThrows
    public void test() {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                log.info("t1 name {} InterruptedException1", Thread.currentThread().getName());
            }
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                log.info("t1 name {} InterruptedException2", Thread.currentThread().getName());
            }
        });
        Thread t2 = new Thread();
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(5);
        t1.interrupt();
        log.info("t1 1 isInterrupted {}", t1.isInterrupted());
        log.info("t1 2 isInterrupted {}", t1.isInterrupted());
    }


    @Test
    public void availableProcessors() {
        int i = Runtime.getRuntime().availableProcessors();
        long f = Runtime.getRuntime().freeMemory();
        long t = Runtime.getRuntime().totalMemory();

        System.out.println(i);
        System.out.println(f);
        System.out.println(t);

    }

    @Test
    public void testOOM() {
        for (; ; ) {
            new MyThread("a", "b");
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new MyThread(lockA, lockB).start();
        new MyThread(lockB, lockA).start();
    }

    private static class MyThread extends Thread {

        private String lockA;
        private String lockB;

        public MyThread(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {

                System.out.println(Thread.currentThread().getName() + " has lockA, try lock B");

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + " has lockA, lock B");

                }

            }
        }
    }
}

