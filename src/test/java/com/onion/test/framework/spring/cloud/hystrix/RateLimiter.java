package com.onion.test.framework.spring.cloud.hystrix;

public interface RateLimiter {
    // 是否要限流
    boolean isOverLimit();
    // 当前QPS总数值（也就是窗口期内的访问总量）
    int currentQps();
    // touch一下；增加一次访问量
    boolean visit();
}

