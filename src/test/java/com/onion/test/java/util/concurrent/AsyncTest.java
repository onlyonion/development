package com.onion.test.java.util.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.nio.reactor.IOReactorException;
import org.junit.Test;

public class AsyncTest {

    RpcService rpcService = new RpcService();
    HttpService httpService = new HttpService();
    ComputeService computeService = new ComputeService();

    static ExecutorService executor = Executors.newFixedThreadPool(2);

    @Test
    public void synchronous() {
        long start = System.currentTimeMillis();

        long rpcResult = rpcService.getRpcResult();
        long httpResult = httpService.getHttpResult();
        computeService.compute(rpcResult, httpResult);

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时：" + time);
    }

    // oneway
    @Test
    public void asynchronous() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Future<Long> rpcFuture = executor.submit(() -> rpcService.getRpcResult());
        Future<Long> httpFuture = executor.submit(() -> httpService.getHttpResult());

        computeService.compute(rpcFuture.get(), httpFuture.get());

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时：" + time);
    }

    @Test
    public void asynchronousFuture() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Future<Long> rpcFuture = executor.submit(() -> rpcService.getRpcResult());
        Future<Long> httpFuture = executor.submit(() -> httpService.getHttpResult());

        Long aLong = rpcFuture.get();
        Long aLong1 = httpFuture.get();

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时：" + time);
    }

    @Test
    public void asynchronousCallback() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时：" + time);
    }

    @Test
    public void asynchronousCompletableFuture() throws InterruptedException {
        long start = System.currentTimeMillis();

        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> rpcService.getRpcResult());
        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(() -> httpService.getHttpResult());

        CompletableFuture<Void> exceptionally = CompletableFuture.allOf(future1, future2)
                .thenRunAsync(() -> {
                    try {
                        computeService.compute(future1.get(), future2.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时：" + time);

        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void asynchronousCompletableFuture2() throws InterruptedException {
        long start = System.currentTimeMillis();

        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> rpcService.getRpcResult());
        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(() -> httpService.getHttpResult());

        CompletableFuture<Long> future3 = CompletableFuture.supplyAsync(() -> rpcService.getRpcResult());
        CompletableFuture<Long> future4 = CompletableFuture.supplyAsync(() -> httpService.getHttpResult());

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时：" + time);

        TimeUnit.SECONDS.sleep(10);
    }

    static class RpcService {
        long getRpcResult() {
            long start = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("耗时：" + time);
            return time;
        }
    }

    static class HttpService {
        long getHttpResult() {
            long start = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("耗时：" + time);
            return time;
        }
    }

    static class ComputeService {
        long compute(long a, long b) {
            long start = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("耗时：" + time + " 计算：a+b=" + (a + b));
            return time;
        }
    }

    static class AsyncService {
        HttpAsyncClient httpAsyncClient = new DefaultHttpAsyncClient();

        AsyncService() throws IOReactorException {
        }

        CompletableFuture<String> getHttpData(String url) {
            CompletableFuture<String> completableFuture = new CompletableFuture<>();

            HttpAsyncRequestProducer producer = HttpAsyncMethods.create(new HttpGet(url));
            BasicAsyncResponseConsumer consumer = new BasicAsyncResponseConsumer();

            FutureCallback<HttpResponse> callback = new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse result) {
                    completableFuture.complete(String.valueOf(result));
                }

                @Override
                public void failed(Exception ex) {
                    completableFuture.completeExceptionally(ex);
                }

                @Override
                public void cancelled() {
                    completableFuture.cancel(true);
                }
            };
            httpAsyncClient.execute(producer, consumer, callback);
            return completableFuture;
        }

    }
}
