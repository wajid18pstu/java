/*
Write a Java program to create and display unique five-digit number using 1, 2, 3, 4,5, 6,. Also count
how many five-digit unique numbers are there.
*/
public class UniqueFiveDigitNumbers {
    public static void main(String[] args) {
        int count = 0;

        // One loop for each of the five digit positions, each digit taken from 1 to 6
        for (int a = 1; a <= 6; a++) {
            for (int b = 1; b <= 6; b++) {
                for (int c = 1; c <= 6; c++) {
                    for (int d = 1; d <= 6; d++) {
                        for (int e = 1; e <= 6; e++) {

                            // "Unique" means no digit is repeated inside the number
                            if (a != b && a != c && a != d && a != e
                                    && b != c && b != d && b != e
                                    && c != d && c != e
                                    && d != e) {

                                int number = a * 10000 + b * 1000 + c * 100 + d * 10 + e;
                                System.out.print(number + " ");
                                count++;

                                if (count % 10 == 0) {      // 10 numbers per line
                                    System.out.println();
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Total unique five-digit numbers: " + count);
    }
}
