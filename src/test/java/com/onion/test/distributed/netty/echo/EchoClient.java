package com.onion.test.distributed.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class EchoClient {
    private final int remotePort;

    private final String remoteHost;

    public EchoClient(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient("127.0.0.1", 8088);
        echoClient.startup();
    }

    private void startup() {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    //TCP推包不延迟
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    })
                    .remoteAddress(new InetSocketAddress(remoteHost, remotePort));
            //连接到服务端，connect是异步连接，在调用同步等待sync，等待连接成功
            ChannelFuture channelFuture = bootstrap.connect().sync();
            //阻塞直到客户端通道关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            log.error("echo client error, {}", ex.getMessage(), ex);
        } finally {
            workGroup.shutdownGracefully();
        }
    }


}
