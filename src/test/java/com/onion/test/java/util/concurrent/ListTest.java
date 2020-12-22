package com.onion.test.java.util.concurrent;

import com.google.common.collect.Lists;
import org.apache.commons.collections.ListUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListTest {

    @Test
    public void test1() {
        List<String> list = Lists.newArrayList("哈哈", "呵呵", "哦哦");
        List<String> list1 = Lists.newArrayList("哈哈", "嘿嘿");

        list1.retainAll(list);

        System.out.println(list1);
        System.out.println(list);
        System.out.println(list.subList(1, 3));
    }

    @Test
    public void test2() {
        List<String> list = Lists.newArrayList("哈哈", "呵呵", "哦哦");
        List<String> list1 = Lists.newArrayList("哈哈", "嘿嘿");

        List intersection = ListUtils.intersection(list, list1);

        System.out.println(list);
        System.out.println(list1);
        System.out.println(intersection);
    }

    @Test
    public void test3() {
        List<String> list = Lists.newArrayList("哈哈", "呵呵", "哦哦");
        Map<Boolean, List<String>> map = list.stream().collect(Collectors.partitioningBy(item -> item.contains("哈哈")));
        System.out.println(map);
        System.out.println(map.get(true));
    }


}
