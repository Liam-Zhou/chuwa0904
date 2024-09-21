### 2. Write a thread-safe singleton class

```
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
        // Optional: Add any initialization code here
    }

    public static Singleton getInstance() {
        if (instance == null) {  // First check (without locking)
            synchronized (Singleton.class) {
                if (instance == null) {  // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

### 3. How to create a new thread(Please also consider Thread Pool approach)?

- Extending the Thread class

  ```
  class MyThread extends Thread {
      @Override
      public void run() {
          System.out.println("Thread is running");
      }
  }

  public class Main {
      public static void main(String[] args) {
          MyThread thread = new MyThread();
          thread.start();  // Start the new thread
      }
  }
  ```

- Implementing the Runnable interface

  ```
  class MyRunnable implements Runnable {
      @Override
      public void run() {
          // Code that runs in the thread
          System.out.println("Runnable thread is running");
      }
  }

  public class Main {
      public static void main(String[] args) {
          Thread thread = new Thread(new MyRunnable());
          thread.start();  // Start the new thread
      }
  }
  ```

- Using a Thread Pool

  ```
  import java.util.concurrent.ExecutorService;
  import java.util.concurrent.Executors;

  class MyTask implements Runnable {
      private String taskName;

      public MyTask(String taskName) {
          this.taskName = taskName;
      }

      @Override
      public void run() {
          System.out.println("Executing task: " + taskName + " by " + Thread.currentThread().getName());
      }
  }

  public class Main {
      public static void main(String[] args) {
          ExecutorService executorService = Executors.newFixedThreadPool(3);

          for (int i = 1; i <= 5; i++) {
              MyTask task = new MyTask("Task " + i);
              executorService.submit(task);
          }

          executorService.shutdown();
      }
  }
  ```

### 4. Difference between Runnable and Callable?

| Feature            | `Runnable`                                                   | `Callable`                                |
| ------------------ | ------------------------------------------------------------ | ----------------------------------------- |
| Return Type        | `void`                                                       | `V` (generic return type)                 |
| Exception Handling | Cannot throw checked exceptions                              | Can throw checked exceptions              |
| Method Signature   | `void run()`                                                 | `V call()`                                |
| Used For           | Tasks without return values                                  | Tasks that return values                  |
| ExecutorService    | `executor.execute()` or `executor.submit()` (returns `null`) | `executor.submit()` (returns `Future<V>`) |

### 5. What is the difference between t.start() and t.run()?

| Aspect                  | `t.start()`                                             | `t.run()`                              |
| ----------------------- | ------------------------------------------------------- | -------------------------------------- |
| **Thread Creation**     | Creates a new thread                                    | No new thread is created               |
| **Method Execution**    | Executes `run()` in a new thread (concurrent)           | Executes `run()` in the current thread |
| **Concurrency**         | The `run()` method runs concurrently with other threads | No concurrency, runs sequentially      |
| **Resource Allocation** | JVM allocates resources for a new thread                | No extra resources are allocated       |

### 6. Which way of creating threads is better: Thread class or Runnable interface?

Implementing the Runnable interface is generally considered the better approach:

| Aspect                        | Extending `Thread`                        | Implementing `Runnable`                                |
| ----------------------------- | ----------------------------------------- | ------------------------------------------------------ |
| **Multiple Inheritance**      | Cannot extend any other class             | Can extend other classes or implement other interfaces |
| **Task & Thread Separation**  | Tightly coupled                           | Task is separate from the thread                       |
| **Reusability**               | Less reusable                             | More reusable across different threads                 |
| **Thread Pool Compatibility** | Not compatible with thread pools directly | Works well with `ExecutorService` and thread pools     |
| **Design Flexibility**        | Less flexible                             | More flexible and adheres to OOP principles            |
| **Concurrency Management**    | Limited to simple use cases               | Ideal for modern concurrent programming                |

### 7. What are the thread statuses?

- NEW: Thread has been created but not yet started.
- RUNNABLE: Thread is ready to run or is actively running.
- BLOCKED: Thread is waiting to acquire a lock (synchronized block/method).
- WAITING: Thread is waiting indefinitely for another thread to perform an action.
- TIMED_WAITING: Thread is waiting for a specific time for another thread to perform an action.
- TERMINATED: Thread has completed execution or has exited due to an exception.

### 8. Demonstrate deadlock and how to resolve it in Java code.

A deadlock occurs in Java when two or more threads are blocked forever, waiting for each other to release locks on shared resources. This can happen when multiple threads hold locks on resources and attempt to acquire locks held by the other threads.

resolve:  
Ensure that all threads acquire locks in the same order, which prevents the circular wait condition

    ```
    public class DeadlockResolved {

        // Resources
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();

        public static void main(String[] args) {
            Thread thread1 = new Thread(() -> {
                synchronized (lock1) {
                    System.out.println("Thread 1: Holding lock 1...");

                    try { Thread.sleep(50); } catch (InterruptedException e) {}

                    System.out.println("Thread 1: Waiting for lock 2...");
                    synchronized (lock2) {
                        System.out.println("Thread 1: Holding lock 1 & 2...");
                    }
                }
            });

            Thread thread2 = new Thread(() -> {
                synchronized (lock1) {  // Lock order is the same (lock1 first)
                    System.out.println("Thread 2: Holding lock 1...");

                    try { Thread.sleep(50); } catch (InterruptedException e) {}

                    System.out.println("Thread 2: Waiting for lock 2...");
                    synchronized (lock2) {
                        System.out.println("Thread 2: Holding lock 1 & 2...");
                    }
                }
            });

            thread1.start();
            thread2.start();
        }
    }
    ```

### 9. How do threads communicate each other?

1. Shared Variables

   ```
   class SharedResource {
       private int count = 0;

       public synchronized void increment() {
           count++;
       }

       public synchronized int getCount() {
           return count;
       }
   }

   public class ThreadCommunicationExample {
       public static void main(String[] args) throws InterruptedException {
           SharedResource sharedResource = new SharedResource();

           Thread t1 = new Thread(() -> {
               for (int i = 0; i < 1000; i++) {
                   sharedResource.increment();
               }
           });

           Thread t2 = new Thread(() -> {
               for (int i = 0; i < 1000; i++) {
                   sharedResource.increment();
               }
           });

           t1.start();
           t2.start();

           t1.join();
           t2.join();

           System.out.println("Final count: " + sharedResource.getCount());  // Should print 2000
       }
   }
   ```

2. wait(), notify(), and notifyAll()

   ```
   class SharedData {
       private int data;
       private boolean isDataAvailable = false;

       public synchronized void produce(int value) throws InterruptedException {
           while (isDataAvailable) {
               wait();  // Wait until the consumer has consumed the previous data
           }
           data = value;
           isDataAvailable = true;
           System.out.println("Produced: " + value);
           notify();  // Notify the consumer that data is ready
       }

       public synchronized int consume() throws InterruptedException {
           while (!isDataAvailable) {
               wait();  // Wait until the producer produces data
           }
           isDataAvailable = false;
           System.out.println("Consumed: " + data);
           notify();  // Notify the producer that data has been consumed
           return data;
       }
   }

   public class ThreadCommunicationExample {
       public static void main(String[] args) {
           SharedData sharedData = new SharedData();

           Thread producer = new Thread(() -> {
               for (int i = 1; i <= 5; i++) {
                   try {
                       sharedData.produce(i);
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }
               }
           });

           Thread consumer = new Thread(() -> {
               for (int i = 1; i <= 5; i++) {
                   try {
                       sharedData.consume();
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }
               }
           });

           producer.start();
           consumer.start();
       }
   }
   ```

3. join() Method

   ```
   public class ThreadJoinExample {
       public static void main(String[] args) throws InterruptedException {
           Thread t1 = new Thread(() -> {
               try {
                   Thread.sleep(1000);  // Simulate work
                   System.out.println("Thread 1 has finished.");
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           });

           Thread t2 = new Thread(() -> {
               try {
                   Thread.sleep(500);  // Simulate work
                   System.out.println("Thread 2 has finished.");
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           });

           t1.start();
           t2.start();

           t1.join();  // Wait for thread t1 to finish
           t2.join();  // Wait for thread t2 to finish

           System.out.println("Both threads have finished. Main thread continues.");
       }
   }
   ```

### 10. What’s the difference between class lock and object lock?

| Aspect              | **Object Lock**                                                              | **Class Lock**                                                                |
| ------------------- | ---------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| **What it locks**   | Locks on a specific instance of a class.                                     | Locks on the class itself (`Class` object), shared by all instances.          |
| **Scope**           | Affects only one instance (object) at a time.                                | Affects all instances of the class (class-wide lock).                         |
| **Synchronization** | `synchronized` instance methods or synchronized blocks using `this`.         | `synchronized` static methods or synchronized blocks using `Class` object.    |
| **Concurrency**     | Different instances can have their own locks, allowing concurrent execution. | Only one thread can execute synchronized static methods across all instances. |
| **Usage Example**   | `public synchronized void method()`                                          | `public static synchronized void method()`                                    |

### 11. What is join() method?

The `join()` method in Java is used to pause the execution of the current thread until the thread on which `join()` was called has completed its execution. This allows one thread to wait for another thread to finish before it continues its own execution.

### 12. what is yield() method?

The `yield()` method in Java is a static method of the `Thread` class that provides a hint to the thread scheduler that the current thread is willing to pause its execution to allow other threads of the same priority or higher to execute.

### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

A ThreadPool is a collection of pre-instantiated threads that are ready to execute tasks. Instead of creating and destroying threads for every task (which is resource-intensive), a ThreadPool allows you to reuse threads, significantly improving performance and resource management. Once a task is completed, the thread returns to the pool and is available for future tasks.

Types of ThreadPools in Java:

- FixedThreadPool
- CachedThreadPool
- SingleThreadExecutor
- ScheduledThreadPool
- WorkStealingPool (Java 8 and above)

A TaskQueue (also known as a BlockingQueue) is an integral part of a ThreadPool. It is a queue of tasks (usually instances of Runnable or Callable) that are waiting to be executed by threads in the pool. When all the threads in the pool are busy, the submitted tasks are stored in the queue until a thread becomes available to pick up and execute the task.

### 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

The core class for working with thread pools is the `Executor` framework in the `java.util.concurrent` package. This framework includes classes like:

- `ExecutorService`: Provides a higher-level API for managing thread pools.
- `Executors`: A utility class that provides factory methods to create various types of thread pools (e.g., `FixedThreadPool`, `CachedThreadPool`, etc.).

**Main Interface for ThreadPool: `ExecutorService`**  
The `ExecutorService` interface is the primary interface that provides the main functionalities of a thread pool in Java. It extends the `Executor` interface and adds more control over thread management, including shutting down the pool, submitting tasks, and managing task results.

### 15. How to submit a task to ThreadPool?

1. Create a ThreadPool
2. Submit a Task
   - Runnable tasks
   - Callable tasks
3. Shutdown the ThreadPool

### 16. What is the advantage of ThreadPool?

| Advantage                        | Description                                                                                             |
| -------------------------------- | ------------------------------------------------------------------------------------------------------- |
| **Improved Performance**         | Reuses existing threads, reducing the overhead of thread creation and destruction.                      |
| **Controlled Concurrency**       | Limits the number of threads running concurrently, preventing thread thrashing or resource exhaustion.  |
| **Efficient Task Queuing**       | Tasks are queued when all threads are busy, ensuring tasks are executed in an orderly manner.           |
| **Resource Management**          | Prevents overloading the system by limiting the number of active threads.                               |
| **Simplified Management**        | Thread lifecycle management is handled automatically, reducing code complexity.                         |
| **Scalability**                  | Can handle large volumes of tasks efficiently, supporting scalability of the application.               |
| **Reduced Latency**              | Threads are pre-created and tasks can execute immediately without waiting for thread creation.          |
| **Scheduling Support**           | Built-in support for scheduled and periodic task execution via `ScheduledThreadPoolExecutor`.           |
| **Error Handling**               | Provides robust error handling with `Future` objects, capturing exceptions and returning task results.  |
| **Thread Safety**                | Thread pool implementations ensure thread-safe handling of tasks, reducing bugs related to concurrency. |
| **Supports Multiple Task Types** | Can handle both `Runnable` and `Callable` tasks for more flexibility.                                   |

### 17. Difference between shutdown() and shutdownNow() methods of executor

| Aspect                   | **`shutdown()`**                          | **`shutdownNow()`**                                         |
| ------------------------ | ----------------------------------------- | ----------------------------------------------------------- |
| **New Task Submission**  | No new tasks are accepted after shutdown. | No new tasks are accepted after shutdown.                   |
| **Running Tasks**        | Running tasks are allowed to complete.    | Running tasks are attempted to be interrupted.              |
| **Queued Tasks**         | Queued tasks are executed after shutdown. | Queued tasks are canceled and returned.                     |
| **Graceful Termination** | Allows all tasks to finish execution.     | Immediate termination attempt; ongoing tasks may stop.      |
| **Return Value**         | No tasks are returned.                    | Returns a list of tasks that were waiting but not executed. |

### 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

Atomic classes are part of the java.util.concurrent.atomic package and provide a way to perform atomic operations on variables. These operations are performed atomically (indivisibly), meaning they are completed without interference from other threads.

Types of Atomic Classes:

1. Atomic Integer Classes

   ```
   import java.util.concurrent.atomic.AtomicInteger;

   public class AtomicIntegerExample {
       public static void main(String[] args) {
           AtomicInteger atomicInt = new AtomicInteger(0);

           // Increment and get the new value
           System.out.println("Incremented Value: " + atomicInt.incrementAndGet());

           // Get the current value
           System.out.println("Current Value: " + atomicInt.get());

           // Get and then increment (returns old value)
           System.out.println("Old Value before increment: " + atomicInt.getAndIncrement());

           // Get the new value after increment
           System.out.println("New Value after increment: " + atomicInt.get());

           // Compare and set
           boolean updated = atomicInt.compareAndSet(2, 5);
           System.out.println("Compare and Set success: " + updated + ", New Value: " + atomicInt.get());
       }
   }
   ```

2. Atomic Boolean Classes

   ```
   import java.util.concurrent.atomic.AtomicBoolean;

   public class AtomicBooleanExample {
       public static void main(String[] args) {
           AtomicBoolean atomicBoolean = new AtomicBoolean(false);

           // Set and get
           atomicBoolean.set(true);
           System.out.println("Current Boolean Value: " + atomicBoolean.get());

           // Compare and set (CAS)
           boolean updated = atomicBoolean.compareAndSet(true, false);
           System.out.println("Compare and Set success: " + updated + ", New Value: " + atomicBoolean.get());

           // Get and set (returns the old value)
           boolean oldValue = atomicBoolean.getAndSet(true);
           System.out.println("Old Value: " + oldValue + ", New Value: " + atomicBoolean.get());
       }
   }
   ```

3. Atomic Reference Classes

   ```
   import java.util.concurrent.atomic.AtomicReference;

   public class AtomicReferenceExample {
       public static void main(String[] args) {
           AtomicReference<String> atomicReference = new AtomicReference<>("initial");

           // Get the current reference value
           System.out.println("Current Value: " + atomicReference.get());

           // Set a new reference
           atomicReference.set("new value");
           System.out.println("Updated Value: " + atomicReference.get());

           // Compare and set (CAS operation)
           boolean updated = atomicReference.compareAndSet("new value", "updated value");
           System.out.println("Compare and Set success: " + updated + ", New Value: " + atomicReference.get());

           // Get and set (returns the old reference value)
           String oldValue = atomicReference.getAndSet("final value");
           System.out.println("Old Value: " + oldValue + ", New Value: " + atomicReference.get());
       }
   }
   ```

Main Methods:

- get(): Gets the current value.
- set(value): Sets the value.
- getAndSet(value): Atomically sets the value and returns the old value.
- incrementAndGet(): Atomically increments the value and returns the new value.
- getAndIncrement(): Atomically increments the value and returns the old value.
- decrementAndGet(): Atomically decrements the value and returns the new value.
- getAndDecrement(): Atomically decrements the value and returns the old value.
- compareAndSet(expectedValue, newValue): Atomically sets the value if it is equal to the expected value (CAS operation).

When to Use Atomic Classes:

- **Concurrency without Synchronization**: Use atomic classes when you need to perform atomic read-modify-write operations without the overhead of locks or synchronization.
- **Single Variable:** If you're only updating a single variable, using an atomic class is more efficient than using a synchronized block or lock.
- **Low Contention:** Atomic classes are efficient when there is low contention between threads. For highly contended scenarios, locks may still perform better.

### 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

`Concurrent collections` are a set of thread-safe data structures provided in the java.util.concurrent package. These collections are designed to be used in multi-threaded environments, where multiple threads might access and modify the collection concurrently.

Common Concurrent Data Structures:
| Collection | Description |
|-----------------------------|------------------------------------------------------------------------------------------|
| **ConcurrentHashMap** | A thread-safe hash map allowing concurrent reads and writes. |
| **ConcurrentLinkedQueue** | A lock-free, thread-safe, unbounded queue. |
| **ConcurrentLinkedDeque** | A lock-free, thread-safe deque allowing operations on both ends. |
| **CopyOnWriteArrayList** | A thread-safe list where reads are fast, and writes create a copy of the list. |
| **CopyOnWriteArraySet** | A thread-safe set where writes create a copy of the set. |
| **BlockingQueue** | A thread-safe queue that blocks when full or empty. |
| **BlockingDeque** | A thread-safe double-ended queue that blocks on both ends. |
| **ConcurrentSkipListMap** | A thread-safe, sorted map using skip list algorithms. |
| **ConcurrentSkipListSet** | A thread-safe, sorted set using skip list algorithms. |
| **DelayQueue** | A thread-safe queue where elements are delayed before being dequeued. |

### 20. What kind of locks do you know? What is the advantage of each lock?

| **Lock Type**      | **Advantages**                                                                             |
| ------------------ | ------------------------------------------------------------------------------------------ |
| **Intrinsic Lock** | Simple, built-in with `synchronized`; reentrant; easy to use.                              |
| **ReentrantLock**  | Try lock, timed lock, interruptible lock, and condition variables for more control.        |
| **ReadWriteLock**  | Allows concurrent reads and exclusive writes, improving performance in read-heavy systems. |
| **StampedLock**    | Optimistic reads for better performance; explicit locking for fine-grained control.        |
| **Atomic Classes** | Lock-free, highly efficient for single variable updates; non-blocking.                     |
| **Semaphore**      | Controls access to a resource by limiting the number of threads allowed to access it.      |
| **CountDownLatch** | Coordinates multiple threads by allowing one or more to wait for others to complete tasks. |
| **CyclicBarrier**  | Synchronizes threads by making them wait at a barrier point until all have reached it.     |

### 21. What is future and completableFuture? List some main methods of ComplertableFuture.

**Difference Between `Future` and `CompletableFuture`:**
| Feature | `Future` | `CompletableFuture` |
|---------------------------|-------------------------------------------|--------------------------------------------------------------|
| **Blocking** | Blocks when calling `get()` | Non-blocking; supports chaining of async operations. |
| **Cancellation** | Supports cancellation | Supports cancellation. |
| **Asynchronous Execution** | No built-in support | Built-in support for asynchronous tasks (`supplyAsync`, etc.). |
| **Combining Futures** | Not supported | Can combine multiple futures using `allOf()` or `anyOf()`. |
| **Error Handling** | No built-in error handling | Supports exception handling with `exceptionally()` and `handle()`. |
| **Chaining** | Does not support chaining | Supports chaining with `thenApply()`, `thenAccept()`, etc. |
| **Completion Callbacks** | Not supported | Supports completion callbacks when future completes. |
| **Non-blocking** | No, `get()` is blocking | Yes, supports non-blocking operations. |

**Common `CompletableFuture` Methods:**

| Method                             | Description                                                                                                 |
| ---------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **`supplyAsync(Supplier<U>)`**     | Runs a task asynchronously that returns a result.                                                           |
| **`runAsync(Runnable)`**           | Runs a task asynchronously that does not return a result.                                                   |
| **`thenApply(Function<T, U>)`**    | Transforms the result of a future when it completes.                                                        |
| **`thenAccept(Consumer<T>)`**      | Consumes the result when it completes, without returning anything.                                          |
| **`thenCombine(CompletionStage)`** | Combines two futures when both complete.                                                                    |
| **`allOf(CompletableFuture...)`**  | Returns a future that completes when all given futures complete.                                            |
| **`anyOf(CompletableFuture...)`**  | Returns a future that completes when any of the given futures complete.                                     |
| **`exceptionally(Function)`**      | Handles exceptions and provides a fallback result.                                                          |
| **`handle(BiFunction)`**           | Handles both the result and the exception when the future completes.                                        |
| **`complete(T)`**                  | Manually completes the future with a result, even if it was not completed earlier.                          |
| **`join()`**                       | Returns the result of the future without throwing checked exceptions (non-blocking alternative to `get()`). |

### 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.

1. Solution Using synchronized and wait/notify

```
public class WaitNotifyExample {
    private final Object lock = new Object();
    private boolean isOddTurn = true;  // Flag to alternate turns

    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();
        Thread oddThread = new Thread(example::printOddNumbers);
        Thread evenThread = new Thread(example::printEvenNumbers);

        oddThread.start();
        evenThread.start();
    }

    public void printOddNumbers() {
        for (int i = 1; i <= 9; i += 2) {
            synchronized (lock) {
                while (!isOddTurn) {
                    try {
                        lock.wait();  // Wait for the even thread to complete
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = false;  // Switch turn to even
                lock.notify();  // Notify the even thread
            }
        }
    }

    public void printEvenNumbers() {
        for (int i = 2; i <= 10; i += 2) {
            synchronized (lock) {
                while (isOddTurn) {
                    try {
                        lock.wait();  // Wait for the odd thread to complete
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = true;  // Switch turn to odd
                lock.notify();  // Notify the odd thread
            }
        }
    }
}
```

2. Solution Using ReentrantLock and Condition (await/signal)

```
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final Lock lock = new ReentrantLock();
    private final Condition oddTurn = lock.newCondition();
    private final Condition evenTurn = lock.newCondition();
    private boolean isOddTurn = true;  // Flag to alternate turns

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        Thread oddThread = new Thread(example::printOddNumbers);
        Thread evenThread = new Thread(example::printEvenNumbers);

        oddThread.start();
        evenThread.start();
    }

    public void printOddNumbers() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();
            try {
                while (!isOddTurn) {
                    oddTurn.await();  // Wait until it's the odd thread's turn
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = false;  // Switch turn to even
                evenTurn.signal();  // Signal the even thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEvenNumbers() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();
            try {
                while (isOddTurn) {
                    evenTurn.await();  // Wait until it's the even thread's turn
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = true;  // Switch turn to odd
                oddTurn.signal();  // Signal the odd thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
```

### 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random

```
public class RandomThreadOutput {
    public static void main(String[] args) {
        // First thread prints 1 to 10
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100);  // Simulate random delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Second thread prints 11 to 20
        Thread thread2 = new Thread(() -> {
            for (int i = 11; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100);  // Simulate random delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Third thread prints 21 to 30
        Thread thread3 = new Thread(() -> {
            for (int i = 21; i <= 30; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100);  // Simulate random delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
```

### 25. completable future:

1. Homework 1:

```
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        // Asynchronously calculate the sum of two numbers
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            int sum = a + b;
            System.out.println("Sum: " + sum);
            return sum;
        });

        // Asynchronously calculate the product of two numbers
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            int product = a * b;
            System.out.println("Product: " + product);
            return product;
        });

        // Combine both futures to ensure both are completed before printing the results
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sumFuture, productFuture);

        // Wait for both tasks to complete and then print a final message
        combinedFuture.thenRun(() -> System.out.println("Both calculations are completed."));

        // Block the main thread until the combinedFuture completes to prevent premature termination
        combinedFuture.join();
    }
}
```

2. Homework 2 (Simulated using `jsonplaceholder`):

```
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreExample {

    // HttpClient for sending requests
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        // Asynchronously fetch data from the Products API
        CompletableFuture<String> productsFuture = fetchData("https://jsonplaceholder.typicode.com/posts");

        // Asynchronously fetch data from the Reviews API
        CompletableFuture<String> reviewsFuture = fetchData("https://jsonplaceholder.typicode.com/comments");

        // Asynchronously fetch data from the Inventory API
        CompletableFuture<String> inventoryFuture = fetchData("https://jsonplaceholder.typicode.com/photos");

        // Combine the results of all three API calls
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // Once all data is fetched, process the results
        combinedFuture.thenRun(() -> {
            try {
                // Get the results from the futures
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                // Simulate merging and processing the data
                System.out.println("Merged Data: ");
                System.out.println("Products: " + products.substring(0, 100) + "...");  // Just showing part of the result
                System.out.println("Reviews: " + reviews.substring(0, 100) + "...");
                System.out.println("Inventory: " + inventory.substring(0, 100) + "...");

                System.out.println("Data merged successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Block main thread until all tasks are completed
        combinedFuture.join();
    }

    // Method to fetch data from the provided API asynchronously
    public static CompletableFuture<String> fetchData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Send the request asynchronously and return the response as a CompletableFuture
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
```

3. Homework 3:

```
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreWithExceptionHandling {

    // HttpClient for sending requests
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        // Asynchronously fetch data from the Products API
        CompletableFuture<String> productsFuture = fetchData("https://jsonplaceholder.typicode.com/posts")
                .exceptionally(ex -> {
                    logException("Products API", ex);
                    return "[Default Product Data]";  // Return default value
                });

        // Asynchronously fetch data from the Reviews API
        CompletableFuture<String> reviewsFuture = fetchData("https://jsonplaceholder.typicode.com/comments")
                .exceptionally(ex -> {
                    logException("Reviews API", ex);
                    return "[Default Reviews Data]";  // Return default value
                });

        // Asynchronously fetch data from the Inventory API
        CompletableFuture<String> inventoryFuture = fetchData("https://jsonplaceholder.typicode.com/photos")
                .exceptionally(ex -> {
                    logException("Inventory API", ex);
                    return "[Default Inventory Data]";  // Return default value
                });

        // Combine the results of all three API calls
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // Once all data is fetched, process the results
        combinedFuture.thenRun(() -> {
            try {
                // Get the results from the futures
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                // Simulate merging and processing the data
                System.out.println("Merged Data: ");
                System.out.println("Products: " + products.substring(0, 100) + "...");  // Just showing part of the result
                System.out.println("Reviews: " + reviews.substring(0, 100) + "...");
                System.out.println("Inventory: " + inventory.substring(0, 100) + "...");

                System.out.println("Data merged successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Block main thread until all tasks are completed
        combinedFuture.join();
    }

    // Method to fetch data from the provided API asynchronously
    public static CompletableFuture<String> fetchData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Send the request asynchronously and return the response as a CompletableFuture
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    // Method to log exception details
    public static void logException(String apiName, Throwable ex) {
        System.err.println("Exception occurred in " + apiName + ": " + ex.getMessage());
    }
}
```
