package com.onion.test.java.lang;

import cn.hutool.cron.task.RunnableTask;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTransmittableTest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    @Test
    public void test() {
        context.set("value-set-in-parent");
//        RunnableTask runnableTask = new RunnableTask(() -> {
//            System.out.println(Thread.currentThread().getName() + " thread local: " + context.get());
//        });
//        Runnable ttlRunnable = TtlRunnable.get(runnableTask);
//        executorService.submit(ttlRunnable);
    }
}
