public class CalculateSumRunnable implements Runnable{

    private int sumUpto;

    public CalculateSumRunnable(int sumUpto) {
        this.sumUpto = sumUpto;
    }

    @Override
    public void run(){
        //Sum
        // Take a rest
        //Print the sum with Name
        int sum = 0;
        for(int i = 1; i > 0; i++){
            sum+=i;
        }

        System.out.println(Thread.currentThread().getName()+" "+sum);
    }

}

