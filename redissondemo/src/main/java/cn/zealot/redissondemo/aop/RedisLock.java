package cn.zealot.redissondemo.aop;

import java.lang.annotation.*;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/27 17:11
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLock {
    String value() default "defaultRedisLock";

    int leaseTime() default 10;
}
