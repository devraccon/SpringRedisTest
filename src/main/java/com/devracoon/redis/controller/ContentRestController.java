package com.devracoon.redis.controller;

import com.devracoon.redis.entity.Content;
import com.devracoon.redis.service.ContentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContentRestController {

    public final RedisTemplate redisTemplate;

    public final ContentServiceImpl contentService;

    @PostMapping("/content/addContent")
    public ContentDTO addContent(HttpServletRequest request , HttpServletResponse response ,@RequestBody ContentDTO dto) throws Exception{

        return contentService.addContent(dto);
    }

    @PostMapping("/content/removeContent")
    public String removeContent(HttpServletRequest request , HttpServletResponse response ,ContentDTO dto) throws Exception{
        contentService.removeContent(dto.getContentId());
        return "OK";
    }


    @GetMapping("/content/findContent")
    public List<ContentDTO> findContent(HttpServletRequest request , HttpServletResponse response ,ContentDTO dto) throws Exception{
        List<ContentDTO> contents = contentService.findContent(dto.getContentName());
        String key = "content";
        String value = "iamvalue";

        final ValueOperations<String, String> stringStringListOperations = redisTemplate.opsForValue();
        log.info("aaaa " + stringStringListOperations.("content"));


        return contents;
    }

}