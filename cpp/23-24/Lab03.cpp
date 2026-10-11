/*
Lab03
Design and implement a Hybrid Merge-Insertion Sort Algorithm with the following
specifications:
Accept an array of n integers, where n > 64.
    1. Recursively divide the array using the Merge Sort strategy until each subarray size
       becomes n/8 (or smaller).
    2. Instead of further recursive division, apply Insertion Sort on each resulting subarray.
    3. Merge the sorted subarrays using the standard Merge (Conquer) process until the entire
       array is sorted.
    4. Display:
        > The original array.
        > The subarrays obtained after division.
        > The sorted subarrays after applying Insertion Sort.
        > The final sorted array after merging.

Tasks
(a) Draw the recursion/division tree for the input size n= 64
(b) Implement the Hybrid Merge-Insertion Sort algorithm in C/C++/Java/Python.
(c) Count and display:
    • Number of recursive divisions performed.
    • Number of comparisons during Insertion Sort.
    • Number of merge operations performed.
(d) Analyze the time complexity of the algorithm and compare it with traditional Merge Sort.
Explain why Insertion Sort is beneficial for small subarrays.
*/

/*
SAMPLE INPUT (n, then n integers; here n = 64):
64
45 12 78 23 56 89 34 67 90 11 43 76 21 54 87 32 65 98 10 41 74 29 52 85 38 61 94 17 40 73 26 59 82 35 68 91 14 47 70 23 56 79 32 65 88 41 74 97 20 53 86 39 62 95 18 51 84 27 60 93 16 49 72 25
(The program works for any n; threshold = n/8, at least 1.)

SAMPLE OUTPUT (division tree part, for n = 64):
Division tree (n = 64, threshold = 8):
[0..63] size=64
|-- [0..31] size=32
|   |-- [0..15] size=16
|   |   |-- [0..7] size=8  <-- leaf (Insertion Sort)
|   |   `-- [8..15] size=8  <-- leaf (Insertion Sort)
...
Recursive divisions : 7
Merge operations    : 7
(comparisons in Insertion Sort depend on the data)
*/

/*
(a) RECURSION / DIVISION TREE for n = 64 (threshold = n/8 = 8):

Level 0:                              [64]
                           /                         \
Level 1:              [32]                             [32]
                    /      \                         /      \
Level 2:        [16]        [16]                 [16]        [16]
               /   \       /   \                /   \       /   \
Level 3:     [8]  [8]    [8]  [8]             [8]  [8]    [8]  [8]
              ^ leaves: size 8 = n/8 -> stop dividing, apply Insertion Sort

Internal nodes (divisions) = 1 + 2 + 4 = 7
Leaf subarrays             = 8
Merge operations           = 7 (same as number of divisions)
*/

/*
(d) ANALYSIS
Traditional Merge Sort: T(n) = 2T(n/2) + O(n)  ->  O(n log n) in all cases.

Hybrid: dividing stops at size k = n/8, giving n/k = 8 subarrays.
  - Insertion Sort on 8 subarrays of size k: 8 * O(k^2) = O(n^2/8) = O(n^2) in the
    worst case in general, but for a FIXED ratio (k = n/8) it is a constant-factor
    trade. If k is a small constant (e.g. 8-16 elements), the cost is O(n*k) = O(n).
  - Merging: log2(n/k) = log2(8) = 3 levels, each O(n) -> O(3n) = O(n).
  - Overall for k = n/8: O(n^2/8 + n); for a constant threshold k: O(n*k + n log(n/k)),
    which stays O(n log n).
  - Space: O(n) auxiliary for merging (same as Merge Sort).
  - Stable: yes (both Insertion Sort and standard merge are stable).

Why Insertion Sort is beneficial for small subarrays:
  1. Low overhead: no recursion, no function-call stack, no temporary array.
  2. For small k, k^2 operations can be cheaper than the constant factors of
     recursive Merge Sort (call overhead + copying to/from temp arrays).
  3. It is adaptive: nearly sorted data costs close to O(k).
  4. It works in-place and is cache-friendly on small contiguous blocks.
  5. It removes the lowest, most call-heavy levels of the recursion tree.
This is why practical libraries (e.g. TimSort, introsort) switch to insertion
sort for small partitions.
*/

