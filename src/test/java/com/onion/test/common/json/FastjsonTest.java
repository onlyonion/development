package com.onion.test.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author lijicong
 * @since 2021-02-01
 */
public class FastjsonTest {

    @Test
    public void testParseMap() {
        String text = "{\"0\":1094.13,\"3\":91.19}";
        Map<String, Double> map = JSON.parseObject(text, Map.class);
        // Double akey = map.get("0"); // 抛异常 Bigdecimal转Double

        Map<String, Double> stringDoubleMap = JSON.parseObject(text, new TypeReference<Map<String, Double>>() {});
        Double aDouble = stringDoubleMap.get("0"); // 不抛异常

    }

    @Test
    public void testParseMapList() {
        String text2 = "{\"1\":[2,3,4,70104,5],\"2\":[5,88,33]}";
        Map<String, List<Long>> map2 = JSON.parseObject(text2, Map.class); // 实际上 Map的key是String类型，value是List<Integer>类型
        boolean contains = map2.get("1").contains(2L); // 返回false
        boolean contains2 = map2.get("1").contains(2); // 返回true

        Map<String, List<Long>> stringListMap = JSON.parseObject(text2, new TypeReference<Map<String, List<Long>>>() {});
        boolean contains3 = stringListMap.get("1").contains(2L); // 返回true
        boolean contains4 = stringListMap.get("1").contains(2); // 返回false
    }

    @Test
    public void test2() {
        String text = "{\"strategyPoint\":\"315\",\"mtStrategy\":\"315\"}";
        Map<String, String> map = JSON.parseObject(text, Map.class);
        System.out.println(map);
    }
}
