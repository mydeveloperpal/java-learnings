package com.mydeveloperpal.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class RedisWrapper implements Closeable {

  private final JedisPool jedisPool;
  private final RedisMonitor redisMonitor;
  private final ScheduledExecutorService scheduledMonitor;

  public RedisWrapper(final RedisConfig redisConfig) {
    addAdditionalConfig(redisConfig);
    this.jedisPool = new JedisPool(redisConfig, redisConfig.getRedisHost(), redisConfig.getRedisPort(), 1000,
        redisConfig.getRedisPassword(), redisConfig.getRedisDatabase(), "mydeveloperpal");
    this.redisMonitor = new RedisMonitor(redisConfig, jedisPool);

    this.scheduledMonitor = Executors.newScheduledThreadPool(1);
    this.scheduledMonitor.scheduleAtFixedRate(redisMonitor, 1, 1, TimeUnit.SECONDS);
  }

  public <T> T apply(Function<Jedis, T> operation) {
    try(Jedis jedis = jedisPool.getResource()) {
      T output = operation.apply(jedis);
      onDemandMonitor(output.toString());
      return output;
    } catch (JedisConnectionException jedisConnectionException) {
      jedisConnectionException.printStackTrace();
    }
    return null;
  }

  private void onDemandMonitor(String source) {
    redisMonitor.printStats(source);
  }

  @Override
  public void close() throws IOException {
    try {
      jedisPool.close();
      scheduledMonitor.shutdown();
    } catch(Exception e) {
      e.printStackTrace();
    }

  }

  private static void addAdditionalConfig(JedisPoolConfig poolConfig) {
    poolConfig.setMaxTotal(10);
    poolConfig.setMaxIdle(8);
    poolConfig.setMinIdle(5);
  }



}
