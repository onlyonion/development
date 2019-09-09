package com.onion.test.java.text;

import java.text.MessageFormat;

public class MessageFormatTest {

    public static void main(String[] args) {
        String format = MessageFormat.format("a:b:{0}:{1}", String.valueOf(11), 33);
        System.out.println(format);
    }

    public void test () {
        //RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }



}
