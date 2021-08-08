package com.onion.test.java.lang;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadJoinTest {

    @Test
    public void test() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                // sleep并不释放锁，并且sleep的暂停和wait暂停是不一样的。
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        // join() 方法使当 前线程停下来等待 ，直至另一个调用join方法的线程 终止。
        thread.join();
        System.out.println(Thread.currentThread().getName());
    }

}
