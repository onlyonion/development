package com.onion.test.java.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TimerTest {

    @Test
    public void test() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getContextClassLoader());
            }
        }, 2000, 1000);

        TimeUnit.MINUTES.sleep(3);
    }

}
