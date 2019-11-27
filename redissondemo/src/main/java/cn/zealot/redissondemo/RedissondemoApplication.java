package cn.zealot.redissondemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("cn.zealot.redissondemo.mapper")
public class RedissondemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissondemoApplication.class, args);
    }

}
