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

@Component
@RequiredArgsConstructor
@Aspect
@Slf4j
public class RedisListCacheAspect {

    private final RedisCacheUtils redisCacheUtils;

    @Around("@annotation(RedisListCacheable)")
    public Object listCacheable(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RedisListCacheable redisListCacheable = AnnotationUtils.getAnnotation(methodSignature.getMethod() , RedisListCacheable.class);

        String key = redisListCacheable.value();
        Object [] args = joinPoint.getArgs();
        if(args.length > 0 ){
            key = key + Joiner.on(":").join(joinPoint.getArgs());
        }else{
            key = key + "noArgs[]";
        }

        log.info("key {}   , join point args : {}" , key , joinPoint.getArgs() );
        Object result = redisCacheUtils.get(key);
        if(ObjectUtils.isEmpty(result)){
            result = joinPoint.proceed();
            redisCacheUtils.set(key, result, redisListCacheable.value());
        }
        return result;
    }

    @Around("@annotation(RedisListCacheEvict)")
    public Object listCacheEvict(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RedisListCacheEvict redisListCacheEvict = AnnotationUtils.getAnnotation(methodSignature.getMethod() , RedisListCacheEvict.class);
        log.info("RedisListCacheEvict value : {}" , redisListCacheEvict.value() );
        redisCacheUtils.evict(redisListCacheEvict.value());
        return joinPoint.proceed();

    }
}
