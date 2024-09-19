# HW5 -- multiThreading

## 1. Read: <https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock>

## 2. Write a thread-safe singleton class

```java
public class Singleton {
    
    // 1, static volatile variable
    private static volatile Singleton instance;
    
    // 2, make constructor be private
    private Singleton() {
    }
    
    // 3. static synchronized getInstance method
    public static Singleton getInstance() {

       // 4, make sure thread safe
       if (instance == null) { // performance

          // t2, t1, t3,
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

## 3. How to create a new thread(Please also consider Thread Pool approach)?

We can use the `Thread` class or implement the `Runnable interface`:

```java
// Using Thread class
Thread thread = new Thread(() -> {
    // Task to run in new thread
});

thread.start();  // 此时JVM才创建新的thread

```

```java
public class MyRunnable implements Runnable{
   
@Override
public void run() {
    System.out.println("Start new thread using Runnable");
      }
}

Thread t2 = new Thread(new MyRunnable());
```

Using the `ExecutorService` from the Java Thread Pool approach is more efficient for managing multiple threads:

```java
// 创建固定⼤⼩的线程池:
ExecutorService executor = Executors.newFixedThreadPool(4);
// 提交任务:
executor.submit(() -> "ganhuo");
executor.submit(task2);
executor.submit(task3);
executor.submit(task4);
executor.submit(task5);

Thread t = new MyThread();
  <- vs -> ExecutorService executor = Executors.newFixedThreadPool(4);

