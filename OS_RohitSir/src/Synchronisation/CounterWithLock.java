package Synchronisation;

import java.util.concurrent.locks.ReentrantLock;

public class CounterWithLock {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();
    //Question can be why is it final?
    //We want all threads to use the same lock so reference remains same

    public void increment(){
        lock.lock(); //acquire the lock
        try{
            count++;
        }finally {
            lock.unlock(); //release after updation
        }
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        CounterWithLock counter = new CounterWithLock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count using locks "+ counter.getCount());
    }
}
