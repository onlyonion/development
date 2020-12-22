package com.onion.test.common.guava.cache;

import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    private LoadingCache<Long, Long> cache = Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
            .build(CaffeineTest::get);

    private AsyncLoadingCache<Long, Long> graphs = Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
            .buildAsync(CaffeineTest::getNull);

    private AsyncLoadingCache<Long, Long> asyncLoadingCache = Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
            .buildAsync(new AsyncCacheLoader<Long, Long>() {
                @Override
                public @NonNull CompletableFuture<Long> asyncLoad(@NonNull Long key, @NonNull Executor executor) {
                    System.out.println(key);
                    System.out.println(executor);
                    return CompletableFuture.supplyAsync(() -> get(key), executor);
                }
            });

    private static Long get(Long advertId) {
        System.out.println("get advertId=" + advertId);
        // throw new NullPointerException();
        return advertId;
        // return null;
    }

    private static Long getNull(Long advertId) {
        return null;
    }

    @Test
    public void testGet() {
        cache.get(1L);
        cache.get(1L);
    }

    @Test
    public void testGetNull() {
        graphs.get(1L);
        graphs.get(1L);
    }

    @Test
    public void testAsyncLoadingCache() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            asyncLoadingCache.get(1L);
            TimeUnit.SECONDS.sleep(2);
        }
    }

}
