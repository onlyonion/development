com.alibaba.csp.sentinel.cluster.client.NettyTransportClient


```java
private Bootstrap initClientBootstrap() {
        Bootstrap b = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup();
        b.group(eventLoopGroup)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, ClusterClientConfigManager.getConnectTimeout())
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    clientHandler = new TokenClientHandler(currentState, disconnectCallback);

                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
                    pipeline.addLast(new NettyResponseDecoder());
                    pipeline.addLast(new LengthFieldPrepender(2));
                    pipeline.addLast(new NettyRequestEncoder());
                    pipeline.addLast(clientHandler);
                }
            });

        return b;
    }

    private void connect(Bootstrap b) {
        if (currentState.compareAndSet(ClientConstants.CLIENT_STATUS_OFF, ClientConstants.CLIENT_STATUS_PENDING)) {
            b.connect(host, port)
                .addListener(new GenericFutureListener<ChannelFuture>() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    if (future.cause() != null) {
                        RecordLog.warn(
                            String.format("[NettyTransportClient] Could not connect to <%s:%d> after %d times",
                                host, port, failConnectedTime.get()), future.cause());
                        failConnectedTime.incrementAndGet();
                        channel = null;
                    } else {
                        failConnectedTime.set(0);
                        channel = future.channel();
                        RecordLog.info("[NettyTransportClient] Successfully connect to server <{}:{}>", host, port);
                    }
                }
            });
        }
    }
```