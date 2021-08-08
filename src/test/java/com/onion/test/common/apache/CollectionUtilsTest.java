package com.onion.test.common.apache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CollectionUtilsTest {
    @Test
    public void test() {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            a.add(i);
            a.add(i);
        }

        List<Integer> b = new ArrayList<>();
        for (int i = 2; i < 8; i++) {
            b.add(i);
        }
        Collection<Integer>  retainAll = CollectionUtils.retainAll(a, b);
        Collection<Integer>  union = CollectionUtils.union(a, b);
        Collection<Integer>  disjunction = CollectionUtils.disjunction(a, b);
        Collection<Integer>  subtract = CollectionUtils.subtract(a, b);
        Collection<Integer>  intersection = CollectionUtils.intersection(a, b);
        Collection<Integer>  collate = CollectionUtils.collate(a, b);

        List<Integer> anotb = a.stream().filter(item -> !b.contains(item)).collect(Collectors.toList());
        a.removeAll(b);

        log.info("a={}, b={}", a, b);
        log.info("retainAll={}", retainAll);
        log.info("union={}", union);
        log.info("disjunction={}", disjunction);
        log.info("subtract={}", subtract);
        log.info("intersection={}", intersection);
        log.info("collate={}", collate);
        log.info("anotb={}", anotb);
        log.info("aRemoveB={}", a);
    }

    @Test
    public void select() {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            a.add(i);
        }

        List<Integer> b = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            b.add(i);
        }

        Collection<Integer> select = CollectionUtils.select(a, object -> object <= 2);
        List<Integer> select1 = CollectionUtils.select(a, object -> object <= 2, new ArrayList<>());
        log.info("select={}", select);
        log.info("select1={}", select1);

        ArrayList<Integer> x1 = new ArrayList<>();
        ArrayList<Integer> x2 = new ArrayList<>();
        ArrayList<Integer> select2 = CollectionUtils.select(a, object -> object <= 2, x1, x2);
        log.info("select2={}", select2);
        log.info("x1={}", x1);
        log.info("x2={}", x2);

        Collection<Integer> selectRejected = CollectionUtils.selectRejected(a, object -> object <= 2);
        log.info("selectRejected={}", selectRejected);
    }

}
