package com.zealot.mybatisplusdemo.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/1/11 17:35
 */
public class FeignConfigMore {
    @Bean
    Request.Options feignOptions() {
        // ZhangSen 2020/8/11 17:20 1秒后连接超时，3秒后响应超时
        return new Request.Options(1, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true);
    }

    @Bean
    public Retryer feignRetryer() {
        // ZhangSen 2020/8/11 17:26 重试（3-1=2）次，每次间隔100到1000随机毫秒
        // 链接超时不会重试，响应超时才会重试
        return new Retryer.Default(100, SECONDS.toMillis(1), 3);
    }
}
