package com.onion.test.distributed.netty.tcppackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String msg = "hello world !!!" + System.getProperty("line.separator");
        byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
        for (int i = 0; i < 100; i++) {
            ByteBuf buf = Unpooled.buffer(bytes.length);
            buf.writeBytes(bytes);
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
