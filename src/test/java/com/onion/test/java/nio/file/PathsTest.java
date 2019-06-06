package com.onion.test.java.nio.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class PathsTest {

    @Test
    public void test() {
        Path path = Paths.get("D:\\temp\\");
        System.out.println(JSON.toJSONString(path));
    }
}
