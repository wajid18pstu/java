// 11. Implement DEQUE.
/*5
2
10
2
20
1
5
5
3
4
5
0*/
#include <iostream>
using namespace std;

class Deque {
    int *arr;
    int front, rear, size, capacity;

public:
    Deque(int cap) : capacity(cap), front(-1), rear(-1), size(0) { arr = new int[cap]; }
    ~Deque() { delete[] arr; }

    bool isEmpty() { return size == 0; }
    bool isFull() { return size == capacity; }

    void insertFront(int x) {
        if (isFull()) { cout << "Deque Overflow\n"; return; }
        if (isEmpty()) front = rear = 0;
        else front = (front - 1 + capacity) % capacity;
        arr[front] = x;
        size++;
    }

    void insertRear(int x) {
        if (isFull()) { cout << "Deque Overflow\n"; return; }
        if (isEmpty()) front = rear = 0;
        else rear = (rear + 1) % capacity;
        arr[rear] = x;
        size++;
    }

    void deleteFront() {
        if (isEmpty()) { cout << "Deque Underflow\n"; return; }
        cout << "Deleted from front: " << arr[front] << endl;
        if (size == 1) front = rear = -1;
        else front = (front + 1) % capacity;
        size--;
    }

    void deleteRear() {
        if (isEmpty()) { cout << "Deque Underflow\n"; return; }
        cout << "Deleted from rear: " << arr[rear] << endl;
        if (size == 1) front = rear = -1;
        else rear = (rear - 1 + capacity) % capacity;
        size--;
    }

    void display() {
        if (isEmpty()) { cout << "Deque is empty\n"; return; }
        cout << "Deque: ";
        for (int i = 0, idx = front; i < size; i++, idx = (idx + 1) % capacity)
            cout << arr[idx] << " ";
        cout << endl;
    }
};

int main() {
    int cap;
    cout << "Enter capacity of deque: ";
    cin >> cap;
    Deque dq(cap);
    int choice, x;
    do {
        cout << "\n1.Insert Front 2.Insert Rear 3.Delete Front 4.Delete Rear 5.Display 0.Exit\n";
        cout << "Enter choice: ";
        cin >> choice;
        switch (choice) {
            case 1: cout << "Value: "; cin >> x; dq.insertFront(x); break;
            case 2: cout << "Value: "; cin >> x; dq.insertRear(x); break;
            case 3: dq.deleteFront(); break;
            case 4: dq.deleteRear(); break;
            case 5: dq.display(); break;
            case 0: break;
            default: cout << "Invalid choice\n";
        }
    } while (choice != 0);
    return 0;
}
