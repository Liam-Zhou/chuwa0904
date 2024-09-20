
### write a thread safe singleton class
```
package chuwa0904.Coding;

public class ThreadSafeSingleton {
    private static volatile  ThreadSafeSingleton instance;//volatile ensure all thread sees the instance latest val
    private static Object mutex = new Object();

    private ThreadSafeSingleton(){}


    //public static synchronize ThreadSafeSingleton getInstance(): exensive thread safe, decrease performancy 
    public static ThreadSafeSingleton getInstance(){
        ThreadSafeSingleton result = instance;
        if(result == null){

            //synchronized block: synchronization will be based on the current object
            //make sure one thread creates the instance
            //sychronized(this)
            synchronized(mutex){
                result = instance;

                //double lock checking: make sure no other thread create the instance while curr thread waiting to enter
                //avoid unnecessary synchronizaion
                if(result == null){
                    instance = result = new ThreadSafeSingleton();
                }
            }
        }
        return result;
    }


}
```
### How to create a new thread(Please also consider Thread Pool approach)?

```
//inherit or extends the thread class
class PrintNumber extends Thread{
    @Override
    public void run(){
        for(int i =1;i<100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            }
        }
    }
}

//implemnting runnable interface
class OddNumber implements Runnable{
    @Override
    public void run(){
        for(int i =1;i<100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            }
        }
    }
}


// implmenting callable interface with future  
//can have retun type, return type can be any type, can use throw excp
class NumThread implements Callable{
    @Override
    
    public Object call() throws Exception{
        int sum=0;
        for(int i=1;i<=100;i++){
            System.out.println(Thread.currentThread().getName()+"--->"+i);
            sum +=i;
        }
        return sum;
    }
}

//thread pool: executor
//manage the threads in the pool 
//improve efficiency (thread ready in pool)
//save resource to perform other tasks



public class CreateThread {
    public static void main(String[] args) {

    //  PrintNumber printNumber = new PrintNumber();
    //  Thread t0 = new Thread(printNumber);
    //  t0.start();

    //  OddNumber oddNumber = new OddNumber();
    //  Thread t1 = new Thread(oddNumber);
    //  t1.start();
    
    //  NumThread numThread = new NumThread();
    //  FutureTask futureTask = new FutureTask(numThread);
    //  Thread t3 = new Thread(futureTask);
    //  t3.start();  

    //create thread pool
    ExecutorService service = Executors.newFixedThreadPool(10);
    ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
    //set up max pool size
    service1.setMaximumPoolSize(50);
    //execute the thread 
    service.execute(new OddNumber()); //runnable
    service.submit(new NumThread()); //callable

    //disconnect the pool
    service.shutdown();


    }
```
### Difference between Runnable and Callable?

- Runnable: void(no return type), cannot throw exception

- Callable: can have retun type, return type can be any type, can use throw excp

### What is the difference between t.start() and t.run()?

- start(): start a new thred then call run() method

-run(): just run method in same thread 

### Which way of creating threads is better: Thread class or Runnable interface

- Runnable interface better: can extend other classes while implementing Runnable interface

```
class Sound {
    public void makeSound(){
        System.out.println("make the sound");
    }
}

//implemnting runnable interface
class OddNumber extends Sound implements Runnable{
    @Override
    public void run(){
        for(int i =1;i<100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            }
        }
    }
}

public static void main(String[] args) {

    OddNumber oddNumber = new OddNumber();
     oddNumber.makeSound();
     Thread t1 = new Thread(oddNumber);
     t1.start();
}
```
### What are the thread statuses?

***before jdk 1.5:***
new->runnable->running->blocked->dead


1. new: just created a new tread, not call the start() yet
```
Thread t1 = new Thread(oddNumber);
```
2. runnable: thread ready to run 
```
enter sysn lock, notify(), notifyAll(),resume()
```
3. blocked:  thread is waiting to enter a syn block/method
```
synchronzied block
```
4. waiting: thread is waiting for another thread to perform a action
```
 Object.wait(): cause curr thread to wait
 sleep(),join()
 ```
5. timed waiting: The thread is waiting for a specified amount of time
```
Thread.sleep();
```
6. terminated: The thread has finished executing, either because it completed its task or was interrupted.
```
stop()
```

### Demonstrate deadlock and how to resolve it in Java code
-  Thread 1 locks String.class and waits for Integer.class,
Thread 2 locks Integer.class and waits for String.class,
```
public void method1() {
    synchronized (String.class) {
        System.out.println("Acquired lock on String.class object");

        synchronized (Integer.class) {
            System.out.println("Acquired lock on Integer.class object");
        }
    }
}

public void method2() {
    synchronized (Integer.class) {
        System.out.println("Acquired lock on Integer.class object");

        synchronized (String.class) {
            System.out.println("Acquired lock on String.class object");
        }
    }
}
```

- By acquiring locks in the same order (first String.class, then Integer.class), you prevent threads from holding one lock and waiting on another in the opposite order, thus avoiding deadlock.
```
public void method1() {
    synchronized (String.class) {  // Lock String.class first
        System.out.println("Acquired lock on String.class object");

        synchronized (Integer.class) {  // Then lock Integer.class
            System.out.println("Acquired lock on Integer.class object");
        }
    }
}

public void method2() {
    synchronized (String.class) {  // Lock String.class first (same order as method1)
        System.out.println("Acquired lock on String.class object");

        synchronized (Integer.class) {  // Then lock Integer.class
            System.out.println("Acquired lock on Integer.class object");
        }
    }
}

```


### How do threads communicate each other?
- using method: wait(),notify(),notifyAll()

