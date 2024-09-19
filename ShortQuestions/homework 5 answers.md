### homework 5 answers

## 1. Read

## 2. Write a thread-safe singleton class

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

## 3. How to create a new thread (Please also consider Thread Pool approach)?

- By extending the Thread class:
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running...");
    }
}
```
- By implementing the Runnable interface:
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running...");
    }
}
```
- Thread Pool Approach: using the ExecutorService

## 4. Difference between Runnable and Callable?

1. Runnable:
    - Runnable is a functional interface that represents a task that does not return a result.
    - The run() method does not throw any checked exceptions and cannot return a value.

2. Callable:
    - Callable can return a result and throw a checked exception.
    - The call() method is used and allows for returning values.

## 5. What is the difference betweent .start() and t.run()?

- t.start(): This starts the thread, and the run() method is called internally by the JVM. A new thread of execution is created.

- t.run(): If call run() directly, it's like a normal method call in the current thread. No new thread is created.

## 6. Which way of creating threads is better: Thread class o rRunnable interface?

- Runnable Interface is better than extending Thread because:
    - Since Java only allows single inheritance, by implementing Runnable, the class can extend other classes.
    - It has better design flexibility and separation of thread logic from thread management.

## 7. What are the thread statuses?

- In Java, a thread can be in one of the following states (these are part of the Thread.State enum):

    1. NEW: The thread is created but not yet started.
    2. RUNNABLE: The thread is running or ready to run.
    3. BLOCKED: The thread is waiting for a monitor lock to enter a synchronized block or method.
    4. WAITING: The thread is waiting for another thread to perform a action.
    5. TIMED_WAITING: The thread is waiting for another thread for a specified time.
    6. TERMINATED: The thread has exited after completing.

## 8. Demonstrate deadlock and how to resolve it in Java code.

- Deadlock occurs when two or more threads are blocked forever, waiting for each other to release resources:
```java
class A {
    synchronized void methodA(B b) {
        System.out.println("Thread 1: Holding lock on A");
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        System.out.println("Thread 1: Waiting for lock on B");
        synchronized (b) {
            System.out.println("Thread 1: Acquired lock on B");
        }
    }
}

class B {
    synchronized void methodB(A a) {
        System.out.println("Thread 2: Holding lock on B");
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        System.out.println("Thread 2: Waiting for lock on A");
        synchronized (a) {
            System.out.println("Thread 2: Acquired lock on A");
        }
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();

        // Thread 1
        new Thread(() -> a.methodA(b)).start();

        // Thread 2
        new Thread(() -> b.methodB(a)).start();
    }
}

```
- In this example:
    - Thread 1 holds lock on A and waits for lock on B.
    - Thread 2 holds lock on B and waits for lock on A.
    - Both threads are blocked forever, resulting in a deadlock.

- How to resolve deadlock?
    - Avoid Nested Locks: Don't acquire multiple locks at the same time.
    - Lock Ordering: Use consistent order in acquiring locks.
    - Use tryLock(): Use ReentrantLock's tryLock() method to acquire a lock only if it's available, avoiding deadlock.

## 9. How do threads communicate each other?

- Threads can communicate using wait/notify/notifyAll methods, which are part of the Object class. They allow a thread to wait until another thread notifies it about a change in the condition.

## 10. What’s the difference between class lock and object lock?

- Object-level Lock: Every object in Java has a unique lock associated with it. When a thread wants to access a synchronized method or block of an object, it needs to acquire the object-level lock. This lock is specific to the object instance.

- Class-level Lock: Class-level lock is used to synchronize static methods. It is associated with the Class object and locks all instances of the class.

## 11. What is join() method?

- The join() method allows one thread to wait until another thread finishes its execution. It is used to ensure that a particular thread completes before the program continues.

## 12. what is yield() method

- The yield() method tells the thread scheduler that the current thread is good to yield its current use of the processor, allowing other threads to be executed. 

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

- ThreadPool: A ThreadPool is a pool of worker threads that efficiently manage the execution of multiple tasks by reusing threads. Instead of creating a new thread for every task, a thread pool maintains a fixed number of threads, and tasks are assigned to available threads.

