package com.onion.test.java.features;

import org.junit.Test;

public class Java11LTSTest {

    // 无操作垃圾收集器ZGC

    @Test
    public void test() {
        // Lambda 表达式中使用 var
        // (var x, var y) -> x.process(y);

        // 判断字符串是否为空白
        boolean blank = " ".isBlank();
        String s1 = " Javastack ".stripTrailing();// " Javastack"
        String s = " Javastack ".stripLeading();// "Javastack "
    }

}
