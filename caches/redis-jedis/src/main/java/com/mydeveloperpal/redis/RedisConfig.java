package com.mydeveloperpal.redis;

import redis.clients.jedis.JedisPoolConfig;

public class RedisConfig extends JedisPoolConfig {

  private final String redisHost = "localhost";
  private final int redisPort = 6379;
  private final String redisPassword = "test";
  private final int redisDatabase = 0;

  public String getRedisHost() {
    return redisHost;
  }

  public int getRedisPort() {
    return redisPort;
  }

  public String getRedisPassword() {
    return redisPassword;
  }

  public int getRedisDatabase() {
    return redisDatabase;
  }
}
