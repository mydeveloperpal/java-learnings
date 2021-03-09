package mydeveloperpal;

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
  private static final int numOfConsumerThreads = 4;

  public static void main(String[] args) {

    final BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(maxQueueSize);
    final ScheduledExecutorService producerService = Executors.newScheduledThreadPool(numOfProducerThreads);
    final ScheduledExecutorService consumerService = Executors.newScheduledThreadPool(numOfConsumerThreads);

    final Producer producer = new Producer(blockingQueue);
    final Consumer consumer = new Consumer(blockingQueue);

    producerService.scheduleAtFixedRate(producer, 0, 1, TimeUnit.SECONDS);
    consumerService.scheduleAtFixedRate(consumer, 5, 2, TimeUnit.SECONDS);

  }

  public static void printMessage(String message) {
    System.out.println(LocalDateTime.now() + " Thread: " + Thread.currentThread().getName() + " " + message);
  }


}
