package com.onion.test.common.guava.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

public class LimiterTest {

    @Test
    public void test() throws ExecutionException {

        // guava cache 存储计数器，过期时间2秒，保证能记录1秒内的计数
        LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long key) throws Exception {
                        return new AtomicLong(2);
                    }
                });

        long limit = 1000;

        while (true) {
            long currentTimeMillis = System.currentTimeMillis();
            if (counter.get(currentTimeMillis).incrementAndGet() > limit) {
                System.out.println("limiter: " + currentTimeMillis);
                continue;
            }

            // biz process
            //System.out.println("biz process");
        }

    }

    // 平滑突发限流
    @Test
    public void testSmoothBursty() {

        // 容量为5，每秒新增5个令牌，每隔200毫秒新增一个令牌
        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < 10; i++) {
            System.out.println(rateLimiter.acquire());
        }
    }

    // 平滑预热限流
    @Test
    public void testSmoothWarmingUp() throws InterruptedException {

        RateLimiter rateLimiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.acquire());
        }
        TimeUnit.MILLISECONDS.sleep(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.acquire());
        }
    }

}
