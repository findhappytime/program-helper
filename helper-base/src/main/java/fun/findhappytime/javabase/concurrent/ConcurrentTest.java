package fun.findhappytime.javabase.concurrent;

import java.util.concurrent.*;

/**
 * @author ：zhangshu09
 * @date ：Created in 2019-12-10 15:10
 * @description：
 */
public class ConcurrentTest {

    class Producer implements Runnable {

        protected BlockingQueue<String> queue = null;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                queue.put("1");  //���������������ֱ���������пռ䣬���ܷţ�Ȼ�����ִ����������
                System.out.println("success put 1 ");
                Thread.sleep(1);
                queue.put("2");
                System.out.println("success put 2 ");
                Thread.sleep(1000);
                queue.put("3");
                System.out.println("success put 3 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {

        protected BlockingQueue<String> queue = null;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                Thread.sleep(10000);
                System.out.println("�����ߵ�1�����" + queue.take());
                System.out.println("�����ߵ�2�����" + queue.take()); //���������������ֱ���������ж��������ó���
                System.out.println("�����ߵ�3�����" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * ScheduledExecutorService
     */
    public static void ScheduledExecutorServiceTest() {
        String schedule = "1";
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        scheduleTask(service, new Runnable() {
            public void run() {
                System.out.println("aaaScheduledExecutorServiceTest...");
            }
        }, schedule);
        scheduleTask(service, new Runnable() {
            public void run() {
                System.out.println("bbbScheduledExecutorServiceTest...");
            }
        }, schedule);
        scheduleTask(service, new Runnable() {
            public void run() {
                System.out.println("cccScheduledExecutorServiceTest...");
            }
        }, schedule);
    }

    private static void scheduleTask(ScheduledExecutorService service,
                                     Runnable counteredTask, String schedule) {
        service.scheduleWithFixedDelay(counteredTask, 1,
                Long.parseLong(schedule), TimeUnit.SECONDS);
    }

    /**
     * ExecutorService execute()
     */

    public static void ExecutorServiceTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("ExecutorServiceTest execute()...");
                }
            }
        });
        System.out.println("--------- ExecutorServiceTest execute() end ...");
        executorService.shutdown();
    }

    /**
     * ExecutorService submit()
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */

    public static void ExecutorServiceSubmit() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<?> future = executorService.submit(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10) {
                    System.out.println("ExecutorServiceTest submit()...");
                }
            }
        });
        System.out.println("--------------------  " + future.get());
        //future.get();  //returns null if the task has finished correctly.
    }

    /**
     * BlockingQueueTest
     */
    public void BlockingQueueTest() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }

    /**
     * ConcurrentMap
     */
    public static void ConcurrentMapTest() {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();
        concurrentMap.put("key", "value");
        String value = concurrentMap.get("key");
        System.out.println("ConcurrentMapTest value : " + value);
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
//    	ConcurrentTest.ScheduledExecutorServiceTest();
//    	ConcurrentTest.ExecutorServiceTest();
//        ConcurrentTest.ExecutorServiceSubmit();
        //���� ���Ѷ���
//        ConcurrentTest.BlockingQueueTest();
//        ConcurrentTest.ConcurrentMapTest();
    }
}


