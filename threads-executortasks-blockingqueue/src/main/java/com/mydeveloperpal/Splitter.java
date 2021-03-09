package com.mydeveloperpal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class Splitter implements Runnable {

  private static final int maxMessageToConsume = 50;
  private final BlockingQueue<Integer> blockingQueue;
  private final ExecutorService consumerService;

  public Splitter(BlockingQueue<Integer> blockingQueue, ExecutorService consumerService) {
    this.blockingQueue = blockingQueue;
    this.consumerService = consumerService;
  }

  @Override
  public void run() {
    final List<Integer> temp = new ArrayList<>(maxMessageToConsume);
    blockingQueue.drainTo(temp, maxMessageToConsume);
    if(temp.size() > 0) {
      consumerService.submit(new Consumer(temp));
    }
  }

}
