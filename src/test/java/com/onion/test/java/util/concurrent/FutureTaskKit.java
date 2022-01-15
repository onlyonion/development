package com.onion.test.java.util.concurrent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author lijicong
 * @since 2021-12-17
 */
@Slf4j
public abstract class FutureTaskKit {

    public static <V> void invokeAll(ExecutorService executor, List<FutureTaskWrapper<V>> tasks) {
        boolean done = true;
        try {

            for (FutureTaskWrapper<V> task : tasks) {
                executor.execute(task);
            }
            for (FutureTaskWrapper<V> task : tasks) {
                if (!task.isDone()) {
                    try {
                        task.get(task.getTimeout(), task.getUnit());
                    } catch (InterruptedException | ExecutionException e) {
                        log.warn("线程池执行异常", e);
                    } catch (TimeoutException e) {
                        System.out.println(task.getName() + " timeout");
                        done = false;
                    }
                }
            }
        } finally {
            if (!done) {
                for (FutureTaskWrapper<V> task : tasks) {
                    task.cancel(true);
                }
            }
        }
    }

    @Getter
    @Setter
    public static class FutureTaskWrapper<V> extends FutureTask<V> {
        private String name;
        private long timeout;
        private TimeUnit unit = TimeUnit.MILLISECONDS;

        public FutureTaskWrapper(Callable<V> callable, long timeout, String name) {
            super(callable);
            this.timeout = timeout;
            this.name = name;
        }
    }
}
