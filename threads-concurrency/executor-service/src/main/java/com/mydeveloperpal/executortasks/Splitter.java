package com.mydeveloperpal.executortasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class Splitter implements Runnable {

  private static final int maxMessageToConsume = 50;
  private final BlockingQueue<Message> source;
  private final ExecutorService consumerService;
  private final ErrorManager errorManager;

  public Splitter(BlockingQueue<Message> source, ExecutorService consumerService) {
    this.source = source;
    this.consumerService = consumerService;
    this.errorManager = new ErrorManager(source);
  }

  @Override
  public void run() {
    final List<Message> temp = new ArrayList<>(maxMessageToConsume);
    source.drainTo(temp, maxMessageToConsume);
    if(temp.size() > 0) {
      consumerService.submit(new Consumer(temp, errorManager));
    }
  }

}