t.start(); 
executor.submit()
```

## 4. Difference between Runnable and Callable?

Runnable: Does not return a result. Its run() method has a void return type.
Callable: Returns a result. Its call() method returns a value of a specified type (V).

## 5. What is the difference between `t.start()` and `t.run()`?

Use `t.start()` to create and run a new thread, whereas `t.run()` merely executes the code in the existing thread.

## 6. Which way of creating threads is better: Thread class or Runnable interface?

Using the `Runnable` interface is better than extending the `Thread` class.

**Separation of Concerns**: Runnable separates task logic from thread management.
**Multiple Inheritance**: Allows the class to extend another class.
**Better Thread Management**: Works well with ExecutorService for efficient thread handling.
**Reusability and Testability**: Decouples task logic from threading, making code more reusable and easier to test.

## 7. What are the thread statuses?

**NEW**: The thread is created but not yet started (t.start() has not been called).
**RUNNABLE**: The thread is ready to run and is waiting for CPU time. This state includes both running (actively executing) and ready-to-run threads.
**BLOCKED**: The thread is blocked, waiting to acquire a lock or monitor that is held by another thread.
**WAITING**: The thread is waiting indefinitely for another thread to perform a particular action (like releasing a lock or notifying).
**TIMED_WAITING**: The thread is waiting for a specific period due to methods like sleep(long millis) or wait(long timeout).
**TERMINATED**: The thread has completed its execution (either finished normally or was terminated due to an exception)

## 8. Demonstrate deadlock and how to resolve it in Java code.

A **deadlock** occurs in Java when two or more threads are blocked forever, each waiting for the other to release a lock.

To resolve the deadlock, ensure that all threads acquire locks in the same order.

## 9. How do threads communicate each other?

Threads communicate in Java using `wait()`, `notify()`, and `notifyAll()` methods:

`wait()`: A thread releases the lock and waits until another thread calls `notify()` or `notifyAll()`.
`notify()`: Wakes up one waiting thread.
`notifyAll()`: Wakes up all waiting threads.

These methods are used inside synchronized blocks to coordinate thread execution.

## 10. What’s the difference between class lock and object lock?

**Class Lock**:

- Acquired on the .class object (e.g., synchronized(MyClass.class)).
- Ensures synchronization across all instances of the class.
- Used for static methods or blocks.

**Object Lock**:

- Acquired on a specific instance of a class (e.g., synchronized(this)).
- Ensures synchronization for that particular object only.
- Used for instance methods or blocks.

**Summary**: Class lock controls access to static members, while object lock controls access to instance members.

## 11. What is `join()` method?

The `join()` method in Java is used to make one thread wait for the completion of another thread.

- **Purpose**: Ensures that a thread (e.g., `Thread A`) waits until another thread (e.g., `Thread B`) has finished executing.
- **Usage**: threadB.join(); makes the current thread (Thread A) wait until Thread B completes.
- **Example**:

```java
thread.join(); // Main thread waits for 'thread' to finish.
```

## 12. what is `yield()` method?

The `yield()` method in Java is a hint to the thread scheduler that the current thread is willing to pause and let other threads of the same or higher priority run.

- **Purpose**: To allow other threads to execute, improving the system's responsiveness.
- **Usage**: Thread.yield(); pauses the current thread and makes it ready to run again, depending on the thread scheduler.
- **Note**: yield() does not guarantee when or if the current thread will be paused; it's just a suggestion to the scheduler.

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

**ThreadPool** is a pool of pre-instantiated threads that are ready to execute tasks, managed by an ExecutorService. It improves performance by reusing threads and reducing the overhead of thread creation.

### Types of ThreadPools**:

- **Fixed Thread Pool**: A pool with a fixed number of threads (`Executors.newFixedThreadPool(n)`).
- **Cached Thread Pool**: A pool that creates new threads as needed but reuses existing ones if available (`Executors.newCachedThreadPool()`).
- **Single Thread Executor**: A pool with a single thread that executes tasks sequentially (`Executors.newSingleThreadExecutor()`).
- **Scheduled Thread Pool**: A pool for executing tasks with a delay or periodically (`Executors.newScheduledThreadPool(n)`).

**TaskQueue in ThreadPool**:
TaskQueue is a queue where tasks (Runnable or Callable) are stored before being executed by threads in the pool. It ensures that tasks are processed in order and provides a buffer when there are more tasks than available threads.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

Use `java.util.concurrent` for thread pools, with interfaces like `ExecutorService` providing the main functions.

## 15. How to submit a task to ThreadPool?

To submit a task to a ThreadPool, use the `submit()` method provided by the `ExecutorService` interface.

## 16. What is the advantage of ThreadPool?

1. **Improved Performance**: Reuses existing threads, reducing the overhead of thread creation and destruction.
2. **Better Resource Management**: Limits the number of concurrent threads, avoiding excessive resource consumption.3. 
3. **Simplified Error Handling**: Provides centralized control and easier management of threads.
4. **Enhanced Responsiveness**: Allows tasks to be queued and processed efficiently, improving application responsiveness.
5. **Scalability**: Makes it easier to scale applications by managing a pool of threads dynamically.

Summary: ThreadPools improve performance, manage resources efficiently, and simplify thread handling.

## 17. Difference between shutdown() and shutdownNow() methods of executor

They are both used to  stop an `ExecutorService`. `Shutdown()` waits for tasks to complete, while `shutdownNow()` attempts to stop all tasks immediately.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

Atomic classes is used to perform **lock-free**, **thread-safe** operations on **single variables**, such as counters, flags, or object references, without the overhead of using explicit synchronization (synchronized blocks).

### Types of Atomic Classes

1. **AtomicInteger**: For atomic operations on integers.
2. **AtomicLong**: For atomic operations on long values.
3. **AtomicBoolean**: For atomic operations on boolean values.
4. **AtomicReference**: For atomic operations on object references.
5. **AtomicIntegerArray**, AtomicLongArray, AtomicReferenceArray: For atomic operations on arrays of int, long, or references.

**Examples**:

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);

        // Increment and get the updated value
        System.out.println("Incremented Value: " + atomicInt.incrementAndGet());

        // Add 5 and get the updated value
        System.out.println("Added 5: " + atomicInt.addAndGet(5));

        // Set to a new value
        atomicInt.set(10);
        System.out.println("Set to: " + atomicInt.get());

        // Compare and set
        boolean success = atomicInt.compareAndSet(10, 20);
        System.out.println("Compare and Set Success: " + success + ", New Value: " + atomicInt.get());
    }
}
```

### Main Methods
- **get()**: Retrieves the current value.
- **set(int newValue)**: Sets to a new value.
- **incrementAndGet()**: Atomically increments by one and returns the new value.
- **addAndGet(int delta)**: Atomically adds the given value and returns the updated value.
- **compareAndSet(int expect, int update)**: Atomically sets the value to `update` if the current value is equal to `expect`.

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

