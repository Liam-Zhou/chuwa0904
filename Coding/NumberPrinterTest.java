package chuwa0904.Coding;
class NumberPrinter {
    private boolean isOdd = true;

    public synchronized void printOdd() throws InterruptedException {
        for (int i = 1; i <= 9; i += 2) {
            while (!isOdd) {
                wait(); // Wait if it's not the odd thread's turn
            }
            System.out.println(Thread.currentThread().getName()+"----> "+i);
            isOdd = false;
            notify(); // Notify the other thread
        }
    }

    public synchronized void printEven() throws InterruptedException {
        for (int i = 2; i <= 10; i += 2) {
            while (isOdd) {
                wait(); // Wait if it's not the even thread's turn
            }
            System.out.println(Thread.currentThread().getName()+"----> "+i);
            isOdd = true;
            notify(); // Notify the other thread
        }
    }
}

 public class NumberPrinterTest {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();

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
