// Lab03
// (B) Design your implementation of the circular queue. The circular queue is a linear data
// structure in which the operations are performed based on FIFO (First In First Out) principle,
// and the last position is connected back to the first position to make a circle. It is also called
// "Ring Buffer".
//
// Menu: 1 <v> enqueue | 2 dequeue | 3 front | 4 rear | 5 display | 0 exit
// The first input is the queue capacity k.
//
// Sample Input:
// 3
// 1 10
// 1 20
// 1 30
// 1 40
// 2
// 1 40
// 5
// 3
// 4
// 0
//
// Sample Output:
// Queue is full
// Dequeued: 10
// Queue: 20 30 40
// Front: 20
// Rear: 40

#include <iostream>
#include <vector>
using namespace std;

class CircularQueue {
    vector<int> a;
    int head, count, cap;
public:
    CircularQueue(int k) : a(k), head(0), count(0), cap(k) {}

    bool isEmpty() { return count == 0; }
    bool isFull()  { return count == cap; }

    bool enQueue(int v) {
        if (isFull()) return false;
        a[(head + count) % cap] = v;
        count++;
        return true;
    }

    bool deQueue(int &v) {
        if (isEmpty()) return false;
        v = a[head];
        head = (head + 1) % cap;
        count--;
        return true;
    }

    int Front() { return isEmpty() ? -1 : a[head]; }
    int Rear()  { return isEmpty() ? -1 : a[(head + count - 1) % cap]; }

    void display() {
        cout << "Queue:";
        for (int i = 0; i < count; i++) cout << " " << a[(head + i) % cap];
        cout << endl;
    }
};

int main() {
    int k;
    cin >> k;
    CircularQueue q(k);
    int choice, v;
    while (cin >> choice && choice != 0) {
        if (choice == 1) {
            cin >> v;
            if (!q.enQueue(v)) cout << "Queue is full" << endl;
        } else if (choice == 2) {
            if (q.deQueue(v)) cout << "Dequeued: " << v << endl;
            else cout << "Queue is empty" << endl;
        } else if (choice == 3) {
            cout << "Front: " << q.Front() << endl;
        } else if (choice == 4) {
            cout << "Rear: " << q.Rear() << endl;
        } else if (choice == 5) {
            q.display();
        }
    }
    return 0;
}
