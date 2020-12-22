package com.onion.test.common.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {

    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .initialCapacity(1).maximumSize(2)
            .refreshAfterWrite(12, TimeUnit.HOURS)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String advertId) throws Exception {
                    return get(advertId);
                }
            });

    private static LoadingCache<String, String> cacheAll = CacheBuilder.newBuilder()
            .refreshAfterWrite(12, TimeUnit.HOURS)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String advertId) throws Exception {
                    return get(advertId);
                }

                @Override
                public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                    Map<String, String> map = new HashMap<>();
                    keys.forEach(aLong -> map.put(aLong, aLong));
                    System.out.println("load data " + keys);
                    return map;
                }
            });



    private static String get(String advertId) {
        System.out.println("get advertId=" + advertId);
        // throw new NullPointerException();
        return advertId;
        // return null;
    }

    @Test
    public void testAll() throws Exception {
        List<String> keys2 = Lists.newArrayList("1", "2");
        List<String> keys3 = Lists.newArrayList("1", "2","3");
        List<String> keys4 = Lists.newArrayList("1", "2","3","4");
        List<String> keys5 = Lists.newArrayList("1", "2","3","4","5");

        System.out.println(cacheAll.getAll(keys2));
        System.out.println(cacheAll.getAll(keys5));
        System.out.println(cacheAll.getAll(keys3));
        System.out.println(cacheAll.getAll(keys4));
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            String aLong1 = cache.get(i + "k");
            String aLong = cache.get(i + "k");
            System.out.println(aLong1 + aLong);
        }
    }
}
