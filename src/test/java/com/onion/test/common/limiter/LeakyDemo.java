package com.onion.test.common.limiter;

public class LeakyDemo {
    public long timeStamp = getNowTime();

    public long capacity; // 桶的容量
    public long rate; // 水漏出的速度
    public long water; // 当前水量(当前累积请求数)

    private long getNowTime() {
        return System.currentTimeMillis();
    }

    public boolean grant() {
        long now = getNowTime();
        water = Math.max(0, water - (now - timeStamp) * rate); // 先执行漏水，计算剩余水量
        timeStamp = now;

        if ((water + 1) < capacity) {
            // 尝试加水,并且水还未满
            water += 1;
            return true;
        }
        else {
            // 水满，拒绝加水
            return false;
        }
    }
}
