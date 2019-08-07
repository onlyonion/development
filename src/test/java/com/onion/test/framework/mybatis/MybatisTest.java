package com.onion.test.framework.mybatis;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.onion.test.common.model.User;

public class MybatisTest {

    public static void main(String[] args) throws IOException {
        SqlSessionDao mybatis = new SqlSessionDao();
        mybatis.selectUserById(3);
    }


    @Test
    public void testQuery() throws IOException {
        SqlSessionDao mybatis = new SqlSessionDao();
        mybatis.selectUserById(3);
    }

    @Test
    public void testInsert() throws IOException {
        SqlSessionDao mybatis = new SqlSessionDao();
        User user = new User();
        user.setUserName("tester");
        mybatis.insert(user);
    }

    @Test
    public void testUpdate() throws IOException {
        SqlSessionDao mybatis = new SqlSessionDao();
        User user = new User();
        user.setId(999099);
        user.setUserName("tester3");
        mybatis.updateUser(user);
    }

    @Test
    public void test() throws IOException {
        SqlSessionDao mybatis = new SqlSessionDao();
        mybatis.deleteAllUsers();
        mybatis.selectAllUserId();
        List<User> userList = mybatis.createUser(20);
        User user = new User(3, "hello", 21, "home");
        mybatis.updateUser(user);
        mybatis.selectUserById(3);
        mybatis.insertUsers(userList);
        //mybatis.deleteAllUsers();
        mybatis.insertIntoUser(user);
    }
}
