package com.onion.test.common.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

    private String name;
    private String address;
    private String mobile;
    @JSONField(format = "#.##")
    private Double salary;
    @JSONField(format = "#.###")
    private Double bonus;

}
