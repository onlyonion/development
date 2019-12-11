package com.onion.test.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author lijicong
 * @since 2019-12-11
 */
public class MapTest {

    @Test
    public void test() throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(), "");
                }, "map-thread-" + i).start();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "map-thread");
        thread.start();
        thread.join();
    }

}
