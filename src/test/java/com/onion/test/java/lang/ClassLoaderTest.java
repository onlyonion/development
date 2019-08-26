package com.onion.test.java.lang;

import org.junit.Test;

public class ClassLoaderTest {

    @Test
    public void test() {
        System.out.println(Object.class.getClassLoader());

        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader());
    }

}
