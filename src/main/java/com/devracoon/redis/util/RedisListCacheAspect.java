package com.devracoon.redis.util;

import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@RequiredArgsConstructor
@Aspect
@Slf4j
public class RedisListCacheAspect {

    private final RedisCacheUtils redisCacheUtils;

    @Around("@annotation(RedisListCacheable)")
    public Object listCacheable(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RedisListCacheable redisListCacheable = AnnotationUtils.getAnnotation(methodSignature.getMethod() , RedisListCacheable.class);
        log.info("join point args : {0}" , joinPoint.getArgs() );
        String key = redisListCacheable.value() + Joiner.on(":").join(joinPoint.getArgs());
        Object result = redisCacheUtils.get(key);
        if(ObjectUtils.isEmpty(result)){
            result = joinPoint.proceed();
        }
        return result;

    }
}
