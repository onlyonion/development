package com.onion.test.java.lang;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ObjectLayoutTest {
    /*
      计算对象的大小（单位为字节）：ClassLayout.parseInstance(obj).instanceSize()
        查看对象内部信息： ClassLayout.parseInstance(obj).toPrintable()
        查看对象外部信息：包括引用的对象：GraphLayout.parseInstance(obj).toPrintable()
        查看对象占用空间总大小：GraphLayout.parseInstance(obj).totalSize()

      对象头大小的变化:
​       关闭指针压缩时，对象头中元数据指针为Klass类型，占用8个字节；
        开启指针压缩时，对象头中元数据指针为narrowKlass 类型，占用8个字节。
     */
    Object object = new Object();

    ExecutorService pool =  Executors.newFixedThreadPool(3);

    @Test
    public void test0() {
        // -XX:BiasedLockingStartupDelay=0
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        pool.execute(() -> {
            object.hashCode();
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        });
        // biasable
        // thin lock
        // fat lock 重量级锁
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    @Test
    public void testUnUseCompressedOops() {
        // -XX:-UseCompressedOops
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }


}
