package com.onion.test.java.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class SuperTest {

    class GrandFather {
        void thinking() throws Throwable {
            System.out.println("I am grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() throws Throwable {
            System.out.println("I am father");
        }
    }

    class Son extends Father {
        void thinking() throws Throwable {
            //super.thinking();
            //System.out.println("I am son");
            MethodType mt = MethodType.methodType(void.class);
            MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
            mh.invoke(this);
        }
    }

    public static void main(String[] args) throws Throwable {
        (new SuperTest().new Son()).thinking();
    }

}
