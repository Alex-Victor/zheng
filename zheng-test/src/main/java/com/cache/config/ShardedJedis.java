package com.cache.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ShardedJedis implements FactoryBean<JedisCommand>, InvocationHandler {

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //这里最好用try finally，可以监控redis性能，发送到统计中心。这里主要是用try resource尝鲜
        try (redis.clients.jedis.ShardedJedis shardedJedis = this.shardedJedisPool.getResource()) {
            return method.invoke(shardedJedis, args);
        }
    }

    @Override
    public JedisCommand getObject() throws Exception {
        return (JedisCommand) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{JedisCommand.class}, this);
    }

    @Override
    public Class<?> getObjectType() {
        return JedisCommand.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
