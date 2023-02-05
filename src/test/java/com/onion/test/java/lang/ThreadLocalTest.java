package com.onion.test.java.lang;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author lijicong
 * @since 2020-02-27
 */
@Slf4j
public class ThreadLocalTest {

    static ThreadLocal<String> local = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "hello";
        }
    };

    public static void main(String[] args) {
        System.out.println(local);
        String s = local.get();
        System.out.println(s);
    }

    @SneakyThrows
    @Test
    public void test() {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Profiler.end());
    }


    static class Profiler {
        private static final ThreadLocal<Long> time = ThreadLocal.withInitial(System::currentTimeMillis);

        public static void begin() {
            time.set(System.currentTimeMillis());
        }

        public static long end() {
            return System.currentTimeMillis() - time.get();
        }
    }

    private static final IThreadLocal iThreadLocal = new IThreadLocal();
    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    private static final InheritableThreadLocal<Object> inheritableThreadLocal = new InheritableThreadLocal<>();

    static class IThreadLocal extends InheritableThreadLocal<Object> {

        @Override
        protected Object initialValue() {
            return LocalDateTime.now();
        }

        @Override
        protected Object childValue(Object parentValue) {
            return "子线程获取父线程传递数据：" + parentValue;
        }
    }

    static class Ithread extends Thread {

        public Ithread(String name) {
            super(name);
        }

        @SneakyThrows
        @Override
        public void run() {
            log.info("thread: {} threadLocal: {}", Thread.currentThread().getName(), threadLocal.get());
            log.info("thread: {} inheritableThreadLocal: {}", Thread.currentThread().getName(), inheritableThreadLocal.get());

            Thread.sleep(100);
        }
    }

    @Test
    public void testIthread() throws InterruptedException {
        threadLocal.set("hello threadLocal");
        inheritableThreadLocal.set("hello inheritableThreadLocal");
        // 情况1：在父类初始化IhThreadLocal前子线程先执行子线程
        Thread iThread1 = new Thread(() -> {
            log.info("thread: {} threadLocal: {}", Thread.currentThread().getName(), threadLocal.get());
            log.info("thread: {} inheritableThreadLocal: {}", Thread.currentThread().getName(), inheritableThreadLocal.get());
        });
        iThread1.start();
        Thread.sleep(1000);

        // 情况2：在父线程初始化IhThreadLocal后执行子线程
        log.info("thread: {} threadLocal: {}", Thread.currentThread().getName(), threadLocal.get());
        log.info("thread: {} inheritableThreadLocal: {}", Thread.currentThread().getName(), inheritableThreadLocal.get());

        Ithread iThread2 = new Ithread("my-thread-2");
        iThread2.start();

        Thread.sleep(1000);
        log.info("thread: {} threadLocal: {}", Thread.currentThread().getName(), threadLocal.get());
        log.info("thread: {} inheritableThreadLocal: {}", Thread.currentThread().getName(), inheritableThreadLocal.get());
    }

}
