package com.zealot.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zealot.mybatisplusdemo.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/4/27 9:15
 */
@Service
public interface UserService extends IService<User> {

    List<User> getAll();

    List<User> list();
}
