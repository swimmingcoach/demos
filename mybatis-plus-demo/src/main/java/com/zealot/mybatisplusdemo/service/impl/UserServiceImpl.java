package com.zealot.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zealot.mybatisplusdemo.mapper.UserMapper;
import com.zealot.mybatisplusdemo.pojo.User;
import com.zealot.mybatisplusdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/4/27 9:26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "dictionary", keyGenerator = "baseCacheKeyGenerator")
    public List<User> list() {
        return userMapper.selectList(Wrappers.emptyWrapper());
    }

    @Cacheable(value = "dictionary", keyGenerator = "baseCacheKeyGenerator")
    public User getById(Serializable id) {
        return getBaseMapper().selectById(id);
    }
}
