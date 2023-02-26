/**
 * 处理TCP粘包拆包方案
 * 1、根据换行符处理半包读写问题
 * @see io.netty.handler.codec.LineBasedFrameDecoder
 * @see io.netty.handler.codec.string.StringDecoder
 * 2、自定义分隔符处理
 * @see io.netty.handler.codec.DelimiterBasedFrameDecoder
 * @see io.netty.handler.codec.string.StringDecoder
 * 3、自定义半包读写器处理
 * @see io.netty.handler.codec.LengthFieldBasedFrameDecoder
 * @see io.netty.handler.codec.string.StringDecoder
 */
package com.onion.test.distributed.netty.tcppackage;