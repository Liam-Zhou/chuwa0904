package chuwa0904.Coding;

import java.lang.classfile.ClassFile.DebugElementsOption;

class DeadLockExample{
    public void method1() {
        synchronized (String.class) {  // Lock String.class first
            System.out.println("Acquired lock on String.class object");
    
            synchronized (Integer.class) {  // Then lock Integer.class
                System.out.println("Acquired lock on Integer.class object");
            }
        }
    }
    
    public void method2() {
        synchronized (Integer.class) {  // Lock String.class first (same order as method1)
            System.out.println("Acquired lock on String.class object");
    
            synchronized (String.class) {  // Then lock Integer.class
                System.out.println("Acquired lock on Integer.class object");
            }
        }
    }
    
}
public class DeadLock {
    public static void main(String[] args) {
        DeadLockExample deadLockExample = new DeadLockExample();
        Thread t1 = new Thread(() -> deadLockExample.method1(), "Thread 1");
        Thread t2 = new Thread(() -> deadLockExample.method2(), "Thread 2");
        t1.start();
        t2.start();
    }
    
}
