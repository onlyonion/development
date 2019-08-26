package com.onion.test.java.lang;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JVMTest {

    public static final String METHOD_AREA_CONSTANT = "I am in method area";
    public static String METHOD_AREA_CLASS_VARIABLE = "I am in method area";

    String headField = "I am in java heap";

    // pc
    // jvm stack = 8基本类型 + 对象引用 + 实例方法
    @Test
    public void methodArea() {
    }

    // native stack; native interface; native library
    private native void nativeMethod();

    // 程序 = 算法 + 数据结构
    // 程序 = 框架 + 业务逻辑

    byte[] byteArray = new byte[1024 * 1024];

    @Test
    public void test() {
        alloc();
    }

    public static void main(String[] args) {
        alloc();
    }

    static void alloc() {
        List<JVMTest> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new JVMTest());
                count += 1;
            }
        } catch (Exception e) {
            System.out.println("Exception count=" + count);
            e.printStackTrace();
        } catch (Throwable e) {
            System.out.println("Throwable count=" + count);
            e.printStackTrace();
        }
    }

}
