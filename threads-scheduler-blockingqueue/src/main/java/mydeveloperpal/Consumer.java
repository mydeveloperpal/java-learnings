package mydeveloperpal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

  private static final int maxMessageToConsume = 50;
  private final BlockingQueue<Integer> blockingQueue;

  public Consumer(BlockingQueue<Integer> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    final List<Integer> temp = new ArrayList<>(maxMessageToConsume);
    blockingQueue.drainTo(temp, maxMessageToConsume);
    sleep(1000);
    Main.printMessage("Drained from " + temp.get(0) + " to " + temp.get(temp.size()-1));
  }

  public static void sleep(int milliSeconds) {
    try {
      Thread.sleep(milliSeconds);
    } catch(InterruptedException interruptedException) {
      Main.printMessage(interruptedException.getMessage());
    }
  }

}
