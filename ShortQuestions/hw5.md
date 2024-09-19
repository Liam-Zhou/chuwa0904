Yakitori Totto

Yakitori 39

# HW5 Multithread

Haifeng Yang

## 1. Class Lock and Object lock

**Limitations:**
It cannot interrupt threads waiting for a lock.
No flexibility in acquiring and releasing locks (e.g., cannot check if a lock is available).

**Class Lock:** Each class has a unique lock, referred as class lock. Achieved by using the keyword 'static synchronized' making a static data thread-safe.

~~~java
public class ClassLevelLockExample  
{    
    // Static synchronized method: any thread call this method will acquire a lock on the .class object, any other thread attempting to call this and any other static synchronized method in ths ClassLevelLockExample will be blocked until the first thread releases the lock
    public static synchronized void staticSynchronizedMethod() {
        
    }
    // Synchronized block on Class object: whenever a thread enter the method will synchorize the class object. Provides same behavior of static synchorized method, but allows to use a specific block of code instead of whole method
    public void classLevelLockMethod()  
    {       
        synchronized (ClassLevelLockExample.class)  
        {         
            //DO your stuff here       
        }    
    } 
} 

// Thread 1 calling static synchronized method
new Thread(() -> SharedResource.staticSynchronizedMethod(), "Thread-1").start();
// Thread 2 calling static synchronized method
new Thread(() -> SharedResource.staticSynchronizedMethod(), "Thread-2").start();
// Thread 3 calling synchronized block on class
new Thread(() -> shared1.synchronizedOnClass(), "Thread-3").start();
// Thread 4 calling synchronized block on class
new Thread(() -> shared2.synchronizedOnClass(), "Thread-4").start();
~~~

**Object Lock: **Each object has a unique lock, referred as object-level lockm, is automatically acquired when a synchronized method or block is executed.

These locks are achieved using the keyworkd synchronized and can protect a non-static method or block so that only the thread will be able to execute the block on a given instance of the class.

~~~java
public class ObjectLevelLockExample  
{    
    public synchronized void synchronizedMethod() {
        // Critical section of code
    }
  	public void objectLevelLockMethod()  
    {   
        synchronized (this)  
        {     
            //DO your stuff here   
        } 
 	}
} 
~~~



## 2. Thread-safe singleton class

### Lazy Loading



~~~java
public class singleton {
    // Volatile ensures instance is visible to all threads
    private static volatile singleton instance;

    private singleton() {} // Constructor should have parentheses.

    public static singleton getInstance() {
        if (instance == null) {
            synchronized (singleton.class) {
                if (instance == null) {
                    instance = new singleton();
                }
            }
        }
        return instance;
    }

    public void hello() {
        System.out.println("Hello, World!");
    }
}

~~~

### Static Inner Class

~~~java
public class singleton {
    private singleton() {}
    private static class singletonHelper {
        private static final singleton INSTANCE = new singleton();
    }
    public static singleton getInstance() {
        return singletonHelper.INSTANCE;
    }
}
~~~

### Synchronized Keyword

~~~java
public class singleton{
    private static singleton instance = null;
    private singleton() {}
    public static sychronized singleton getInstance() {
        if(instance == null)
        	instance = new singleton();
       	return instance;
    }
}
~~~



## 3. How to create a new thread

### Extends Thread

~~~java
class MyThread extends Thread {
    public void run() {
        
    }
}
Thread thread = new MyThread();
thread.start();
~~~

### Implement Runnable

~~~java
class MyRunnable implements Runnable{
    public void run() {
        
    }
}
Thread thread = new Thread(new MyRunnable());
thread.start();
~~~

### Ussign Callable and Future

~~~java
class Mycallable implements Callable<Integer> {
    public Integer call() throws Exception {
        return 123;
    }
}

public class Main() {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<Ingeter> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer result = new futureTask.get();
        System.out.print("Result " + result);
    }
}
~~~

### Using Lambda

~~~java
public class Main() {
	public static void main(String[] args) {
        Thread thread = new Thread(() -> {System.out.print("Hello");});
        thread.start();
    }
}
~~~

