import java.util.Scanner;

/*
Write a Java program to display Pascal's triangle in and also in reverse order.
Test Data
Input number of rows: 5
Expected Output :
Input number of rows: 5
    1
   1 1
  1 2 1
 1 3 3 1
1 4 6 4 1
1 4 6 4 1
 1 3 3 1
  1 2 1
   1 1
    1
*/
public class PascalTriangle {

    // Prints one row of Pascal's triangle (row index starts at 0)
    static void printRow(int row, int totalRows) {
        // leading spaces so the triangle is centred (each number takes 4 columns)
        for (int s = 0; s < 2 * (totalRows - 1 - row); s++) {
            System.out.print(" ");
        }

        int value = 1;                          // C(row, 0) is always 1
        for (int col = 0; col <= row; col++) {
            System.out.printf("%4d", value);
            value = value * (row - col) / (col + 1);   // next value: C(row, col + 1)
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number of rows: ");
        int n = sc.nextInt();

        // Pascal's triangle
        System.out.println();
        for (int row = 0; row < n; row++) {
            printRow(row, n);
        }

        // Pascal's triangle in reverse order
        System.out.println();
        for (int row = n - 1; row >= 0; row--) {
            printRow(row, n);
        }
    }
}
