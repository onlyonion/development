package com.onion.test.java.lang;

import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {

    @Test
    public void test() {
        Map<String, Object> m = new WeakHashMap<>();
        m.put(null, "abc");
        System.out.println(m.get(null));
        m.put("a", null);
        System.out.println(m.get("a"));
        m.put(null, null);
        System.out.println(m.get(null));
        m.put("b", "abc");

    }
}
