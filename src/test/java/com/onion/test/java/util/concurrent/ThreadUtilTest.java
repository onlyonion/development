package com.onion.test.java.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadUtilTest {

    @Test
    public void countDownLatchTest() throws InterruptedException {

        CountDownLatch c = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                c.countDown();
                System.out.println(Thread.currentThread().getName());
            }).start();
        }

        c.await();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CountDownLatch");
    }

    @Test
    public void cyclicBarrierTest() throws InterruptedException {

        CyclicBarrier c = new CyclicBarrier(5, () -> System.out.println(Thread.currentThread().getName() + " hello"));

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    System.out.println(Thread.currentThread().getName());
                    c.await();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " come on");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("NumberWaiting" + c.getNumberWaiting());

            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier");
    }
}
