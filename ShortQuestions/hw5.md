# Hw5

## Question 2
```
public class Singleton {
    private volatile static Singleton instance;
    private Singleton(){

    }
    public synchronized static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
```

## Question 3
* We can Create a Thread by Extending the Thread Class
  ```
  class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hellow world");
    }
  }
    .....main
        MyThread thread = new MyThread();
        thread.start(); 
  ```

* We can create a Thread by extending Runnable Class
  ```
  class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable is running: " + Thread.currentThread().getName());
    }
  }
  public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();  // Start the new thread
    }
  }
  ```
  Or just use a lambda expression
  ```
  public void run() {
        System.out.println("Runnable is running: " + Thread.currentThread().getName());
  }
  ```

* We can use the thread pool class ExecutorService to create thread:
  ```
  ExecutorService executor = new ExecutorService.newFixedThreadPool(2);
  executor.submit(() -> System.out.println("1"));
  executor.submit(() -> System.out.println("2"));
  executor.shutdown();
  ```
* If we want to return value or throw an exception. We can use callable
  ```
  class MyCallable implements Callable {

    @Override
    public Object call() throws Exception {
        return "hello";
    }
  }
  ```

## Qustion 4
Runnable doesn't return any results, but callable return results and throw exceptions

## Question 5
t.start start a new thread and excute the task but t.run() will excute the task in the current thread.

## Question 6
* If we extend the Thread class, you cannot extend any other class. But Runnable is a interface, Java can do multiple interface inheritance. 
* Runnable only represent a task that a thread can run. But if we inherite Thread class, it will make the code harder to reuse.
* If we extend Thread class, we cannot make use of the thread pool. Runnable is a task that thread can run, it can be submit to the thread pool.

So implementing Runnable to create thread is a better choice in Java

## Question 7
* NEW: A new Thread has been created but has not yet started running.
* RUNNABLE: Thread is ready to run and may be running.
* BLOCKED: Thread is blocked, waiting for a lock
* WAITING: Thread is waiting indefinitely for another thread to signal it.
* TIMED_WAITING: Thread is waiting for a specific amount of time or until another thread signals it.
* TERMINATED: Thread has finished execution or was terminated due to an uncaught exception.


## Question 8
Deadlock in Java occurs when two or more threads are blocked forever, each waiting on the other to release a lock. This situation arises when multiple threads try to acquire locks on shared resources in a circular wait condition.
A simple way to avoid deadlocks is to avoid avoid nested locks whenever possible. 
Another effective way to prevent deadlock is to establish a consistent locking order.

I demostrate a simple deadlock example in /coding/Lock.java

## Question 9
Threads can communicate with each other using Shared Objects, Variables, wait() / notify() / notifyAll() Mechanism, and Atomic Variables

## Question 10
The object lcok applies to a specific object, and other threads can still access other instances of the class. But the Class lock The lock applies to the class itself, preventing all threads from executing synchronized static methods/blocks of that class. The object lcok can let multiple threads can execute synchronized methods of different instances concurrently, but for class block only one thread can execute synchronized static methods/blocks for the class, regardless of how many objects are created.

## Question 11
The join() method in Java is used to pause the execution of the current thread until the thread on which join() is called completes its execution. It can have one input (long millies) means Waits for the thread to finish for up to the specified number of milliseconds.

## Question 12
yield() allows the currently executing thread to pause its execution and give other threads of the same or higher priority a chance to run.

## Question 13
A Thread Pool in Java is a pool of worker threads that are created and managed by a thread management framework, such as ExecutorService. Instead of creating a new thread every time a task needs to be executed, a thread pool reuses existing threads, which improves performance and reduces the overhead of thread creation and destruction.
1. Fixed Thread Pool: A thread pool with a fixed number of threads.
   ```
   ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
   ```
2. Cached Thread Pool: A thread pool that creates new threads as needed, but reuses previously created threads when they become available.
   ```
   ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
   ```
3. Single Thread Executor: A thread pool with only one thread.
   ```
   ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
   ```
4. Scheduled Thread Pool: A thread pool that can schedule tasks to run after a delay or periodically.
   ```
   ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
   ```
5. Work-Stealing Pool: A thread pool that maintains a pool of worker threads that attempt to find and execute tasks in a stealable manner.
   ```
   ExecutorService workStealingPool = Executors.newWorkStealingPool();
   ```
