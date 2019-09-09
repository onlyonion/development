package com.onion.test.common.json;

import com.google.gson.Gson;
import org.junit.Test;

public class GsonTest {

    @Test
    public void test() {
        Person person = new Person();
        person.setName("hello");
        person.setAddress("this is west sky");

        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println(json);
    }

    // {"name":"hello","address":"this is west sky"}
    @Test
    public void parse() {
        Gson gson = new Gson();
        String jsonString = "{\"name\":\"hello\",\"address\":\"this is west sky\"}";
        Person person = gson.fromJson(jsonString, Person.class);
        System.out.println(person);
    }

}
