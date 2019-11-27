package cn.zealot.redissondemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/27 17:11
 */
@Aspect
@Component
@Slf4j
public class RLockHandler {

    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(redisLock)")
    public void around(ProceedingJoinPoint joinPoint, RedisLock redisLock) {
        //获取锁名称
        String lockName = redisLock.value();
        //获取超时时间，默认10秒
        int leaseTime = redisLock.leaseTime();
        RLock rlock = redissonClient.getLock(lockName);
        rlock.lock();
        try {
            log.info("Lock [{}] succeed.", lockName);
            joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("Lock [{}] error!", lockName);
            throwable.printStackTrace();
        } finally {
            //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
            rlock.unlock();
        }
        log.info("Unlock [{}] over.", lockName);
    }

}
