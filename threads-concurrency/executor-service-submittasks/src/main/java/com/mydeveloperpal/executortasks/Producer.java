package com.mydeveloperpal.executortasks;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Producer implements Runnable {

  private static final int maxIntegersToProduce = 100;
  private static int counter = 0;

  private final BlockingQueue<Message> source;

  public Producer(BlockingQueue<Message> source) {
    this.source = source;
  }

  public void run() {
    IntStream.range(0, numOfIntegersToProduce()).forEach(i -> produceInteger());
  }

  private void produceInteger() {
    try {
      source.put(new Message(++counter));
    } catch(InterruptedException interruptedException) {
      Main.printMessage("Exception for number: "  + counter + " " + interruptedException.getMessage());
    }
  }

  private static Integer numOfIntegersToProduce() {
    Random random = new Random();
    int randomNumber = random.nextInt(maxIntegersToProduce);
    Main.printMessage(" generating " + randomNumber + " numbers");
    return randomNumber;
  }


}
