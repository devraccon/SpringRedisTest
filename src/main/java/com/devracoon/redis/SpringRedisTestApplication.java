package com.devracoon.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRedisTestApplication {

    public static void main(String[] args) {
        String aaa = "aaaa";
        switch (aaa){
            case "aaaa":
            case "bbbb":
            case "cccc":
                System.out.println("aaaaaaaaaaa");
        }
        SpringApplication.run(SpringRedisTestApplication.class, args);
    }

}
