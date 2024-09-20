package chuwa0904.Coding;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NumberPrinterWithLock{
    private boolean isOdd = true;
    private final Lock lock = new ReentrantLock();
    private final Condition oddCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();

    public void printOdd() throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= 9; i += 2) {
                while (!isOdd) {
                    oddCondition.await(); // Wait if it's not the odd thread's turn
                }
                System.out.println(Thread.currentThread().getName()+"----> "+i);
                isOdd = false;
                evenCondition.signal(); // Signal the even thread
            }
        } finally {
            lock.unlock();
        }
    }

    public void printEven() throws InterruptedException {
        lock.lock();
        try {
            for (int i = 2; i <= 10; i += 2) {
                while (isOdd) {
                    evenCondition.await(); // Wait if it's not the even thread's turn
                }
                System.out.println(Thread.currentThread().getName()+"----> "+i);
                isOdd = true;
                oddCondition.signal(); // Signal the odd thread
            }
        } finally {
            lock.unlock();
        }
    }
}
public class NumberPrinterWithLockTest {
    public static void main(String[] args) {
        NumberPrinterWithLock printer = new NumberPrinterWithLock();

        Thread oddThread = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        oddThread.start();
        evenThread.start();
    }
}

