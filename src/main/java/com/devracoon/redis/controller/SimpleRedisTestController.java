package com.devracoon.redis.controller;

import com.devracoon.redis.service.SimpleRedisTestServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimpleRedisTestController {

    private final SimpleRedisTestServiceImpl simpleRedisTestService;

    @GetMapping("/simple/getSimpleData")
    public List<String> getSimpleData(HttpServletRequest request , HttpServletResponse response) throws Exception{
        List<String> result = simpleRedisTestService.getSimpleData("param1");
        return result;
    }

    @GetMapping("/simple/removeSimpleData")
    public void removeSimpleData(HttpServletRequest request , HttpServletResponse response) throws Exception{
        simpleRedisTestService.simpleDataEvict();
    }

}
