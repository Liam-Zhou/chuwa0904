package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentCollection {
private static final int THREAD_COUNT = 10;
    private static final int OPERATIONS_PER_THREAD = 10;

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executorService.submit(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    map.put(threadId * OPERATIONS_PER_THREAD + j, threadId);
                }
            });
        }


        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);


        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < THREAD_COUNT * OPERATIONS_PER_THREAD; j++) {
                    if (map.get(j) == null) {
                        System.out.println("Missing key: " + j);
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final map size: " + map.size());
        System.out.println("Expected map size: " + (THREAD_COUNT * OPERATIONS_PER_THREAD));
    }
}
