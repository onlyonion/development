package com.onion.test.java.lang;

import org.elasticsearch.action.support.AdapterActionFuture;
import org.junit.Test;

import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;

public class ClassTest {

    @Test
    public void test() {
        System.out.println(Future.class.isAssignableFrom(AdapterActionFuture.class));
        System.out.println(Future.class.isAssignableFrom(RunnableFuture.class));
        System.out.println(RunnableFuture.class.isAssignableFrom(Future.class));
    }
}
