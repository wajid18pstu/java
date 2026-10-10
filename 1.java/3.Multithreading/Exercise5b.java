/*
Now write a program that uses multiple threads to solve the same problem, but for the
range 1 to 100000. By using threads, your program will take less time to do the
computation when it is run on a multiprocessor computer. At the end of the program,
output the elapsed time, the integer that has the largest number of divisors, and the
number of divisors that it has. For this exercise, you should simply divide up the problem
into parts and create one thread to do each part.
*/
public class Exercise5b {

    // Each thread searches its own part of the range
    static class DivisorThread extends Thread {
        private final int start;
        private final int end;
        int maxDivisors = 0;       // result of this thread
        int numberWithMax = 0;     // result of this thread

        DivisorThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int n = start; n <= end; n++) {
                int divisors = countDivisors(n);
                if (divisors > maxDivisors) {
                    maxDivisors = divisors;
                    numberWithMax = n;
                }
            }
        }
    }

    static int countDivisors(int n) {
        int count = 1;                       // n itself
        for (int d = 1; d <= n / 2; d++) {
            if (n % d == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        final int MAX = 100000;
        int numThreads = Runtime.getRuntime().availableProcessors();
        int chunk = MAX / numThreads;

        long startTime = System.currentTimeMillis();

        // Divide the range 1..MAX into parts, one thread per part
        DivisorThread[] threads = new DivisorThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int from = i * chunk + 1;
            int to = (i == numThreads - 1) ? MAX : (i + 1) * chunk;
            threads[i] = new DivisorThread(from, to);
            threads[i].start();
        }

        // Wait for all threads, then combine their results
        int maxDivisors = 0;
        int numberWithMax = 0;
        for (DivisorThread t : threads) {
            t.join();
            if (t.maxDivisors > maxDivisors) {
                maxDivisors = t.maxDivisors;
                numberWithMax = t.numberWithMax;
            }
        }

        long elapsed = System.currentTimeMillis() - startTime;

        System.out.println("Threads used: " + numThreads);
        System.out.println("Elapsed time: " + elapsed + " milliseconds");
        System.out.println("Integer with the most divisors between 1 and " + MAX + ": " + numberWithMax);
        System.out.println("Number of divisors: " + maxDivisors);
    }
}
