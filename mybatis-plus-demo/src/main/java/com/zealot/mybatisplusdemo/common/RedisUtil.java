package com.zealot.mybatisplusdemo.common;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Component
public class RedisUtil {
    @Autowired
    private JedisCluster jedisCluster;

    private static final Integer expireSeconds = 60;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    private static final Long RELEASE_SUCCESS = 1L;

    public long lpush(String key, String... value) {
        return jedisCluster.lpush(key, value);
    }

    public void rpop(String key) {
        jedisCluster.rpop(key);
    }

    public Long llen(String key) {
        return jedisCluster.llen(key);
    }

    public List<String> lrange(String key, Long start, Long stop) {
        return jedisCluster.lrange(key, start, stop);
    }

    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    public void set(String key, String value, int expireTime) {
        jedisCluster.setex(key, expireTime, value);
    }

    public Long hSet(final String key, final String field, final String value) {
        return jedisCluster.hset(key, field, value);
    }

    public Long hSetNx(final String key, final String field, final String value) {
        return jedisCluster.hsetnx(key, field, value);
    }

    public String hGet(final String key, final String field) {
        String ret = jedisCluster.hget(key, field);
        if (ret == null || ret.equalsIgnoreCase("nil")) {
            ret = "";
        }
        return ret;
    }

    public Long hIncrBy(final String key, final String field, final long value) {
        return jedisCluster.hincrBy(key, field, value);
    }

    public Long hIncr(final String key, final String field) {
        return jedisCluster.hincrBy(key, field, 1);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (jedisCluster.exists(key)) {
            final String object = getObject(key);
            if (StringUtils.hasText(object)) {
                return JSON.parseObject(object, clazz);
            }
        }
        return null;
    }

    public String getObject(String key) {
        return jedisCluster.get(key);
    }

    public boolean hasKey(String key) {
        return jedisCluster.exists(key);
    }

    public void setWithExpireTime(String key, String value, int expireTime) {
        jedisCluster.setex(key, expireTime, value);
    }

    public void setAtExpireTime(String key, String value, Long milliseconds) {
        jedisCluster.psetex(key, milliseconds, value);
    }

    public String get(String key) {
        String value = jedisCluster.get(key);
        return value;
    }

    public void delete(String key) {
        jedisCluster.del(key);
    }

    public Set<String> getHKeys(String key) {
        Set<String> keys = jedisCluster.hkeys(key);
        return keys;
    }

    public Set<String> getKeys(String pattern) {

        Set<String> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();

        for (String key : clusterNodes.keySet()) {
            JedisPool jedisPool = clusterNodes.get(key);
            Jedis jedisConn = jedisPool.getResource();
            try {
                keys.addAll(jedisConn.keys(pattern));
            } catch (Exception e) {

            } finally {
                jedisConn.close();
            }
        }
        return keys;
    }

    /**
     * 设置过期时间
     *
     * @param token
     * @param seconds
     */
    public void expire(String token, int seconds) {
        jedisCluster.expire(token, seconds);
    }

}
