# HW5 MultiThreading

## 1. Read Multithreading Interview Questions

[Multithreading Interview Questions](https://www.interviewbit.com/multithreading-interview-questions/#benefits-of-multithreading)

## 2. Write a thread-safe singleton class

```
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {

        if (instance == null) {
            synchronized(this) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

## 3. How to create a new thread(Please also consider Thread Pool approach)?

1. Extending the Thread class and rewrite run() method

```
class MultithreadingDemo extends Thread {
    public void run() {
        System.out.printIn("My thread is in running state.");
    }
    public static void main(String args[]) {
        MultithreadingDemo obj = new MultithreadingDemo();
        obj.start();
    }
}
```

2. Implementing Runnable interface in Java

```
class MultithreadingDemo implements Runnable {
    publiv void run() {
        System.out.printIn("My thread is in running state.");
    }
    public static void main(String args) {
        MultithreadingDemo obj = new MultithreadingDemo();
        Thread tobj = new Thread(obj);
        tobj.start();
    }
}
```

3. Thread Pool

```
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(()-> "ganhuo");
```

## 4. Difference between Runnable and Callable

Runnable doesn't have return value; Callable has the return value.

## 5. What is the difference between t.start() and t.run()?

`t.start()`: In simple words, the start() method is used to start or begin the execution of a newly created thread. When the start() method is called, a new thread is created and this newly created thread executes the task that is kept in the run() method. One can call the start() method only once.

`t.run()`: In simple words, the run() method is used to start or begin the execution of the same thread. When the run() method is called, no new thread is created as in the case of the start() method. This method is executed by the current thread. One can call the run() method multiple times.

`t.start()` start a new thread and excute the program, whereas `t.run()` run the program in the current Thread.

## 6. Which way of creating threads is better: Thread class or Runnable interface?

The Runnable interface is generally considered a better approach for creating threads in Java, rather than directly extending the Thread class.

1. Separation of Concerns: When you use the Runnable interface, you separate the task (the code you want to run) from the thread itself (the mechanism that runs the code). This improves clarity and makes it easier to reuse the task logic in different contexts. Whereas, when you extend Thread, the task and the thread execution are tightly coupled, which can make your code harder to maintain and reuse. The logic is tied directly to the thread instance.
2. Extensibility: Java only supports single inheritance, meaning that if you extend Thread, your class cannot extend any other class. Using Runnable allows you to extend any other class if needed, making your class design more flexible.
3. Reusability: By implementing Runnable, you can reuse the task across multiple threads or execution contexts. The same Runnable task can be passed to multiple threads or managed by thread pools (such as using ExecutorService), enhancing reusability.

## 7. What are the thread statuses?

In Java, threads go through several different states during their lifecycle, as defined in the Thread.State enumeration. A thread can be in one of the following six states at any given time: `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`.

## 8. Demonstrate deadlock and how to resolve it in Java code

Deadlock, as the name suggests, is a situation where multiple threads are blocked forever. It generally occurs when multiple threads hold locks on different resources and are waiting for other resources to complete their task.

Deadlocks can be resolved by ensuring that all threads acquire the locks in the same order. One approach is to always lock the resources in a predefined order.

## 9. How do threads communicate each other?

1. Using `wait()`, `notify()`, and `notifyAll()`
2. Using ReentrantLock and Condition. `condition.await()`, `condition.signal()`, and `condition.signalAll()`.

## 10. What’s the difference between class lock and object lock?

`Class lock`: In java, each and every class has a unique lock usually referred to as a class level lock. These locks are achieved using the keyword ‘static synchronized’ and can be used to make static data thread-safe. It is generally used when one wants to prevent multiple threads from entering a synchronized block.

`Object Lock`: In java, each and every object has a unique lock usually referred to as an object-level lock. These locks are achieved using the keyword ‘synchronized’ and can be used to protect non-static data. It is generally used when one wants to synchronize a non-static method or block so that only the thread will be able to execute the code block on a given instance of the class.

## 11. What is join() method?

The `join()` method in Java is a thread synchronization mechanism that allows one thread to wait for the completion of another thread. When a thread calls join() on another thread, it pauses its execution until the target thread (the thread being joined) finishes.

## 12. what is yield() method

The `yield()` method in Java is a thread scheduling mechanism provided by the Thread class. It is used to suggest to the thread scheduler that the current thread is willing to relinquish its CPU time and allow other threads of the same or higher priority to execute.

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

A `ThreadPool` in Java is a pool of pre-instantiated reusable threads that are used to execute tasks concurrently. Instead of creating a new thread for each task, which can be resource-intensive, a ThreadPool maintains a pool of worker threads, each of which can pick up tasks from a task queue and execute them.  
Fixed Thread Pool; Cached Thread Pool; Single Thread Pool

The TaskQueue in a ThreadPool is a queue where tasks are placed when they are submitted to the ThreadPool but no threads are immediately available to execute them. The TaskQueue acts as a buffer between the tasks and the available threads in the pool. It holds tasks until a thread becomes available to process them

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

Library: java.util.concurrent.  
ExecutorService Interface provide main functions of thread-pool.

## 15. How to submit a task to ThreadPool?

1. Create a ThreadPool: You can create a thread pool using one of the factory methods provided by the Executors utility class.
2. Submit the Task: Use the submit() or execute() method provided by ExecutorService to submit the task to the thread pool.
3. Retrieve Result (if applicable): If you submit a Callable task, you can retrieve the result using the Future object.

## 16. What is the advantage of ThreadPool?

1. Improved Performance and Efficiency
2. Better Control Over System Resources
3. Simplified Concurrency Management
4. Task Management and Queuing
5. Reduced Risk of Memory Leaks
6. Improved Application Stability

## 17. Difference between shutdown() and shutdownNow() methods of executor

![alt text](./Picture/image1.png)

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

Atomic classes in Java are part of the `java.util.concurrent` atomic package and provide a way to perform thread-safe operations on single variables without using locks. These classes allow for atomic (indivisible) operations on variables like integers, booleans, and object references, ensuring that these operations are completed without interruption, even in multithreaded environments.  
E.g. AtomicInteger, AtomicLong, AtomicBoolean.

**Multithreaded Counters or Flags:** When you have a shared counter, flag, or state between threads that requires atomic updates without using locks.  
**High-Performance Scenarios:** In performance-critical applications where locking would cause too much overhead, atomic classes provide a lock-free alternative.  
**Simple Use Cases:** When you're dealing with a single variable (like an integer or boolean) or reference that needs to be updated atomically, atomic classes are more lightweight than using synchronization mechanisms like synchronized or Lock.

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

Concurrent collections are thread-safe collections in Java, like CopyOnWriteArrayList, ConcurrentHashMap, CopyOnWriteArraySet, ArrayBlockingQueue, LinkedBlockingDeque.

## 20. What kind of locks do you know? What is the advantage of each lock?

1. ReentrantLock: Reentrancy, Explicit locking, Fairness, Try locking, interruptibility;
2. ReadWriteLock: Concurrent Reads, Exclusive writes, improved throughput;
3. StampedLock: Optimistic Reads, Pessimistic Reads/Writes, Efficiency;

## 21. What is future and completableFuture? List some main methods of ComplertableFuture.

`Future` is an interface in Java, introduced in Java 5 as part of the `java.util.concurrent` package. It represents the result of an asynchronous computation that might not be available immediately. A Future object allows you to check if the computation is complete, wait for it to finish, retrieve the result, or cancel the task.  
Key methods: `get()`, `isDone`, `cancel(boolean)`, `isCancelled()`

## 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.

```
public class OddEventPrinter {
    private static final Object monitor = new Object();
    private static int value = 1;

    public static void main(String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start();//t0
        new Thread(runnable).start();//t1
    }

    static class PrintRunnable implements Runnable {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        @Override
        public void run() {
            // synchronized : 门
            // 门里有资源
            // 买一把锁 monitor
            lock.lock();
            try   {
                while (value <= 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    condition.signalAll();
                    try {
                        condition.await(); // 解锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
```

## 24. Create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.

```
public class PrintNumber1 {
    private static int n = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printNumber());
        Thread t2 = new Thread(() -> printNumber());
        Thread t3 = new Thread(() -> printNumber());

        t1.start();
        t2.start();
        t3.start();
    }

    private static synchronized void printNumber() {
        int count = 10;
        while (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + n++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PrintNumber1.class.notifyAll();
    }
}
```

## 25. Completable Future

### Homework1:

```
import java.util.concurrent.CompletableFuture;

public class Homework1 {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;

        // CompletableFuture to calculate the sum asynchronously
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // CompletableFuture to calculate the product asynchronously
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // Print the results when both tasks are completed
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));

        // Wait for both CompletableFutures to complete
        CompletableFuture.allOf(sumFuture, productFuture).join();

        System.out.println("All tasks completed!");
    }
}
```

Homework2:

```
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Homework2 {
    public static void main(String[] args) {
        // Initialize the HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Asynchronous call to fetch products data
        CompletableFuture<String> productFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts/1");

        // Asynchronous call to fetch reviews data
        CompletableFuture<String> reviewsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/comments?postId=1");

        // Asynchronous call to fetch inventory data
        CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/users/1");

        // Combine the results from all three API calls
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(productFuture, reviewsFuture, inventoryFuture)
            .thenRun(() -> {
                System.out.println("All data fetched successfully:");
                System.out.println("Products: " + productFuture.join());
                System.out.println("Reviews: " + reviewsFuture.join());
                System.out.println("Inventory: " + inventoryFuture.join());
            });

        // Wait for the combined task to complete
        combinedFuture.join();
        System.out.println("Processing done!");
    }

    // Helper method to fetch data asynchronously using HttpClient
    private static CompletableFuture<String> fetchData(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
```

## Homework3:

```
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Homework3 {
    public static void main(String[] args) {
        // Initialize the HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Fetch products data with exception handling
        CompletableFuture<String> productFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts/1")
            .exceptionally(ex -> {
                System.out.println("Error fetching product data: " + ex.getMessage());
                return "Default Product Data";
            });

        // Fetch reviews data with exception handling
        CompletableFuture<String> reviewsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/comments?postId=1")
            .exceptionally(ex -> {
                System.out.println("Error fetching reviews data: " + ex.getMessage());
                return "Default Reviews Data";
            });

        // Fetch inventory data with exception handling
        CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/users/1")
            .exceptionally(ex -> {
                System.out.println("Error fetching inventory data: " + ex.getMessage());
                return "Default Inventory Data";
            });

        // Combine the results from all three API calls with exception handling
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(productFuture, reviewsFuture, inventoryFuture)
            .thenRun(() -> {
                System.out.println("All data fetched successfully:");
                System.out.println("Products: " + productFuture.join());
                System.out.println("Reviews: " + reviewsFuture.join());
                System.out.println("Inventory: " + inventoryFuture.join());
            });

        // Wait for the combined task to complete
        combinedFuture.join();
        System.out.println("Processing done!");
    }

    // Helper method to fetch data asynchronously using HttpClient
    private static CompletableFuture<String> fetchData(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}

```
