package com.onion.test.common.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User extends Person {

    private Integer id;

    private Long longId;

    private Integer userId;
    private String userName;
    private Integer userAge;
    private String userAddress;

    public User() {
    }

    public User(Integer id, String userName, Integer userAge, String userAddress) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
        this.userAddress = userAddress;
    }
}
