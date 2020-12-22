package com.onion.test.java.lang;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public void test() {
        excetionFinally();
    }

    private int excetionFinally() {
        Integer a = null;
        try {
            a.getClass();
        } catch (Exception e) {
            System.out.println("catch");
            return returnCall();
        } finally {
            System.out.println("finally");
        }
        return returnCall();
    }

    private int returnCall() {
        System.out.println("return");
        return 0;
    }

}
