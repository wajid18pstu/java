import java.util.Scanner;

/*
Write a Java program to display the number rhombus structure.
Test Data
Input the number: 7
Expected Output :
      1
     212
    32123
   4321234
  543212345
 65432123456
7654321234567
 65432123456
  543212345
   4321234
    32123
     212
      1
*/
public class NumberRhombus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number: ");
        int n = sc.nextInt();

        // 2n - 1 rows in total: the widest row is in the middle
        for (int row = 1; row <= 2 * n - 1; row++) {
            int size = (row <= n) ? row : 2 * n - row;   // largest number in this row

            // leading spaces to centre the row
            for (int s = 0; s < n - size; s++) {
                System.out.print(" ");
            }

            // numbers counting down from size to 1
            for (int c = size; c >= 1; c--) {
                System.out.print(c);
            }

            // numbers counting up from 2 to size
            for (int c = 2; c <= size; c++) {
                System.out.print(c);
            }

            System.out.println();
        }
    }
}
