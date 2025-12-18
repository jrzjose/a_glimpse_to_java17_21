package org.example.virtualthreads;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Virtual implements Runnable {
    private int id; // custom id
    public Virtual(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        int reads = 0;
        while (reads++<30) {
            try {
                System.out.println("Thread ID: " + Thread.currentThread().threadId() + ", custom-Id:" + id);
                Path filePath = Paths.get(System.getProperty("user.dir") + "/app/sample.txt");
                String content = Files.readString(filePath);
                System.out.println(id +" -> " + content);
                Thread.sleep(10);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    
}
