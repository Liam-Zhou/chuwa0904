2. Write a thread-safe singleton class
    See code
3. How to create a new thread(Please also consider Thread Pool approach)?
    See code
4. Difference between Runnable and Callable?
    Runnable: Does not return any result. 
    Callable: Returns a result.
5. What is the difference between t.start() and t.run()?
    1. t.start()
        - Purpose: t.start() is the method used to actually start a new thread in Java. It tells the JVM to initiate the thread's life cycle.
        - Behavior: When you call t.start(), a new thread is created and the run() method is executed in this new thread, concurrently with the main thread.
        - Thread Creation: t.start() creates a new thread and invokes the run() method within this new thread.
        - Concurrency: The run() method executes asynchronously (in parallel) with the calling thread.
    2. t.run()
        - Purpose: t.run() is just a regular method call, like calling any other - method in Java. It does not start a new thread.
        - Behavior: When you call t.run() directly, it simply executes the run() method in the current thread (the thread from which it was called).
        - Thread Creation: No new thread is created when t.run() is called. The run() method is executed within the current thread, sequentially.
        - Concurrency: The run() method executes synchronously (sequentially) in the same thread as the caller.
6. Which way of creating threads is better: Thread class or Runnable interface?
    Runnable, since Java supports single inheritance, implementing the Runnable interface allows your class to extend another class if needed. Also, Runnable separates the task from the thread management, promoting better design.
7. What are the thread statuses?
    - New：新创建的线程，尚未执行；
    - Runnable：运行中的线程，正在执行run()方法的Java代码；
    - Blocked：运行中的线程，因为某些操作被阻塞而挂起；
    - Waiting：运行中的线程，因为某些操作在等待中；
    - Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
    - Terminated：线程已终止，因为run()方法执行完毕。
8. Demonstrate deadlock and how to resolve it in Java code.
    See code.
9. How do threads communicate each other?
    1. Using wait(), notify(), and notifyAll()
    2. Using Shared Variables with volatile and Atomic Classes
10. What’s the difference between class lock and object lock?
    1. Object Lock
        Definition: An object lock is a lock associated with an instance of a class. Each object in Java has a built-in lock (or monitor) that is acquired by threads when they enter a synchronized instance method or a synchronized block on that object.
        Scope: An object lock is specific to an instance of the class. If you synchronize a method or a block on an object, it means only the thread holding the lock of that particular instance can access the synchronized code block.
        Usage:
        Synchronized instance methods (synchronized keyword applied to methods).
        Synchronized blocks using synchronized(this) or another object reference.
    2. Class Lock
        Definition: A class lock is associated with the Class object representing the class. Each class in Java has a unique Class object, and class-level synchronization ensures that only one thread can execute the synchronized block or method associated with the class at any given time.
        Scope: The class lock is global to all instances of the class. When a thread acquires the class lock, it prevents other threads from entering any synchronized static methods or synchronized blocks that lock on the Class object.
        Usage:
        Synchronized static methods (synchronized keyword applied to static methods).
        Synchronized blocks using synchronized(ClassName.class).
11. What is join() method?
    pause the execution of the current thread until the thread on which join() was called has finished
12. what is yield() method
    The yield() method in Java is a hint to the thread scheduler that the current thread is willing to yield its current use of the CPU. 
13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
    1. A ThreadPool in Java is a pool of worker threads that are managed by a thread manager (Executor). Thread pools allow you  reuse previously created threads to execute new tasks
    Types:
        - Fixed Thread Pool 
        - Cached Thread Pool
        - Single Thread Executor
        - Scheduled Thread Pool 
    2. What is the TaskQueue in ThreadPool?
        The TaskQueue is a blocking queue that holds tasks submitted to the thread pool for execution. If all threads in the pool are busy, new tasks are added to the queue to wait for a thread to become available.
14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
    java.util.concurrent
    ExecutorService
    Executors
15. How to submit a task to ThreadPool?
    use the submit() or execute() method of the ExecutorService
16. What is the advantage of ThreadPool?
    1. Improved Performance:
        By reusing existing threads, thread pools minimize the overhead of thread creation and destruction, leading to better performance.
    2. Better Resource Management:
        Limits the number of threads running at any given time, thus reducing the risk of resource exhaustion (e.g., memory and CPU).
    3. Simplified Management:
        Abstracts the complexity of thread management. You can submit tasks without worrying about thread creation, scheduling, and synchronization.
    4. Task Queueing:
        If all threads are busy, additional tasks are queued until a thread becomes available, ensuring that tasks are executed in a controlled manner.
