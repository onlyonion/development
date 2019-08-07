package com.onion.test.java.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    static class ClassA {
        public void println(String s) {
            System.out.println(getClass().getName());
            System.out.println(s);
        }
    }

    static class ClassB {
        public void println(String s) {
            System.out.println(getClass().getName());
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object object = System.currentTimeMillis() % 2 == 0 ? new ClassA() : new ClassB();
        getPrintlnMethodHandle(object).invokeExact("hello methodHandle");

    }

    private static MethodHandle getPrintlnMethodHandle(Object reveiwer) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(reveiwer.getClass(), "println", methodType).bindTo(reveiwer);

    }

}
