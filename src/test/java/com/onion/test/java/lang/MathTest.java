package com.onion.test.java.lang;

import org.junit.Test;

public class MathTest {

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            double random = Math.random();
            if (random < 0.0001) {
                System.out.println(random);
            }
        }
    }
}
