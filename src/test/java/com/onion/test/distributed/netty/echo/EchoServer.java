package com.onion.test.distributed.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    private void startup() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(workGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    //TCP半连接队列长度
                    .option(ChannelOption.SO_BACKLOG, 10000)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //绑定端口
            ChannelFuture future = serverBootstrap.bind(port).sync();
            log.info("echo server already startup");
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
            log.info("echo server already close");
        } catch (Exception ex) {
            log.error("echo server error, {}", ex.getMessage(), ex);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        EchoServer server = new EchoServer(8088);
        server.startup();
    }
}
