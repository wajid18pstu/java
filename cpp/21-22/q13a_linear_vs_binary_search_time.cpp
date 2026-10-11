// 13. A. Write a program to compare the computational time of the linear search and the b[inary]
//        search algorithms.
//input 1000000
#include <iostream>
#include <vector>
#include <chrono>
using namespace std;
using namespace std::chrono;

int linearSearch(const vector<int>& a, int key) {
    for (size_t i = 0; i < a.size(); i++)
        if (a[i] == key) return (int)i;
    return -1;
}

int binarySearch(const vector<int>& a, int key) {
    int low = 0, high = (int)a.size() - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (a[mid] == key) return mid;
        else if (a[mid] < key) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}

int main() {
    int n;
    cout << "Enter size of sorted array: ";
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) a[i] = i * 2;   // sorted data: 0,2,4,...

    int key = a[n - 1];       // worst case for linear search (last element)
    const int REPEAT = 1000;  // repeat to get measurable time
    volatile int sink = 0;

    auto s1 = high_resolution_clock::now();
    for (int r = 0; r < REPEAT; r++) sink = linearSearch(a, key);
    auto e1 = high_resolution_clock::now();

    auto s2 = high_resolution_clock::now();
    for (int r = 0; r < REPEAT; r++) sink = binarySearch(a, key);
    auto e2 = high_resolution_clock::now();

    double lin = duration<double, micro>(e1 - s1).count() / REPEAT;
    double bin = duration<double, micro>(e2 - s2).count() / REPEAT;

    cout << "Searching key " << key << " in array of size " << n << endl;
    cout << "Linear search time (avg): " << lin << " microseconds\n";
    cout << "Binary search time (avg): " << bin << " microseconds\n";
    return 0;
}
