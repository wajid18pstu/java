/*
Lab01 (b)
Suppose, you are given two input strings: A text string, T, and a pattern string, P. Write a
program to implement Naive Pattern Matching Algorithm. The program should search for all
occurrences of the pattern P in the text T and print the starting indices of each match.
*/

/*
SAMPLE INPUT (text T on the first line, pattern P on the second line):
ABABDABACDABABCABAB
ABAB

SAMPLE OUTPUT:
Pattern found at indices: 0 10 15
*/

#include <iostream>
#include <string>
#include <vector>
using namespace std;

// Naive pattern matching: returns all starting indices of P in T
vector<int> naiveSearch(const string& T, const string& P) {
    vector<int> res;
    int n = T.size(), m = P.size();
    for (int i = 0; i + m <= n; i++) {
        int j = 0;
        while (j < m && T[i + j] == P[j]) j++;
        if (j == m) res.push_back(i);
    }
    return res;
}

int main() {
    string T, P;
    getline(cin, T);
    getline(cin, P);
    vector<int> r = naiveSearch(T, P);
    if (r.empty()) cout << "Pattern not found\n";
    else {
        cout << "Pattern found at indices:";
        for (int x : r) cout << " " << x;
        cout << "\n";
    }
    return 0;
}
