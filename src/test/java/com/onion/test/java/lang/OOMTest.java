package com.onion.test.java.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.misc.Unsafe;

//import sun.misc.Unsafe;

public class OOMTest {

    /**
     * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void javaHeapSpace() {
        List<byte[]> list = new ArrayList<>();
        for (; ; ) {
            byte[] b = new byte[1024 * 1024];
            list.add(b);
        }
    }

    /**
     * -Xss128k
     */
    @Test
    public void stackLeak() {
        stackLeak();
    }

    /**
     * -Xms10m -Xmx10m -Xss2m
     */
    @Test
    public void stackOOM() {
        for (; ; ) {
            new Thread(() -> dontStop()).start();
        }
    }

    private void dontStop() {
        while (true) {

        }
    }

    /**
     * -XX:PermSize=10m -XX:MaxPermSize=10m
     * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     */
    @Test
    public void methodArea() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i > 0; i++) {
            String intern = String.valueOf(i).intern();
            list.add(intern);
        }
    }

    /**
     * java.lang.OutOfMemoryError: Metaspace
     */
    @Test
    public void methodArea2() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMTest.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o, objects);
                }
            });
            enhancer.create();
        }

    }

    /**
     * -Xmx10m -XX:MaxDirectMemorySize=10m
     */
    @Test
    public void directMemoryOOM() throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

    static final int _1MB = 1024 * 1024;

}
