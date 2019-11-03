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


    @Test
    public void test() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new User(i, "name-" + 1));
        }
        Map<Integer, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getUserId));

        boolean match = list.stream().anyMatch(item -> 1 == item.getUserId());

        List<String> flat = list.stream().map(user -> user.getUsername().split("-"))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());

        List<User> limit2 = list.stream().limit(2).collect(Collectors.toList());
    }


    @AllArgsConstructor
    @Data
    private static class User {

        private Integer userId;
        private String username;
    }
}
