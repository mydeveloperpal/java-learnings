package com.mydeveloperpal.redis;

import java.io.IOException;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    RedisConfig redisConfig = new RedisConfig();
    try(RedisWrapper wrapper = new RedisWrapper(redisConfig)) {
      applyRedisCommands(wrapper);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private static void applyRedisCommands(RedisWrapper wrapper) {
      IntStream.range(0, 1000)
          .forEach(i -> {
            sleep();
            final String value = String.valueOf(i);
            wrapper.apply(redis -> redis.ping(value));
          });

  }

  private static void sleep() {
    try {
      Thread.sleep(1000);
    } catch(InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
  }
}
