package com.onion.test.distributed.netty.tcppackage;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomServer {
    private final int port;

    public CustomServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        CustomServer server = new CustomServer(8099);
        server.startup();
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
                            //粘包处理
                            //1、通过换行符分割
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //2、通过自定义分隔符
                            //ByteBuf delimiter = Unpooled.copiedBuffer("\n", CharsetUtil.UTF_8);
                            //ch.pipeline().addLast(new DelimiterBasedFrameDecoder(2048, delimiter));
                            // TODO: 2021/11/15 3、自定义长度 LengthFieldBasedFrameDecoder
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new CustomServerHandler());
                        }
                    });
            //绑定端口
            ChannelFuture future = serverBootstrap.bind(port).sync();
            log.info("server already startup");
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
            log.info("server already close");
        } catch (Exception ex) {
            log.error("server error, {}", ex.getMessage(), ex);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

}
