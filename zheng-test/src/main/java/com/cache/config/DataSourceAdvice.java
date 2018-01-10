package com.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    private Logger logger = LoggerFactory.getLogger(DataSourceAdvice.class);

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        this.logger.info("DataSourceAdvice before,target method:{}", method.getName());
        ThreadLocalUtils.getDataSourceHolder().set(method.getAnnotation(DataSource.class).value());
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        this.logger.info("DataSourceAdvice after,target method:{}", method.getName());
        ThreadLocalUtils.getDataSourceHolder().remove();
    }
}
