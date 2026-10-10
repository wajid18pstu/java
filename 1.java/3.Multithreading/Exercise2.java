/*
2. You need to run this following simple program with at least three threads (name those
threads as A,B,C....), and find the output.
i.
Initialize a variable sum = 0
ii.
Run a for loop for 10 times. i.e : for(int x = 0; x < 10; x++) . Add the value x to
the value sum in each iteration
iii.
Print the thread name, and the value as follows : "Thread : A - value : 45 " in
each iteration
iv.
Finally Print the sum from each thread as follows : "Thread : A - Sum : 45 "
*/
public class Exercise2 implements Runnable {
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        int sum = 0;                              // i.  each thread has its own sum

        for (int x = 0; x < 10; x++) {            // ii. loop 10 times
            sum += x;
            System.out.println("Thread : " + name + " - value : " + sum);   // iii.
        }

        System.out.println("Thread : " + name + " - Sum : " + sum);         // iv.
    }

    public static void main(String[] args) {
        Thread a = new Thread(new Exercise2(), "A");
        Thread b = new Thread(new Exercise2(), "B");
        Thread c = new Thread(new Exercise2(), "C");

        a.start();
        b.start();
        c.start();
        // Lines from A, B and C may interleave differently on each run,
        // but every thread ends with "Sum : 45".
    }
}
