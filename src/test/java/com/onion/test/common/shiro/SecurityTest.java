package com.onion.test.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.Test;

public class SecurityTest {

    @Test
    public void test1() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject);
    }


    @Test
    public void test2() {
        Subject subject = ThreadContext.getSubject();
        ThreadContext.bind(subject);
    }

}