In a Thread Pool, the Task Queue is a waiting area where tasks are held until a thread is available to execute them. If all threads in the pool are busy, new tasks are placed in this queue.
1. Task Submission: When a task is submitted to the thread pool via the submit() or execute() method, the task is placed in the task queue if all the threads are busy.
2. Task Execution: As soon as a thread becomes available (i.e., a previously running task finishes), it pulls a task from the task queue and starts executing it.
3. Queue Types: The task queue can be implemented using different data structures, such as a blocking queue or a priority queue, depending on the requirements.

## Question 14

java.util.concurrent library is used to create ThreadPool, provides utilities for managing concurrency
The ExecutorService interface is the primary interface that provides the core functions for creating and managing thread pools. It extends the Executor interface and provides additional methods:
* void execute(Runnable command): Executes a Runnable task asynchronously.
* <T> Future<T> submit(Callable<T> task): Submits a Callable task for execution and returns a Future representing the result.
* Future<?> submit(Runnable task): Submits a Runnable task for execution and returns a Future representing the completion of the task.
* <T> Future<T> submit(Runnable task, T result): Submits a Runnable task and returns a Future that will complete with the given result when the task is done.

## Question 15
We create the task using Runnable or Callable interface Use the ExecutorService interface to sumbit the task:
* execute(Runnable)	Executes a Runnable task without returning a result or tracking its completion.
* submit(Runnable)	Submits a Runnable task and returns a Future<?> that can be used to check completion.
* submit(Callable<T>)	Submits a Callable task and returns a Future<T> representing the result.
* invokeAll(Collection)	Submits a collection of Callable tasks and returns a list of Future objects.
* invokeAny(Collection)	Submits a collection of Callable tasks and returns the result of the first completed task.

## Quesiton 16
* Resource Efficiency: Reuses threads, reducing the overhead of frequent thread creation and destruction.
* Performance: Improves overall application performance by managing threads efficiently.
* Concurrency Control: Limits the number of active threads, preventing system overload or resource exhaustion.
* Task Queueing: Queues tasks when all threads are busy, ensuring large numbers of tasks can be handled efficiently.

## Question 17
|----| shutdown() | shutdownNow()|
|----| --------   |    -------   |
|Task Handling|Allows ongoing tasks to complete but prevents new tasks from being submitted.|Attempts to stop all ongoing tasks and cancels pending tasks.|
|Pending Tasks|Tasks that have been submitted but not yet started will continue to wait in the queue.|Pending tasks are cancelled and returned as a list.|
|Immediate Termination|Does not terminate running tasks immediately; waits for them to finish.|Attempts to terminate running tasks immediately (interrupts threads).|
|State After Call|isShutdown() will return true, but isTerminated() will return false until all tasks are done.|isShutdown() will return true, and running tasks will be interrupted.|
|Use Case|Graceful shutdown where you want to finish all submitted tasks before stopping.|Forceful shutdown when you want to stop everything as soon as possible.|

## Question 18
Atomic class can perform thread-safe operations on single variables without using explicit synchronization. We use atomic class because it is thread safety, non-blocking, and avoids synchronization overhead.
```
private static AtomicInteger atomicInteger = new AtomicInteger(1);
public static void main(String[] args) {
  System.out.println(atomicInteger.incrementAndGet());
  System.out.println(atomicInteger.decrementAndGet());
  System.out.println(atomicInteger.get());//count
}
output:
2
1
1
```
## Question 19
* ConcurrentHashMap:	A thread-safe, highly-concurrent map optimized for high read/write concurrency.
* CopyOnWriteArrayList:	A thread-safe variant of ArrayList where write operations create a new copy of the array.
* CopyOnWriteArraySet:	A thread-safe variant of Set backed by a CopyOnWriteArrayList.
* ConcurrentLinkedQueue:	A lock-free, thread-safe queue suitable for high-throughput concurrent applications.
LinkedBlockingDeque:	A thread-safe deque that allows elements to be added or removed from both ends.

## Question 20
* Intrinsic Locks: Acquired using synchronized keyword, easy to use with automatically lock release.
* ReentrantLock: Acquired manually using lock() and released with unlock() in finally.
* ReadWriteLock: Provides two locks: read lock for multiple readers and write lock for exclusive writers. Allows higher concurrency in read-heavy systems.
* StampedLock: Supports three modes: exclusive write lock, shared read lock, and optimistic read lock. Optimistic read locks allow threads to read without blocking unless a write happens.Better for read-dominant scenarios. Optimistic locking reduces contention.