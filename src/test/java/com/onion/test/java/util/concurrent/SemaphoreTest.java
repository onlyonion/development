package com.onion.test.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    @Test
    public void test() throws InterruptedException {
        Semaphore semphore = new Semaphore(100);
        // 请求处理
        if (semphore.getQueueLength() > 100) {
            return;
        }
        try {
            semphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semphore.release();
        }
    }

}
