# Multi Threading

## 1. Class lock vs. object lock

## 2. 
```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (this) {
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
### 1. Extends Thread Class
```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("start new thread using extends thread");
    } }
Thread t = new MyThread(); // JVM没有创建thread t.start(); // 此时JVM才创建新的thread
```
### 2. Implements Runnable
```java
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Start new thread using Runnable");
    } }
Thread t2 = new Thread(new MyRunnable());
```
### 3. Implements Callable
```java
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Start new thread using Callable";
    } }
```
### 4. Thread Pool
```java
// 创建固定大小的线程池:
ExecutorService executor = Executors.newFixedThreadPool(4); // 提交任务:
executor.submit(() -> "ganhuo");
executor.submit(task2);
executor.submit(task3);
executor.submit(task4);
executor.submit(task5);
```

## 4. Difference Between Runnable and Callable
- Runnable: has no return, cannot throw checked exceptions
- Callable: has return, Can throw checked exceptions.

## 5. What is the difference between t.start() and t.run()?
- t.start() starts a new thread to execute the task(run())
- t.run() execute the task in the current thread.

## 6. Which Way of Creating Threads is Better: Thread Class or Runnable Interface?
- Use Runnable for flexibility and when extending other classes. 
- Use Thread class for simplicity when no other class inheritance is needed.


## 7. Thread Statuses
- **NEW**: Thread object created but start() not yet called.
- **RUNNABLE**: Thread is ready or running, start() has been called.
- **BLOCKED**: Thread is waiting for a lock.
- **WAITING**: Thread is waiting for another thread to perform a specific action.
- **TIMED_WAITING**: Thread is waiting for a specified time before returning.
- **TERMINATED**: Thread has finished execution.

## 8. Deadlock and how to resolve it
If two threads are waiting for each other forever such type of infinite waiting is called deadlock in java. Synchronized keyword is the only reason for deadlock situation hence while using synchronized keyword we have to take special care.
### How to resolve:
- Using Thread.join() Method: We can get a deadlock if two threads are waiting for each other to finish indefinitely using thread join. Then our thread has to wait for another thread to finish, it is always best to use Thread.join() method with the maximum time you want to wait for the thread to finish.
- Use Lock Ordering: We have to always assign a numeric value to each lock and before acquiring the lock with a higher numeric value we have to acquire the locks with a lower numeric value.

## 9. How Threads Communicate with Each Other
- wait(): Pauses a thread until notified.
- notify(): Wakes up one waiting thread.
- notifyAll(): Wakes up all waiting threads.

## 10. Class Lock vs. Object Lock
### 1. Class Lock:
Synchronizes on the Class object, used for static members.
```java
public class ClassLevelLockExample
{
    public void classLevelLockMethod()
    {
        synchronized (ClassLevelLockExample.class)
        {
            //DO your stuff here       
        }
    }
} 
```
### 2. Object Lock:
Synchronizes on the instance (this), used for instance members.
```java
public class ObjectLevelLockExample
{
    public void objectLevelLockMethod()
    {
        synchronized (this)
        {
            //DO your stuff here   
        }
    }
} 
  ```

## 11. What is join() method?
The join() method allows one thread to wait for another thread to complete its execution.
Makes the calling thread wait until the target thread finishes.

## 12. what is yield() method?
The yield() method suggests that the current thread pause its execution, allowing other threads an opportunity to execute.


## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
A pool of reusable threads used to execute multiple tasks concurrently, reusing threads instead of creating new ones for each task.
### Types of ThreadPool
- FixedThreadPool: A thread pool with a fixed number of threads.
- CachedThreadPool: A thread pool that dynamically adjusts the number of threads based on tasks.
- SingleThreadExecutor: A thread pool with only one thread for sequential task execution.
### What is the TaskQueue in ThreadPool?
A queue that holds tasks waiting to be executed by threads in the pool.


## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
The `java.util.concurrent` package is used to create thread pools in Java.
The `ExecutorService` interface provides the main functions of a thread pool, such as submitting tasks and managing thread execution.

## 15. How to submit a task to ThreadPool?
Use the submit() or execute() method from the ExecutorService.
- submit(): Submits a task for execution, can return a Future.
- execute(): Similar to submit(), but does not return a result.
```java
ExecutorService executor = Executors.newFixedThreadPool(4); 
executor.submit(() -> "ganhuo");
executor.submit(task2);
executor.submit(task3);
executor.submit(task4);
executor.submit(task5);
```

## 16. What is the advantage of ThreadPool?
- Improved Performance: Reduces thread creation overhead.
- Efficient Resource Use: Manages concurrent tasks efficiently.
- Task Queuing: Queues tasks for execution when threads are available.
- Automatic Management: Handles thread lifecycle automatically.
- Better Error Handling: Offers flexible error handling.

## 17. Difference between shutdown() and shutdownNow() methods of executor
- shutdown(): Completes all submitted tasks and prevents new ones from being accepted.
- shutdownNow(): Cancels all pending tasks and tries to stop running tasks.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic classes provide thread-safe operations on single variables without synchronization.
### Types:
- AtomicInteger: For integer values.
- AtomicLong: For long values.
- AtomicBoolean: For boolean values.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        // Increment and get the previous value
        System.out.println(atomicInteger.getAndIncrement()); // Output: 1

        // Get the current value
        System.out.println(atomicInteger.get()); // Output: 2
    }
}
```
### When to use:
Use atomic classes when we need to update a single variable in a thread-safe manner without locking.


## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections are thread-safe data structures designed to handle concurrent access by multiple threads without requiring explicit synchronization.
- CopyOnWriteArrayList: A thread-safe variant of ArrayList where modifications result in a new copy.
- ConcurrentHashMap: A thread-safe version of HashMap with high concurrency.
- CopyOnWriteArraySet A thread-safe variant of HashSet using CopyOnWriteArrayList.
- ArrayBlockingQueue / LinkedBlockingQueue: A thread-safe version of Queue.
- LinkedBlockingDeque: A thread-safe version of Deque.

## 20. What kind of locks do you know? What is the advantage of each lock?
### 1. ReentrantLock
Allows advanced locking features like interruptible locks and timed waits.
### 2. Condition
Provides methods for thread communication (e.g., await(), signal()) with ReentrantLock.
### 3. ReadWriteLock
Improves performance by allowing multiple readers or a single writer.
### 4. StampedLock
Offers optimistic locking with reduced contention for read operations.


## 21. What is future and completableFuture? List some main methods of ComplertableFuture.
### 1. Future
Represents the result of an asynchronous computation.
Key methods: get(), get(long timeout, TimeUnit unit), cancel(boolean mayInterruptIfRunning), isDone()
### 2. CompletableFuture
Extends Future with more features for asynchronous programming.
Key methods:
thenAccept(), exceptionally(), thenApplyAsync(), anyOf(), allOf()

## 22. Practice

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.
```java
public class OddEventPrinter {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int value = 1;
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Runnable oddPrinter = () -> printNumbers(true);
        Runnable evenPrinter = () -> printNumbers(false);

        new Thread(oddPrinter, "Thread-0").start();
        new Thread(evenPrinter, "Thread-1").start();
    }

    private static void printNumbers(boolean odd) {
        lock.lock();
        try {
            while (value <= 10) {
                while (isOddTurn != odd) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (value % 2 == (odd ? 1 : 0)) {
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                }
                isOddTurn = !isOddTurn;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}

```

## 24. Create 3 threads, one thread output 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. 

## 25. Completable future
