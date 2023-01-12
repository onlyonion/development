package com.onion.test.java.util;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeTest {

    @Test
    public void test() {
        Deque<String> d =  new ArrayDeque<>();
        d.add("a");
        d.addFirst("b");
        d.addLast("c");
        System.out.println(d);
    }
}
