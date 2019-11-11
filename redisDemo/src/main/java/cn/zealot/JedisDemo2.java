package cn.zealot;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/10/28 17:02
 */
public class JedisDemo2 {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        JedisPool pool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.set("pooledJedis", "hello jedis pool!");
            System.out.println(jedis.get("pooledJedis"));
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //ªπªÿpool÷–
            if(jedis != null){
                jedis.close();
            }
        }

        pool.close();
    }
}
