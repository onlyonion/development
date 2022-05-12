package com.onion.test.java.lang;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author lijicong
 * @since 2020-02-27
 */
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

}
