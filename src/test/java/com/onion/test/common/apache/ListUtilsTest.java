package com.onion.test.common.apache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListUtilsTest {

    @Test
    public void test() {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            a.add(i);
        }

        List<Integer> b = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            b.add(i);
        }

        List<Integer> sum = ListUtils.sum(a, b);
        List<Integer> union = ListUtils.union(a, b);
        List<Integer> intersection = ListUtils.intersection(a, b);
        log.info("a={}, b={}", a, b);
        log.info("sum={}", sum);
        log.info("union={}", union);
        log.info("intersection={}", intersection);
    }
}
