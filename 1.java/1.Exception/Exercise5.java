import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
5. Write a Java program to create a method that reads a file and throws an exception if the
file is not found.
Sample Output:
Error: The file 'sample.txt' was not found.
*/
public class Exercise5 {
    static void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("The file '" + fileName + "' was not found.");
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        try {
            readFile("sample.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
