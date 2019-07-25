package com.onion.test.distributed.dubbo;

import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

import com.alibaba.dubbo.common.utils.LRUCache;

public class LRUCacheTest {

    @Test
    public void dubbo() {
        LRUCache<String, String> lru = new LRUCache<>();
        lru.put("a", "a");
        lru.put("b", "b");
        lru.put("c", "c");
        lru.put("d", "d");

        System.out.println(lru);
        lru.get("c");
        lru.get("a");
        System.out.println(lru);
    }

    @Test
    public void druid() {
        com.alibaba.druid.util.LRUCache<String, String> lru = new com.alibaba.druid.util.LRUCache<>(10, 16, 0.75f, true);
    }

    @Test
    public void mybartis() {
        PerpetualCache perpetualCache = new PerpetualCache("a");
        LruCache lru = new LruCache(perpetualCache);
    }
}
