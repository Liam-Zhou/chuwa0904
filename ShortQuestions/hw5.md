## 1. 
Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock

## 2. Write a thread-safe singleton class
```
public class GFG {
    private static GFG instance;
    private GFG() {

    }

    synchronized public static GFG getInstance() {
        if(instance == null) {
            instance = new GFG();
        }
        return instance;
    }
}
```
Thread safe singleton means the instance is created so that singleton is maintained in multithread environment. To achieve this, we need to make getInstance() method synchronized so multiple threads can't access it at the same time. /

```
public class GFG {
    private static volatile GFG instance;
    private GFG() {

    }

    public static GFG getInstance() {
        if(instance == null) {
            synchronized (GFG.class) {
                if(instance == null) {
                    instance = new GFG();
                }
            }
        }
        return instance;
    }
}
```
Use double check locking./

## 3. How to create a new thread(Please also consider Thread Pool approach)?
1. Extends thread class
```
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("start new thread using extends thread");
    }
}
Thread t = new Thread();
t.start(); // new thread created by JVM at this line
```

2. Implements Runnable
```
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Start new thread using Runnable");
    }
}
Thread t2 = new Thread(new MyRunnable());
```

3. Implement Callable
```
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Start new thread using Callable";
    }
}
```

## 4. Difference between Runnable and Callable?
Callable has return but Runnable has not return.

## 5. What is the difference between t.start() and t.run()?
t.start() starts a new thread to execute the task./
t.run() execute the task in the current thread.

## 6. Which way of creating threads is better: Thread class or Runnable interface?
Runnable interface is a better way to create thread. The creation and run tasks are separated in runnable interface, so we have more reusability. /
When we extend Thread class, we can’t extend any other class even we require and When we implement Runnable, we can save a space for our class to extend any other class in future or now./
When we extend Thread class, each of our thread creates unique object and associate with it. When we implements Runnable, it shares the same object to multiple threads.

## 7. What are the thread statuses?
new: we create a thread, but haven't started it./
runnable: start() is executed, it includes both ready and running states./
blocked: it's blocked by locks./
waiting: the thread in waiting state needs to wait for other threads to do some actions./
timed_waiting: it can return to previous state after a given time./
terminated: execution done.

## 8. Demonstrate deadlock and how to resolve it in Java code.
Thread x and y needs resource A and B for their tasks. Thread x takes resource A, and thread y takes resource B, and both thread A and B go into the waiting stage./
We can use wait and notify to solve it:
```
public class OddEventPrinter {
    private static final Object monitor = new Object();
    private static final int value = 1; //shared resource

    public static void main (String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start(); //t0
        new Thread(runnable).start(); //t1
    }

    static class PrintRunnable implements Runnable {
        public void run() {
            synchronized (monitor) {
                while(value <= 10) {
                    System.out.println(Thread.currentThread(),getName() + ":" + value++);
                    monitor.notifyAll(); //notify threads with the shared resource
                    try {
                        monitor.wait(); //use the resource and go into waiting state
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
} 
```

## 9. How do threads communicate each other?
wait() makes the current thread to release the lock, and this is done until a specific amount of time has passed or another thread calls the notify() or notifyAll() method for this object./
notify() wakes a single thread out of multiple threads on the current object’s monitor./
notifyAll() wakes up all the threads that are on the current object’s monitor.

## 10. What’s the difference between class lock and object lock?
Object-level lock: Every object in java has a unique lock. If a thread wants to execute the synchronized method on the given object. First, it has to get a lock-in that object. Once the thread got the lock then it is allowed to execute any synchronized method on that object. Once method execution completes,the thread automatically releases the lock. It can be used when you want non-static method or non-static block of the code should be accessed by only one thread./
Class level lock: Every class in Java has a unique lock. If a thread wants to execute a static synchronized method, then the thread requires a class level lock. Once a thread got the class level lock, then it is allowed to execute any static synchronized method of that class. Once method execution completes automatically thread releases the lock. It can be used when we want to prevent multiple threads to enter the synchronized block in any of all available instances on runtime

## 11. What is join() method?
If main thread calls t.join(), then main thread will stop and wait for thread t to complete its task, and main thread will continue after thread t finishes its task.

