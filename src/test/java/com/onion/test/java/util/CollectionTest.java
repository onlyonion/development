package com.onion.test.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {

    @Test
    public void test() {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }
        List<Integer> b = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            b.add(i);
        }
        System.out.println(a);
        System.out.println(b);
        a.retainAll(b);
        System.out.println(a);
    }
}
