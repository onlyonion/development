package com.onion.test.java.features;

import org.junit.Test;

public class Java14Test {

    @Test
    public void test() {
        testNullPointer(null);
    }

    public void testNullPointer(String s) {
        System.out.println(s.equals("s"));
    }
}
