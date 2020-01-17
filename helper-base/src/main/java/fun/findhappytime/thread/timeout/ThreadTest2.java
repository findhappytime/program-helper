package fun.findhappytime.thread.timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author ：zhangshu09
 * @date ：Created in 2019-12-09 16:43
 * @description：
 */
public class ThreadTest2 {
    static class Task implements Runnable {
        public String name;
        private int time;

        public Task(String s, int t) {
            name = s;
            time = t;
        }

        public void run() {
            for (int i = 0; i < time; ++i) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(name  + " is interrupted when calculating, will stop...");
                    // 注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
                    return;
                }
                System.out.println("task " + name + " " + (i + 1) + " round");
            }
            System.out.println("task " + name + " finished successfully");
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task1 = new Task("one", 1);
        Task task2 = new Task("two", 2);
        Task task3 = new Task("three", 3);
        Task task4 = new Task("four", 5);
        Future<?> future1 = executor.submit(task1);
        Future<?> future2 = executor.submit(task2);
        Future<?> future3 = executor.submit(task3);
        Future<?> future4 = executor.submit(task4);

        List<Future<?>> futures = new ArrayList<>();
        futures.add(future1);
        futures.add(future2);
        futures.add(future3);
        futures.add(future4);
        try {
            if (executor.awaitTermination(3, TimeUnit.SECONDS)) {
                System.out.println("task finished");
            } else {
                System.out.println("task time out,will terminate");
                for (Future<?> f : futures) {
                    if (!f.isDone()) {
                        f.cancel(true);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("executor is interrupted");
        } finally {
            executor.shutdown();
        }
    }
}
