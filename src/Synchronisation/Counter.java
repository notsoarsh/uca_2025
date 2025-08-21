package Synchronisation;

public class Counter {

    int count = 0;
//    synchronized void increment(){
//        count++;
//    }
    void increment(){
        synchronized (this) { //better for performance
            count++;
        }
    }


    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for(int i =0; i < 1000000; i++){
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i =0; i < 1000000; i++){
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.count);
        //here we are not getting 2000000 always

    }
}
