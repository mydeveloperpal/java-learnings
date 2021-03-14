package com.mydeveloperpal.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisMonitor implements Runnable {

  private final JedisPool jedisPool;
  private final JedisPoolConfig poolConfig;

  public RedisMonitor(JedisPoolConfig poolConfig, JedisPool jedisPool) {
    this.jedisPool = jedisPool;
    this.poolConfig = poolConfig;
  }

  @Override
  public void run() {
    printStats("scheduler");
  }

  public void printStats(String caller) {
    int active = jedisPool.getNumActive();
    int idle = jedisPool.getNumIdle();
    int total = active + idle;
    String log = String.format(
        "JedisPool: Caller=%s, Active=%d, Idle=%d, Waiters=%d, total=%d, maxTotal=%d, minIdle=%d, maxIdle=%d",
        caller,
        active,
        idle,
        jedisPool.getNumWaiters(),
        total,
        poolConfig.getMaxTotal(),
        poolConfig.getMinIdle(),
        poolConfig.getMaxIdle()
    );
    System.out.println(log);
  }
}
