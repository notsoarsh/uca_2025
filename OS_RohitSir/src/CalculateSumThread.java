public class CalculateSumThread extends Thread {

    private int sumUpto;

    public CalculateSumThread(int sumUpto) {
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
