package com.onion.test.java.lang;

import java.util.Random;

public class Test {
    @org.junit.Test
    public void test() {
        for (; ; ) {
            System.out.println(new Random().nextInt());
        }
    }
}
