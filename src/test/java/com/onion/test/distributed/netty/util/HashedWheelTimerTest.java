package com.onion.test.distributed.netty.util;

import io.netty.channel.epoll.Epoll;
import io.netty.channel.kqueue.KQueue;
import io.netty.util.HashedWheelTimer;
import io.netty.util.NettyRuntime;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HashedWheelTimerTest {

    public static void main(String[] args) throws InterruptedException {
        final HashedWheelTimer timer = new HashedWheelTimer(
                Executors.defaultThreadFactory(),
                100,
                TimeUnit.MILLISECONDS,
                32);

        timer.newTimeout(new TimerTask() {
            @Override
            public void run(final Timeout timeout) throws Exception {
                System.out.println("lee");   //打印名字

            }
        }, 1000, TimeUnit.MILLISECONDS);

        for (int i = 0; i < 10; i++) {
            final int j = i;
            timer.newTimeout(timeout -> System.out.println("task " + j),
                    1000 * j,
                    TimeUnit.MILLISECONDS);
        }

        // Thread.sleep(10000);
    }

    @Test(timeout = 50000000)
    public void testNettyRuntime() {
        System.out.println(NettyRuntime.availableProcessors());
        System.out.println(Epoll.isAvailable());
        System.out.println(KQueue.isAvailable());

        // Assert.assertFalse(timer.stop().isEmpty());
    }


}
