package com.onion.test.common.json;

import java.util.Arrays;

import org.junit.Test;

public class ProtostuffTest {

    @Test
    public void test() {
        Person person = new Person();
        person.setName("hello");
        person.setAddress("this is west sky");


        byte[] data = ProtostuffUtils.serialize(person);
        System.out.println("序列化后：" + Arrays.toString(data));
        Person result = ProtostuffUtils.deserialize(data, Person.class);
        System.out.println("反序列化后：" + result.toString());
    }
}
