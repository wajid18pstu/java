/*
5. Which integer between 1 and 10000 has the largest number of divisors, and how many
divisors does it have? Write a program to find the answers and print out the results. It is
possible that several integers in this range have the same, maximum number of divisors.
Your program only has to print out one of them.
*/
public class Exercise5a {
    static int countDivisors(int n) {
        int count = 1;                       // n itself is always a divisor
        for (int d = 1; d <= n / 2; d++) {   // no divisor other than n is larger than n/2
            if (n % d == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int maxDivisors = 0;
        int numberWithMax = 0;

        for (int n = 1; n <= 10000; n++) {
            int divisors = countDivisors(n);
            if (divisors > maxDivisors) {
                maxDivisors = divisors;
                numberWithMax = n;
            }
        }

        System.out.println("Integer with the most divisors between 1 and 10000: " + numberWithMax);
        System.out.println("Number of divisors: " + maxDivisors);
    }
}
