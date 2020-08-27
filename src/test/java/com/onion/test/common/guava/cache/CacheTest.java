package com.onion.test.common.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {

    private static LoadingCache<Long, Long> cache = CacheBuilder.newBuilder()
            .initialCapacity(1).maximumSize(2)
            .refreshAfterWrite(12, TimeUnit.HOURS)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build(new CacheLoader<Long, Long>() {
                @Override
                public Long load(Long advertId) throws Exception {
                    return get(advertId);
                }


            });

    private static Long get(Long advertId) {
        System.out.println("get advertId=" + advertId);
        // throw new NullPointerException();
        return advertId;
        // return null;
    }

    public static void main(String[] args) throws ExecutionException {
        for (int i = 0; i < 10; i++) {
            Long aLong1 = cache.get((long) i);
            Long aLong = cache.get((long) i);
            System.out.println(aLong1 + aLong);
        }

    }
}
