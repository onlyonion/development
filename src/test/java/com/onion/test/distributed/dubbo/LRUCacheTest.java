package com.onion.test.distributed.dubbo;

import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;


public class LRUCacheTest {

    @Test
    public void dubbo() {
        org.apache.dubbo.common.utils.LRUCache<String, String> lru =
                new org.apache.dubbo.common.utils.LRUCache<>();
        
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
        com.alibaba.druid.util.LRUCache<String, String> lru =
                new com.alibaba.druid.util.LRUCache<>(10);
    }

    @Test
    public void mybartis() {
        PerpetualCache perpetualCache = new PerpetualCache("a");
        org.apache.ibatis.cache.decorators.LruCache lru =
                new org.apache.ibatis.cache.decorators.LruCache(perpetualCache);
    }

    public static class LRUDemo extends LinkedHashMap<String, Object> {
        private int capacity;

        /**
         * 当LinkedHashMap的accessOrder参数为true时，即会按照访问顺序排序，最近访问的放在最前，最早访问的放在后面
         */
        public LRUDemo(int capacity) {
            super(16, 0.75f, true);
            this.capacity = capacity;
        }

        /**
         * LinkedHashMap自带的判断是否删除最老的元素方法，默认返回false，即不删除老数据
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            return size() > capacity;
        }
    }
}
