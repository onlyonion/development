package com.onion.test.java.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class BlockQueueTest {
    BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    @Test
    public void testBlockingQueue() throws InterruptedException {

        Thread thread = new Thread(() -> take());
        thread.start();

        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
        TimeUnit.SECONDS.sleep(3);
    }

    private void take() {
        String msg = null;
        try {
            System.out.println("queue.take");
            msg = queue.take();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException come, breaker");
            e.printStackTrace();
        }
        System.out.println(msg);
    }

}
