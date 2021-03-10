package com.mydeveloperpal.executortasks;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ErrorManager {

  private static final int RETRY_COUNT = 2;
  private final BlockingQueue<Message> blockingQueue;

  public ErrorManager(BlockingQueue<Message> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  public void processMessages(List<Message> messages) {
    messages.stream()
        .filter(message -> message.isWithinRetryCountLimit(RETRY_COUNT))
        .map(message -> new Message(message.getValue(), message.getRetryCount()+1))
        .forEach(blockingQueue::add);
  }
}
