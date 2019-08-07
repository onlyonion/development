package com.onion.test.java.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolExecutorTest {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch down = new CountDownLatch(5);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                System.out.println("start work");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("end work");
                down.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 2, TimeUnit.SECONDS);

        down.await();
    }

    @Test
    public void test2() throws InterruptedException {
        CountDownLatch down = new CountDownLatch(5);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                System.out.println("start work");
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("end work");
                //down.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 2, TimeUnit.SECONDS);

        down.await();
    }

}



