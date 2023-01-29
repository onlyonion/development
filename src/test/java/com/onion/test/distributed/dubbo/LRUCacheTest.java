package com.onion.test.distributed.dubbo;

import org.apache.dubbo.common.utils.LRUCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;


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
