package com.onion.test.distributed.netty.channel;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ChannelTest {

    @Test
    public void testNioEventLoopGroup() {
        NioEventLoopGroup group = new NioEventLoopGroup();

        group.submit(() -> System.out.println("task"));

    }

    @Test
    public void test() throws InterruptedException {
        DefaultEventLoop loop = new DefaultEventLoop();

        loop.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                log.info("hello {}", "world");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        TimeUnit.SECONDS.sleep(10);
    }
}
