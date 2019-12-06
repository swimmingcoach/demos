package cn.zealot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RedissondemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissondemoApplication.class, args);
    }

}
