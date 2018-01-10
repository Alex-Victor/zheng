package com.cache.config;

import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.JedisCommands;

public interface JedisCommand extends JedisCommands,BinaryJedisCommands {
}
