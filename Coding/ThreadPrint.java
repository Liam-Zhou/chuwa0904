package chuwa0904.Coding;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

//inherit or extends the thread class
//1-10
class One extends Thread{
    @Override
    public void run(){
        for(int i =1;i<=10;i++){
            
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            
        }
    }
}


//implemnting runnable interface
//11-20
class Eleven  implements Runnable{
    @Override
    public void run(){
        for(int i =11;i<=20;i++){
            
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            
        }
    }
}

//21-22
class TwentyOne  implements Runnable{
    @Override
    public void run(){
        for(int i =21;i<=22;i++){
            
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            
        }
    }
}


public class ThreadPrint {
    public static void main(String[] args) {
        One one = new One();
        Eleven eleven = new Eleven();
        TwentyOne tw = new TwentyOne();

        Thread t1 = new Thread(one);
        Thread t2 = new Thread(eleven);
        Thread t3 = new Thread(tw);

        t1.start();
        t2.start();
        t3.start();
    }
}
