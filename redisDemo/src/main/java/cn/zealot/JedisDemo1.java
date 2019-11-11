package cn.zealot;

import redis.clients.jedis.Jedis;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/10/28 16:51
 */
public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("singleJedis", "hello jedis!");
        System.out.println(jedis.get("singleJedis"));
        jedis.close();
    }
}
