package com.zealot.mybatisplusdemo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zealot.mybatisplusdemo.enums.Gender;
import com.zealot.mybatisplusdemo.pojo.User;
import com.zealot.mybatisplusdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void list() {
        List<User> users = userService.list();
        users.forEach(System.out::println);
    }

    @Test
    void save() {
        List<User> list = new ArrayList<User>() {{
            add(new User(null, "1", Gender.male, 1, "1@test.com", 0, 0));
        }};

        userService.saveBatch(list, 2);
    }

    @Test
    void saveOrUpdateBatch() {
        /*
          Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5b332439]
          JDBC Connection [HikariProxyConnection@1670053034 wrapping com.mysql.cj.jdbc.ConnectionImpl@58601e7a] will be managed by Spring
          ==>  Preparing: SELECT id,name,age,email FROM user WHERE id=?
          ==> Parameters: 6(Long)
          <==    Columns: id, name, age, email
          <==        Row: 6, 1, 1, 1@test.com
          <==      Total: 1
          Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5b332439]
          JDBC Connection [HikariProxyConnection@1670053034 wrapping com.mysql.cj.jdbc.ConnectionImpl@58601e7a] will be managed by Spring
          ==>  Preparing: UPDATE user SET name=?, age=?, email=? WHERE id=?
          ==> Parameters: 6(String), 6(Integer), 1@test.com(String), 6(Long)
          ==>  Preparing: INSERT INTO user ( name, age, email ) VALUES ( ?, ?, ? )
          ==> Parameters: 13(String), 13(Integer), 13@test.com(String)
         */
        List<User> list = new ArrayList<User>() {{
            add(new User(6L, "6", Gender.male, 6, "6@test.com", 0, 0));
            add(new User(null, "13", Gender.male, 13, "13@test.com", 0, 0));
        }};

        userService.saveOrUpdateBatch(list, 3);
    }

    @Test
    void removeByIds() {
        /*
          ==>  Preparing: DELETE FROM user WHERE id IN ( ? , ? , ? )
          ==> Parameters: 6(Long), 7(Long), 8(Long)
          <==    Updates: 3
         */
        List<Long> ids = Arrays.asList(9L, 10L, 11L);
        userService.removeByIds(ids);
    }

    @Test
    void removeByMap() {
        /*
        ==>  Preparing: DELETE FROM user WHERE name = ?
        ==> Parameters: 12(String)
        <==    Updates: 0
         */
        Map<String, Object> map = Maps.newHashMap("name", "12");
        userService.removeByMap(map);
    }

    @Test
    void page() {
        /*==>  Preparing: SELECT COUNT(1) FROM user
        ==> Parameters:
        <==    Columns: COUNT(1)
        <==        Row: 10
        ==>  Preparing: SELECT id, name, age, email FROM user ORDER BY age ASC LIMIT ?,?
        ==> Parameters: 7(Long), 7(Long)
        <==    Columns: id, name, age, email
        <==        Row: 4, Sandy, 21, test4@baomidou.com
        <==        Row: 5, Billie, 24, test5@baomidou.com
        <==        Row: 3, Tom, 28, test3@baomidou.com
        <==      Total: 3
         */
        Page<User> page = new Page<>(2, 7);
        page.addOrder(OrderItem.asc("age"));
        IPage<User> userPage = userService.page(page);
        log.debug("共{}页", userPage.getPages());
        log.debug("共{}数据", userPage.getTotal());
        log.debug("详情如下：");
        log.debug(userPage.getRecords().toString());
    }

}
