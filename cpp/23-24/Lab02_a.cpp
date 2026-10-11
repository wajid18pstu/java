/*
Lab02 (a)
Suppose 20, 30 are existing nodes, insert node 10 at start in the list, insert node 50 at the end,
insert node 40 after the 3rd node. Suppose 10, 20, 30 are existing nodes, delete the node from
start, suppose 20, 30, 40 are existing nodes, delete the node from end, finally, 20, 30, 50, 60 are
existing nodes, delete the 3rd node from the list. Implement the above scenario using insertion
and deletion algorithm in linked list.
*/

/*
SAMPLE INPUT:
(none - the scenario values are fixed by the question)

SAMPLE OUTPUT:
--- Insertion ---
After insert at start (10): 10 -> 20 -> 30 -> NULL
After insert at end (50): 10 -> 20 -> 30 -> 50 -> NULL
After insert 40 after 3rd node: 10 -> 20 -> 30 -> 40 -> 50 -> NULL

--- Deletion ---
Existing: 10 -> 20 -> 30 -> NULL
After delete from start: 20 -> 30 -> NULL
Existing: 20 -> 30 -> 40 -> NULL
After delete from end: 20 -> 30 -> NULL
Existing: 20 -> 30 -> 50 -> 60 -> NULL
After delete 3rd node: 20 -> 30 -> 60 -> NULL
*/

#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;
    Node(int d) : data(d), next(nullptr) {}
};

void insertAtStart(Node*& head, int v) {
    Node* n = new Node(v);
    n->next = head;
    head = n;
}

void insertAtEnd(Node*& head, int v) {
    Node* n = new Node(v);
    if (!head) { head = n; return; }
    Node* t = head;
    while (t->next) t = t->next;
    t->next = n;
}

// Insert after the pos-th node (1-based)
void insertAfterPos(Node*& head, int pos, int v) {
    Node* t = head;
    for (int i = 1; t && i < pos; i++) t = t->next;
    if (!t) { cout << "Position out of range\n"; return; }
    Node* n = new Node(v);
    n->next = t->next;
    t->next = n;
}

void deleteFromStart(Node*& head) {
    if (!head) { cout << "List empty\n"; return; }
    Node* t = head;
    head = head->next;
    delete t;
}

void deleteFromEnd(Node*& head) {
    if (!head) { cout << "List empty\n"; return; }
    if (!head->next) { delete head; head = nullptr; return; }
    Node* t = head;
    while (t->next->next) t = t->next;
    delete t->next;
    t->next = nullptr;
}

// Delete the pos-th node (1-based)
void deleteAtPos(Node*& head, int pos) {
    if (!head) { cout << "List empty\n"; return; }
    if (pos == 1) { deleteFromStart(head); return; }
    Node* t = head;
    for (int i = 1; t->next && i < pos - 1; i++) t = t->next;
    if (!t->next) { cout << "Position out of range\n"; return; }
    Node* d = t->next;
    t->next = d->next;
    delete d;
}

void display(Node* head) {
    for (Node* t = head; t; t = t->next) cout << t->data << " -> ";
    cout << "NULL\n";
}

int main() {
    cout << "--- Insertion ---\n";
    Node* list = nullptr;
    insertAtEnd(list, 20);
    insertAtEnd(list, 30);                 // existing nodes: 20, 30
    insertAtStart(list, 10);
    cout << "After insert at start (10): "; display(list);
    insertAtEnd(list, 50);
    cout << "After insert at end (50): "; display(list);
    insertAfterPos(list, 3, 40);
    cout << "After insert 40 after 3rd node: "; display(list);

    cout << "\n--- Deletion ---\n";
    Node* l1 = nullptr;
    insertAtEnd(l1, 10); insertAtEnd(l1, 20); insertAtEnd(l1, 30);
    cout << "Existing: "; display(l1);
    deleteFromStart(l1);
    cout << "After delete from start: "; display(l1);

    Node* l2 = nullptr;
    insertAtEnd(l2, 20); insertAtEnd(l2, 30); insertAtEnd(l2, 40);
    cout << "Existing: "; display(l2);
    deleteFromEnd(l2);
    cout << "After delete from end: "; display(l2);

    Node* l3 = nullptr;
    insertAtEnd(l3, 20); insertAtEnd(l3, 30); insertAtEnd(l3, 50); insertAtEnd(l3, 60);
    cout << "Existing: "; display(l3);
    deleteAtPos(l3, 3);
    cout << "After delete 3rd node: "; display(l3);
    return 0;
}
