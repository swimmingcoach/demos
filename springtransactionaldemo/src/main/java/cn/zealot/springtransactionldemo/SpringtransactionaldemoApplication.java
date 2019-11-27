package cn.zealot.springtransactionldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringtransactionaldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringtransactionaldemoApplication.class, args);
    }

}
