package com.onion.test.common.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

public class JacksonTest {

    @Getter
    @Setter
    public static class Person {
        private String name;
        private String address;
        private String mobile;
    }


    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        person.setName("hello");
        person.setAddress("this is west sky");
        System.out.println(objectMapper.writeValueAsString(person));
    }
}
