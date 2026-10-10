import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
6. Write a Java program that reads a list of numbers from a file and throws an exception if
any of the numbers are positive.
Sample Output:
Suppose you have a file named numbers.txt with the following content:
-5
-2
3
0 -8
When you run the program, it will throw an exception as soon as it encounters the
positive number 3 and print:
Error: The file contains a positive number: 3
If the file only contains non-positive numbers, it will print:
The file contains only non-positive numbers.
*/
class PositiveNumberException extends Exception {
    public PositiveNumberException(String message) {
        super(message);
    }
}

public class Exercise6 {
    static void checkNumbers(String fileName) throws IOException, PositiveNumberException {
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                int n = Integer.parseInt(sc.next());
                if (n > 0) {
                    throw new PositiveNumberException("The file contains a positive number: " + n);
                }
            }
        }
        System.out.println("The file contains only non-positive numbers.");
    }

    public static void main(String[] args) {
        try {
            checkNumbers("numbers.txt");
        } catch (PositiveNumberException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file 'numbers.txt' was not found.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
