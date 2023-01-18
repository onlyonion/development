com.alibaba.csp.sentinel.cluster.server.NettyTransportServer

## define
```java
    private final int port;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    private final ConnectionPool connectionPool = new ConnectionPool();

    private final AtomicInteger currentState = new AtomicInteger(SERVER_STATUS_OFF);
    private final AtomicInteger failedTimes = new AtomicInteger(0);

```

## methods
```java

   public void start() {
        if (!currentState.compareAndSet(SERVER_STATUS_OFF, SERVER_STATUS_STARTING)) {
            return;
        }

        ServerBootstrap b = new ServerBootstrap();
        this.bossGroup = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup(DEFAULT_EVENT_LOOP_THREADS);
        b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 128)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
                    p.addLast(new NettyRequestDecoder());
                    p.addLast(new LengthFieldPrepender(2));
                    p.addLast(new NettyResponseEncoder());
                    p.addLast(new TokenServerHandler(connectionPool));
                }
            })
            .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
            .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .childOption(ChannelOption.SO_TIMEOUT, 10)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childOption(ChannelOption.SO_RCVBUF, 32 * 1024);
        b.bind(port).addListener(new GenericFutureListener<ChannelFuture>() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.cause() != null) {
                    RecordLog.info("[NettyTransportServer] Token server start failed (port=" + port + "), failedTimes: " + failedTimes.get(),
                        future.cause());
                    currentState.compareAndSet(SERVER_STATUS_STARTING, SERVER_STATUS_OFF);
                    int failCount = failedTimes.incrementAndGet();
                    if (failCount > MAX_RETRY_TIMES) {
                        return;
                    }

                    try {
                        Thread.sleep(failCount * RETRY_SLEEP_MS);
                        start();
                    } catch (Throwable e) {
                        RecordLog.info("[NettyTransportServer] Failed to start token server when retrying", e);
                    }
                } else {
                    RecordLog.info("[NettyTransportServer] Token server started success at port {}", port);
                    currentState.compareAndSet(SERVER_STATUS_STARTING, SERVER_STATUS_STARTED);
                }
            }
        });
    }

```