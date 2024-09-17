package com.hw5.createthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TheadPool {
    public static void main(String[] args) {
        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("Thread " + Thread.currentThread().getId() + " is running");
            });
        }
        executor.shutdown();  // Shutdown the executor once tasks are completed
    }
}
