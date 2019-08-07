package com.onion.test.distributed.rocketmq.filter;

import org.apache.rocketmq.filter.util.BitsArray;
import org.apache.rocketmq.filter.util.BloomFilter;
import org.apache.rocketmq.filter.util.BloomFilterData;
import org.junit.Test;

public class BloomFilterTest {

    @Test
    public void test() {
        BloomFilter filter = BloomFilter.createByFn(1, 10);
        System.out.println(filter);

        for (int i = 0; i < 100; i++) {
            BloomFilterData generate = filter.generate(String.valueOf(i));
            System.out.println(generate);
        }

        BitsArray bitsArray = BitsArray.create(1);
        boolean hit = filter.isHit("1", bitsArray);
        System.out.println(hit);

    }
}
