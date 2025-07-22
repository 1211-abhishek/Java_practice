package MultiThreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServicePractice {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Integer> task1 = () -> {
            Thread.sleep(2000);
            System.out.println("task 1 by : " + Thread.currentThread().getName() + " ThreadID : " + Thread.currentThread().getId());
            return 1;
        };
        Callable<Integer> task2 = () -> {
            System.out.println("task 2 by : " + Thread.currentThread().getName() + " ThreadID : " + Thread.currentThread().getId());
            return 2;
        };
        Callable<Integer> task3 = () -> {
            System.out.println("task 3 by : " + Thread.currentThread().getName() + " ThreadID : " + Thread.currentThread().getId());
            return 3;
        };
        Callable<Integer> task4 = () -> {
            System.out.println("task 4 by : " + Thread.currentThread().getName() + " ThreadID : " + Thread.currentThread().getId());
            return 4;
        };
        Callable<Integer> task5 = () -> {
            System.out.println("task 5 by : " + Thread.currentThread().getName() + " ThreadID : " + Thread.currentThread().getId());
            return 5;
        };


        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        List<Callable<Integer>> taskList = Arrays.asList(task1, task2, task3, task4, task5);
//        List<Future<Integer>> futureList = executorService.invokeAll(taskList);
//
//        for (Future<Integer> i : futureList) {
//
//            System.out.println(i.get());
//        }
        //System.out.println("in for");

        Future<Integer> submit1 = executorService.submit(task1);
        Future<Integer> submit2 = executorService.submit(task2);
        Future<Integer> submit3 = executorService.submit(task3);
        Future<Integer> submit4 = executorService.submit(task4);
        Future<Integer> submit5 = executorService.submit(task5);


        executorService.awaitTermination(5,TimeUnit.SECONDS);

        System.out.println("Main thread end");
    }
}
