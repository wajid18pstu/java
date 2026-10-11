/*
Lab01 (a)
The values of array are given: 3,6,21,22,25,32,37,41,49,50,53,56,58,65,71,75. and the given
integer k is equal to 71. Implement linear search and binary search algorithms. How many
comparisons are required to find the desired number if a given integer k occurs in the sequence
in the both cases?
*/

/*
SAMPLE INPUT:
71

SAMPLE OUTPUT:
Linear Search: found at index 14 (position 15) with 15 comparisons
Binary Search: found at index 14 (position 15) with 4 comparisons
*/

#include <iostream>
#include <vector>
using namespace std;

// Linear search: returns index or -1, comparisons counted in 'cmp'
int linearSearch(const vector<int>& a, int k, int& cmp) {
    cmp = 0;
    for (int i = 0; i < (int)a.size(); i++) {
        cmp++;
        if (a[i] == k) return i;
    }
    return -1;
}

// Binary search (array must be sorted): returns index or -1
int binarySearch(const vector<int>& a, int k, int& cmp) {
    cmp = 0;
    int lo = 0, hi = (int)a.size() - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        cmp++;
        if (a[mid] == k) return mid;
        else if (a[mid] < k) lo = mid + 1;
        else hi = mid - 1;
    }
    return -1;
}

int main() {
    vector<int> a = {3, 6, 21, 22, 25, 32, 37, 41, 49, 50, 53, 56, 58, 65, 71, 75};
    int k;
    cin >> k;

    int cmp;
    int idx = linearSearch(a, k, cmp);
    if (idx != -1)
        cout << "Linear Search: found at index " << idx << " (position " << idx + 1
             << ") with " << cmp << " comparisons\n";
    else
        cout << "Linear Search: " << k << " not found (" << cmp << " comparisons)\n";

    idx = binarySearch(a, k, cmp);
    if (idx != -1)
        cout << "Binary Search: found at index " << idx << " (position " << idx + 1
             << ") with " << cmp << " comparisons\n";
    else
        cout << "Binary Search: " << k << " not found (" << cmp << " comparisons)\n";
    return 0;
}
