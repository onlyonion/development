package com.onion.test.common.apache;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionTest {
    @Test
    public void test() {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            a.add(i);
        }

        List<Integer> b = new ArrayList<>();
        for (int i = 3; i < 5; i++) {
            b.add(i);
        }
        Collection  c = CollectionUtils.retainAll(a, b);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
