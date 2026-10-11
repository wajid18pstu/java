// 2. Implement Merge-sort algorithm.
#include <iostream>
#include <vector>
using namespace std;

void merge(vector<int>& a, int l, int m, int r) {
    vector<int> L(a.begin() + l, a.begin() + m + 1);
    vector<int> R(a.begin() + m + 1, a.begin() + r + 1);
    size_t i = 0, j = 0;
    int k = l;
    while (i < L.size() && j < R.size())
        a[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
    while (i < L.size()) a[k++] = L[i++];
    while (j < R.size()) a[k++] = R[j++];
}

void mergeSort(vector<int>& a, int l, int r) {
    if (l >= r) return;
    int m = l + (r - l) / 2;
    mergeSort(a, l, m);
    mergeSort(a, m + 1, r);
    merge(a, l, m, r);
}

int main() {
    int n;
    cout << "Enter number of elements: ";
    cin >> n;
    vector<int> a(n);
    cout << "Enter elements: ";
    for (int &x : a) cin >> x;
    mergeSort(a, 0, n - 1);
    cout << "Sorted array: ";
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
