# hw5-multiTreading
## 1. Read:https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lo ck
- **Class Lock**: the keyword ‘static synchronized’ and can be used to make static data thread-safe
- **Object Lock**: These locks are achieved using the keyword ‘synchronized’ and can be used to protect non-static data

## 2. Write a thread-safe singleton class
```
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

## 3.How to create a new thread (Please also consider the Thread Pool approach)?
```
public class MyThread extend Thread{
    public void run(){
        System.out.println("lalalla");
    }
}
    public class Main{
        public static void main(String[] args){
      
            MyTread t = new MyThread();
            t.start();
    }
}
```
```
public class MyRunnable implments Runnable{
    public void run(){
        System.out.println("lalal");
    }
} 
    public class Main{
        public static void main(String[] args){
            MyThread t = new MyThread();
            t.start();
        }
    }
    
}
```
```
public class MyThreadPool{
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for(int i = 0; i < 10; i++){
            Runnable worker = new MyRunnable();
            executor.execute(worker); 
        }
        executor.shutdown();
        while(!executor.isTerminated()){
        }
        System.out.println("All threads end");
    }

}
```

## 4. Difference between Runnable and Callable?

| **Feature**       | **Runnable**                                       | **Callable**                                         |
|-------------------|----------------------------------------------------|------------------------------------------------------|
| **Method Name**   | `run()`                                            | `call()`                                             |
| **Return Type**   | `void`                            | Generic type    |
| **Exception**     | Does not allow throwing checked exceptions         | Allows throwing checked exceptions                   |
| **Usage**         | Used with threads directly and with executors      | Used with executors that handle return values        |
| **Compatibility** | Can be used in basic threading and executors       | Primarily used with executors that support futures    |
| **Result Handling** | No built-in mechanism for handling results       | Results can be accessed via `Future` objects          |
| **Typical Use Case** | Simple tasks that do not return a result       | Complex tasks requiring results and exception handling |

## 5. What is the difference between t.start() and t.run()?
- **t.start()**: starts a new thread
- **t.run()**:It behaves like a normal method call to `run()`

## 6.Which way of creating threads is better: Thread class or Runnable interface?
| **Method**       | **Thread Class**                                                                                                            | **Runnable Interface**                                                                                                               |
|------------------|----------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| **Inheritance**  | Extends `Thread` class. You can't extend any other class because Java supports single inheritance.                         | Implements `Runnable` interface. Allows extending another class, providing more flexibility in designing your class hierarchy.       |
| **Flexibility**  | Less flexible as it requires subclassing `Thread`, tying your class tightly to a specific implementation of threading.      | More flexible. Allows you to implement `Runnable` and extend another class, making it more versatile.                                |
| **Usage**        | Directly create a thread object and override the `run()` method.                                                           | Implement the `run()` method and pass the `Runnable` object to a `Thread` object to execute.                                         |


## 7.What are the thread statuses?
| **State**        | **Description**                                                                                                                                                             |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **NEW**          | The thread has been created but not yet started. It remains in this state until the program starts the thread with `Thread.start()`.                                        |
| **RUNNABLE**     | The thread is executing in the Java virtual machine. This state includes both threads that are executing and threads that are ready to run but waiting for resource allocation.|
| **BLOCKED**      | The thread is blocked and waiting for a monitor lock. This state occurs when a thread tries to access a synchronized block or method and another thread currently holds the lock. |
| **WAITING**      | The thread is waiting indefinitely for another thread to perform a particular action, such as notification via `notify()` or `notifyAll()` or to release a lock.             |
| **TIMED_WAITING**| Similar to WAITING, but the thread waits for only a specified amount of time. After the time expires, the thread will return to RUNNABLE if it can proceed.                  |
| **TERMINATED**   | The thread has exited, either because the `run()` method has completed normally or because it has terminated due to an uncaught exception or `Thread.stop()` has been called. |


## 8.Demonstrate deadlock and how to resolve it in Java code.
```
public class MyResolvedDeadlock {
private static Object Lock1 = new Object();
private static Object Lock2 = new Object();

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("lala.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (Lock2) {
                    System.out.println("lala.");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("lala.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (Lock2) {
                    System.out.println("lala.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }
}
```
using the same lock order

## 9.How do threads communicate with each other?
- **Shared Variables**
- **wait(), notify(), and notifyAll() Methods**


## 10.What’s the difference between class lock and object lock?
- **Class Lock**: the keyword ‘static synchronized’ and can be used to make static data thread-safe
- **Object Lock**: These locks are achieved using the keyword ‘synchronized’ and can be used to protect non-static data


## 11.What is the join() method?
- **join()**: is a mechanism that allows one thread to wait for the completion of another

## 12. What is the yield() method?
- **yield()**:is a static method of the Thread class that suggests to the thread scheduler that the current thread is willing to yield its current use of a processor


## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
- **ThreadPool**:is a collection of pre-initialized threads that can be used to execute tasks
- **Types of ThreadPool**: Fixed Thread Pool, Cached Thread Pool, Single Thread Executor, Scheduled Thread Pool
- **Task Queue**:FIFO

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
- **library**: java.util.concurrent
- **main functions of thread-pool**: java.util.concurrent.Executor and java.util.concurrent.ExecutorService

## 15. How to submit a task to ThreadPool?
- **Runnable Tasks**: do not return a result and use execute()
```
ExecutorService executor = Executors.newFixedThreadPool(5);
Runnable task = () -> System.out.println("lalaaaaa");
executor.execute(task);
executor.shutdown(); 
```
- **Callable Tasks**:return a result and submit() return future object
```
ExecutorService executor = Executors.newFixedThreadPool(5);
Callable<String> task = () -> {
    return "123";
};
Future<String> future = executor.submit(task);
try {
    String result = future.get();
    System.out.println(result);
} catch (InterruptedException | ExecutionException e) {
    e.printStackTrace();
}
executor.shutdown();

```
## 16. What is the advantage of ThreadPool?
### Advantages of ThreadPool
#### 1. **Resource Management**
- **Reduced Resource Consumption**： Minimizes overhead by reusing threads instead of creating and destroying them for each task.
- **Improved System Stability**： Prevents resource exhaustion by limiting the number of concurrent threads, which can lead to system instability or degraded performance.

#### 2. **Performance Optimization**
- **Reduced Latency**： Threads are already available in the pool, reducing the time it takes to start a new task.
- **Load Distribution**： Efficiently distributes tasks among multiple threads, improving overall system performance.

#### 3. **Improved Task Management**
- **Task Queueing**： Manages a queue of tasks, allowing the application to submit tasks without concern for thread availability.
- **Future and Callable Integration**： Seamlessly works with Future and Callable for complex execution scenarios, including scheduled and dependent tasks.

#### 4. **Enhanced Control**
- **Priority Tasks**： Allows prioritizing tasks so more critical tasks can be executed first.
- **Exception Handling**： Provides structured handling of exceptions that occur during task execution, facilitating centralized recovery and logging.

## 17. Difference between shutdown() and shutdownNow() methods of ExecutorService
- **shutdown()**：Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
- **shutdownNow()**: Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution.


## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. When to use it?
- **Atomic classes**：esigned to support lock-free thread-safe programming on single variables
- **Types of Atomic Classes**：AtomicInteger, AtomicLong, AtomicBoolean, AtomicReference, AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray.
```
import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomic {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int incrementedValue = atomicInteger.incrementAndGet();
        System.out.println("Incremented: " + incrementedValue);

        boolean wasUpdated = atomicInteger.compareAndSet(1, 5);
        System.out.println("Updated from 1 to 5: " + wasUpdated);
        System.out.println("Current Value: " + atomicInteger.get());
    }
}

```
- **Atomic classes**: Concurrency Control, Lock-Free Algorithms, Counters


## 19.What are concurrent collections? Can you list some concurrent data structures (Thread-safe)?
- **concurrent collections**: provides several thread-safe collection classes that support full concurrency of retrievals and high expected concurrency for updates.
- **some concurrent data structures**: ConcurrentHashMap, ConcurrentLinkedQueue, ConcurrentSkipListMap, ConcurrentSkipListSet, CopyOnWriteArrayList, CopyOnWriteArraySet, BlockingQueue

## 20. What kind of locks do you know? What is the advantage of each lock?
| **Type of Lock**            | **Advantages**                                                                                             | **Use Cases**                                                                                          |
|-----------------------------|------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| **synchronized blocks** | Easy to use; automatic release reduces deadlock risks.                       | Suitable for simple scenarios with block-structured locking around resources.                         |
| **Reentrant Locks** | Advanced lock management, supports conditions, fairness, and non-blocking attempts to lock. | Complex data structures, environments requiring precise thread scheduling and handling.               |
| **Read/Write Locks** | Allows multiple readers simultaneously if no writers, improves read-heavy performance. | Resources or data structures that are frequently read but less frequently modified.                   |
| **Stamped Lock** | Supports read and write locks with an optimistic read mode for better throughput. | High-performance applications where read operations predominate.                                       |
| **Semaphore** | Controls the number of threads accessing a resource; can manage resource pools. | Managing access to a pool of resources or controlling the concurrency level in critical sections.      |
| **Spin Locks**               | Efficient for scenarios with short wait times due to minimal lock contention.   | Low-level programming or situations where thread blocking is minimal and quick access is necessary.    |


## 21. What is future and CompletableFuture? List some main methods of CompletableFuture.

- **future**: When a task that implements Callable is submitted to an executor service, it returns a Future object
- **CompletableFuture**: extends Future and implements CompletionStage, providing a more flexible and powerful way to handle asynchronous code execution
- **main methods of CompletableFuture**: supplyAsync, thenApply, thenAccept, thenRun, exceptionally, combine


## 22.Type the code by yourself and try to understand it.
## 23.Write a code to create 2 threads, one thread prints 1,3,5,7,9, another thread prints 2,4,6,8,10. One solution uses synchronized and wait notify, another solution uses ReentrantLock and await, signal.
```
public class OddEvenPrinter {
    private static final Object lock = new Object();
    private static int count = 1;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (count < 10) {
                synchronized (lock) {
                    if (count % 2 != 0) { 
                        System.out.println(Thread.currentThread().getName() + " prints " + count++);
                        lock.notify(); 
                    } else {
                        try {
                            lock.wait(); 
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }, "Odd Thread");
        Thread evenThread = new Thread(() -> {
            while (count <= 10) {
                synchronized (lock) {
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " prints " + count++);
                        lock.notify(); 
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }, "Even Thread");

        oddThread.start();
        evenThread.start();
    }
}

```

```
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int count = 1;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (count < 10) {
                lock.lock();
                try {
                    if (count % 2 != 0) { 
                        System.out.println(Thread.currentThread().getName() + " prints " + count++);
                        condition.signal();
                    } else {
                        try {
                            condition.await(); 
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "Odd Thread");

        Thread evenThread = new Thread(() -> {
            while (count <= 10) {
                lock.lock();
                try {
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " prints " + count++);
                        condition.signal();
                    } else {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "Even Thread");

        oddThread.start();
        evenThread.start();
    }
}

```
## 24. Create 3 threads, one thread outputs 1-10, one thread outputs 11-20, one thread outputs 21-30. Threads run sequence is random.
```
public class PrintNumbers {
    public static void main(String[] args) {
        // Define three separate tasks for the threads
        Runnable print1to10 = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " prints " + i);
            }
        };

        Runnable print11to20 = () -> {
            for (int i = 11; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + " prints " + i);
            }
        };

        Runnable print21to30 = () -> {
            for (int i = 21; i <= 30; i++) {
                System.out.println(Thread.currentThread().getName() + " prints " + i);
            }
        };


        Thread thread1 = new Thread(print1to10, "Thread-1");
        Thread thread2 = new Thread(print11to20, "Thread-2");
        Thread thread3 = new Thread(print21to30, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

```

## 25. Completable future:
### Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.

```


```

### Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing.
```

```