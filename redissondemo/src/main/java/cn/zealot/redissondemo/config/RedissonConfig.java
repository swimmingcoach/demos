package cn.zealot.redissondemo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/27 10:37
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.commandTimeout}")
    private int commandTimeout;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        ClusterServersConfig cf = config.useClusterServers().setScanInterval(2000);

        String[] cNodes = clusterNodes.split(",");
        for (String node : cNodes) {
            String[] hp = node.split(":");
            cf.addNodeAddress("redis://" + hp[0] + ":" + hp[1]);
        }

        return Redisson.create(config);
    }
}
