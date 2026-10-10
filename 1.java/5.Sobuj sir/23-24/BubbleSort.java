import java.util.Scanner;

/*
Write a Java program to sort an array of given integers using the Bubble sorting (Ascending and
Descending) Algorithm. Input need to take from keyboard
Original Array:[7, -5, 3, 2, 1, 0, 45]
Sorted Array : [-5, 0, 1, 2, 3, 7, 45]
*/
class BubbleSort {

    static void bubbleSortAscending(int[] arr) {
        int n = arr.length;
        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < n - 1 - pass; i++) {
                if (arr[i] > arr[i + 1]) {          // smaller value should come first
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {                         // already sorted, stop early
                break;
            }
        }
    }

    static void bubbleSortDescending(int[] arr) {
        int n = arr.length;
        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < n - 1 - pass; i++) {
                if (arr[i] < arr[i + 1]) {          // larger value should come first
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    static void printArray(String label, int[] arr) {
        System.out.print(label + "[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] original = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            original[i] = sc.nextInt();
        }

        // Copies, so the original array stays unchanged between the two sorts
        int[] ascending = new int[n];
        int[] descending = new int[n];
        for (int i = 0; i < n; i++) {
            ascending[i] = original[i];
            descending[i] = original[i];
        }

        bubbleSortAscending(ascending);
        bubbleSortDescending(descending);

        System.out.println();
        printArray("Original Array : ", original);
        printArray("Sorted Array (Ascending)  : ", ascending);
        printArray("Sorted Array (Descending) : ", descending);
    }
}
