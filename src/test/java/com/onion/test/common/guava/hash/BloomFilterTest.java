package com.onion.test.common.guava.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;

public class BloomFilterTest {

    @Test
    public void test() {
        BloomFilter<String> stringBloomFilter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(String from, PrimitiveSink into) {
                into.putString(from, Charsets.UTF_8);
            }
        }, 1024 * 1024 * 32);

        stringBloomFilter.put("1");
        stringBloomFilter.put("2");

        boolean a = stringBloomFilter.mightContain("3");
        System.out.println(a);

        boolean b = stringBloomFilter.mightContain("1");
        System.out.println(b);
    }

    // Goolge在BigTable中就使用了BloomFilter，以避免在硬盘中寻找不存在的条目。

    private static final int insertions = 10_00;

    @Test
    public void bfTest() {
        //初始化一个存储string数据的布隆过滤器，初始化大小100w,不能设置为0
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions, 0.001);

        //初始化一个存储string数据的set，初始化大小100w
        Set<String> sets = new HashSet<>(insertions);

        //初始化一个存储string数据的set，初始化大小100w
        List<String> lists = new ArrayList<>(insertions);

        //向三个容器初始化100万个随机并且唯一的字符串---初始化操作
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int wrong = 0;//布隆过滤器错误判断的次数
        int right = 0;//布隆过滤器正确判断的次数
        for (int i = 0; i < insertions * 100; i++) {
            String test = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();//按照一定比例选择bf中肯定存在的字符串
            if (bf.mightContain(test)) {
                if (sets.contains(test)) {
                    right++;
                } else {
                    wrong++;
                }
            }
        }

        System.out.println("right=" + right);
        System.out.println("wrong=" + wrong);
    }
}
