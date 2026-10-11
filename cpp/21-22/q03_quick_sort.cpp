// 3. Implement Quick sort algorithm.
#include <iostream>
#include <vector>
using namespace std;

int partition(vector<int>& a, int low, int high) {
    int pivot = a[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (a[j] < pivot) {
            i++;
            swap(a[i], a[j]);
        }
    }
    swap(a[i + 1], a[high]);
    return i + 1;
}

void quickSort(vector<int>& a, int low, int high) {
    if (low < high) {
        int p = partition(a, low, high);
        quickSort(a, low, p - 1);
        quickSort(a, p + 1, high);
    }
}

int main() {
    int n;
    cout << "Enter number of elements: ";
    cin >> n;
    vector<int> a(n);
    cout << "Enter elements: ";
    for (int &x : a) cin >> x;
    quickSort(a, 0, n - 1);
    cout << "Sorted array: ";
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
