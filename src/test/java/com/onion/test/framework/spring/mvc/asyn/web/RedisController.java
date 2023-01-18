package com.onion.test.framework.spring.mvc.asyn.web;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedissonClient redissonClient;

    @RequestMapping("/lock")
    public String lock(String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            lock.lock();

            try {
                TimeUnit.SECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } finally {
            lock.unlock();
        }
        return "ok";
    }

    @RequestMapping("/lockWait")
    public String lockWait(String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            boolean locked = lock.tryLock(1, 3, TimeUnit.SECONDS);

            if (!locked) {
                throw new RuntimeException("稍后再试");
            }

            try {
                TimeUnit.SECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "ok";
    }

    @RequestMapping("/fairLock")
    public String fairLock(String key) {
        RLock lock = redissonClient.getFairLock(key);
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        return "ok";
    }

    @RequestMapping("/locks")
    public String locks(String key1, String key2) {
        RLock lock1 = redissonClient.getLock(key1);
        RLock lock2 = redissonClient.getLock(key2);

        RLock multiLock = redissonClient.getMultiLock(lock1, lock2);
        RLock redLock = redissonClient.getRedLock(lock1, lock2);
        try {
            redLock.lock();
        } finally {
            redLock.unlock();
        }
        return "ok";
    }

}
