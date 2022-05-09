package com.onion.test.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijicong
 * @since 2022-03-22
 */
public class LockTest {
    ReentrantLock lock = new ReentrantLock();

    @Test
    public void test() {
        lock.lock();
        lock.lock();
        lock.lock();
    }
}
