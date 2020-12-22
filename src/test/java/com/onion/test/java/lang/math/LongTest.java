package com.onion.test.java.lang.math;

import org.junit.Test;

public class LongTest {


    @Test
    public void test() {
        Long a = -1l;
        System.out.println(Long.valueOf(a.toString()) == a);
    }
}
