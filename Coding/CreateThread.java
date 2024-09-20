package chuwa0904.Coding;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

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

     OddNumber oddNumber = new OddNumber();
     oddNumber.makeSound();
     Thread t1 = new Thread(oddNumber);
     t1.start();
    
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
}