**Concurrent collections** in Java are thread-safe data structures designed for use in multi-threaded environments. They are part of the `java.util.concurrent` package and provide high-performance, non-blocking alternatives to traditional synchronized collections.

Some Concurrent Data Structures (Thread-safe):

- `ConcurrentHashMap`: A thread-safe version of HashMap that allows concurrent read and write operations without locking the entire map.
- `CopyOnWriteArrayList`: A thread-safe variant of ArrayList where all mutative operations (like add, set) are implemented by making a fresh copy of the array.
- `CopyOnWriteArraySet`: A thread-safe variant of Set backed by a CopyOnWriteArrayList.
- `ConcurrentLinkedQueue`: A thread-safe, non-blocking queue based on linked nodes, ideal for producer-consumer scenarios.
- `ConcurrentLinkedDeque`: A thread-safe double-ended queue (deque).
- `LinkedBlockingQueue`: A thread-safe queue that can be optionally bounded.
- `ArrayBlockingQueue`: A thread-safe, bounded queue based on an array.
- `PriorityBlockingQueue`: A thread-safe priority queue with optional capacity constraints.
- `DelayQueue`: A thread-safe queue where elements can only be taken when their delay has expired.
- `SynchronousQueue`: A blocking queue where each insert operation must wait for a corresponding remove operation.
- `ConcurrentSkipListMap`: A thread-safe, sorted map similar to TreeMap, but designed for concurrent access.
- `ConcurrentSkipListSet`: A thread-safe, sorted set similar to TreeSet.

## 20. What kind of locks do you know? What is the advantage of each lock?

1. `Synchronized` Lock (Intrinsic Lock):
- **Description**: Built-in lock provided by the Java language using the synchronized keyword.
- **Advantages**: Simple to use and integrates seamlessly with Java's intrinsic locking mechanism. Ensures mutual exclusion and visibility of changes between threads.

2. `ReentrantLock`:
- **Description**: A more flexible and powerful lock from the java.util.concurrent.locks package.
- **Advantages**:
  - Allows more control over the lock, such as timed lock waiting (tryLock()), interruptible lock acquisition, and the ability to implement fair locking (FIFO).
  - Supports multiple condition variables (using newCondition()).

3. `ReadWriteLock`:
- **Description**: A pair of locks that separates read and write access (ReentrantReadWriteLock).
- **Advantages**: Improves performance by allowing multiple threads to read simultaneously while only one thread can write, reducing contention in read-heavy scenarios.

4. `StampedLock`:
- **Description**: A new type of lock introduced in Java 8 that offers three modes: writing, reading, and optimistic reading.
- **Advantages**:
  - Optimistic read lock allows read operations to proceed without locking, improving throughput in scenarios where write operations are rare.
  - Higher performance than ReentrantReadWriteLock in some cases due to lower contention.

5. `Condition Lock`:
- **Description**: Used with ReentrantLock to provide more granular wait/notify mechanisms.
- **Advantages**: Enables multiple condition objects for the same lock, allowing more precise thread coordination than the intrinsic wait/notify.

## 21. What is `future` and `completableFuture`? List some main methods of ComplertableFuture.

- `Future`: An interface in Java representing the result of an asynchronous computation. It provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result.
   - **Limitations**: Cannot manually complete, lacks chaining, and does not support combining multiple futures easily.
- `CompletableFuture`: A more advanced and flexible implementation of `Future` introduced in Java 8, part of `java.util.concurrent`. It allows building asynchronous, non-blocking applications with additional features such as chaining, combining multiple futures, and manually completing them.

**Main Methods of CompletableFuture**:
- `supplyAsync(Supplier<U> supplier)`: Runs a task asynchronously and returns a CompletableFuture that holds the result.
- `runAsync(Runnable runnable)`: Runs a Runnable task asynchronously and returns a CompletableFuture<Void>.
- `thenApply(Function<U, V> fn)`: Transforms the result of a computation when it completes.
- `thenAccept(Consumer<U> action)`: Consumes the result of a computation when it completes.
- `thenCompose(Function<U, CompletableFuture<V>> fn)`: Chains multiple futures in sequence; waits for one to finish before starting another.
- `thenCombine(CompletableFuture<U> other, BiFunction<U, V, R> fn)`: Combines the results of two futures when both complete.
- `exceptionally(Function<Throwable, U> fn)`: Handles exceptions that occur during computation.
- `complete(U value)`: Manually completes the CompletableFuture with a value.
- `join()`: Retrieves the result of the computation, waiting if necessary.

