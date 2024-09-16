package chuwa0904.Coding;

class ClassLock extends Thread{
    public void  ClassLockMethod(){
        synchronized(ClassLock.class){
            System.out.println("class lock example");
        }
    }

    @Override
    public void run(){
        ClassLockMethod();
    }
}

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

public class Lock {
    public static void main(String[] args) {
        ClassLock cl = new ClassLock();
        Thread t1 =  new Thread(cl);
        t1.start();
    }

}
