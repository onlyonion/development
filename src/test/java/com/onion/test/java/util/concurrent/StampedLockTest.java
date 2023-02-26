package com.onion.test.java.util.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

@Slf4j
public class StampedLockTest {

    private int num = 0;

    private StampedLock sl = new StampedLock();

    public int getNum() {
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        int retNum = num;
        if (!sl.validate(stamp)) {
            // 悲观读
            stamp = sl.readLock();
            try {
                retNum = num;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return retNum;
    }

    public void setNum() {
        long stamp = sl.writeLock();
        try {
            num++;
        } finally {
            sl.unlockWrite(stamp);
        }
    }
}
