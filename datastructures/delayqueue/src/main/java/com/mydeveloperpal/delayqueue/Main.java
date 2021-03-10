package com.mydeveloperpal.delayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class Main {

  private BlockingQueue<DelayedInteger> integers;

  public Main(BlockingQueue<DelayedInteger> integers) {
    this.integers = integers;
  }

  public static void main(String[] args) {
    final BlockingQueue<DelayedInteger> integers = new DelayQueue<>();
    Main main = new Main(integers);
  }
}
