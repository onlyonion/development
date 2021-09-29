package com.onion.test.java.util.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MapTest {

    @Test
    public void testSkipList() {

        ConcurrentSkipListMap<String, Integer> mapKeyString = new ConcurrentSkipListMap<>();

        mapKeyString.put("y", 3);
        mapKeyString.put("x", 2);
        mapKeyString.put("c", 1);
        mapKeyString.put("b", 4);
        mapKeyString.put("a", 5);

        System.out.println(mapKeyString);

        ConcurrentSkipListMap<Integer, Integer> mapKeyInteger = new ConcurrentSkipListMap<>();

        mapKeyInteger.put(85, 1);
        mapKeyInteger.put(71, 1);
        mapKeyInteger.put(32, 1);
        mapKeyInteger.put(21, 1);
        mapKeyInteger.put(14, 1);
        mapKeyInteger.put(7, 1);

        System.out.println(mapKeyInteger);
    }

    @Test
    public void testHashMap() {
        Map<String, String> map = new HashMap<>();
        System.out.println(map.put("hello", "world"));
        System.out.println(map.get("hello"));
    }

    @Test
    public void testHash() {
        int h2 = hash("hello");
        System.out.println(h2);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void test() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        FeatureDO featureDO = new FeatureDO();
        featureDO.setMap(new HashMap<>());

        FutureTask<Void> voidFutureTask = new FutureTask<Void>(() -> {
            TimeUnit.MILLISECONDS.sleep(20);
            featureDO.setMap(null);
            return null;
        });

        executorService.submit(voidFutureTask);
        try {
            voidFutureTask.get(7, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.info("超时", e);
        }

        if (featureDO.getMap() != null) {
            TimeUnit.MILLISECONDS.sleep(15);
            featureDO.getMap().put("1", 1);
        }
    }

    @Data
    private class FeatureDO {
        Map<String, Object> map;
    }

}
