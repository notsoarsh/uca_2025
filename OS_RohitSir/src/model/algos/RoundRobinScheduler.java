package model.algos;

import java.util.*;

class RoundRobinScheduler implements Scheduler {
    int timeQuantum; //the time that is given to the process to execute
    Map<Task, Thread> taskThreadMap; //Maps each task to its running threads

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
        this.taskThreadMap = new HashMap<>();
    }

    @Override
    public void execute(List<Task> taskList) throws InterruptedException {
        Queue<Task> queue = new LinkedList<>(); // Ready queue
        for (Task t : taskList) {
            Thread thread = new Thread(t); //one thread per task
            thread.start();
            taskThreadMap.put(t, thread);
        }

        int currentTime = 0; //CLOCK
        int completed = 0; //Completed tasks
        int idx = 0; // Index for task arrival

        while (completed < taskList.size()) {

            // adding in the queue , the task that has arrived
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                queue.offer(taskList.get(idx));
                idx++;
            }
            //if no task is ready , wait the cpu for 1 sec and increment time
            if (queue.isEmpty()) {
                currentTime++;
                Thread.sleep(100);
                continue;
            }

            Task currentTask = queue.poll();
            int units = 0;

            while (units < timeQuantum && !currentTask.isCompleted()) {
                currentTask.resume(); // Execute one unit
                Thread.sleep(100); //slow down the process by 1 sec
                currentTime++;
                units++;

                // Other Task Might Arrive in the same Time
                while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                    queue.offer(taskList.get(idx));
                    idx++;
                }
            }

            if (currentTask.isCompleted()) { //if task completed , compute the stats
                currentTask.calculateTimes(currentTime);
                completed++;
                System.out.printf("Task %d is completed at this time %d", currentTask.getTaskId(), currentTime);
            } else {
                // task is paused and sent to the queue
                queue.offer(currentTask);
            }
        }

        // Make sure threads join back
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
/**
 * Notes
 * The RoundRobinScheduler simulates time-sliced CPU scheduling:
 *
 * All tasks are started together (each in their own thread) but paused by default.
 *
 * The scheduler:
 *
 * Picks a task from the ready queue.
 *
 * Lets it run for timeQuantum time units (e.g., 2 units).
 *
 * If it’s not finished → pauses it and moves it to the back of the queue.
 *
 * If it’s done → records completion time.
 *
 * This repeats until all tasks are complete.
 */

/**
 * High-level flow
 * Preparation
 *
 * All tasks are wrapped into their own dedicated threads.
 *
 * Each thread starts immediately, but is paused right away so it doesn’t consume CPU until the scheduler tells it to run.
 *
 * Scheduler loop
 *
 * The scheduler maintains a queue of tasks and cycles through them in a fixed time quantum.
 *
 * For each task in order:
 *
 * Resume the task’s thread (allowing it to run for the quantum).
 *
 * Sleep in the scheduler for that quantum duration.
 *
 * Pause the task’s thread after its turn.
 *
 * If the task still has work left (remainingTime > 0), put it back into the queue; otherwise, it’s finished.
 *
 * Thread control
 *
 * Pausing is done by setting a flag (isPaused = true) and making the thread wait on a lock object.
 *
 * Resuming wakes it up (lock.notify()), letting it continue from where it left off.
 *
 * wait() is used so the paused thread releases the lock and doesn’t waste CPU.
 *
 * Termination
 *
 * The scheduler stops when all tasks are completed (queue is empty).
 *
 * Each task’s thread naturally exits once remainingTime == 0.
 */