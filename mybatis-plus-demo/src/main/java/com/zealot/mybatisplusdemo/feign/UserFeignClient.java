package com.zealot.mybatisplusdemo.feign;

import com.zealot.mybatisplusdemo.config.FeignConfigMore;
import com.zealot.mybatisplusdemo.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/8/11 16:01
 */
@FeignClient(value = "userFeignClient", url = "http://127.0.0.1:9085/user", configuration = FeignConfigMore.class)
public interface UserFeignClient {

    @GetMapping("/list")
    List<User> list();
}
