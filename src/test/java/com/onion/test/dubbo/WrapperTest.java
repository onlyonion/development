package com.onion.test.dubbo;

import org.junit.Test;

import com.alibaba.dubbo.common.bytecode.ClassGenerator;
import com.alibaba.dubbo.common.bytecode.Wrapper;
import com.alibaba.fastjson.JSON;

public class WrapperTest {

    @Test
    public void test () {
        Wrapper wrapper = Wrapper.getWrapper(My.class);
        System.out.println(wrapper);
    }


    private static class My implements ClassGenerator.DC {

    }

    @Test
    public void system() {
        System.out.println(JSON.toJSONString(System.getProperties(), true));
    }

}
