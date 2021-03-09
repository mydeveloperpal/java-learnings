package com.mydeveloperpal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Consumer implements Runnable {

  private final List<Integer> elements;


  public Consumer(List<Integer> elements) {
    this.elements = elements;
  }

  @Override
  public void run() {
    sleep(5000);
    Main.printMessage("Drained from " + elements.get(0) + " to " + elements.get(elements.size()-1));
  }

  public static void sleep(int milliSeconds) {
    try {
      Thread.sleep(milliSeconds);
    } catch(InterruptedException interruptedException) {
      Main.printMessage(interruptedException.getMessage());
    }
  }

}
