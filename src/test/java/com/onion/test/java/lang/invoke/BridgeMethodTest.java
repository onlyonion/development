package com.onion.test.java.lang.invoke;

import java.lang.reflect.Method;

public class BridgeMethodTest {

    interface Parent<T> {
        T bridgeMethod(T param);
    }
    // 一个类继承了一个范型类或者实现了一个范型接口, 那么编译器在编译这个类的时候就会生成一个叫做桥接方法的混合方法

    // 混合方法简单的说就是由编译器生成的方法, 方法上有synthetic修饰符
    static class Child implements Parent<String> {
        public String bridgeMethod(String param) {
            return param;
        }
    }

    public static void main(String[] args) throws Exception {
        // 使用java的多态
        Parent parent = new Child();
        System.out.println(parent.bridgeMethod("abc123"));// 调用的是实际的方法
        Class<? extends Parent> clz = parent.getClass();
        Method method = clz.getMethod("bridgeMethod", Object.class); // 获取桥接方法
        System.out.println(method.isBridge()); // true

        Method method2 = clz.getMethod("bridgeMethod", String.class); // 获取桥接方法
        System.out.println(method2.isBridge());

        System.out.println(method.invoke(parent, "hello")); // 调用的是桥接方法
        System.out.println(method.invoke(parent, new Object())); // 调用的是桥接方法

        // 调用的是桥接方法, 会报ClassCastException: java.lang.Object cannot be cast to java.lang.String`错误`
        System.out.println(parent.bridgeMethod(new Object()));
    }
}
