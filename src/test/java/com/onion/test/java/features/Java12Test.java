package com.onion.test.java.features;

public class Java12Test {

    // Java 12 以后，switch 不仅可以作为语句，也可以作为表达式。
    // switch可以有返回值 并且使用->直接
    private String switchTest(int i) {
        return switch (i) {
            case 1 -> "1";
            default -> "0";
        };
    }

}
