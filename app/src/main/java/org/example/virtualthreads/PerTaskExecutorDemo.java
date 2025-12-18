package org.example.virtualthreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerTaskExecutorDemo {
    public void start() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        int vthreads = 100;
        for (int i = 0; i < vthreads; i++) {
            executorService.submit(new Virtual(i));
        }

        System.out.println("********* threads scheduled." + vthreads);
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdown();
    }

    public static void main(String[] args) throws Exception {
        PerTaskExecutorDemo demo = new PerTaskExecutorDemo();
        demo.start();
    }
}
