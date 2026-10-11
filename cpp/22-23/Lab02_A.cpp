// Lab02
// (A) Suppose 20, 30 are existing nodes, insert node 10 at start in the list, insert node 50 at the end,
// insert node 40 after the 20th node. Suppose 10, 20, 30 are existing nodes, delete the node from
// start, suppose 30, 40, 40 are existing nodes, delete the node from end, finally, 20, 30, 50, 60 are
// existing nodes, delete the 3rd node from the list. Implement the above scenario using insertion
// and deletion algorithm in a linked list.
//
// Menu: 1 <v> insert at start | 2 <v> insert at end | 3 <key> <v> insert after node with value key
//       4 delete from start   | 5 delete from end   | 6 <pos> delete node at position (1-based)
//       7 display             | 0 exit
//
// Sample Input:
// 2 20
// 2 30
// 1 10
// 2 50
// 3 20 40
// 7
// 4
// 5
// 6 3
// 7
// 0
//
// Sample Output:
// List: 10 20 40 30 50
// List: 20 40

#include <iostream>
using namespace std;

struct Node {
    int data;
    Node *next;
    Node(int d) : data(d), next(nullptr) {}
};

Node *head = nullptr;

void insertStart(int v) {
    Node *t = new Node(v);
    t->next = head;
    head = t;
}

void insertEnd(int v) {
    Node *t = new Node(v);
    if (!head) { head = t; return; }
    Node *p = head;
    while (p->next) p = p->next;
    p->next = t;
}

void insertAfter(int key, int v) {
    Node *p = head;
    while (p && p->data != key) p = p->next;
    if (!p) { cout << "Node " << key << " not found" << endl; return; }
    Node *t = new Node(v);
    t->next = p->next;
    p->next = t;
}

void deleteStart() {
    if (!head) { cout << "List is empty" << endl; return; }
    Node *t = head;
    head = head->next;
    delete t;
}

void deleteEnd() {
    if (!head) { cout << "List is empty" << endl; return; }
    if (!head->next) { delete head; head = nullptr; return; }
    Node *p = head;
    while (p->next->next) p = p->next;
    delete p->next;
    p->next = nullptr;
}

void deletePos(int pos) {
    if (!head || pos < 1) { cout << "Invalid position" << endl; return; }
    if (pos == 1) { deleteStart(); return; }
    Node *p = head;
    for (int i = 1; i < pos - 1 && p; i++) p = p->next;
    if (!p || !p->next) { cout << "Invalid position" << endl; return; }
    Node *t = p->next;
    p->next = t->next;
    delete t;
}

void display() {
    cout << "List:";
    for (Node *p = head; p; p = p->next) cout << " " << p->data;
    cout << endl;
}

int main() {
    int choice, a, b;
    while (cin >> choice && choice != 0) {
        switch (choice) {
            case 1: cin >> a; insertStart(a); break;
            case 2: cin >> a; insertEnd(a); break;
            case 3: cin >> a >> b; insertAfter(a, b); break;
            case 4: deleteStart(); break;
            case 5: deleteEnd(); break;
            case 6: cin >> a; deletePos(a); break;
            case 7: display(); break;
        }
    }
    return 0;
}
