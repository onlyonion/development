package com.onion.test.common.btrace;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BtraceTest {

    Random random = new Random();

    public void calc() {
        int a = random.nextInt(10);
        int b = random.nextInt();
        int add = add(a, b);
        log.info("a={}, b={}, add={}", a, b, add);
    }

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        for (; ; ) {
            BtraceTest calculate = new BtraceTest();
            calculate.calc();
        }
    }

}

