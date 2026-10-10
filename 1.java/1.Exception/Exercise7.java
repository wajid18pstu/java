import java.io.*;

/*
7. Write a Java program that reads a file and throws an exception if the file is empty. If the
file has no content, the program will throw a custom exception indicating that the file is
empty.
Sample Output:

If the file sample.txt is empty, the program will output:
Error: The file is empty: sample.txt

If the file is not empty, it will print the content of the file line by line.

If the file does not exist, the output will be:
Error: The file 'sample.txt' was not found.
Example Files:
1. Non-empty file (sample.txt):
Hello, world!
This is a test file.
Output:
Hello, world!
This is a test file.
File read successfully!
2. Empty file (empty.txt): (File has zero length, no content)
Output:
Error: The file is empty: empty.txt
3. File not found (nonexistent.txt):
Output:
Error: The file 'nonexistent.txt' was not found.
*/
class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}

public class Exercise7 {
    static void readFile(String fileName) throws IOException, EmptyFileException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("The file '" + fileName + "' was not found.");
        }
        if (file.length() == 0) {
            throw new EmptyFileException("The file is empty: " + fileName);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println("File read successfully!");
    }

    public static void main(String[] args) {
        String[] files = {"sample.txt", "empty.txt", "nonexistent.txt"};
        for (String f : files) {
            try {
                readFile(f);
            } catch (EmptyFileException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
