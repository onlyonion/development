package com.onion.test.java.lang;

import org.junit.Test;

public class StringTest {

    public static void main(String[] args) {
        Long a = null;
        String s = a + ";";
        String sb = String.valueOf(a) + ";";
        System.out.println(s + sb);
    }

    @Test
    public void testIntern() {
        System.out.println("java" == "java".intern()); // true

        // 如果字符串常量池（方法区）中已经包含了等于此String对象的字符串，则返回代表池中这个字符串的String对象的引用
        // 否则，会将此字符串对象包含的字符串添加到常量池中，并且返回此String对象的引用
        String s = new StringBuilder().append("qq").append("jj").toString();
        System.out.println(s == s.intern()); // true

        // sun.misc.Version
        String java = new StringBuilder().append("ja").append("va").toString();
        System.out.println(java == java.intern()); // false

        String java1 = new StringBuilder().append("java").toString();
        System.out.println(java1 == java1.intern()); // false
    }

}
