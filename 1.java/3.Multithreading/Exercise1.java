/*
1. Write a Java program to perform a runnable interface, take two threads t1 and t2 and
fetch the names of the thread using getName() method.
Expected output:
Thread names are following:
Thread A
Thread B
*/
public class Exercise1 implements Runnable {
    @Override
    public void run() {
        // Fetch the name of the thread that is running this code
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Exercise1 task = new Exercise1();

        Thread t1 = new Thread(task, "Thread A");
        Thread t2 = new Thread(task, "Thread B");

        System.out.println("Thread names are following:");

        t1.start();
        t1.join();   // wait so the output order is always A then B

        t2.start();
        t2.join();
    }
}
