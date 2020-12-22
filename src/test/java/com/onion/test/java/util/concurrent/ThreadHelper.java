package com.onion.test.java.util.concurrent;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author lijicong
 * @since 2020-11-05
 */
public class ThreadHelper {
    public static Integer doBiz(Integer no) {
        try {
            TimeUnit.MILLISECONDS.sleep(20);
            System.out.println(Thread.currentThread().getName() + "-" + no);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return no;
    }

    public static void print(Object... args) {
        System.out.println(Thread.currentThread().getName() + "-" + Arrays.toString(args));
    }
}
