#### 2. Write a thread-safe singleton class
```
public class Singleton {

    // Private constructor to prevent instantiation
    private Singleton() {
        // Optional: Prevent reflection-based instantiation
        if (SingletonHelper.INSTANCE != null) {
            throw new IllegalStateException("Singleton instance already created.");
        }
    }

    // Static inner helper class that holds the Singleton instance
    private static class SingletonHelper {
        // The Singleton instance is created when the class is loaded
        private static final Singleton INSTANCE = new Singleton();
    }

    // Global access point to the Singleton instance
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```
#### 3. How to create a new thread(Please also consider Thread Pool approach)?
Extending the Thread class.
Implementing the Runnable interface.
Using a Thread Pool (Executor Framework): **a) Fixed Thread Pool**: A thread pool with a fixed number of threads. If all threads are busy, new tasks wait in a queue.
                                          **b) Cached Thread Pool**: A thread pool that creates new threads as needed but reuses previously created threads when available. It’s suitable for tasks with short execution times.
                                          **c) Scheduled Thread Pool**: A thread pool that can schedule tasks to execute after a given delay or periodically.

#### 4. Difference between Runnable and Callable?
Feature	                      Runnable	                          Callable
Return Type	            void (no return value)	            A generic result (via Future)
Exception Handling	    Cannot throw checked exceptions	    Can throw checked exceptions
Method Signature        void run()	                        V call()
Use Case	            Tasks without a return value	    Tasks with a result or exceptions

#### 5. What is the difference between t.start() and t.run()?
Use t.start() when you want to start a new thread and execute code in parallel.
Use t.run() if you just want to call the method directly without starting a new thread, which behaves like a normal method call.

#### 6. Which way of creating threads is better: Thread class or Runnable interface?
In most cases, implementing Runnable is better than extending the Thread class because:

It provides greater flexibility (can extend another class).
It encourages separation of concerns (task logic vs. thread management).
It works well with thread pools and modern concurrency frameworks.

#### 7. What are the thread statuses?
**1. NEW**: A thread is in the NEW state when it is created but has not yet started. In this state, the thread's start() method has not been called yet.
**2. RUNNABLE**: Once a thread's start() method is called, it enters the RUNNABLE state. A RUNNABLE thread is either executing in the JVM or waiting for a CPU time slice to be assigned to it by the operating system. This includes both running and ready-to-run threads.
**3. BLOCKED**: A thread is in the BLOCKED state when it is waiting for a monitor lock to enter or re-enter a synchronized block or method. This can happen when another thread is holding the lock, and the current thread is blocked until the lock becomes available.
**4. WAITING**: A thread is in the WAITING state when it is waiting indefinitely for another thread to perform a particular action. This occurs when a thread calls methods like Object.wait(), Thread.join(), or LockSupport.park().
**5. TIMED_WAITING**: A thread is in the TIMED_WAITING state when it is waiting for another thread to perform an action but with a time limit. The thread remains in this state for a specified time or until the event it's waiting for occurs, whichever comes first.
**6. TERMINATED**: A thread is in the TERMINATED state once it has completed execution, either by returning from the run() method or by throwing an unhandled exception. After reaching this state, the thread cannot be restarted.

#### 8. Demonstrate deadlock and how to resolve it in Java code.
A deadlock occurs when two or more threads are blocked forever, waiting for each other to release the resources they need to proceed. In Java, deadlocks often happen when synchronized blocks or methods acquire locks on multiple resources in different orders.
```
public class DeadlockDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task1(), "Thread-1");
        Thread t2 = new Thread(new Task2(), "Thread-2");

        t1.start();
        t2.start();
    }

    static class Task1 implements Runnable {
        public void run() {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": Holding lock 1...");
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println(Thread.currentThread().getName() + ": Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + ": Acquired lock 2!");
                }
            }
        }
    }

    static class Task2 implements Runnable {
        public void run() {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + ": Holding lock 2...");
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println(Thread.currentThread().getName() + ": Waiting for lock 1...");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + ": Acquired lock 1!");
                }
            }
        }
    }
}
```
Resolve deadlock: 1. Lock Ordering: If all threads acquire the locks in the same order, the deadlock can be prevented
                  2. Using tryLock() (With ReentrantLock): You can use tryLock() to attempt acquiring a lock and fail gracefully if the lock is not available, avoiding deadlock.
#### 9. How do threads communicate each other?
**1. Shared Variables and Synchronization**
Threads can communicate by updating shared variables, but to ensure that communication is safe and to prevent race conditions, the shared variables need to be properly synchronized. synchronized blocks or methods ensure that only one thread can access the shared variable at a time.

**2. wait(), notify(), and notifyAll()**
The wait(), notify(), and notifyAll() methods are part of the Object class and are used to facilitate communication between threads. These methods work in conjunction with synchronized blocks.

