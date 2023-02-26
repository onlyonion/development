package com.onion.test.distributed.netty.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.junit.Test;

public class ReactorTest {

    @Test
    public void testSingleThreadReactor() {
        EventLoopGroup group = new NioEventLoopGroup(1);
        ServerBootstrap b = new ServerBootstrap();
        b.group(group);
    }

    @Test
    public void testMultiThreadReactor() {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(group);
    }

    @Test
    public void testMainSubReactor() {
        // 主 Reactor 负责处理 Accept，然后把 Channel 注册到从 Reactor 上
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从 Reactor 主要负责 Channel 生命周期内的所有 I/O 事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
    }

}
