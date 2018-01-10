package com.cache.config;

import org.springframework.core.NamedThreadLocal;

public class ThreadLocalUtils {
    private final static NamedThreadLocal<Integer> cacheExpireHolder = new NamedThreadLocal<>("redis-cache-expire-holder");
    private final static NamedThreadLocal<DataSourceEnum> dataSourceHolder = new NamedThreadLocal<>("dataSource-holder");

    public static NamedThreadLocal<Integer> getCacheExpireHolder() {
        return cacheExpireHolder;
    }

    public static NamedThreadLocal<DataSourceEnum> getDataSourceHolder() {
        return dataSourceHolder;
    }
}
