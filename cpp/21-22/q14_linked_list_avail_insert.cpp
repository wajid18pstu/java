// 14. Suppose you have a linked list L and a vail list A. You want to insert a new item N [from the avail]
//     list A to the linked list at the position after X and before Y where X and Y are the [consecutive]
//     items in the linked list L. Write a program to insert this new item N into the list L [from]
//     the avail list.
/*3
10 20 40
2
30 20 40*/
#include <iostream>
using namespace std;

struct Node {
    int info;
    Node* next;
};

Node* L = nullptr;      // linked list L
Node* AVAIL = nullptr;  // avail (free) list A

// Build the avail list with 'count' free nodes
void createAvail(int count) {
    for (int i = 0; i < count; i++) {
        Node* t = new Node{0, AVAIL};
        AVAIL = t;
    }
}

void appendToL(int x) {
    Node* t = new Node{x, nullptr};
    if (!L) { L = t; return; }
    Node* p = L;
    while (p->next) p = p->next;
    p->next = t;
}

void display(const char* name, Node* h) {
    cout << name << ": ";
    for (Node* p = h; p; p = p->next) cout << p->info << " ";
    cout << endl;
}

// Take a node from the avail list, store N in it and insert it after X and before Y
bool insertBetween(int N, int X, int Y) {
    if (!AVAIL) { cout << "Overflow: avail list is empty!\n"; return false; }

    // find X such that X is immediately followed by Y
    Node* p = L;
    while (p && p->next && !(p->info == X && p->next->info == Y)) p = p->next;
    if (!p || !p->next || p->info != X || p->next->info != Y) {
        cout << "X and Y are not consecutive items in L!\n";
        return false;
    }

    Node* newNode = AVAIL;        // remove first node from avail list
    AVAIL = AVAIL->next;
    newNode->info = N;            // store item N
    newNode->next = p->next;      // link new node before Y
    p->next = newNode;            // link X to the new node
    return true;
}

int main() {
    int n, availCount;
    cout << "Enter number of items in list L: ";
    cin >> n;
    cout << "Enter " << n << " items: ";
    for (int i = 0; i < n; i++) { int x; cin >> x; appendToL(x); }

    cout << "Enter number of free nodes in avail list A: ";
    cin >> availCount;
    createAvail(availCount);

    int N, X, Y;
    cout << "Enter new item N, and consecutive items X and Y: ";
    cin >> N >> X >> Y;

    display("Before insertion, L", L);
    if (insertBetween(N, X, Y)) display("After insertion,  L", L);
    return 0;
}
