// 1. Implement Insertion sort algorithm.
#include <iostream>
#include <vector>
using namespace std;

void insertionSort(vector<int>& a) {
    for (size_t i = 1; i < a.size(); i++) {
        int key = a[i];
        int j = (int)i - 1;
        while (j >= 0 && a[j] > key) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = key;
    }
}

int main() {
    int n;
    cout << "Enter number of elements: ";
    cin >> n;
    vector<int> a(n);
    cout << "Enter elements: ";
    for (int &x : a) cin >> x;
    insertionSort(a);
    cout << "Sorted array: ";
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
