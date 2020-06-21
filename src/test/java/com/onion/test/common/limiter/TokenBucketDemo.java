package com.onion.test.common.limiter;

public class TokenBucketDemo {
    public long timeStamp = getNowTime();
    public long capacity; // 桶的容量
    public long rate; // 令牌放入速度
    public long tokens; // 当前令牌数量

    private long getNowTime() {
        return System.currentTimeMillis();
    }

    public boolean grant() {
        long now = getNowTime();
        // 先添加令牌
        tokens = Math.min(capacity, tokens + (now - timeStamp) * rate);
        timeStamp = now;
        if (tokens < 1) {
            // 若不到1个令牌,则拒绝
            return false;
        }
        else {
            // 还有令牌，领取令牌
            tokens -= 1;
            return true;
        }
    }
}