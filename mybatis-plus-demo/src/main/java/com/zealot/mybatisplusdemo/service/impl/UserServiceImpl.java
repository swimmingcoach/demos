package com.zealot.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zealot.mybatisplusdemo.mapper.UserMapper;
import com.zealot.mybatisplusdemo.pojo.User;
import com.zealot.mybatisplusdemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/4/27 9:26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
