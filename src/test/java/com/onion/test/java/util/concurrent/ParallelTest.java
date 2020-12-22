package com.onion.test.java.util.concurrent;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class ParallelTest {

    private static ForkJoinPool forkJoinPoolCopyAndPut = new ForkJoinPool(12);

    @Test
    public void test() throws ExecutionException, InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000L; i++) {
            list.add("i-" + i);
        }
        Instant start = Instant.now();
        ForkJoinTask<?> submit = forkJoinPoolCopyAndPut.submit(() -> list.parallelStream().forEach(adv -> doBiz()));
        submit.get();
        System.out.println("耗时0：" + Duration.between(start, Instant.now()).toMillis() + "ms");

        Instant start1 = Instant.now();
        list.parallelStream().forEach(adv -> doBiz());
        System.out.println("耗时1：" + Duration.between(start1, Instant.now()).toMillis() + "ms");

        Instant start2 = Instant.now();
        list.stream().forEach(adv -> doBiz());
        System.out.println("耗时2：" + Duration.between(start2, Instant.now()).toMillis() + "ms");
    }

    private void doBiz() {
        int a = 1;
        int b = 2;
        int c = a + b;

        /*try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
