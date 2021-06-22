package com.devracoon.redis;

import org.springframework.boot.SpringApplication;

public class TestMain {
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