### Use Executor Framwork

~~~java
public class Main {
    public static void main(String[] args) {
        // Create a thread pool with a single thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit a Runnable task
        executorService.submit(() -> {
            System.out.println("ExecutorService is running.");
        });

        // Shutdown the executor service
        executorService.shutdown();
    }
}
~~~



## 4. Difference Runnable and Callable

### Key Differences Between `Runnable` and `Callable`:

| Aspect                 | **Runnable**                                                 | **Callable**                                                 |
| ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Return Type**        | `Runnable` does **not return any value** (void).             | `Callable` **returns a value** or result (generic type `V`). |
| **Checked Exceptions** | `Runnable` cannot throw checked exceptions.                  | `Callable` can throw checked exceptions (`Exception`).       |
| **Method**             | Has a single `run()` method, which returns `void`.           | Has a single `call()` method, which returns a value (`V`).   |
| **Usage**              | Used when tasks don't need to return a result or throw exceptions. | Used when tasks need to return a result or handle exceptions. |
| **Thread Execution**   | Typically used with `Thread` or `ExecutorService`.           | Typically used with `ExecutorService` and `Future`.          |

Runnable does not handle exception, often used in simple tasks like background processing, no need for returned value. It is executed by **Thread** or submitted to an **ExecutorService**.

Callable **compute and return a result, throws exceptions**  and is generally executed using ExecutorService along with future to retrieve the result.

## 5. Difference t.start() and t.run()

### Start()

**Starts a new Thread** and JVM schedules it for execution. Inside of start(), the run() method is called. The run() method will be executed concurrently with other threads.

Start() moves the thread state from NEW to RUNNABLE.

### Run()

**Do not create a new thread** Just execute the run() method in the current thread synchronously, blocking the current thread until it completes.

## 6. Which way to create thread is better: Thread Class or Runnable interface?

Generally maybe runnable.

- Thread needs to be extended, so you cannot extend other classes, while runnable can be implemented
- Runnable decouple from the threading mechanism, as extending Thread couples the task with thread management.
- Runnable has higher Reusability and flexibility. It separate logic from thread, It can be passed into multiple thread using same code.
- ThreadPooling
- Thread Safety

## 7. What are the thread statuses

NEW - After a thread created

NUNNABLE - After thread.start()

BLOCKED - when it is waiting to aquire a lock or monitor

WAITING / TIMED_WAITING - calls **object.wait()**, called **thread.join() on another thread**, LockSuppport.Park() to wait for an external event, waiting without a specific time limit. when it is waiting indefinitely for another thread to perform a particular action. 

TERMINATED - Once finished the run () method

##  8. Demostrate deadlock and how to solve it in java

### DeadLock

resource1 try to lock resource2, resource 2 try to lock resource 1, then both wait for release of resources.

~~~java
package src;

public class deadlock {
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();
    
    public deadlock() {}

