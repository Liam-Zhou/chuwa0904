# hw5 Multithreading

## 2. Write a thread-safe singleton class

Using volatile and synchronized keyword to guarantee thread-safe.

```Java
public class Singleton {
    private String name;
    // volatile instance
    private static volatile Singleton instance;

    private Singleton(String name) {
        this.name = name;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton("Kevin");
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

## 3. How to create a new thread (Please also consider Thread Pool approach)?
1. Creating a New Thread
  
    Extend `Thread` class, override `run()` method.
   ```Java
    class Cat extends Thread{
        int time = 0;
        public void run(){
            while(true){
                System.out.println("Cat " + (++time) + " Thread name: " + Thread.currentThread().getName());
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (time == 80) break;
            }

        }
    }
   ```

   Implement `Runnable` interface, implement `run()` method.
   ```Java
    class Dog implements Runnable{
        int count = 0;
        public void run(){
            while(true){
                System.out.println("Dog " + (++count) + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count == 10){
                    break;
                }
            }
        }
    }
   ```

2. Thread Pool 
   ```Java
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
   ```

## 4. Difference between Runnable and Callable?

The `run()` method in Runnable interface does not have the return type, but the `run()` method in Callable interface has the return type.

`run()` method in Runnable interface:

```Java
public interface Runnable {
    void run();
}
```

`run()` method in Callable interface:

```Java
public interface Callable<V> {
    V call() throws Exception;
}
```

## 5. What is the difference between t.start() and t.run()?

1. When `start()` is called by a thread class, a new thread is created and this newly created thread executes the task that is kept in the `run()` method. One can call the `start()` method only once.
2. When the `run()` method is called, no new thread is created as in the case of the `start()` method. This method is executed by the current thread. One can call the `run()` method multiple times. 

## 6. Which way of creating threads is better: Thread class or Runnable interface?

Runnable interface is better, since interface allow multi-implements and users can design members in the interface, so Runnable interface provides more flexibility in terms of class design.

Advantages of Runnable interface: 
1. It provides more flexibility in terms of class design and reusability.
2. It decouples task logic from thread management.
3. It works better with modern threading mechanisms like ExecutorService and thread pools, which are preferred for managing concurrent tasks in larger applications.

## 7. What are the thread statuses?

In Java, a thread can exist in various states during its lifecycle. These states help define the current execution status of the thread and are part of the Thread.State enumeration.

- **NEW**: Thread is created but not started (before start() is called).
- **RUNNABLE**: Thread is ready to run or is currently running (after start() is called).
- **BLOCKED**: Thread is waiting to acquire a lock (waiting to enter a synchronized block).
- **WAITING**: Thread is waiting indefinitely for another thread to perform an action (e.g., wait()).
- **TIMED_WAITING**: Thread is waiting for a specific amount of time (e.g., sleep(), join(timeout)).
- **TERMINATED**: Thread has finished execution (either completed or crashed).

## 8. Demonstrate deadlock and how to resolve it in Java code.

Deadlock happens when: 

- Mutual Exclusion: Resources cannot be shared.
- Hold and Wait: A thread is holding at least one resource and waiting for additional resources.
- No Preemption: Resources cannot be forcibly taken from threads.
- Circular Wait: There exists a circular chain of threads, each waiting for a resource held by the next thread in the chain.

How to solve:

If thread t1 locks lock1 and then tries to lock lock2, at the same time, thread t2 locks lock2 and then tries to lock lock1. Deadlock will happen.

**Lock Ordering**: Let lock1 first, then lock2.
This prevents deadlock because both threads will follow the same locking sequence, avoiding circular wait conditions.

## 9. How do threads communicate each other?

1. `notify()`, `notifyAll()`, `wait()`, allow one thread to signal another thread that a condition has changed.
2. Synchronized members, threads can communicate by reading from and writing to shared variables. Proper synchronization is required to ensure data consistency and avoid race conditions.
3. `java.util.concurrent` package, simplify thread communication and synchronization. (eg. `BlockingQueue`
, thread-safe queue)

## 10. What’s the difference between class lock and object lock?

1. **Class Lock**: In java, each and every class has a unique lock usually referred to as a class level lock. These locks are achieved using the keyword ‘static synchronized’ and can be used to make static data thread-safe. It is generally used when one wants to prevent multiple threads from entering a synchronized block. 
2. **Object Lock**: In java, each and every object has a unique lock usually referred to as an object-level lock. These locks are achieved using the keyword ‘synchronized’ and can be used to protect non-static data. It is generally used when one wants to synchronize a non-static method or block so that only the thread will be able to execute the code block on a given instance of the class.

## 11. What is join() method? & 12. What is yield() method?

1. `join()` waits for a thread to finish. Inside t1 thread, call t2.join(), t1 thread will wait for t2 thread to finish.
2. `yield()`, suggests that the thread scheduler give other threads a chance to run. It does not guarantee that the current thread will pause or that other threads will run immediately.

```Java
public static void main(String[] args) throws InterruptedException {
    Td td = new Td();  // Create a thread
    td.start();

    for(int i = 1; i <= 10; i++) {
        Thread.sleep(1000);
        System.out.println("main thread " + i);
        if(i == 5) {
            System.out.println("T");
            td.join();
            //Thread.yield();
            System.out.println("Td thread is end");
        }
    }
}