## 12. what is yield() method?
A yield() method is a static method of Thread class and it can stop the currently executing thread and will give a chance to other waiting threads of the same priority. If in case there are no waiting threads or if all the waiting threads have low priority then the same thread will continue its execution.

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
A thread pool is a software design pattern for achieving concurrency of execution in a computer program. A thread pool maintains multiple threads waiting for tasks to be allocated for concurrent execution by the supervising program./
There are fixedThreadPool, CachedThreadPool and SingleThreadPool./
TaskQueue stores the tasks and will assign them to each thread according to the order.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
Library: java.util.concurrent/
Interface: ExecutorService/
Implementation class: ThreadPoolExecutor/
## 15. How to submit a task to ThreadPool?
es.submit(new Task("name"))

## 16. What is the advantage of ThreadPool?
It offers better performance and resource management compared to creating single threads directly. Thread pools control the number of concurrent tasks, reduce the overhead of thread creation and destruction, and improve performance. Moreover, thread pools allow task queuing for pending execution, automatically manage thread lifecycles, and provide more flexible error handling mechanisms.

## 17. Difference between shutdown() and shutdownNow() methods of executor?
The shutdown() method will allow previously submitted tasks to execute before terminating, while the shutdownNow() method prevents waiting tasks from starting and attempts to stop currently executing tasks.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
It's a set of classes within the java.util.concurrent.atomic package that provide thread-safe operations on single variables, allowing for atomic operations (operations that execute completely without interruption from other threads) on primitive data./
There're AtomicInteger, AtomicLong, AtomicBoolean. /
```
public class AtomicDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main (String[] args) {
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }
}
```

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
It's the thread-safe version of Collection./
Examples: List, Map, Set, Queue, Deque

## 20. What kind of locks do you know? What is the advantage of each lock?
ReentrantLock: 一个线程可以多次获取同一个锁/
ReadWriteLock: 只允许一个线程写入，没有写入时多个线程允许同时读（提高性能）/
StampedLock: 读的过程中允许写/

## 21. What is future and completableFuture? List some main methods of ComplertableFuture.
Future is the placeholders for a result of an operation that hasn't finished yet. Once the operation finishes, the Future will contain that result.\
CompletableFuture is futures that also allow you to string tasks together in a chain. You can use them to tell some worker thread to "go do some task X, and when you're done, go do this other thing using the result of X". Using CompletableFutures, we can do something with the result of the operation without actually blocking a thread to wait for the result. \
Examples of CompletableFuture: thenAccept(), exceptionally(), thenApplyAsync(), anyof(), allof()

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
```
public class OddEventPrinter {
    private static final Object monitor = new Object();
    private static final int value = 1; //shared resource

    public static void main (String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start(); //t0
        new Thread(runnable).start(); //t1
    }

    static class PrintRunnable implements Runnable {
        public void run() {
            synchronized (monitor) {
                while(value <= 10) {
                    System.out.println(Thread.currentThread(),getName() + ":" + value++);
                    monitor.notifyAll(); //notify threads with the shared resource
                    try {
                        monitor.wait(); //use the resource and go into waiting state
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
} 
```

```
public class OddEventPrinter {
    private static final Object monitor = new Object();
    private static final int value = 1; //shared resource

    public static void main (String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start(); //t0
        new Thread(runnable).start(); //t1
    }
    static class PrintRunnable implements Runnable {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        @Override
        public void run() {
            lock.lock();
            try {
                while (value <= 10) {
                    System.out.println(Thread.currentThread().getName() + ":" + value++);
                    condition.signalAll();
                    try {
                        condition.await();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
```

## 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
```
public class OddEventPrinter {
    private static final Object monitor = new Object();
    private static final int value = 1; //shared resource

    public static void main (String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start(); //t0
        new Thread(runnable).start(); //t1
        new Thread(runnable).start(); //t3
    }

   private static synchronized void printNumber() {
    int count = 10;
    while(count-- > 0) {
        System.out.println(Thread.currentThread().getName() + ": " + n++);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    printNumber1.class.notifyAll();
   }
} 
```
## 25
1.  Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
```
public class AsyncSumProduct {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        // CompletableFuture to calculate the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // CompletableFuture to calculate the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // Combine both results asynchronously and print them
        sumFuture.thenAcceptBoth(productFuture, (sum, product) -> {
            System.out.println("Sum: " + sum);
            System.out.println("Product: " + product);
        }).join();  // Ensures the main thread waits for the completion
    }
}
```

