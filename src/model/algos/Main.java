package model.algos;
import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create test tasks (taskId, arrivalTime, burstTime, priority)
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 0, 6, 2));  // Task 1: Arrives at 0, needs 6 units, priority 2
        tasks.add(new Task(2, 1, 4, 1));  // Task 2: Arrives at 1, needs 4 units, priority 1
        tasks.add(new Task(3, 2, 2, 3));  // Task 3: Arrives at 2, needs 2 units, priority 3
        tasks.add(new Task(4, 3, 8, 2));  // Task 4: Arrives at 3, needs 8 units, priority 2
        tasks.add(new Task(5, 4, 3, 1));  // Task 5: Arrives at 4, needs 3 units, priority 1

        // Create scheduler
        Scheduler scheduler = new SRTF();

        // Execute SRTF scheduling
        scheduler.execute(tasks);

        // Print results
        System.out.println("\nFinal Results:");
        System.out.println("ID\tArrival\tBurst\tPriority\tCompletion\tTurnaround\tWaiting");
        for (Task t : tasks) {
            System.out.printf("%d\t%d\t%d\t%d\t\t%d\t\t%d\t\t%d\n",
                    t.getTaskId(),
                    t.getArrivalTime(),
                    t.getBurstTime(),
                    t.getPriority(),
                    t.getCompletionTime(),
                    t.getTurnaroundTime(),
                    t.getWaitingTime());
        }
    }
}
