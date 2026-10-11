// 10. Implement Tower of Hanoi Recursion with Stack.
// input 3
#include <iostream>
#include <stack>
using namespace std;

// Each stack frame stores one "recursive call": hanoi(n, from, to, aux)
// stage 0 -> not yet expanded, stage 1 -> ready to move disk n and do the second sub-call
struct Frame {
    int n;
    char from, to, aux;
    int stage;
};

void hanoiWithStack(int n, char A, char C, char B) {
    stack<Frame> st;
    st.push({n, A, C, B, 0});
    int moves = 0;

    while (!st.empty()) {
        Frame f = st.top();
        st.pop();
        if (f.n == 0) continue;

        if (f.stage == 0) {
            // Simulates: hanoi(n-1, from, aux, to); move; hanoi(n-1, aux, to, from);
            // (pushed in reverse order because a stack is LIFO)
            st.push({f.n - 1, f.aux, f.to, f.from, 0});   // 3rd: hanoi(n-1, aux, to, from)
            st.push({f.n, f.from, f.to, f.aux, 1});       // 2nd: move disk n
            st.push({f.n - 1, f.from, f.aux, f.to, 0});   // 1st: hanoi(n-1, from, aux, to)
        } else {
            cout << "Move disk " << f.n << " from " << f.from << " to " << f.to << endl;
            moves++;
        }
    }
    cout << "Total moves: " << moves << endl;
}

int main() {
    int n;
    cout << "Enter number of disks: ";
    cin >> n;
    hanoiWithStack(n, 'A', 'C', 'B');
    return 0;
}
