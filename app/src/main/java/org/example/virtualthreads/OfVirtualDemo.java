package org.example.virtualthreads;

import java.util.concurrent.TimeUnit;

public class OfVirtualDemo {

    public void start() throws InterruptedException {
        int vthreads = 1000;
        Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
        Thread[] list = new Thread[vthreads];
        for (int i = 0; i < vthreads; i++) {
            list[i] = builder.start(new Virtual(i));
        }

        System.out.println("********* threads scheduled.");
        TimeUnit.SECONDS.sleep(100);
        for (int i = 0; i < vthreads; i++) {
            list[i].interrupt();
        }
        
    }

    public static void main(String[] args) throws InterruptedException {
        OfVirtualDemo demo = new OfVirtualDemo();
        demo.start();
    }
}
