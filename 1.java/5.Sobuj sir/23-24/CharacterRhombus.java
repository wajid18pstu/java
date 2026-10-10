import java.util.Scanner;

/*
Write a Java program to display the following character rhombus structure.

Test Data
Input the number: 7
Expected Output :
      A
     ABA
    ABCBA
   ABCDCBA
  ABCDEDCBA
 ABCDEFEDCBA
ABCDEFGFEDCBA
 ABCDEFEDCBA
  ABCDEDCBA
   ABCDCBA
    ABCBA
     ABA
      A
*/
public class CharacterRhombus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number: ");
        int n = sc.nextInt();

        // 2n - 1 rows in total: the widest row is in the middle
        for (int row = 1; row <= 2 * n - 1; row++) {
            int size = (row <= n) ? row : 2 * n - row;   // highest letter in this row

            // leading spaces to centre the row
            for (int s = 0; s < n - size; s++) {
                System.out.print(" ");
            }

            // letters A up to the highest letter
            for (int c = 0; c < size; c++) {
                System.out.print((char) ('A' + c));
            }

            // letters back down, ending at A
            for (int c = size - 2; c >= 0; c--) {
                System.out.print((char) ('A' + c));
            }

            System.out.println();
        }
    }
}