#include <iostream>
#include <vector>
#include <string>
using namespace std;

long long divisions = 0, insertionComparisons = 0, mergeOps = 0;
int threshold;
vector<pair<int,int>> leaves;   // (left, right) ranges of leaf subarrays

void printRange(const vector<int>& a, int l, int r) {
    cout << "[ ";
    for (int i = l; i <= r; i++) cout << a[i] << " ";
    cout << "]";
}

// Step 1: divide recursively (Merge Sort strategy) until size <= threshold.
// Also prints the division tree as it is built.
void divide(int l, int r, const string& prefix = "", bool isLast = true, bool isRoot = true) {
    int size = r - l + 1;
    cout << prefix << (isRoot ? "" : (isLast ? "`-- " : "|-- "))
         << "[" << l << ".." << r << "] size=" << size;
    if (size <= threshold) {
        cout << "  <-- leaf (Insertion Sort)\n";
        leaves.push_back({l, r});
        return;
    }
    cout << "\n";
    divisions++;
    int mid = (l + r) / 2;
    string childPrefix = prefix + (isRoot ? "" : (isLast ? "    " : "|   "));
    divide(l, mid, childPrefix, false, false);
    divide(mid + 1, r, childPrefix, true, false);
}

// Step 2: insertion sort on a[l..r], counting key comparisons
void insertionSort(vector<int>& a, int l, int r) {
    for (int i = l + 1; i <= r; i++) {
        int key = a[i], j = i - 1;
        while (j >= l) {
            insertionComparisons++;
            if (a[j] > key) { a[j + 1] = a[j]; j--; }
            else break;
        }
        a[j + 1] = key;
    }
}

// Standard merge of a[l..mid] and a[mid+1..r]
void mergeParts(vector<int>& a, int l, int mid, int r) {
    vector<int> tmp;
    int i = l, j = mid + 1;
    while (i <= mid && j <= r) tmp.push_back(a[i] <= a[j] ? a[i++] : a[j++]);
    while (i <= mid) tmp.push_back(a[i++]);
    while (j <= r) tmp.push_back(a[j++]);
    for (int k = 0; k < (int)tmp.size(); k++) a[l + k] = tmp[k];
    mergeOps++;
}

// Step 3: merge back following the same division structure
void mergeAll(vector<int>& a, int l, int r) {
    if (r - l + 1 <= threshold) return;
    int mid = (l + r) / 2;
    mergeAll(a, l, mid);
    mergeAll(a, mid + 1, r);
    mergeParts(a, l, mid, r);
}

int main() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    threshold = n / 8;
    if (threshold < 1) threshold = 1;

    cout << "Original array:\n";
    printRange(a, 0, n - 1);
    cout << "\n\n";

    cout << "Division tree (n = " << n << ", threshold = " << threshold << "):\n";
    divide(0, n - 1);
    cout << "\nSubarrays obtained after division (size <= " << threshold << "):\n";
    for (auto& p : leaves) { printRange(a, p.first, p.second); cout << "\n"; }

    for (auto& p : leaves) insertionSort(a, p.first, p.second);
    cout << "\nSorted subarrays after Insertion Sort:\n";
    for (auto& p : leaves) { printRange(a, p.first, p.second); cout << "\n"; }

    mergeAll(a, 0, n - 1);
    cout << "\nFinal sorted array:\n";
    printRange(a, 0, n - 1);
    cout << "\n\n";

    cout << "Number of recursive divisions        : " << divisions << "\n";
    cout << "Number of comparisons (Insertion Sort): " << insertionComparisons << "\n";
    cout << "Number of merge operations           : " << mergeOps << "\n";
    return 0;
}
