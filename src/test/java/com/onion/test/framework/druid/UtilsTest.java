package com.onion.test.framework.druid;

import java.io.IOException;

import org.junit.Test;

import com.alibaba.druid.util.Utils;

public class UtilsTest {

    @Test
    public void test() throws IOException {
        String s = Utils.readFromResource("demo.properties");
        System.out.println(s);
    }

}
