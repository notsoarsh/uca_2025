package practice;

import java.util.Scanner;

public class StartStop {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        final Object lock = new Object(); // for wait/notify
        final boolean[] paused = {false};
        final boolean[] running = {true};

        Thread t1 = new Thread(() -> {
            int i = 0;
            while (running[0]) {
                synchronized (lock) {
                    while (paused[0]) { // pause loop
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return; // thread stopped
                        }
                    }
                }
                System.out.println(i++);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        t1.start(); // start only once

        while (true) {
            char c = sc.next().charAt(0);
            if (c == 'p') { // pause
                paused[0] = true;
            } else if (c == 'r') { // resume
                synchronized (lock) {
                    paused[0] = false;
                    lock.notify();
                }
            } else if (c == 'e') { // end
                running[0] = false;
                t1.interrupt();
                t1.join();
                break;
            }
        }
    }
}
