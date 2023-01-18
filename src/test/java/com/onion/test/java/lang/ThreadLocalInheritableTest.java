package com.onion.test.java.lang;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadLocalInheritableTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();
    private static ThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();

    /**
     * 输出子线程的IhThreadLocalUse的值
     */
    static class Ithread extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("子线程值:" + inheritableThreadLocal.get());
        }
    }

    @SneakyThrows
    @Test
    public void testInheritableThreadLocal() {
        // 主线程第一次赋值
        inheritableThreadLocal.set("first set1");
        System.out.println("父线程第一次获取值:" + inheritableThreadLocal.get());

        executorService.submit(new Ithread());
        Thread.sleep(2000);

        inheritableThreadLocal.set("second set2");
        System.out.println("父线程第二次获取值:" + inheritableThreadLocal.get());

        executorService.submit(new Ithread());
        executorService.shutdown();
    }

    private static ThreadLocal<String> CONTEXT = new InheritableThreadLocal<>();
    private static ExecutorService executor1 = Executors.newSingleThreadScheduledExecutor();

    private static ExecutorService executor = new ThreadPoolExecutor(1, 1,
            60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512));
    private static ExecutorService executorWrap = new ThreadPoolExecutorWrap(1, 1,
            60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512));

    @Test
    public void test2() {
        execute("test2");
        execute("test3");
        execute("test4");
    }

    public void execute(String value) {
        CONTEXT.set(value);

        // 方式1：在用户任务中直接进行手动获取/设置上下文逻辑
        executor.submit(new RunnableWrap(() -> print(CONTEXT)));

        // 方式2：自定义线程池，封装成支持保存/设置上下文的任务
        executorWrap.submit(() -> print(CONTEXT));

        executor1.submit(() -> print(CONTEXT));
    }

    public void print(ThreadLocal<String> threadLocal) {
        System.out.println(Thread.currentThread().getName()+ " thread local: " + threadLocal.get());
        log.info("{} {}", Thread.currentThread().getName(), threadLocal.get());
    }

    static class ThreadPoolExecutorWrap extends ThreadPoolExecutor {
        public ThreadPoolExecutorWrap(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public Future<?> submit(Runnable task) {
            if (task == null) {
                throw new NullPointerException();
            }
            RunnableFuture<Void> ftask = newTaskFor(new RunnableWrap(task), null);
            execute(ftask);
            return ftask;
        }
    }

    static class RunnableWrap implements Runnable {
        private String contextValue;
        private Runnable task;

        public RunnableWrap(Runnable task) {
            this.contextValue = CONTEXT.get();
            this.task = task;
        }

        @Override
        public void run() {
            try {
                CONTEXT.set(contextValue);
                // 用户任务逻辑
                task.run();
            } finally {
                CONTEXT.remove();
            }
        }
    }


    static ThreadLocal<String> TTL = new InheritableThreadLocal<>();
    static ThreadLocal<String> TTL2 = new TransmittableThreadLocal<>();

    @Test
    @SneakyThrows
    public void testTransmittableThreadLocal() {
        new Thread(() -> {
            // 在父线程中设置变量
            TTL.set("throwable");
            new Thread(() -> {
                methodFrame1();
            }).start();

            try {
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1000);
    }

    private static void methodFrame1() {
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println(Thread.currentThread().getName() + " " + TTL.get());
    }
}
