//public class Main {
////    static int a =10;
//    public static void main(String[] args) {
//
//        Thread t1 = new Thread(() -> {
//            int sum = 0;
//            for(int i =0 ;i< 10000 ; i++){
//                sum+=i;
//            }
////            System.out.println(Thread.currentThread().getName()+sum+" "+a);
//        });
////        t1.setName("Arshpal");
////        t1.setPriority(10);
////        t1.start();  /* Invokes the thread */
//////          t1.run();    /* Used to execute the function of the thread */
////
////
////        long product = 1 ;
////        for(int i =1 ;i<1000;i++){
////            product*=i;
////        }
////        a+=5;
////        System.out.println(Thread.currentThread().getName()+product+" "+a);
//
//
//        CalculateSumThread t1 = new CalculateSumThread(10);
//        CalculateSumThread t2 = new CalculateSumThread(100);
//        CalculateSumThread t3 = new CalculateSumThread(1000);
////        t1.start();
//////        t1.start(); if we start the thread again that is started , we get an IllegalThreadStateException
////        t3.start();
//
//        /// Three ways to implement the methods in a particular thread
//        ///way 1
//        Thread t1 = new Thread(() -> {
//            //IMPLEMENT HERE
//        });
//        /// way 2  we discussed by creating a class of our own and implementing Thread class
//        /// way 3 of passing a Runnable class object
//
//        CalculateSumRunnable c1 = new CalculateSumRunnable(10);
////        c1.setName(); cannot do so
//        CalculateSumThread t2 = new CalculateSumThread(10);
//        t2.setName("Runnable Implement");
//
//
//
//
//
//    }
//}