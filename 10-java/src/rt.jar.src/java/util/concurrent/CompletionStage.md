java.util.concurrent.CompletionStage

## hierarchy
```
CompletionStage (java.util.concurrent)
    RedisFuture (io.lettuce.core)
    CompletableFuture (java.util.concurrent)
        DelegatingCompletableFuture (org.springframework.util.concurrent)
        MonoToCompletableFuture (reactor.core.publisher)
        DefaultConnectionFuture (io.lettuce.core)
        AsyncCommand (io.lettuce.core.protocol)
            TimedAsyncCommand (io.lettuce.core.masterslave)
            TransactionalCommand (io.lettuce.core.protocol)
            TimedAsyncCommand (io.lettuce.core.cluster.topology)
        PipelinedRedisFuture (io.lettuce.core.cluster)
        Completable in Callback (org.eclipse.jetty.util)
        Completable in Promise (org.eclipse.jetty.util)
    ConnectionFuture (io.lettuce.core)
```