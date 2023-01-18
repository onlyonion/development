package com.onion.test.framework.spring.cloud.hystrix;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义滑动时间窗口demo - Hystrix也是类似采用这种。
 * - 实现runnable方法：用于控制滑动动作，重置桶的值以及总量值
 *
 */
public class MyDefinedSlideWinDemoLimiter implements RateLimiter, Runnable {
    /** 每秒最多允许5个请求，这是默认值，你可以通过构造方法指定 **/
    private static final int DEFAULT_ALLOWED_VISIT_PER_SECOND = 5;
    /** 最大访问每秒 **/
    private long maxVisitPerSecond;
    /** 默认把1s分为十个桶，这是默认值 **/
    private static final int DEFAULT_BUCKET = 10;
    private int bucket;
    /** 每个桶对应当前的请求数 **/
    private static AtomicInteger[] countPerBucket = null;

    /** 总请求数 **/
    private AtomicInteger count;
    private volatile int index;

    /** 构造器 **/
    public MyDefinedSlideWinDemoLimiter() {
        this(DEFAULT_BUCKET, DEFAULT_ALLOWED_VISIT_PER_SECOND);
    }
    public MyDefinedSlideWinDemoLimiter(int bucket, long maxVisitPerSecond) {
        this.bucket = bucket;
        this.maxVisitPerSecond = maxVisitPerSecond;
        countPerBucket = new AtomicInteger[bucket];
        for (int i = 0; i < bucket; i++) {
            countPerBucket[i] = new AtomicInteger();
        }
        count = new AtomicInteger(0);
    }
    /**
     * 是否超过限制：当前QPS总数是否超过了最大值（默认每秒5个）
     * 注意：这里应该是>=。因为其实如果桶内访问数量已经等于5了，就应该限制住外面的再进来
     */
    @Override
    public boolean isOverLimit() {
        return currentQps() >= maxVisitPerSecond;
    }
    @Override
    public int currentQps() {
        return count.get();
    }
    /**
     * 访问一次，次数+1（只要请求进来了就+1），并且告知是否加载
     * 请注意：放在指定的桶
     */
    @Override
    public boolean visit() {
        countPerBucket[index].incrementAndGet();
        count.incrementAndGet();
        return isOverLimit();
    }
    @Override
    public void run() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~窗口向后滑动一下~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // 桶内的指针向前滑动一下：表示后面的visit请求应该打到下一个桶内
        index = (index + 1) % bucket;
        // 初始化新桶。并且拿出旧值（其实就是把当前这个桶的值释放出来，然后看下这个桶之前是否有访问过，有的话就对count总数减去，然后告诉可以进行访问）
        int val = countPerBucket[index].getAndSet(0);
        // 这个步骤一定不要变了：因为废弃了一个桶，所以总值要减去~
        if (val == 0) {
            // 这个桶等于0，说明这个时刻没有流量进来
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~窗口没能释放出流量，继续保持限流~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else {
            count.addAndGet(-val);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~窗口释放出了[" + val + "]个访问名额，你可以访问了~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }


    public static void main(String[] args) throws Exception {
        MyDefinedSlideWinDemoLimiter rateLimiter = new MyDefinedSlideWinDemoLimiter();
        // 使用一个线程定时滑动这个窗口：100ms滑动一次（一般保持个桶的跨度保持一致）
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(rateLimiter, 100, 100, TimeUnit.MILLISECONDS);
        // scheduledExecutorService.scheduleWithFixedDelay(rateLimiter, 100, 100, TimeUnit.MILLISECONDS);

        // 此处使用单线程访问，你可以改造成多线程版本
        while (true) {
            String currThreadName = Thread.currentThread().getName();
            boolean overLimit = rateLimiter.isOverLimit();
            if (overLimit) {
                System.out.printf("线程[%s]===被限流了===，因为访问次数已经超过阈值[%s]\n%n", currThreadName, rateLimiter.currentQps());
            } else {
                rateLimiter.visit();
                System.out.printf("线程[%s]访问成功，当前访问总数[%s]\n%n", currThreadName, rateLimiter.currentQps());
            }
            Thread.sleep(200);
        }
    }
}

