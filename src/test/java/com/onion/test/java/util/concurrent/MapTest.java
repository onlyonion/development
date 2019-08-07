package com.onion.test.java.util.concurrent;

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

}
