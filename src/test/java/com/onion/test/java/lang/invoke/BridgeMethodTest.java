package com.onion.test.java.lang.invoke;

import java.lang.reflect.Method;
import org.junit.Test;
import org.springframework.core.BridgeMethodResolver;

public class BridgeMethodTest {

    interface Parent<T> {
        T bridgeMethod(T param);
    }
    // 一个类继承了一个范型类或者实现了一个范型接口, 那么编译器在编译这个类的时候就会生成一个叫做桥接方法的混合方法

    // 混合方法简单的说就是由编译器生成的方法, 方法上有synthetic修饰符
    // ACC_SYNTHETIC ACC_BRIDGE
    static class Child implements Parent<String> {
        public String bridgeMethod(String param) {
            return param;
        }

        /*
        public Object bridgeMethod(Object param) {
            return this.bridgeMethod(((String) param));
        }
        */
    }

    public static void main(String[] args) throws Exception {
        // 使用java的多态
        Parent parent = new Child();
        System.out.println(parent.bridgeMethod("abc123"));// 调用的是实际的方法
        Class<? extends Parent> clz = parent.getClass();
        Method method = clz.getMethod("bridgeMethod", Object.class); // 获取桥接方法
        System.out.println(method.isBridge()); // true

        Method method2 = clz.getMethod("bridgeMethod", String.class); // 获取桥接方法
        System.out.println(method2.isBridge()); // false

        System.out.println(method.invoke(parent, "hello")); // 调用的是桥接方法

        /*
        在运行时当参数类型不是SubClass声明的类型时，会抛出类型转换异常，因为这时调用的是桥接方法，而在桥接方法中会进行强制类型转换，所以才会抛出类型转换异常。
        这里类型只能是String，因为SubClass的泛型类型声明是String类型的，如果指定其他类型，那么在编译时就会错误
        这样就把类型检查从运行时提前到了编译时。这就是泛型的好处
         */
        System.out.println(method.invoke(parent, new Object()));

        // 调用的是桥接方法, 会报ClassCastException: java.lang.Object cannot be cast to java.lang.String`错误`
        // System.out.println(parent.bridgeMethod(new Object()));

        // 泛型是在1.5引入的，为了向前兼容，所以会在编译时去掉泛型（泛型擦除）
    }

    @Test
    public void test() throws NoSuchMethodException {
        Parent parent = new Child();
        Class<? extends Parent> clz = parent.getClass();
        Method method = clz.getMethod("bridgeMethod", Object.class); // 获取桥接方法
        Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
    }
}
