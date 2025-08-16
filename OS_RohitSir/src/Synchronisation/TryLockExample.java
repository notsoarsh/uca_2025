package Synchronisation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void doWork() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {  //try for 1 sec to get lock
                try {
                    System.out.println(Thread.currentThread().getName() + " got the lock. Yay!");
                    Thread.sleep(2000); //simulate work
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " couldn't acquire the lock.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        TryLockExample ex = new TryLockExample();

        Thread t1 = new Thread(ex::doWork, "T1");
        Thread t2 = new Thread(ex::doWork, "T2");

        t1.start();
        t2.start();

    }
}

/**
 *
 * Basically in this program we are saying that each thread waits for some amount of time
 * trying to get the lock. If it doesn't acquire the lock, it moves forward.
 * This avoids DEADLOCK
 */