- Types of ThreadPool: In Java, you can create different types of thread pools using the Executors class:

    - Fixed Thread Pool: A pool with a fixed number of threads.=
    - Cached Thread Pool: Creates new threads as needed but reuses existing ones when available.
    - Single Thread Pool: A pool with only one thread that executes tasks sequentially.
    - Scheduled Thread Pool: A pool used to schedule tasks to run after a delay or periodically.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

- Library: Java’s built-in java.util.concurrent package provides the necessary classes to create and manage thread pools.

- Interface: The ExecutorService interface provides the main functions of the thread pool, such as submit(), shutdown(), and others.

## 15. How to submit a task to ThreadPool?

- Ways to submit tasks to a thread pool are the submit() or execute() method provided by the ExecutorService.
    - Using execute(): This method is used when you don’t need a return value from the task.
    - Using submit(): This method is used when you expect a return value. It returns a Future object that you can use to get the result of the computation.

## 16. What is the advantage of ThreadPool?

- Improved Performance: By reusing existing threads, the overhead of creating and destroying threads is minimized which improved performance.
- Better Resource Management: Thread pools can control the number of concurrent threads, which helps in managing system resources.
- Reduced Latency: Tasks can be executed as soon as a thread becomes available, without waiting for the creation of a new thread.

## 17. Difference between shutdown() and shutdownNow() methods of executor

- shutdown():

    - Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
    - The pool will stop accepting new tasks but will allow ongoing tasks to complete before shutting down.

- shutdownNow():

    - Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were waiting to be executed.
    - It does not guarantee that the tasks will stop immediately, but it makes an effort to do so.


## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

- Atomic Classes: These are classes provided in the java.util.concurrent.atomic package that allow for atomic operations on single variables without the need for synchronization. Types of Atomic Classes:
    1. AtomicInteger
    2. AtomicLong
    3. AtomicBoolean
    4. AtomicReference

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

- Concurrent Collections are thread-safe collections provided in java.util.concurrent that are designed to be used in multithreaded environments without external synchronization. Examples of Concurrent Data Structures:
    - ConcurrentHashMap
    - CopyOnWriteArrayList
    - ConcurrentLinkedQueue

## 20. What kind of locks do you know? What is the advantage of each lock?

- ReentrantLock: This lock allows a thread to acquire the same lock multiple times without causing deadlock. It provides more control than synchronized, such as the ability to try locking and the ability to interrupt lock acquisition.

- ReadWriteLock: It allows multiple threads to read the data as long as no thread is writing to it. If a thread is writing, no other thread can read or write. This improves performance when read operations vastly outnumber write operations.

## 21. What is future and completableFuture? List some main methods of ComplertableFuture.

- Future: A Future represents the result of an asynchronous computation. It allows you to retrieve the result once the computation is complete or cancel it if needed.
- CompletableFuture: CompletableFuture is an extension of Future that allows you to write non-blocking code and chain multiple asynchronous tasks.
- Main Methods of CompletableFuture:
    - thenApply(Function<T, U> fn): Applies a function to the result of the previous stage.
    - thenAccept(Consumer<T> action): Consumes the result of the previous stage without returning anything.
    - thenCombine(CompletableFuture<U>, BiFunction<T, U, V>): Combines the results of two futures.
    - exceptionally(Function<Throwable, ? extends T>): Handles exceptions in the computation.
    - thenRun(Runnable): Runs a task when the previous stage completes.

## 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading).

## 23. Write a Code to Create 2 Threads: One Thread Prints Odd Numbers, and the Other Prints Even Numbers.

## 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1).

## 25. completable future:

1. Homework1: Writea simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.

```java
public class CompletableFutureExample {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        // Asynchronously calculate the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);

        // Asynchronously calculate the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        // Print the results
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));
    }
}
```

2. Homework 2: Implement CompletableFuture to Fetch Data from Three APIs: Products, Reviews, and Inventory, and Merge the Fetched Data.

- Code in code files.

3. Homework3: For Homework2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.

- Code in code files.