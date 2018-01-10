package com.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Objects;

/**
 * 操作Redis缓存，提供callback回调，在回调中可以决定是从数据库中读取
 */
public class CacheTemplate {
    @Autowired@Qualifier("shardedJedis")
    private JedisCommand shardedJedis;
    private Logger logger = LoggerFactory.getLogger(CacheTemplate.class);

    /**
     * 先从cache中查找，找不到去执行db，然后把db执行结果缓存起来
     * @param key
     * @param seconds
     * @param callback
     * @param <T>
     * @return
     */
    public <T> T get(String key, int seconds, DbCallback<T> callback){
        byte[] bytes = this.shardedJedis.get(key.getBytes());
        if(bytes != null){
            this.logger.info("from cache");
            return (T) RedisByteSerializer.toObject(bytes);
        }
        this.logger.info("from db===============");
        T t = callback.doInDb();
        this.shardedJedis.setex(key.getBytes(), seconds, RedisByteSerializer.toByteArray(t));
        return t;
    }

    public Long evict(String key){
        Objects.requireNonNull(key);
        return this.shardedJedis.del(key.getBytes());
    }
}
