package com.onion.test.java.util.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.Test;

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

}
