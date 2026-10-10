/*
Write a Java recursive method to check if a given array is sorted in ascending order,
*/
public class SortedArrayCheck {

    // Compares the element at 'index' with the next one, then recurses on the rest
    static boolean isSorted(int[] arr, int index) {
        // Base case: reached the last element (or the array has 0 or 1 elements)
        if (index >= arr.length - 1) {
            return true;
        }
        // If a pair is out of order, the array is not sorted
        if (arr[index] > arr[index + 1]) {
            return false;
        }
        // Otherwise check the remaining part of the array
        return isSorted(arr, index + 1);
    }

    public static void main(String[] args) {
        int[][] tests = {
            {1, 2, 3, 7, 6},
            {1, 3, 2, 4, 5},
            {5, 4, 3, 2, 1},
            {2, 2, 7, 9},
            {10},
            {}
        };

        for (int[] arr : tests) {
            System.out.println(java.util.Arrays.toString(arr)
                    + " sorted in ascending order? " + isSorted(arr, 0));
        }
    }
}
