package com.onion.test.distributed.dubbo;

import org.apache.dubbo.common.bytecode.ClassGenerator;
import org.apache.dubbo.common.bytecode.Wrapper;
import org.junit.Test;

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