### What Õs the difference between class lock and object lock?
***class lock***
- can be used to make static data thread-safe
```
package chuwa0904.Coding;

class ClassLock extends Thread{
    public void ClassLockMethod(){
        synchronized(ClassLock.class){
            System.out.println("class lock example");
        }
    }

    @Override
    public void run(){
        ClassLockMethod();
    }
}

public class Lock {
    public static void main(String[] args) {
        ClassLock cl = new ClassLock();
        Thread t1 =  new Thread(cl);
        t1.start();
    }

}

```
***Object lock***
- using the keyword ‘synchronized’ and can be used to protect non-static data

```
class ObjLock extends Thread{
    public void ObjLockMethod(){
        synchronized(this){
            System.out.println("object lock example");
        }
    }

    @Override
    public void run(){
        ObjLockMethod();
    }
}
```

### What is join() method?
- join() method is generally used to pause the execution of a current thread unless and until the specified thread on which join is called is dead or completed.


### what is yield() method
```
Thread.yield();  // Current thread suggests giving up CPU for other threads

```


### What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

- A ThreadPool is a collection of threads that are already created and ready to use. Instead of making a new thread every time there's a task, the ThreadPool reuses these existing threads to do the work.

1. Fixed ThreadPool
```
ExecutorService service = Executors.newFixedThreadPool(3); // 3 threads in the pool
```
2. Cache pool
```
ExecutorService service = Executors.newCachedThreadPool();
```

3. Single ThreadPool

```
ExecutorService service = Executors.newSingleThreadExe
```

4. Scheduled ThreadPool
```
ScheduledExecutorService service = Executors.newScheduledThreadPool(2); // 2 threads in the pool
```




### Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

java.util.concurrent library

```
import java.util.concurrent.ThreadPoolExecutor;
```
Interface ExecutorService provides methods for threadpool
```
    //create thread pool
    ExecutorService service = Executors.newFixedThreadPool(10);
    ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
    //set up max pool size
    service1.setMaximumPoolSize(50);
    //execute the thread 
    service.execute(new OddNumber()); //runnable
    service.submit(new NumThread()); //callable

    //disconnect the pool
    service.shutdown();
```

### How to submit a task to ThreadPool?

thread pool can accept runnable and callable interface
```
//create thread pool
    ExecutorService service = Executors.newFixedThreadPool(10);
    ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
    //set up max pool size
    service1.setMaximumPoolSize(50);
    //execute the thread 
    service.execute(new OddNumber()); //runnable
    service.submit(new NumThread()); //callable

    //disconnect the pool
    service.shutdown();
```

### What is the advantage of ThreadPool?

-  Saves the time and resources needed to create and destroy threads.
-  Limits the number of active threads, preventing system overload

### Difference between shutdown() and shutdownNow() methods of executor
-  shutdown() will just tell the executor service that it can't accept new tasks, but the already submitted tasks continue to run

- with shutdownNow() running task is interrupted,will attempt to stop all executing tasks right away

### What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

- Java provides a java.util.concurrent.atomic package in which atomic classes are defined. The atomic classes provide a lock-free and thread-safe environment or programming on a single variable. It also supports atomic operations. All the atomic classes have the get() and set() methods that work on the volatile variable. The method works the same as read and writes on volatile variables.

- Atomic means only one thread accesses the variable (static type). Atomic is thread-safe, but it is slow. Nonatomic means multiple threads access the variable (dynamic type). Nonatomic is thread-unsafe, but it is fast.

```
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {  
    private AtomicInteger count = new AtomicInteger(0);

    public void incrementCount() {  
        count.set(1);  // Atomic operation
    }

    public int getCount() {
        return count.get();  // Safely retrieve the value
    }
}
```

### What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
- Concurrent collections are thread-safe data structures that allow multiple threads to access and modify them simultaneously without causing data issues.


1. ConcurrentHashMap: A thread-safe version of HashMap
2. CopyOnWriteArrayList: A thread-safe version of ArrayList
3. ConcurrentLinkedQueue: A thread-safe version of a queue 
4. BlockingQueue 
5. ConcurrentSkipListMap: A thread-safe sorted map.

### What kind of locks do you know? What is the advantage of each lock?
1. ReentrantLock : Allows multiple locking
2. ReadLock: Multiple readers allowed
3. WriteLock: Ensures that only one thread can write at a time,No thread can read while a thread is writing



### What is future and completableFuture? List some main methods of ComplertableFuture.

- Future is a simple representation of an asynchronous task. It is a type that holds the result from a task that may not have finished yet but may be some time in future
```
  Future<ResultType> future = executorService.submit(Callable<ResultType> task);
```


-CompletableFuture

```
CompletableFuture<ResultType> future = CompletableFuture.supplyAsync(Supplier<ResultType> supplier);

```
1. supplyAsync(): It complete its job asynchronously. 
2. thenApply():The method accepts function as an arguments. It returns a new CompletableStage when this stage completes normally.
3. join(): the method returns the result value when complete.

### Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)1. One solution use synchronized and wait notify 2. One solution use ReentrantLock and await, signal
code in NumberPrinterTest.java NumberPrinterWithLockTest.java

### 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random

code in ThreadPrint.java

chuwa0904.Coding.ThreadPrint 
Thread-1----> 1
Thread-1----> 2
Thread-3----> 21
Thread-2----> 11
Thread-1----> 3
Thread-1----> 4
Thread-1----> 5
Thread-1----> 6
Thread-1----> 7
Thread-1----> 8
Thread-1----> 9
Thread-1----> 10
Thread-3----> 22
Thread-2----> 12
Thread-2----> 13
Thread-2----> 14
Thread-2----> 15
Thread-2----> 16
Thread-2----> 17
Thread-2----> 18
Thread-2----> 19
Thread-2----> 20

### Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.

code in CalSum.java

### Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing 

code in DataFetcher.java

