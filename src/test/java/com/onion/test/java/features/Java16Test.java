package com.onion.test.java.features;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Java16Test {

    @Test
    public void testInstanceof() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        if (list instanceof ArrayList<String> arr) {
            System.out.println(arr.size());
        }
    }

    @Test
    public void testSeal() {

    }

    @Test
    public void testSwtichCase() {
        var i = new Random().nextInt();
        switch (i) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println(i);
                break;
        }

        switch (i) {
            case 1, 4, 6 -> System.out.println("1");
            case 2 -> System.out.println("2");
            default -> System.out.println(i);
        }

        var level = switch (i) {
            case 1 -> "游戏";
            case 2 -> {
                System.out.println("游戏人生");
                yield "人生"; // 返回，不用return
            }
            default -> "hello";
        };
    }

    @Test
    public void testRecord() {

    }

    record Point(int x, int y) {
    }

    // java.lang.Record

    /*
    record Point(int x, int y) {
        // Implicitly declared fields
        private final int x;
        private final int y;

        // Other implicit declarations elided ...

        // Implicitly declared canonical constructor
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
     */

    record Range(int lo, int hi) {
        Range {
            if (lo > hi)  // referring here to the implicit constructor parameters
                throw new IllegalArgumentException(String.format("(%d,%d)", lo, hi));
        }
    }


}
