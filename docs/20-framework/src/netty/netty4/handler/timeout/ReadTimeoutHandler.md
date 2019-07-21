io.netty.handler.timeout.ReadTimeoutHandler

## hierarchy
```
ChannelHandlerAdapter (io.netty.channel)
    ChannelInboundHandlerAdapter (io.netty.channel)
        ChannelDuplexHandler (io.netty.channel)                     duplex 双工 复式 小复式 全双工
            IdleStateHandler (io.netty.handler.timeout)
                ReadTimeoutHandler (io.netty.handler.timeout)

```

## define
```java
public class ReadTimeoutHandler extends IdleStateHandler {
    private boolean closed;
    public ReadTimeoutHandler(int timeoutSeconds) {
        this(timeoutSeconds, TimeUnit.SECONDS);
    }
    public ReadTimeoutHandler(long timeout, TimeUnit unit) {
        super(timeout, 0, 0, unit);
    }

    @Override
    protected final void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        assert evt.state() == IdleState.READER_IDLE;
        readTimedOut(ctx);
    }
    protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
        if (!closed) {
            ctx.fireExceptionCaught(ReadTimeoutException.INSTANCE);
            ctx.close();
            closed = true;
        }
    }
}
```