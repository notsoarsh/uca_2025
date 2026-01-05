import java.util.*;

public class TestSingleTon implements Runnable {
  public static void main(String[] args) throws InterruptedException {
    //multithreaded
        
    long startTime = System.currentTimeMillis();
    Thread[] users = new Thread[10];
    for (int i = 0; i < 10; i++) {
      users[i] = new Thread(new TestSingleTon());
    }    
    //start all threads
    for (int i = 0; i < 10; i++) {
      users[i].start();
    } 
    
    //join threads
    for (int i = 0; i < 10; i++) {
      users[i].join();
    } 
    System.out.println("Total time taken = " + (System.curretnTimeMillis() - startTime));
    
    
  }
}
