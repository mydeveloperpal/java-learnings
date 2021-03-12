package com.mydeveloperpal.executortasks;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

  private static final int maxQueueSize = 5000;
  private static final int numOfProducerThreads = 1;
  private static final int numOfSplitterThreads = 1;
  private static final int numOfConsumerThreads = 4;

  public static void main(String[] args) {

    final BlockingQueue<Message> source = new LinkedBlockingQueue<>(maxQueueSize);
    final ScheduledExecutorService producerService = Executors.newScheduledThreadPool(numOfProducerThreads);
    final ScheduledExecutorService splitterService = Executors.newScheduledThreadPool(numOfSplitterThreads);
    final ExecutorService consumerService = Executors.newFixedThreadPool(numOfConsumerThreads);

    final Producer producer = new Producer(source);
    final Splitter splitter = new Splitter(source, consumerService);

    producerService.scheduleAtFixedRate(producer, 0, 1, TimeUnit.SECONDS);
    splitterService.scheduleAtFixedRate(splitter, 3, 1, TimeUnit.SECONDS);
  }

  public static void printMessage(String message) {
    System.out.println(LocalDateTime.now() + " Thread: " + Thread.currentThread().getName() + " " + message);
  }
}
