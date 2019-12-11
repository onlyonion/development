package com.onion.test.java.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lijicong
 */
public class AtomicTest {

    static AtomicInteger ai = new AtomicInteger(1);

    @Test
    public void testAtomicInteger() {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }

    static int[] value = new int[] { 1, 2 };
    static AtomicIntegerArray aia = new AtomicIntegerArray(value); // 复制一份

    @Test
    public void testAtomicIntegerArray() {
        aia.getAndSet(0, 3);
        System.out.println(aia.get(0));
        System.out.println(value[0]);
    }

    static AtomicReference<User> ar = new AtomicReference<>();

    @Test
    public void testAtomicReference() {
        User hellow = new User("hellow", 18);
        ar.set(hellow);
        User world = new User("world", 19);
        ar.compareAndSet(hellow, world);
        System.out.println(ar.get());
    }

    static AtomicIntegerFieldUpdater<User> aifu = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    @Test
    public void testAtomicIntegerFieldUpdater() {
        User user = new User("youyou", 99);
        aifu.getAndIncrement(user);
        System.out.println(aifu.get(user));
        System.out.println(user);
    }

    @AllArgsConstructor
    @Data
    static class User {
        private String name;
        //private int old; // 使用原子更新字段时 java.lang.IllegalAccessException:  an not access a member of class XXX with modifiers "private"
        public volatile int old;
    }

}
