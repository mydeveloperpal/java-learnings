package com.mydeveloperpal.executortasks;

import java.util.List;
import java.util.Random;

public class Consumer implements Runnable {

  private final List<Message> tempHolder;
  private final ErrorManager errorManager;

  public Consumer(List<Message> tempHolder, ErrorManager errorManager) {
    this.tempHolder = tempHolder;
    this.errorManager = errorManager;
  }

  @Override
  public void run() {
    try {
      Main.printMessage("Started processing elements " + tempHolder);
      sleep(3000);
      throwAnError();
      Main.printMessage("Finished processing elements " + tempHolder);
    } catch (Exception e) {
      e.printStackTrace();
      errorManager.processMessages(tempHolder);
    }
  }

  public static void sleep(int milliSeconds) {
    try {
      Thread.sleep(milliSeconds);
    } catch(InterruptedException interruptedException) {
      Main.printMessage(interruptedException.getMessage());
    }
  }
  
  private void throwAnError() {
    if(new Random().nextBoolean()) {
        throw new RuntimeException("Exception processing elements ");
    }
  }

}