wait(): Causes the current thread to wait until another thread calls notify() or notifyAll() on the same object.
notify(): Wakes up one of the threads that called wait() on the same object.
notifyAll(): Wakes up all threads that are waiting on the same object.

#### 10. What’s the difference between class lock and object lock?
Aspect	                  Object Lock                                            Class Lock
Lock Type	    Associated with an instance of the class	                Associated with the Class object itself
Scope	        Affects instance methods of a specific object	            Affects static methods or blocks synchronized on the class
Concurrency  	Multiple threads can hold locks on different objects	    Only one thread can hold the class lock, no matter how many objects exist
Use Case	    Used to synchronize access to instance data	                Used to synchronize access to static data

#### 11. What is join() method?
The main purpose of the join() method is to ensure that a thread completes its task before the execution of another thread (typically the main thread) continues. It is particularly useful when you want to ensure that one or more threads have completed their execution before proceeding with subsequent operations.

#### 12. what is yield() method
The yield() method is used to hint the thread scheduler that the current thread is willing to pause its execution to allow other threads of the same or higher priority to execute

#### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
A ThreadPool in Java is a collection of worker threads that are managed and reused to perform tasks. Instead of creating a new thread for each task, which can be resource-intensive and inefficient, a thread pool maintains a pool of worker threads ready to execute tasks. This approach helps improve performance by reducing the overhead of thread creation and destruction and managing the execution of tasks efficiently.

**Fixed Thread Pool**: A thread pool with a fixed number of threads. When all threads are busy, new tasks are queued until a thread becomes available.
**Cached Thread Pool**: A thread pool with an unlimited number of threads, which creates new threads as needed and reuses previously constructed threads when they are available. Threads are kept alive for 60 seconds before being terminated.
**Single Thread Executor**: A thread pool with a single worker thread that processes tasks sequentially. This ensures that only one task is executed at a time.
**Scheduled Thread Pool**: A thread pool that can schedule tasks to run after a delay or periodically. Useful for tasks that need to be executed at fixed intervals or after a specific delay.
**Work Stealing Pool (Introduced in Java 8)**: A thread pool designed for high performance with a work-stealing algorithm. Tasks are distributed across available threads, and idle threads can "steal" tasks from busy threads.

The **TaskQueue** (often referred to as a blocking queue) in a thread pool is a queue where tasks are stored before being executed by the threads in the pool. The TaskQueue manages the tasks that are waiting to be executed when all threads in the pool are busy.

#### 14. Library: java.util.concurrent
Main Interface: ExecutorService
Additional Interfaces: Executor, ScheduledExecutorService
Key Class: ThreadPoolExecutor (for detailed configuration and control)

#### 15. How to submit a task to ThreadPool?
Create a ThreadPool: Use one of the factory methods from the Executors class to create an instance of ExecutorService.
Define a Task: Create a task that implements Runnable or Callable.
Submit the Task: Use the submit() method of ExecutorService to submit the task for execution.

#### 16. What is the advantage of ThreadPool?
ThreadPool: Efficiently manages and reuses threads, leading to better resource utilization, performance, and scalability.
Task Management: Provides task queueing and scheduling, simplifies code, and improves overall system efficiency.

#### 17. Difference between shutdown() and shutdownNow() methods of executor
shutdown(): Initiates an orderly shutdown, completing currently executing tasks and processing queued tasks.
shutdownNow(): Attempts to immediately stop all tasks, including interrupting executing tasks and discarding pending tasks.

#### 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic classes provide a way to perform thread-safe operations on single variables without using synchronization. They offer atomic operations, which are operations that are performed as a single, indivisible step, ensuring that they are thread-safe and preventing race conditions.

**AtomicBoolean**: Represents a boolean value that may be updated atomically.
**AtomicInteger**: Represents an integer value that may be updated atomically.
**AtomicLong**: Represents a long value that may be updated atomically.
**AtomicReference**: Represents a reference to an object that may be updated atomically.
**AtomicIntegerArray**: Represents an array of integers with atomic operations.
**AtomicLongArray**: Represents an array of long values with atomic operations.
**AtomicReferenceArray**: Represents an array of object references with atomic operations.

When to Use Atomic Classes
Simple State Management: When you need to manage simple state changes (like counters, flags, or references) in a thread-safe manner without the overhead of synchronization.

Performance: When performance is critical and you want to avoid the overhead associated with synchronization blocks or locks.

High Contention Scenarios: When multiple threads frequently update a shared variable, atomic classes provide an efficient way to handle such updates.

Lock-Free Algorithms: When implementing lock-free data structures or algorithms where atomic operations can improve performance.


#### 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections provide thread-safe data structures designed for concurrent access and modification by multiple threads. They are specifically built to handle situations where multiple threads interact with the same data structure, ensuring thread safety and reducing the need for explicit synchronization.

