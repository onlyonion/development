package com.onion.test.java.util.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolExecutorTest {

    @Test
    public void testScheduledExecutorService() throws InterruptedException {
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
    public void testMyThreadPoolExecutor() {
        MyThreadPoolExecutor pool = new MyThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        // pool.shutdown(); // java.util.concurrent.RejectedExecutionException
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getName()));
            pool.shutdown(); // java.util.concurrent.RejectedExecutionException
        }
    }

    static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            print("beforeExecute");
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            print("afterExecute");
        }

        @Override
        protected void terminated() {
            print("terminated");
        }

        private void print(String name) {
            System.out.println(name + " isShutdown:" + isShutdown());
            System.out.println(name + " isTerminating:" + isTerminating());
            System.out.println(name + " isTerminated:" + isTerminated());
        }
    }

}



