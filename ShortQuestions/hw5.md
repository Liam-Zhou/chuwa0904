### 1. Read

https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock

### 2. Thread-safe Singleton Class

A thread-safe singleton ensures that only one instance of the class is created even when multiple threads are trying to access it.

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
        // private constructor
    }

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

### 3. How to Create a New Thread (Including ThreadPool Approach)

- **Creating a Thread using `Thread` class:**
   ```java
   public class MyThread extends Thread {
       public void run() {
           System.out.println("Thread is running");
       }
   
       public static void main(String[] args) {
           MyThread thread = new MyThread();
           thread.start();
       }
   }
   ```

- **Creating a Thread using `Runnable` interface:**
   ```java
   public class MyRunnable implements Runnable {
       public void run() {
           System.out.println("Runnable thread is running");
       }
   
       public static void main(String[] args) {
           Thread thread = new Thread(new MyRunnable());
           thread.start();
       }
   }
   ```

- **Creating a Thread using ThreadPool (ExecutorService):**
   ```java
   import java.util.concurrent.ExecutorService;
   import java.util.concurrent.Executors;
   
   public class ThreadPoolExample {
       public static void main(String[] args) {
           ExecutorService executor = Executors.newFixedThreadPool(3);
           executor.submit(() -> System.out.println("Task executed"));
           executor.shutdown();
       }
   }
   ```

### 4. Difference Between `Runnable` and `Callable`
- **Runnable**:
  - Does not return a result.
  - Cannot throw checked exceptions.
  ```java
  public class MyRunnable implements Runnable {
      public void run() {
          System.out.println("Runnable is running");
      }
  }
  ```

- **Callable**:
  - Returns a result using generics.
  - Can throw checked exceptions.
  ```java
  import java.util.concurrent.Callable;
  
  public class MyCallable implements Callable<Integer> {
      public Integer call() throws Exception {
          return 42;
      }
  }
  ```

### 5. Difference Between `t.start()` and `t.run()`
- **`t.start()`**: Creates a new thread and executes the `run()` method in that new thread.
- **`t.run()`**: Just calls the `run()` method in the **current** thread (no new thread is created).

### 6. Which Way is Better: Extending `Thread` vs Implementing `Runnable`?
- **Runnable** is preferred because:
  - Java doesn’t support multiple inheritance. If you extend `Thread`, you cannot extend another class.
  - `Runnable` provides flexibility and separation of task from thread execution.

### 7. Thread States
- **New**: Thread is created but not started.
- **Runnable**: Thread is eligible to run.
- **Blocked**: Waiting to acquire a lock.
- **Waiting**: Waiting indefinitely for another thread to perform an action.
- **Timed Waiting**: Waiting for another thread for a specified amount of time.
- **Terminated**: Thread has finished execution.

### 8. Demonstrate Deadlock and Resolve it

- **Deadlock Example**:
   ```java
   public class DeadlockExample {
       private final Object lock1 = new Object();
       private final Object lock2 = new Object();
   
       public void method1() {
           synchronized (lock1) {
               System.out.println("Thread 1: Holding lock1...");
               synchronized (lock2) {
                   System.out.println("Thread 1: Holding lock1 and lock2...");
               }
           }
       }
   
       public void method2() {
           synchronized (lock2) {
               System.out.println("Thread 2: Holding lock2...");
               synchronized (lock1) {
                   System.out.println("Thread 2: Holding lock2 and lock1...");
               }
           }
       }
   
       public static void main(String[] args) {
           DeadlockExample example = new DeadlockExample();
           Thread t1 = new Thread(example::method1);
           Thread t2 = new Thread(example::method2);
           t1.start();
           t2.start();
       }
   }
   ```

- **Solution (Avoid deadlock by locking in the same order):**
   Ensure all threads acquire locks in the same order.

### 9. How Do Threads Communicate With Each Other?
Threads communicate using **`wait()`**, **`notify()`**, and **`notifyAll()`**.

```java
synchronized (obj) {
    while (condition) {
        obj.wait();
    }
    obj.notify();
}
```

### 10. Difference Between Class Lock and Object Lock
- **Object Lock**: Locks a single instance of a class.
- **Class Lock**: Locks the entire class, i.e., all instances of the class.

### 11. What is `join()` Method?
`join()` allows one thread to wait for another thread to complete its execution.

```java
Thread t = new Thread(() -> {
    System.out.println("Thread is running");
});
t.start();
t.join();  // Waits for thread `t` to finish
```

### 12. What is `yield()` Method?
`yield()` allows the current thread to give up the CPU and let other threads of the same or higher priority execute.

```java
Thread.yield();
```

### 13. What is ThreadPool? Types of ThreadPools and TaskQueue
- **ThreadPool**: A pool of pre-created threads that can be reused.
- **Types of ThreadPool**:
  - FixedThreadPool
  - CachedThreadPool
  - SingleThreadExecutor
  - ScheduledThreadPool
- **TaskQueue**: A queue that stores tasks waiting for threads in the pool.

### 14. Which Library is Used to Create ThreadPool? Which Interface Provides Main Functions of ThreadPool?
- **Library**: `java.util.concurrent`
- **Interface**: `ExecutorService`