## 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
   1. One solution use synchronized and wait notify
   2. One solution use ReentrantLock and await, signal
   ```
   Thread-0: 1
   Thread-1: 2
   Thread-0: 3
   Thread-1: 4
   Thread-0: 5
   Thread-1: 6
   Thread-0: 7
   Thread-1: 8
   Thread-0: 9
   Thread-1: 10
   Process finished with exit code 0
   ```
1. Using `synchronized`, `wait()`, and `notify()`

```java
public class OddEvenPrinter {
    private final Object lock = new Object();
    private boolean isOddTurn = true; // To track which thread's turn

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread(printer::printOddNumbers, "Thread-0");
        Thread evenThread = new Thread(printer::printEvenNumbers, "Thread-1");

        oddThread.start();
        evenThread.start();
    }

    // Method to print odd numbers
    public void printOddNumbers() {
        for (int i = 1; i <= 9; i += 2) {
            synchronized (lock) {
                while (!isOddTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = false; // Set turn to even
                lock.notify();
            }
        }
    }

    // Method to print even numbers
    public void printEvenNumbers() {
        for (int i = 2; i <= 10; i += 2) {
            synchronized (lock) {
                while (isOddTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = true; // Set turn to odd
                lock.notify();
            }
        }
    }
}
```

2. Using `ReentrantLock` and `Condition` (`await()` and `signal()`)

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isOddTurn = true;

    public static void main(String[] args) {
        OddEvenPrinterWithLock printer = new OddEvenPrinterWithLock();
        Thread oddThread = new Thread(printer::printOddNumbers, "Thread-0");
        Thread evenThread = new Thread(printer::printEvenNumbers, "Thread-1");

        oddThread.start();
        evenThread.start();
    }

    // Method to print odd numbers
    public void printOddNumbers() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();
            try {
                while (!isOddTurn) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = false; // Set turn to even
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    // Method to print even numbers
    public void printEvenNumbers() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();
            try {
                while (isOddTurn) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = true; // Set turn to odd
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
```

## 24. create 3 threads, one thread output 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)

```
Thread-0: 1
Thread-0: 2
Thread-0: 3
Thread-0: 4
Thread-0: 5
Thread-0: 6
Thread-0: 7
Thread-0: 8
Thread-0: 9
Thread-0: 10
Thread-2: 11
Thread-2: 12
Thread-2: 13
Thread-2: 14
Thread-2: 15
Thread-2: 16
Thread-2: 17
Thread-2: 18
Thread-2: 19
Thread-2: 20
Thread-1: 21
Thread-1: 22
Thread-1: 23
Thread-1: 24
Thread-1: 25
Thread-1: 26
Thread-1: 27
Thread-1: 28
Thread-1: 29
Thread-1: 30
```

```java
public class PrintNumber1 {
    public static void main(String[] args) {
        // Thread 1: Outputs 1-10
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-1");

        // Thread 2: Outputs 11-20
        Thread thread2 = new Thread(() -> {
            for (int i = 11; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-2");

        // Thread 3: Outputs 21-22
        Thread thread3 = new Thread(() -> {
            for (int i = 21; i <= 22; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-3");

        // Start threads (order of execution will be random)
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
```

## 25. completable future:
1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureHomework1 {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        // Asynchronously compute the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            int sum = a + b;
            System.out.println("Sum: " + sum);
            return sum;
        });

        // Asynchronously compute the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            int product = a * b;
            System.out.println("Product: " + product);
            return product;
        });

        // Wait for both computations to complete
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sumFuture, productFuture);

        // When both tasks are complete, print a message
        combinedFuture.thenRun(() -> System.out.println("Both sum and product are computed."));

        // Block and wait for all tasks to complete (for demonstration purposes)
        combinedFuture.join();
    }
}

```

2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟，)
   - 1. Sign In to Developer.BestBuy.com
   - 2. Best Buy Developer API Documentation (bestbuyapis.github.io)
   - 3. 可以⽤fake api <https://jsonplaceholder.typicode.com/>
   - 4. Github public api: <https://api.github.com/users/your-user-name/repos>
   
```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcher {
    public static void main(String[] args) {
        // Define the API endpoints
        String productsApi = "https://jsonplaceholder.typicode.com/posts"; // Simulate products API
        String reviewsApi = "https://jsonplaceholder.typicode.com/comments"; // Simulate reviews API
        String inventoryApi = "https://jsonplaceholder.typicode.com/photos"; // Simulate inventory API

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Fetch products data asynchronously
        CompletableFuture<String> productsFuture = fetchData(client, productsApi);

        // Fetch reviews data asynchronously
        CompletableFuture<String> reviewsFuture = fetchData(client, reviewsApi);

        // Fetch inventory data asynchronously
        CompletableFuture<String> inventoryFuture = fetchData(client, inventoryApi);

        // Combine all futures and process the data once all are completed
        CompletableFuture<Void> allDataFetched = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)
                .thenRun(() -> {
                    try {
                        String productsData = productsFuture.get();
                        String reviewsData = reviewsFuture.get();
                        String inventoryData = inventoryFuture.get();

                        // Simulate merging the fetched data for further processing
                        System.out.println("Merged Data:");
                        System.out.println("Products Data: " + productsData.substring(0, 100)); // Print partial data for readability
                        System.out.println("Reviews Data: " + reviewsData.substring(0, 100));
                        System.out.println("Inventory Data: " + inventoryData.substring(0, 100));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        // Wait for all tasks to complete (for demonstration purposes)
        allDataFetched.join();
    }

    // Method to fetch data from the given API asynchronously
    private static CompletableFuture<String> fetchData(HttpClient client, String apiEndpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
```

3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcherWithExceptionHandling {
    public static void main(String[] args) {
        // Define the API endpoints
        String productsApi = "https://jsonplaceholder.typicode.com/posts"; // Simulate products API
        String reviewsApi = "https://jsonplaceholder.typicode.com/comments"; // Simulate reviews API
        String inventoryApi = "https://jsonplaceholder.typicode.com/photos"; // Simulate inventory API

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Fetch products data asynchronously with exception handling
        CompletableFuture<String> productsFuture = fetchDataWithExceptionHandling(client, productsApi, "Default Products Data");

        // Fetch reviews data asynchronously with exception handling
        CompletableFuture<String> reviewsFuture = fetchDataWithExceptionHandling(client, reviewsApi, "Default Reviews Data");

        // Fetch inventory data asynchronously with exception handling
        CompletableFuture<String> inventoryFuture = fetchDataWithExceptionHandling(client, inventoryApi, "Default Inventory Data");

        // Combine all futures and process the data once all are completed
        CompletableFuture<Void> allDataFetched = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)
                .thenRun(() -> {
                    try {
                        String productsData = productsFuture.get();
                        String reviewsData = reviewsFuture.get();
                        String inventoryData = inventoryFuture.get();

                        // Simulate merging the fetched data for further processing
                        System.out.println("Merged Data:");
                        System.out.println("Products Data: " + productsData.substring(0, Math.min(100, productsData.length())));
                        System.out.println("Reviews Data: " + reviewsData.substring(0, Math.min(100, reviewsData.length())));
                        System.out.println("Inventory Data: " + inventoryData.substring(0, Math.min(100, inventoryData.length())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        // Wait for all tasks to complete (for demonstration purposes)
        allDataFetched.join();
    }

    // Method to fetch data from the given API asynchronously with exception handling
    private static CompletableFuture<String> fetchDataWithExceptionHandling(HttpClient client, String apiEndpoint, String defaultValue) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    // Log the exception
                    System.err.println("Exception occurred while fetching data from: " + apiEndpoint);
                    System.err.println("Error: " + ex.getMessage());
                    // Return default value
                    return defaultValue;
                });
    }
}
```



