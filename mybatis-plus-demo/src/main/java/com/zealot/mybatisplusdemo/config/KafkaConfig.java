package com.zealot.mybatisplusdemo.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/9/30 14:43
 */

@Configuration
public class KafkaConfig {

    @Bean
    public AdminClient adminClient() {
        return KafkaAdminClient.create(getProps());
    }

    // 配置Kafka
    private Properties getProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
/*    props.put("retries", 2); // 重试次数
    props.put("batch.size", 16384); // 批量发送大小
    props.put("buffer.memory", 33554432); // 缓存大小，根据本机内存大小配置
    props.put("linger.ms", 1000); // 发送频率，满足任务一个条件发送*/
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

}

