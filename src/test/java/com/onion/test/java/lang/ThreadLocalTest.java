package com.onion.test.java.lang;

/**
 * @author lijicong
 * @since 2020-02-27
 */
public class ThreadLocalTest {

    static ThreadLocal<String> local = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "hello";
        }
    };

    public static void main(String[] args) {
        System.out.println(local);

        String s = local.get();
        System.out.println(s);

    }

}