17. Difference between shutdown() and shutdownNow() methods of executor
    shutdown():The executor stops accepting new tasks, but the already submitted tasks are allowed to complete.
    shutdownNow():Immediate Shutdown
18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
    1. What are Atomic Classes?
        Atomic classes are part of the java.util.concurrent.atomic package.
        They provide a way to perform atomic operations on single variables, ensuring thread safety without using synchronization.
        Operations on atomic classes are lock-free and thread-safe, ensuring that they happen atomically without the need for locks.
        Types of Atomic Classes
        There are several atomic classes provided in Java:
    2. types
        AtomicBoolean
        AtomicInteger
        AtomicLong
        AtomicReference
        AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray
    ```java
    import java.util.concurrent.atomic.AtomicInteger;
    import java.util.concurrent.atomic.AtomicReference;

    public class AtomicExample {
        public static void main(String[] args) {
            // Example with AtomicInteger
            AtomicInteger atomicInteger = new AtomicInteger(0);
            
            // Increment and get
            System.out.println("Increment and Get: " + atomicInteger.incrementAndGet()); // 1
            
            // Add and get
            System.out.println("Add and Get: " + atomicInteger.addAndGet(5)); // 6
            
            // Compare and set
            boolean updated = atomicInteger.compareAndSet(6, 10);
            System.out.println("Compare and Set: " + updated + ", New Value: " + atomicInteger.get()); // true, 10

            // Example with AtomicReference
            AtomicReference<String> atomicReference = new AtomicReference<>("Initial");
            
            // Get and set
            String oldValue = atomicReference.getAndSet("Updated");
            System.out.println("Old Value: " + oldValue); // Initial
            System.out.println("New Value: " + atomicReference.get()); // Updated
        }
    }
    ```
19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
    They provide thread-safe collection classes that can be safely used and modified by multiple threads concurrently.
    ConcurrentHashMap
    CopyOnWriteArrayList
20. What kind of locks do you know? What is the advantage of each lock?
    Intrinsic Locks
    Explicit Locks
        ReadWriteLock: Maintains a pair of locks for read and write access.
        Advantages: Allows multiple threads to read but only one to write, improving read performance.
21. What is future and completableFuture? List some main methods of ComplertableFuture.
    Future
        Represents the result of an asynchronous computation.
        Provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result.
        Limitations: It cannot be manually completed and does not support chaining multiple asynchronous tasks.
    CompletableFuture
        An extension of Future that provides a more powerful and flexible way to handle asynchronous programming.
        Supports multiple ways to complete, combine, and compose tasks.
    ```java
    import java.util.concurrent.CompletableFuture;

    public class CompletableFutureExample {
        public static void main(String[] args) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                System.out.println("Task running in: " + Thread.currentThread().getName());
                return "Hello";
            }).thenApply(result -> {
                System.out.println("Transforming result in: " + Thread.currentThread().getName());
                return result + " World!";
            }).thenAccept(result -> {
                System.out.println("Final result: " + result);
            });

            // Block and wait for the completion of the future
            future.join();
        }
    }
    ```
22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
    1. One solution use synchronized and wait notify
    2. One solution use ReentrantLock and await, signal
    ```java
    public class PrintNumbersWithWaitNotify {
        private final Object lock = new Object();
        private boolean oddTurn = true;

        public static void main(String[] args) {
            PrintNumbersWithWaitNotify printer = new PrintNumbersWithWaitNotify();
            
            Thread oddThread = new Thread(() -> printer.printOddNumbers(), "OddThread");
            Thread evenThread = new Thread(() -> printer.printEvenNumbers(), "EvenThread");

            oddThread.start();
            evenThread.start();
        }

        // Method to print odd numbers
        public void printOddNumbers() {
            synchronized (lock) {
                for (int i = 1; i <= 9; i += 2) {
                    while (!oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                    oddTurn = false;
                    lock.notify();
                }
            }
        }

        // Method to print even numbers
        public void printEvenNumbers() {
            synchronized (lock) {
                for (int i = 2; i <= 10; i += 2) {
                    while (oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                    oddTurn = true;
                    lock.notify();
                }
            }
        }
    }
    ```