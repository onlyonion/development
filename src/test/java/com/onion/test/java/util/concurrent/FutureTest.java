package com.onion.test.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    static ExecutorService executor = Executors.newSingleThreadExecutor();

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            TimeUnit.SECONDS.sleep(1L);
            return "hello world";
        };
        FutureTask<String> stringFutureTask = new FutureTask<>(callable);
        executor.submit(stringFutureTask);
        String s = stringFutureTask.get();
        System.out.println(s);
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1L);
            return "hello world";
        });

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1L);
            return "hello world";
        });

        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1L);
            return "hello world";
        });

        FutureTask<String> futureTask4 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1L);
            return "hello world";
        });

        executor.submit(futureTask1);
        executor.submit(futureTask2);
        executor.submit(futureTask3);
        executor.submit(futureTask4);

        futureTask1.get();
        futureTask2.get();
        futureTask3.get();
        futureTask4.get();
    }


}
