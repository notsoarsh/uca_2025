package model.algos;

import java.util.*;

public class SRTF implements Scheduler {
    Map<Task, Thread> taskThreadMap = new HashMap<>();

    @Override
    public void execute(List<Task> taskList) throws InterruptedException {

        //Creating priority queue based on the remaining time
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
            if (a.getRemainingTime() != b.getRemainingTime()) {
                return a.getRemainingTime() - b.getRemainingTime();
            } else if (a.getArrivalTime() != b.getArrivalTime()) {
                return a.getArrivalTime() - b.getArrivalTime();
            } else {
                return a.getTaskId() - b.getTaskId(); // final tie-breaker
            }
        });



        //starting all the threads
        for (Task t : taskList) {
            Thread thread = new Thread(t);
            thread.start();
            taskThreadMap.put(t, thread);
        }

        int currentTime = 0;
        int idx = 0;
        int completed = 0;

        //Adding the tasks that have arrived to pq
        while (completed < taskList.size()) {
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                pq.offer(taskList.get(idx));
                idx++;
            }

            if (pq.isEmpty()) {
                currentTime++;
                Thread.sleep(100);
                continue;
            }

            //Core logic
            // Task with shortest remaining time
            Task currentTask = pq.poll();

            currentTask.resume();  // Execute 1 time unit
            Thread.sleep(100);
            currentTime++;

            // Check if task is done
            if (currentTask.isCompleted()) {
                currentTask.calculateTimes(currentTime);
                completed++;
                System.out.printf("Task %d is completed at this time %d\n", currentTask.getTaskId(), currentTime);
            } else {
                pq.offer(currentTask); // Put back if not done
            }
        }

        // Join all threads
        for (Thread t : taskThreadMap.values()) {
            t.join();
        }

        printStatistic(taskList);
    }

    private void printStatistic(List<Task> taskList) {
        System.out.println("\nTask | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf(" %2d  |     %2d     |     %2d     |    %2d\n",
                    t.getTaskId(), t.getCompletionTime(),
                    t.getTurnaroundTime(), t.getWaitingTime());
        }
    }
}
