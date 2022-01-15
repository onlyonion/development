package com.onion.test.java.util.concurrent;

import com.onion.test.java.util.concurrent.FutureTaskKit.FutureTaskWrapper;
import org.jcp.xml.dsig.internal.SignerOutputStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class FutureTest {

    static ExecutorService executor = Executors.newFixedThreadPool(10);

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        FutureTask<String> tast = new FutureTask<>(() -> task(1));
        executor.submit(tast);
        String s = tast.get();
        System.out.println(s);
    }

    private String task(int timeout) throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
        String txt = Thread.currentThread().getName() + " finish in " + timeout + "s";
        System.out.println(txt);
        return "return " + txt;
    }

    private String task(int timeout, Map<String, String> map) throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
        String txt = Thread.currentThread().getName() + " finish in " + timeout + "s";
        System.out.println(txt);
        map.put("k" + timeout, txt);
        return "return " + txt;
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask1 = new FutureTask<>(() -> task(4));
        FutureTask<String> futureTask2 = new FutureTask<>(() -> task(8));
        FutureTask<String> futureTask3 = new FutureTask<>(() -> task(12));
        FutureTask<String> futureTask4 = new FutureTask<>(() -> task(16));
        executor.submit(futureTask1);
        executor.submit(futureTask2);
        executor.submit(futureTask3);
        executor.submit(futureTask4);
        //        futureTask1.get();
        //        futureTask2.get();
        //        futureTask3.get();
        //        futureTask4.get();
        try {
            futureTask1.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        try {
            futureTask2.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        try {
            futureTask3.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        try {
            futureTask4.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws InterruptedException, ExecutionException {
        List<Callable<String>> calls = new ArrayList<>();
        calls.add(() -> task(1));
        calls.add(() -> task(2));
        calls.add(() -> task(1));
        calls.add(() -> task(4));

        List<Future<String>> futures = executor.invokeAll(calls, 3, TimeUnit.SECONDS);
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }

    @Test
    public void test4() throws InterruptedException, ExecutionException, TimeoutException {
        List<Callable<String>> calls = new ArrayList<>();
        calls.add(() -> task(1));
        calls.add(() -> task(2));
        calls.add(() -> task(1));
        calls.add(() -> task(4));

        List<Future<String>> futures = executor.invokeAll(calls);
        for (Future<String> future : futures) {
            System.out.println(future.get(10, TimeUnit.MILLISECONDS));
        }
    }

    @Test
    public void test6() throws InterruptedException, ExecutionException, TimeoutException {
        List<Callable<String>> calls = new ArrayList<>();
        calls.add(() -> task(1));
        calls.add(() -> task(2));
        calls.add(() -> task(1));
        calls.add(() -> task(4));

        List<Object> collect = calls.parallelStream().map(callable -> new FutureTask(callable))
                .peek(task -> executor.submit(task))
                .map(task -> {
                    try {
                        return task.get(1200, TimeUnit.MILLISECONDS);
                    } catch (TimeoutException e) {
                        String txt = Thread.currentThread().getName() + " finish";
                        System.out.println(txt);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test5() {
        Map<String, String> map = new HashMap<>();
        List<FutureTaskWrapper<String>> tasks = new ArrayList<>();
        tasks.add(new FutureTaskWrapper<>(() -> task(2, map), 1100, "任务1"));
        tasks.add(new FutureTaskWrapper<>(() -> task(3, map), 1100, "任务2"));
        tasks.add(new FutureTaskWrapper<>(() -> task(4, map), 1100, "任务3"));
        tasks.add(new FutureTaskWrapper<>(() -> task(5, map), 1100, "任务4"));
        FutureTaskKit.invokeAll(executor, tasks);

        System.out.println(map);
    }


}