### 15. How to Submit a Task to ThreadPool?

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> System.out.println("Task executed"));
executor.shutdown();
```

### 16. What is the Advantage of ThreadPool?
- Efficient resource management.
- Avoids the overhead of creating and destroying threads.
- Optimizes task execution by reusing threads.

### 17. Difference Between `shutdown()` and `shutdownNow()` Methods of Executor
- **`shutdown()`**: Initiates an orderly shutdown. Previously submitted tasks are executed, but no new tasks are accepted.
- **`shutdownNow()`**: Attempts to stop all running tasks immediately and returns a list of unexecuted tasks.

### 18. What are Atomic Classes? Types of Atomic Classes?
- **Atomic classes** provide lock-free, thread-safe operations on single variables.
- **Types**:
  - `AtomicInteger`, `AtomicLong`, `AtomicBoolean`
  - `AtomicReference`
  
  **Example**:
  ```java
  AtomicInteger count = new AtomicInteger(0);
  count.incrementAndGet();
  ```

### 19. What is Concurrent Collections? List Some Concurrent Data Structures
- **Concurrent Collections**: Thread-safe versions of collections.
  - `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`

### 20. Types of Locks and Advantages
- **Locks**:
  - **ReentrantLock**: Allows a thread to reenter the same lock.
  - **ReadWriteLock**: Allows multiple readers or one writer.

### 21. What is `Future` and `CompletableFuture`?
- **Future**: Represents the result of an asynchronous computation.
- **CompletableFuture**: Extends `Future`, providing more methods for chaining tasks.

  **Methods**:
  - `thenApply()`, `thenAccept()`, `thenRun()`

### 23. Write Code for Two Threads Printing Odd and Even Numbers

**Solution using `synchronized` and `wait/notify`:**
```java
public class OddEvenPrinter {
    private final Object lock = new Object();
    private boolean odd = true;

    public void printOdd() {
        synchronized (lock) {
            for (int i = 1; i <= 9; i += 2) {
                while (!odd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                odd = false;
                lock.notify();
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            for (int i = 2; i <= 10; i += 2) {
                while (odd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                odd = true;
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread t1 = new Thread(printer::printOdd, "Thread-0");
        Thread t2 = new Thread(printer::printEven, "Thread-1");

        t1.start();
        t2.start();
    }
}
```

### 24. 

- **Homework 1: CompletableFuture to Get the Sum and Product of Two Integers Asynchronously**

Here's a simple Java program that uses `CompletableFuture` to asynchronously calculate the sum and product of two integers, then prints the results.

```java
import java.util.concurrent.CompletableFuture;

public class AsyncCalculation {
    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        // Asynchronously calculate the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating the sum...");
            return a + b;
        });

        // Asynchronously calculate the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating the product...");
            return a * b;
        });

        // Print the results once both calculations are done
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));

        // To prevent the main thread from exiting before asynchronous tasks complete
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
```

- **Homework 2: Fetch Data from Multiple APIs Using CompletableFuture**

In this homework, we'll simulate fetching data from three APIs: `products`, `reviews`, and `inventory`. We'll use `jsonplaceholder.typicode.com` for fake API data.

```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OnlineStoreAPI {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static CompletableFuture<String> fetchProducts() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> fetchReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/comments"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> fetchInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> productsFuture = fetchProducts();
        CompletableFuture<String> reviewsFuture = fetchReviews();
        CompletableFuture<String> inventoryFuture = fetchInventory();

        // Combine all futures and process the data together
        CompletableFuture<Void> allData = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // Process the data when all tasks are completed
        allData.thenRun(() -> {
            try {
                System.out.println("Products Data: " + productsFuture.get());
                System.out.println("Reviews Data: " + reviewsFuture.get());
                System.out.println("Inventory Data: " + inventoryFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).join(); // To make sure the main thread waits for completion
    }
}
```

- **Homework 3: Exception Handling with CompletableFuture**

In this homework, we'll add exception handling to Homework 2. If an API call fails, it returns a default value, and the exception is logged.

```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OnlineStoreAPIWithExceptionHandling {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static CompletableFuture<String> fetchProducts() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch products: " + ex.getMessage());
                    return "Default Products Data";
                });
    }

    public static CompletableFuture<String> fetchReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/comments"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch reviews: " + ex.getMessage());
                    return "Default Reviews Data";
                });
    }

    public static CompletableFuture<String> fetchInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch inventory: " + ex.getMessage());
                    return "Default Inventory Data";
                });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> productsFuture = fetchProducts();
        CompletableFuture<String> reviewsFuture = fetchReviews();
        CompletableFuture<String> inventoryFuture = fetchInventory();

        // Combine all futures and process the data together
        CompletableFuture<Void> allData = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // Process the data when all tasks are completed
        allData.thenRun(() -> {
            try {
                System.out.println("Products Data: " + productsFuture.get());
                System.out.println("Reviews Data: " + reviewsFuture.get());
                System.out.println("Inventory Data: " + inventoryFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).join(); // To make sure the main thread waits for completion
    }
}
```