class Td extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("td thread " + i);
        }
    }
}
```

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

1. A ThreadPool in Java is a collection of worker threads that efficiently manage the execution of multiple tasks. Instead of creating a new thread for each task, which can be resource-intensive and inefficient, a thread pool reuses a fixed number of threads to execute tasks. This approach can significantly improve performance and resource utilization.
2. ThreadPool types: 

   - Fixed Thread Pool (Executors.newFixedThreadPool): A pool with a fixed number of threads.
   - Cached Thread Pool (Executors.newCachedThreadPool): A pool that creates new threads as needed but reuses existing ones.
   - Single Thread Executor (Executors.newSingleThreadExecutor): A pool with a single worker thread.
   - Scheduled Thread Pool (Executors.newScheduledThreadPool): A pool that supports task scheduling with delays or periodic execution.
   - Custom Thread Pools (ThreadPoolExecutor): Highly configurable pools for custom requirements.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

1. java.util.concurrent
2. ExecutorService

## 15. How to submit a task to ThreadPool?

In `ExecutorService` interface, `execute()` and `submit()` can submit a task to ThreadPool.

`execute()`:
```Java
// Creating a fixed thread pool with 4 threads
ExecutorService executorService = Executors.newFixedThreadPool(4);

// Submitting tasks using execute()
for (int i = 0; i < 10; i++) {
    final int taskId = i;
    executorService.execute(() -> {
        System.out.println("Task " + taskId + " is running by " +   Thread.currentThread().getName());
    });
}

// Shutdown the thread pool after tasks are completed
executorService.shutdown();
```

`submit()`:
```Java
// Submitting tasks using submit(Runnable)
Future<?> future = executorService.submit(() -> {
    System.out.println("Runnable Task executed by " + Thread.currentThread().getName());
});
```

## 16. What is the advantage of ThreadPool?

The main advantages of using a ThreadPool include improved performance, better resource management, simplified concurrency handling, scalability, and better error handling. ThreadPools provide a robust and efficient way to manage tasks in concurrent applications, making them a preferred approach for multi-threading in Java applications.

## 17. Difference between shutdown() and shutdownNow() methods of executor

1. `shutdown()` only sets the thread pool state to SHUTWDOWN, and the tasks being executed will continue to execute, while the tasks not being executed will be interrupted. 
2. While `shutdownNow()` sets the thread pool state to STOP, the tasks being executed will be stopped, and the tasks not being executed will be returned.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

1. Atomic classes in Java are part of the java.util.concurrent.atomic package, providing a way to safely manipulate variables in a multi-threaded environment without the need for synchronization or locking. These classes offer atomic operations, which means the operations are thread-safe and cannot be interrupted by other threads.

2. 
   - AtomicInteger: For atomic operations on int values.
   - AtomicLong: For atomic operations on long values.
   - AtomicBoolean: For atomic operations on boolean values.
   - AtomicReference<V>: For atomic operations on object references.
   - AtomicIntegerArray: For atomic operations on arrays of int values.
   - AtomicLongArray: For atomic operations on arrays of long values.
   - AtomicReferenceArray<E>: For atomic operations on arrays of object references.
   - AtomicStampedReference<V>: For atomic operations on references with version stamps (useful in avoiding the ABA problem).
   - AtomicMarkableReference<V>: For atomic operations on references with a boolean mark (often used for algorithms like lock-free linked lists).


3. Example code:
    ```Java
    import java.util.concurrent.atomic.AtomicInteger;

    public class Atomic {
        private static AtomicInteger counter = new AtomicInteger(0);

        public static void main(String[] args) {
            Runnable task = () -> {
                for (int i = 0; i < 1000; i++) {
                    counter.incrementAndGet();  // Increment the counter atomically
                }
            };

            // Create and start two threads
            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);
            t1.start();
            t2.start();

            // Wait for both threads to finish
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Print the final counter value
            System.out.println("Final counter value: " + counter.get());
        }
    }
    ```


4. Use atomic classes when you need lock-free, thread-safe operations on single variables (like integers, booleans, or object references) in highly concurrent environments. They are ideal for small, simple operations where synchronization or locks would be too expensive. Atomic classes reduce the need for explicit synchronization, thus improving performance in multi-threaded scenarios.

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

Vector, Hashtable, ConcurrentHashMap, CopyOnWriteArraySet, ConcurrentLinkedQueue, BlockingQueue.

## 20. What kind of locks do you know? What is the advantage of each lock?

1. Synchronized Block/Method: Simple to use.
2. ReentrantLock (java.util.concurrent.locks.ReentrantLock): It can be configured for fairness (first-come-first-served) by passing true to the constructor, which ensures threads are granted access in the order they requested it. Try-Lock: Non-blocking option using tryLock(), which attempts to acquire the lock but does not block if it's already held.

## 21. What is future and completableFuture? List some main methods of ComplertableFuture.

1. In Java, Future and CompletableFuture are mechanisms used to represent the result of asynchronous computations, allowing developers to manage concurrency more easily.
2. `get()`, `isDone()`, `Cancel()`, `isCancelled()`.

## 22. 






