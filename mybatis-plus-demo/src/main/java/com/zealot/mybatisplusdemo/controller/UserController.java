package com.zealot.mybatisplusdemo.controller;

import com.zealot.mybatisplusdemo.pojo.User;
import com.zealot.mybatisplusdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/8/5 9:58
 */
@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ResponseBody
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/get")
    @ResponseBody
    public User get(Integer id) {
        return userService.getById(id);
    }
}