    public void startThreads() {
        Thread thread1 = new Thread(() -> {
            synchronized(resource1) {
                System.out.println("Thread 1: Locked Resource 1");
                try { Thread.sleep(100); } 
                catch (InterruptedException e) {}
                synchronized(resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized(resource2) {
                System.out.println("Thread2 : Locked Resources 2");
                try {Thread.sleep(100);}
                catch ( InterruptedException e ){}
                synchronized(resource1) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
~~~

### How to avoid

#### Use Lock Ordering, ensure both threads lock the resources in the same order. In this way there is no circular wait

For exmaple, both threads should try to lock resource1 first, and then resource2.

~~~java
public class DeadlockSolved {
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public static void main(String[] args) {
        DeadlockSolved example = new DeadlockSolved();
        example.startThreads();
    }

    public void startThreads() {
        // Thread 1
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {  // Both threads lock resource1 first
                System.out.println("Thread 1: Locked resource 1");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {  // Then lock resource2
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            synchronized (resource1) {  // Both threads lock resource1 first
                System.out.println("Thread 2: Locked resource 1");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {  // Then lock resource2
                    System.out.println("Thread 2: Locked resource 2");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

~~~

#### Avoid Long-Held Locks

#### Use tryLock() with ReentrantLock (skip for now)

## 9. How do threads communicate each other

Threads commuinicate with each other primarily through shared objects and using certian synchronization to coordinate their activities.

### Shared Objects and Synchornization

### wait(), notify(), notifyAll()

**Wait() and notify() simple example** ask current thread to wait until another thread call notify() or notifyall() one the same object

~~~java
class DataBuffer {
    private int data = -1;
    private boolean available = false;

    // Producer will set the data
    public synchronized void produce(int value) {
        while (available) {
            try {
                wait();  // Wait until the consumer consumes the data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = value;
        available = true;
        System.out.println("Produced: " + data);
        notify();  // Notify the consumer
    }

    // Consumer will get the data
    public synchronized int consume() {
        while (!available) {
            try {
                wait();  // Wait until the producer produces the data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        System.out.println("Consumed: " + data);
        notify();  // Notify the producer
        return data;
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        DataBuffer buffer = new DataBuffer();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                buffer.produce(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                buffer.consume();
            }
        });

        producer.start();
        consumer.start();
    }
}
// output
Produced: 0
Consumed: 0
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
Produced: 3
Consumed: 3
Produced: 4
Consumed: 4
~~~

Notify only wakes one waiting thread.

NotifyAll wakes all the waiting threads, each of them will try to reacquire the lock on object and conitnue execution. 

However, even notified, they will first be moved to **runnable state and has to contend for the lock on the object monitor** so can they execute.

### Thread signaling join()

It allows one thread to wait for another thread to finish its execution.

~~~java
public class JoinExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate some work
                System.out.println("Thread 1 completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(500); // Simulate some work
                System.out.println("Thread 2 completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();  // Main thread waits for thread1 to finish
            thread2.join();  // Main thread waits for thread2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads have completed execution.");
    }
}

~~~



### Using ExecutorService and Future

High-level abstraction

~~~java
public class FutureExample{
	public static main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFiexedThreadPool(2);
        Future<Integer> futureResult = executor.submit(() - > {
            Thread.sleep(1000);
            return 42;
        });
        // Wait for the result to be available
        System.out.print("Result from future " + futureResult.get());
        executor.shutdown();
    }
}
~~~



### Using PipedInputStream and PipedOutputStream

mainly used for sending data between threads in a producer-consumer fashion.

~~~java
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.IOException;

public class PipedStreamExample {
    public static void main(String[] args) throws IOException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);

        Thread producer = new Thread(() -> {
            try {
                out.write("Hello from producer!".getBytes());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                int data;
                while ((data = in.read()) != -1) {
                    System.out.print((char) data);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}

~~~



## 10. What's the difference between class lock and object lock

See Answer 1

## 11. What is .join() method

Ensure that a thread does not finish its execution until another thread has completed. The thread will enter WAITING state when other thread call join()

## 12. What is yield() method (Rarely used)

Allow current thread voluntarily pause its execution and give other threads a chance to run. Unlike join(), sleep() will block the thread, yield() thread remains in RUNNABLE state and can be picked up again immediately by the scheduler.

Does not guarentee the current thread will stop execution or other threads will get CPU time. In merely makes the thread "Suggest" to the scheduler that it can be paused.

~~~java
public class YieldExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1 - iteration: " + i);
                Thread.yield();  // Yield to other threads
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2 - iteration: " + i);
                Thread.yield();  // Yield to other threads
            }
        });

        thread1.start();
        thread2.start();
    }
}
~~~



## 13. What is ThreadPool?How many types of ThreadPool? What is the TaskQueue in ThreadPool?

ThreadPool is just like other pool method, pre-create workers that can be reused to execute task

### Types of Thread pool: 

- Fixed ThreadPool
- Cached ThreadPool
- Single ThreadExecutor
- Scheduled ThreadPool
- Work Stealing Pool

### TaskQueue

A queue where tasks(Runnable, callable) are placed. 

## 14. Which library is used to create threadpool? Which interface provide main funtions of thread-pool

### Libarary: java.util.concurrent

### Executor provide the main funtionalities of a thread pool

- You can submit tasks to Executor service to be executed by a thread in pool
- Provide methods to gracefully shut down the thread pool, allow tasks to finish before the pool is terminated
- Manage the lifecycle of worker threads, ensures efficiency
- Methods:
  - submit()
  - shutdown()
  - shutdownnow()
  - awaitTermination(long timeout, TimeUnit unit): Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted.
  - invokeAll(): Executes the given collection of `Callable` tasks and returns a list of `Future` objects representing the status and results of the tasks.
  - invokeAny(): Executes the given collection of Callable tasks and returns the result of one of the successfully completed tasks. Other tasks may be canceled.

## 15. How to submit a task to ThreadPool

### Submit a Runnable

~~~java
// Step 1: Create a ThreadPool with 3 threads
ExecutorService executor = Executors.newFixedThreadPool(3);
// Step 2: Submit Runnable tasks to the ThreadPool
executor.submit(() -> {
    System.out.println("Task 1: Executing in " + Thread.currentThread().getName());
});

executor.submit(() -> {
    System.out.println("Task 2: Executing in " + Thread.currentThread().getName());
});

executor.submit(() -> {
    System.out.println("Task 3: Executing in " + Thread.currentThread().getName());
});

// Step 3: Shut down the ThreadPool (gracefully shuts down after all tasks are finished)
executor.shutdown();
~~~



### Submit a Callable

~~~java
// Step 1: Create a ThreadPool with 3 threads
ExecutorService executor = Executors.newFixedThreadPool(3);
// Step 2: Submit Callable tasks to the ThreadPool
Future<Integer> future1 = executor.submit(() -> {
    System.out.println("Task 1: Executing in " + Thread.currentThread().getName());
    return 1;  // Return a result
});

Future<Integer> future2 = executor.submit(() -> {
    System.out.println("Task 2: Executing in " + Thread.currentThread().getName());
    return 2;  // Return a result
});

Future<Integer> future3 = executor.submit(() -> {
    System.out.println("Task 3: Executing in " + Thread.currentThread().getName());
    return 3;  // Return a result
});

try {
    // Step 3: Retrieve the results from the Callable tasks using Future.get()
    System.out.println("Result from Task 1: " + future1.get());
    System.out.println("Result from Task 2: " + future2.get());
    System.out.println("Result from Task 3: " + future3.get());
} catch (InterruptedException | ExecutionException e) {
    e.printStackTrace();
}

// Step 4: Shut down the ThreadPool
executor.shutdown();
~~~



### Use invokeAll or InvokeAny

~~~java
public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(3);

    // Step 1: Create a list of Callable tasks
    List<Callable<Integer>> tasks = List.of(
        () -> {
            System.out.println("Task 1");
            return 1;
        },
        () -> {
            System.out.println("Task 2");
            return 2;
        },
        () -> {
            System.out.println("Task 3");
            return 3;
        }
    );

    try {
        // Step 2: Submit all tasks to the pool and wait for results
        List<Future<Integer>> results = executor.invokeAll(tasks);

        // Step 3: Retrieve results
        for (Future<Integer> result : results) {
            System.out.println("Result: " + result.get());
        }
    } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
    }

    executor.shutdown();
}
~~~



## 16. What is the advantage of ThreadPool

- Improve Performance, avoid overheads of keep creating new threads, Reduced Latency
- Efficient resources management: control over the number of threads, avoid resources exhaustion, and avoid thread starvation
- Task Queuing, safe manually management of threads exectution
- Simplified task management of ExecutorService abstract away the thread creation, task execution and thread lifecycle management, Developers only need to submit tasks and let the ThreadPool handle the rest.
- Support scheduled and delayed execution
- Enhacned concurrency control
- Graceful shutdown
- Scalability
- Result management with callable and Future

## 17. Differences between shutdown() and shutdownNow() methods of executor

Immediately shutdown compares to graceful shutdown.

### **Key Differences Between `shutdown()` and `shutdownNow()`**:

| **Aspect**              | **`shutdown()`**                                             | **`shutdownNow()`**                                          |
| ----------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Ongoing Tasks**       | Allows currently running tasks to complete.                  | Attempts to stop all currently executing tasks immediately.  |
| **Pending Tasks**       | No new tasks are accepted, but tasks in the queue continue to be executed. | Attempts to cancel tasks that are waiting in the task queue. |
| **Thread Interruption** | Running tasks are not interrupted.                           | Attempts to interrupt all currently running threads.         |
| **Return Type**         | Does not return anything.                                    | Returns a list of tasks that were waiting in the task queue but were not started. |
| **Gracefulness**        | Graceful shutdown: completes running tasks before termination. | Abrupt shutdown: forces termination of running tasks.        |
| **Use Case**            | Use when you want the pool to finish all running and queued tasks. | Use when you need an immediate halt, potentially leaving tasks incomplete. |

## 18. What is Atomic Classes? How many types of Atomic classes? Code exmaple of Atomic classes and its main methods. When to use it?

Meaning that operations are performed in **thread-safe** manner without need for explicit synchronization (synchronized or locks)

### Types of Atomic Classes

**AtomicInteger**: For atomic operations on integers.

**AtomicLong**: For atomic operations on long integers.

**AtomicBoolean**: For atomic operations on booleans.

**AtomicReference**: For atomic operations on object references.

**AtomicIntegerArray**: For atomic operations on arrays of integers.

**AtomicLongArray**: For atomic operations on arrays of long integers.

**AtomicReferenceArray**: For atomic operations on arrays of object references.

**AtomicStampedReference**: Adds a version number (stamp) to an atomic reference to prevent ABA problems.

**AtomicMarkableReference**: Allows atomic operations on a reference paired with a boolean marker, useful for marking references as logically deleted or altered.

Exmaple:

~~~java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);

        // incrementAndGet(): Atomically increments by 1 and returns the updated value
        System.out.println("Incremented Value: " + atomicInt.incrementAndGet());  // 1

        // getAndIncrement(): Atomically increments by 1 but returns the previous value
        System.out.println("Previous Value: " + atomicInt.getAndIncrement());      // 1
        System.out.println("Current Value: " + atomicInt.get());                   // 2

        // addAndGet(): Atomically adds the given value and returns the result
        System.out.println("After Adding 5: " + atomicInt.addAndGet(5));           // 7

        // compareAndSet(): Atomically sets the value if the current value equals the expected value
        boolean updated = atomicInt.compareAndSet(7, 10);  // Changes from 7 to 10
        System.out.println("Was the value updated? " + updated);  // true
        System.out.println("Current Value: " + atomicInt.get());  // 10
    }
}

~~~



## 19. What is the concurrent collections? List some concurrent data structure (Thread-safe)

The collections are designed to be thread-safe, without requiring explicit synchronization

- Concurrent_HashMap
- ConcurrentLinkedQueue
- BlockingQueue
- CopyOnWriteArrayList, CopyOnWriteArraySet
- ConcurrentSkipListMap, ConcurrentSkipListSet
- SynchronousQueue

## 20. What kind of locks do you know? What is the advantage of each lock?

| **Lock Type**             | **Advantages**                                               |
| ------------------------- | ------------------------------------------------------------ |
| **Intrinsic Lock**        | Simple to use with `synchronized`, built-in Java mechanism, guarantees atomicity and visibility. |
| **ReentrantLock**         | More flexible than intrinsic locks, supports reentrancy, fairness, and try-lock mechanisms. |
| **ReadWriteLock**         | Allows multiple reads and exclusive writes, improving performance for read-heavy workloads. |
| **StampedLock**           | Optimistic reading for improved performance, better for read-dominant scenarios. |
| **Condition (Reentrant)** | Finer-grained thread communication, allows multiple conditions on a single lock. |
| **Semaphore**             | Controls access to a limited set of resources, useful for managing concurrent access to resources. |

### Intrinsic Locks (Synchronized Locks)

~~~java
public class IntrinsicLockExample {
    private final Object lock = new Object();

    public void synchronizedMethod() {
        synchronized (lock) {
            System.out.println("Thread-safe method");
        }
    }
}
~~~



### ReentrantLock

~~~java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void threadSafeMethod() {
        lock.lock();  // Acquire the lock
        try {
            System.out.println("Thread-safe method");
        } finally {
            lock.unlock();  // Always release the lock
        }
    }
}
~~~



### ReadWriteLock

~~~java
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void readMethod() {
        readLock.lock();
        try {
            System.out.println("Reading data");
        } finally {
            readLock.unlock();
        }
    }

    public void writeMethod() {
        writeLock.lock();
        try {
            System.out.println("Writing data");
        } finally {
            writeLock.unlock();
        }
    }
}
~~~



### StampedLock

~~~java
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private final StampedLock lock = new StampedLock();
    private int data = 0;

    public void writeMethod() {
        long stamp = lock.writeLock();  // Acquire the write lock
        try {
            data++;
        } finally {
            lock.unlockWrite(stamp);  // Release the write lock
        }
    }

    public int optimisticReadMethod() {
        long stamp = lock.tryOptimisticRead();  // Acquire optimistic read
        int currentData = data;
        if (!lock.validate(stamp)) {
            // Fallback to acquiring the read lock if write occurred
            stamp = lock.readLock();
            try {
                currentData = data;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return currentData;
    }
}

~~~



### Condition 

~~~java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void awaitCondition() throws InterruptedException {
        lock.lock();
        try {
            while (!ready) {
                condition.await();  // Wait until signaled
            }
            System.out.println("Condition met, continuing");
        } finally {
            lock.unlock();
        }
    }

    public void signalCondition() {
        lock.lock();
        try {
            ready = true;
            condition.signal();  // Signal a waiting thread
        } finally {
            lock.unlock();
        }
    }
}

~~~



### Semaphore

~~~java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private final Semaphore semaphore = new Semaphore(3);  // Allows 3 threads to access the resource

    public void accessResource() throws InterruptedException {
        semaphore.acquire();  // Acquire a permit
        try {
            System.out.println(Thread.currentThread().getName() + " is accessing the resource");
        } finally {
            semaphore.release();  // Release the permit
        }
    }
}
~~~



## 21. What is future and completeFuture?

Future is a incomplete collection, normally we would use the complete Future for most of the time.

## 22. Practice multiple threading in package com.chuwa.tutorial.t08_multithreading

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.

~~~java

public class alternativePrint {
    private static int cnt = 1;
    private static boolean t1_Turn = true;
    private static final Object lock = new Object();

    public void StartThread() {
        Thread t1 = new Thread(() -> {
            synchronized(lock) {
                while(cnt < 10) {
                    while(!t1_Turn) {
                        try{
                            lock.wait();
                        }catch (InterruptedException  e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Thead 1 : " + cnt);
                    cnt++;
                    t1_Turn = false;
                    lock.notify();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized(lock) {
                while(cnt <= 10) {
                    while(t1_Turn) {
                        try{
                            lock.wait();
                        }catch (InterruptedException  e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Thead 2 : " + cnt);
                    cnt++;
                    t1_Turn = true;
                    lock.notify();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

public class main {
    public static void main(String[] args) throws IOException {
        alternativePrint ap = new alternativePrint();
        ap.StartThread();
    }
}
~~~

### ReentrantLock Example

~~~java
public class reentrantLockPrint {
    private static final ReentrantLock lock = new ReentrantLock();  // Lock object
    private static final Condition t1TurnCondition = lock.newCondition();  // Condition for t1
    private static final Condition t2TurnCondition = lock.newCondition();  // Condition for t2
    private static int cnt = 1;  // Shared counter
    private static boolean t1Turn = true;  // Flag to control thread turns

    public void startThreads() {
        Thread t1 = new Thread(() -> {
            while (cnt <= 10) {
                lock.lock();  // Acquire the lock
                try {
                    while (!t1Turn) {
                        t1TurnCondition.await();  // Wait until it's t1's turn
                    }
                    if (cnt <= 10) {  // Ensure cnt <= 10 to avoid extra prints
                        System.out.print(cnt + " ");
                        cnt++;
                    }
                    t1Turn = false;  // Switch turn to t2
                    t2TurnCondition.signal();  // Signal t2 to proceed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();  // Release the lock
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (cnt <= 10) {
                lock.lock();  // Acquire the lock
                try {
                    while (t1Turn) {
                        t2TurnCondition.await();  // Wait until it's t2's turn
                    }
                    if (cnt <= 10) {  // Ensure cnt <= 10 to avoid extra prints
                        System.out.print(cnt + " ");
                        cnt++;
                    }
                    t1Turn = true;  // Switch turn to t1
                    t1TurnCondition.signal();  // Signal t1 to proceed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();  // Release the lock
                }
            }
        });

        t1.start();
        t2.start();
    }
}

public class main {
    public static void main(String[] args) throws IOException {
        reentrantLockPrint ap = new reentrantLockPrint();
        ap.StartThread();
    }
}
~~~



## 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.

~~~java
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

        print1.class.notifyAll();
    }
~~~



## 25. completable future:

~~~java
// Write a simple program that uses CompletableFuture to asynchronously get the sum
public class completablePrint {

    public void start() {
        int a = 3, b = 4;
        CompletableFuture<Void> sumPrint = CompletableFuture.runAsync(() -> {
            int sum = a + b;
            System.out.println("Sum: " + sum);
        });
        CompletableFuture<Void> multiplyPrint = CompletableFuture.runAsync(() -> {
            int mult = a * b;
            System.out.println("Sum: " + mult);
        });
        // Wait for both computations to complete
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sumPrint, multiplyPrint);
        combinedFuture.join();
        System.out.println("The end");
    }
}
~~~

~~~java
// Assume there is an online store that needs to fetch data from three APIs: products,
// reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
// data for further processing

public class Main {
    public static void main(String[] args) {
        completeFutureFetchAPI cp = new completeFutureFetchAPI();
        cp.get("oSaumurai");
    }
}

public class completeFutureFetchAPI {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public void get(String myrepo) {
        CompletableFuture<String> personalRepos = CompletableFuture.supplyAsync(() -> fetchRepos(myrepo));
        CompletableFuture<String> contributors = personalRepos.thenApplyAsync(reposJson -> {
            JSONArray reposArray = new JSONArray(reposJson);
            for (int i = 0; i < reposArray.length(); i++) {
                JSONObject repo = reposArray.getJSONObject(i);
                String repoName = repo.getString("name");
                String contributorsUrl = repo.getString("contributors_url");

                // Fetch contributors for each repository asynchronously
                CompletableFuture.supplyAsync(() -> fetchContributors(contributorsUrl))
                        .thenAccept(contributorsJson -> {
                            if (contributorsJson.startsWith("[")) {
                                JSONArray contributorsArray = new JSONArray(contributorsJson);
                                System.out.println("Contributors for repository '" + repoName + "':");
                                for (int j = 0; j < contributorsArray.length(); j++) {
                                    JSONObject contributor = contributorsArray.getJSONObject(j);
                                    System.out.println("Contributor: " + contributor.getString("login"));
                                }
                            } else if (contributorsJson.startsWith("{")) {
                                System.out.println("Received an object instead of an array for contributors. Response: " + contributorsJson);
                            } else {
                                System.out.println("Unexpected response format: " + contributorsJson);
                            }
                        }).join(); // Wait for this task to complete
            }
            return null; // Return null since the function expects Void
        });
        // Merge tasks
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(personalRepos, contributors);
        combinedFuture.join(); // Block until all the tasks are done
    }
        // Fetch repositories (Products)
    public static String fetchRepos(String userName) {
        String url = "https://api.github.com/users/" + userName + "/repos";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    // Fetch contributors for each repository (Reviews)
    public static String fetchContributors(String contributorsUrl) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(contributorsUrl))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching contributors";
        }
    }
}
~~~
