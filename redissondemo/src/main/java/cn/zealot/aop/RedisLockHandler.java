package cn.zealot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/27 17:11
 */
@Aspect
@Component
@Slf4j
public class RedisLockHandler {

    private final RedissonClient redissonClient;

    @Autowired
    public RedisLockHandler(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(redisLock)")
    public Object around(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        Object ret;
        String lockName = redisLock.value();
        int leaseTime = redisLock.leaseTime();
        RLock rlock = redissonClient.getLock(lockName);
        rlock.lock(leaseTime, TimeUnit.SECONDS);
        try {
            log.info("Lock [{}] succeed.", lockName);
            ret = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Lock [{}] error!", lockName);
            throw e;
        } finally {
            rlock.unlock();
        }
        log.info("Unlock [{}] over.", lockName);
        return ret;
    }
}