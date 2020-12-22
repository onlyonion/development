package com.onion.test.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

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
        System.out.println(collect.get(true));
        System.out.println(collect.get(false));
    }

    @Test
    public void anyMatch() {
        boolean match = list.stream().anyMatch(item -> 1 == item.getUserId());
    }

    @Test
    public void flatMap() {
        List<String> flat = list.stream().map(user -> user.getUsername().split("-"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    @Test
    public void limit() {
        List<User> limit2 = list.stream().limit(2).collect(Collectors.toList());
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


    @AllArgsConstructor
    @Data
    private static class User {

        private Integer userId;
        private String username;
    }
}
