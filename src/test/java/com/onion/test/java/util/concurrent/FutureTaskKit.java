package cn.com.duiba.nezha.engine.common.utils;

import com.dianping.cat.Cat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
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
                executor.execute(task.getFutureTask());
            }
            for (FutureTaskWrapper<V> task : tasks) {
                FutureTask<V> f = task.getFutureTask();
                if (!f.isDone()) {
                    try {
                        task.getFutureTask().get(task.getTimeout(), task.getUnit());
                    } catch (InterruptedException | ExecutionException e) {
                        log.warn("线程池执行异常", e);
                    } catch (TimeoutException e) {
                        Cat.logMetricForCount(task.getCatName());
                        done = false;
                    }
                }
            }
        } finally {
            if (!done) {
                for (FutureTaskWrapper<V> task : tasks) {
                    task.getFutureTask().cancel(true);
                }
            }
        }
    }

    @Data
    public static class FutureTaskWrapper<V> {
        private FutureTask<V> futureTask;
        private String catName;
        private long timeout;
        private TimeUnit unit;
    }
}
