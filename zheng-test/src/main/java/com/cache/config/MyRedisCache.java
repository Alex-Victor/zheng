package com.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Objects;
import java.util.concurrent.Callable;

public class MyRedisCache implements Cache {

    private Logger logger = LoggerFactory.getLogger(MyRedisCache.class);

    @Autowired@Qualifier("shardedJedis")
    private JedisCommand shardedJedis;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.shardedJedis;
    }

    @Override
    public ValueWrapper get(Object key) {
        Objects.requireNonNull(key);
        byte[] bytes = this.shardedJedis.get(key.toString().getBytes());
        if(bytes != null && bytes.length > 0){
            this.logger.info("get from cache...");
            return  new SimpleValueWrapper(RedisByteSerializer.toObject(bytes));
        }
        this.logger.info("no data in cache, should from db");
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Objects.requireNonNull(key);
        byte[] bytes = this.shardedJedis.get(key.toString().getBytes());
        if(bytes != null && bytes.length > 0){
            return null;
        }
        Object object = RedisByteSerializer.toObject(bytes);
        if(type.isAssignableFrom(object.getClass())){
            return (T)object;
        }else{
            return null;
        }
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(Object key, Object value) {
        Objects.requireNonNull(key);
        if(ThreadLocalUtils.getCacheExpireHolder().get()!=null && ThreadLocalUtils.getCacheExpireHolder().get() > 0){
            this.logger.info("cache with expire.....");
            this.shardedJedis.setex(key.toString().getBytes(), ThreadLocalUtils.getCacheExpireHolder().get(), RedisByteSerializer.toByteArray(value));
        }else{
            this.logger.info("cache without expire.....");
            this.shardedJedis.set(key.toString().getBytes(), RedisByteSerializer.toByteArray(value));
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void evict(Object key) {
        this.shardedJedis.del(key.toString().getBytes());
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

}
