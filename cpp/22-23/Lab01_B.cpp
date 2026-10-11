// Lab01
// (B) Suppose, you are given two input strings: A text string, T, and a pattern string, P. Write a
// program to implement Naive Pattern Matching Algorithm. The program should search for all
// occurrences of the pattern in the text and print the starting indices of each match.
//
// Sample Input:
// ABABDABACDABABCABAB
// ABAB
//
// Sample Output:
// Pattern found at index 0
// Pattern found at index 10
// Pattern found at index 15

#include <iostream>
#include <string>
using namespace std;

int main() {
    string T, P;
    getline(cin, T);
    getline(cin, P);

    int n = T.size(), m = P.size();
    bool found = false;

    for (int i = 0; i + m <= n; i++) {
        int j = 0;
        while (j < m && T[i + j] == P[j]) j++;
        if (j == m) {
            cout << "Pattern found at index " << i << endl;
            found = true;
        }
    }
    if (!found) cout << "Pattern not found" << endl;
    return 0;
}
