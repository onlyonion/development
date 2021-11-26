package com.onion.test.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Java8Stream {
    static List<User> list = new ArrayList<>();
    static {
        for (int i = 0; i < 10; i++) {
            list.add(new User(i, "name-" + 1));
        }
    }

    @Test
    public void groupingBy() {
        Map<Integer, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getUserId));
        Map<Boolean, List<User>> collect = list.stream().collect(Collectors.groupingBy(user -> user.getUserId() > 11));
        log.info("true list {}", collect.get(true));
        log.info("false list {}", collect.get(false));
    }

    @Test
    public void anyMatch() {
        boolean match = list.stream().anyMatch(item -> 1 == item.getUserId());
        log.info("match {}", match);
    }

    @Test
    public void flatMap() {
        List<String> flat = list.stream().map(user -> user.getUsername().split("-"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        log.info("flat {}", flat);
    }

    @Test
    public void limit() {
        List<User> limit2 = list.stream().limit(2).collect(Collectors.toList());
        log.info("limit2 {}", limit2);
    }

    @Test
    public void stream() {
        list.stream().filter(item -> {
            System.out.println(item);
            return true;
        }).filter(item -> {
            System.err.println(item);
            return true;
        }).collect(Collectors.toList());
    }

    @Test
    public void testReference() {
        List<User> collect = list.stream().filter(item -> item.getUserId() < 5).collect(Collectors.toList());
        for (User user : collect) {
            user.setUsername("modify-" + user.getUsername());
        }
        log.info("list {}", JSON.toJSONString(list));
    }

    @Test
    public void testskip() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        List<Integer> collect = integers.stream().skip(0).limit(10).collect(Collectors.toList());
        System.out.println(collect);
    }



    @AllArgsConstructor
    @Data
    private static class User {

        private Integer userId;
        private String username;

    }
}
