package com.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class CacheExpireAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    private Logger logger = LoggerFactory.getLogger(DataSourceAdvice.class);
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        this.logger.info("CacheExpireAdvice before, target method:{}", method.getName());
        ThreadLocalUtils.getCacheExpireHolder().set(method.getAnnotation(CacheExpire.class).value());
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        this.logger.info("CacheExpireAdvice after, target method:{}", method.getName());
        ThreadLocalUtils.getCacheExpireHolder().remove();
    }
}
