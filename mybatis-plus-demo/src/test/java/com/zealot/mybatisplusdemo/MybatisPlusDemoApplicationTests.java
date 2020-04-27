package com.zealot.mybatisplusdemo;

import com.zealot.mybatisplusdemo.mapper.UserMapper;
import com.zealot.mybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
