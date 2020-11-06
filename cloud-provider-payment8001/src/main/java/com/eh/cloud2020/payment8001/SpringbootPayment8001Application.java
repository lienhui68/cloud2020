package com.eh.cloud2020.payment8001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.eh.cloud2020.payment8001.dao")
@SpringBootApplication
public class SpringbootPayment8001Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootPayment8001Application.class, args);
    }
}
