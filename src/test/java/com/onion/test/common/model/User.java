package com.onion.test.common.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User extends Person {

    private Integer id;

    private Integer userId;
    private String userName;
    private Integer sex;
}
