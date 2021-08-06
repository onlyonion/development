package com.onion.test.java.util.concurrent.forkjoin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


@Slf4j
public class ForkJoinTest {

    @Test
    public void test() throws InterruptedException {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();


        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(value -> {
            int gap = 1000 - concurrentHashMap.size();
            concurrentHashMap.putAll(getData(gap));
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("map size = {}", concurrentHashMap.size());
    }

    private Map<? extends String, ?> getData(int gap) {
        return null;
    }
}
