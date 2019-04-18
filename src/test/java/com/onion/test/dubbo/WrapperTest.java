package com.onion.test.dubbo;

import org.junit.Test;

import com.alibaba.dubbo.common.bytecode.ClassGenerator;
import com.alibaba.dubbo.common.bytecode.Wrapper;

public class WrapperTest {

    @Test
    public void test () {
        Wrapper wrapper = Wrapper.getWrapper(My.class);
    }


    private static class My implements ClassGenerator.DC {

    }

}
