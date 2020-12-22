package com.onion.test.java.lang;

public class StringTest {

    public static void main(String[] args) {
        Long a = null;
        String s = a + ";";
        String sb = String.valueOf(a) + ";";
        System.out.println(s + sb);
    }

}
