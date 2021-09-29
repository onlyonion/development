package com.onion.test.java.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author lijicong
 * @since 2019-12-11
 */
public class MapTest {

    @Test
    public void testHashMapMultiThread() throws InterruptedException {
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

    @Test
    public void testHashMapNull() {
        Map<String, String> map = new HashMap<>(1);
        for (int i = 0; i < 30; i++) {
            map.put("key" + i, "value-" + i);
        }
    }

    @Test
    public void testKey() {
        Map<Integer, String> map = new HashMap<>(1);
        for (int i = 0; i < 20; i++) {
            map.put(i,"value-" + i);
        }
        System.out.println(map);
        map.keySet().retainAll(Arrays.asList(1, 2,3 ));
        System.out.println(map);
        long l = Long.parseLong("162526476741901");
        Long aLong = Long.valueOf("168849912485986");
        System.out.println(aLong);
    }

    @Test
    public void testPut() throws InterruptedException {
        Map<Integer, String> map = new HashMap<>(1);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                map.put(i,"value-" + i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                String s = map.get(i);
                System.out.println("key-" + i + " value-" + s);
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    @Test
    public void testTreeMap() {
        Map<Integer, String> map = new TreeMap<>();
        for (int i = 0; i < 30; i++) {
            map.put((int) (Math.random() * 100), "value-" + i);
        }
        for (Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

    @Test
    public void test() {
        Map<Integer, Map<Integer, String>> map = new HashMap<>();
        map.compute(1, (key, oldValue) -> {
            if (oldValue == null) {
                oldValue= new HashMap<>();
            }
            oldValue.put(11, "new11");
            return oldValue;
        });
        map.compute(1, (key, oldValue) -> {
            if (oldValue == null) {
                oldValue= new HashMap<>();
            }
            oldValue.put(22, "new22");
            return oldValue;
        });
        System.out.println(map);
    }
}