ConcurrentHashMap
CopyOnWriteArrayList
CopyOnWriteArraySet
ConcurrentLinkedQueue
BlockingQueue Implementations (e.g., LinkedBlockingQueue)
ConcurrentSkipListMap

#### 20. What kind of locks do you know? What is the advantage of each lock?
**Synchronized Blocks/Methods**: Simple synchronization for basic use cases.
**ReentrantLock**: Advanced control with features like try-lock and fairness.
**ReadWriteLock**: Better performance for read-heavy scenarios.
**StampedLock**: High-performance locking with support for optimistic reads.
**Semaphore**: Manages access to a limited number of resources.
**CountDownLatch**: Coordinates threads waiting for a set of operations to complete.

#### 21. What is future and completableFuture? List some main methods of ComplertableFuture.
CompletableFuture is an extension of Future introduced in Java 8. It provides a more powerful and flexible way to handle asynchronous computations and build complex asynchronous pipelines. CompletableFuture allows you to write non-blocking code using a fluent API and supports chaining of tasks.

**Creation:**
supplyAsync(Supplier<U> supplier): Creates a CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool().
runAsync(Runnable runnable): Creates a CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool().
**Combining Futures:**
thenApply(Function<? super T,? extends U> fn): Returns a new CompletableFuture that, when this future completes, is completed with the result of applying the provided function to this future's result.
thenAccept(Consumer<? super T> action): Returns a new CompletableFuture that, when this future completes, performs the given action with the result of this future.
thenRun(Runnable action): Returns a new CompletableFuture that, when this future completes, executes the given action.
**Exception Handling:**
exceptionally(Function<Throwable, ? extends T> fn): Returns a new CompletableFuture that, when this future completes exceptionally, is completed with the result of applying the provided function to the exception.
handle(BiFunction<? super T,Throwable,? extends U> fn): Returns a new CompletableFuture that, when this future completes, is completed with the result of applying the provided function to this future's result and exception (if any).
**Combining Multiple Futures:**
allOf(CompletableFuture<?>... cfs): Returns a new CompletableFuture that is completed when all of the given CompletableFuture instances complete.
anyOf(CompletableFuture<?>... cfs): Returns a new CompletableFuture that is completed when any of the given CompletableFuture instances complete.
**Completing Future:**
complete(T value): Completes this future with the given value.
completeExceptionally(Throwable ex): Completes this future with an exception.
**Joining and Waiting:**
join(): Retrieves the result of the computation, waiting if necessary. It throws unchecked exceptions for failures.
getNow(T valueIfAbsent): Returns the result of the computation if it is completed, or returns the specified valueIfAbsent if the computation is not yet completed.
#### 22. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
  ##### 1. One solution use synchronized and wait notify
  ```
  public class OddEvenPrinterSynchronized {

    private static final Object lock = new Object();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> printNumbers(true));
        Thread evenThread = new Thread(() -> printNumbers(false));

        oddThread.start();
        evenThread.start();
    }

    private static void printNumbers(boolean isOdd) {
        synchronized (lock) {
            for (int i = (isOdd ? 1 : 2); i <= 10; i += 2) {
                while (isOdd != isOddTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(i);
                isOddTurn = !isOddTurn;
                lock.notifyAll();
            }
        }
    }
    }
  ```
  ##### 2. One solution use ReentrantLock and await, signal
  ```
  public class OddEvenPrinterLock {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> printNumbers(true));
        Thread evenThread = new Thread(() -> printNumbers(false));

        oddThread.start();
        evenThread.start();
    }

    private static void printNumbers(boolean isOdd) {
        lock.lock();
        try {
            for (int i = (isOdd ? 1 : 2); i <= 10; i += 2) {
                while (isOdd != isOddTurn) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(i);
                isOddTurn = !isOddTurn;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
  ```

#### 23. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.
```
public class NumberPrinter {

    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Create and submit tasks to the executor service
        executorService.submit(new NumberTask(1, 10));
        executorService.submit(new NumberTask(11, 20));
        executorService.submit(new NumberTask(21, 22));

        // Shut down the executor service
        executorService.shutdown();
    }

    // Runnable task that prints numbers from start to end
    static class NumberTask implements Runnable {
        private final int start;
        private final int end;

        NumberTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                try {
                    Thread.sleep(100); // Optional: Sleep to simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
```

#### 24. Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
```
public class CompletableFutureExample {

    public static void main(String[] args) {
        // Define the integers
        int a = 5;
        int b = 10;

        // Create CompletableFuture for sum calculation
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> sum(a, b));

        // Create CompletableFuture for product calculation
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> product(a, b));

        // Combine the results and print them
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));
    }

    // Method to calculate the sum
    private static int sum(int x, int y) {
        return x + y;
    }

    // Method to calculate the product
    private static int product(int x, int y) {
        return x * y;
    }
}
```