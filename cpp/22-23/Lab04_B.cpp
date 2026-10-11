// Lab04
// (B) The Tower of Hanoi is a classic recursive problem involving three rods and a number of
// disks of different sizes. The objective is to move all the disks from the source rod to the
// destination rod, obeying the following rules:
// i. Only one disk can be moved at a time.
// ii. Each move involves taking the upper disk from one of the stacks (rods) and placing it
//     on top of another stack.
// iii. No disk may be placed on top of a smaller disk.
// iv. Traditionally, the problem is solved recursively. However, in this task, you must
//     implement Tower of Hanoi using stacks and an iterative or simulation-based
//     approach, not recursion.
//
// Input: n (number of disks)
//
// Sample Input:
// 3
//
// Sample Output:
// Move disk 1 from A to C
// Move disk 2 from A to B
// Move disk 1 from C to B
// Move disk 3 from A to C
// Move disk 1 from B to A
// Move disk 2 from B to C
// Move disk 1 from A to C

#include <iostream>
#include <stack>
#include <cmath>
using namespace std;

// Makes the only legal move between rods x and y (in either direction).
void moveBetween(stack<int> &x, char nx, stack<int> &y, char ny) {
    if (x.empty()) {
        int d = y.top(); y.pop(); x.push(d);
        cout << "Move disk " << d << " from " << ny << " to " << nx << endl;
    } else if (y.empty()) {
        int d = x.top(); x.pop(); y.push(d);
        cout << "Move disk " << d << " from " << nx << " to " << ny << endl;
    } else if (x.top() < y.top()) {
        int d = x.top(); x.pop(); y.push(d);
        cout << "Move disk " << d << " from " << nx << " to " << ny << endl;
    } else {
        int d = y.top(); y.pop(); x.push(d);
        cout << "Move disk " << d << " from " << ny << " to " << nx << endl;
    }
}

int main() {
    int n;
    cin >> n;

    stack<int> src, aux, dest;
    char S = 'A', A = 'B', D = 'C';

    for (int i = n; i >= 1; i--) src.push(i);

    // For an even number of disks, swap the roles of auxiliary and destination rods
    if (n % 2 == 0) {
        swap(aux, dest);
        swap(A, D);
    }

    long long totalMoves = (1LL << n) - 1;
    for (long long i = 1; i <= totalMoves; i++) {
        if (i % 3 == 1)      moveBetween(src, S, dest, D);
        else if (i % 3 == 2) moveBetween(src, S, aux, A);
        else                 moveBetween(aux, A, dest, D);
    }
    return 0;
}
