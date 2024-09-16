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
