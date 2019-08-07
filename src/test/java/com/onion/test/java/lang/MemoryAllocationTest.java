package com.onion.test.java.lang;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class MemoryAllocationTest {
    static final int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    @Test
    public void testAllocation() {
        byte[] b1 = new byte[2 * _1MB];
        byte[] b2 = new byte[2 * _1MB];
        byte[] b3 = new byte[2 * _1MB];
        byte[] b4 = new byte[2 * _1MB];
    }

    /**
     * -XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=314578
     */
    @Test
    public void testPretenureSizeThreshold() throws InterruptedException {
        TimeUnit.SECONDS.sleep(60);
        byte[] b1 = new byte[6 * _1MB];
    }

    /**
     * -XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=314578
     */
    @Test
    public void test() throws InterruptedException {
        while (true) {
            byte[] b1 = new byte[1024 * 256];
            TimeUnit.MILLISECONDS.sleep(300);
        }
    }
}
