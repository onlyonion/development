package com.onion.test.java.lang;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void test() {
        for (; ; ) {
            System.out.println(new Random().nextInt());
        }
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


}

class MyThread extends Thread {

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