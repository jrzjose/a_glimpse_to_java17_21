package org.example.virtualthreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public void start() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        int vthreads = 100;
        for (int i = 0; i < vthreads; i++) {
            executorService.submit(new Virtual(i));
        }
        
        System.out.println("********* threads scheduled.");
        executorService.shutdown();
    }

    public static void main(String[] args) throws Exception {
        FixedThreadPoolDemo demo = new FixedThreadPoolDemo();
        demo.start();
    }
}
