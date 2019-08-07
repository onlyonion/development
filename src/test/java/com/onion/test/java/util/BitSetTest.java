package com.onion.test.java.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.BitSet;

import org.junit.Test;

public class BitSetTest {

    // Bitset的基本原理是，用1位来表示一个数据是否出现过，0为没有出现过，1表示出现过。
    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        BitSet bitSet = new BitSet();

        Field words = BitSet.class.getDeclaredField("words");
        words.setAccessible(true);
        long[] longArray = (long[]) words.get(bitSet);
        System.out.println(Arrays.toString(longArray));

        for (int i = 0; i < 64; i++) {
            bitSet.set(i);
        }

        System.out.println(bitSet.size());
        System.out.println(bitSet);

        System.out.println(bitSet.get(111));


        System.out.println(Arrays.toString((long[]) words.get(bitSet)));
    }
}
