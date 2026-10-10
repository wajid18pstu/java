import java.util.Scanner;

/*
Write a Java program to find a specified element in a given array of elements using Binary
Search. Input need to take from keyboard. Don't use any built in method.
Input : 1, 5, 6, 7, 8, 11
Output: 11 is at index 5
*/
public class BinarySearchProgram {

    // Returns the index of key in the sorted array, or -1 if it is not present
    static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return mid;              // found
            } else if (arr[mid] < key) {
                low = mid + 1;           // search the right half
            } else {
                high = mid - 1;          // search the left half
            }
        }
        return -1;                       // not found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements in ascending order:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Binary search only works on a sorted array, so check that first
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("Error: the array must be sorted in ascending order.");
                return;
            }
        }

        System.out.print("Enter the element to search: ");
        int key = sc.nextInt();

        int index = binarySearch(arr, key);

        if (index != -1) {
            System.out.println(key + " is at index " + index);
        } else {
            System.out.println(key + " is not found in the array");
        }
    }
}
